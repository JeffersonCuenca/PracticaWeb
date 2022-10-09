package pe.edu.upc.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import pe.edu.upc.entities.Torta;

public class TortaExporterPdf {
	
	private List<Torta> listaTortas;

	public TortaExporterPdf(List<Torta> listaTortas) {
		super();
		this.listaTortas = listaTortas;
	}
	
	private void Cabecera(PdfPTable table) {
		PdfPCell celda = new PdfPCell();
	
		celda.setBackgroundColor(Color.red);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.white);
		
		celda.setPhrase(new Phrase("ID", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("NOMBRE TORTA", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("DIAMETRO", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("CANTIDAD DE PORCIONES", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("PRECIO", fuente));
		table.addCell(celda);
		
	}
	
	private void DatosTabla(PdfPTable table) {
		String diametro, precio;
		diametro = " cm";
		precio = "S/ ";
		
		for(Torta torta : listaTortas) {
			table.addCell(String.valueOf(torta.getIdTorta()));
			table.addCell(torta.getNombreTorta());
			table.addCell(String.valueOf(torta.getDiametroTorta() + diametro));
			table.addCell(torta.getPorcionesTorta());
			table.addCell(String.valueOf(precio + torta.getPrecioTorta()));
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);		
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.red);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Tortas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {1f,3f,3f,3f,3f});
		table.setWidthPercentage(110);
		
		Cabecera(table);
		DatosTabla(table);
		
		documento.add(table);
		documento.close();
	}
	
}
