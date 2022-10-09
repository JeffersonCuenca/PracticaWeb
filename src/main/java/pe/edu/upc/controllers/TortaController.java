package pe.edu.upc.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import pe.edu.upc.entities.Torta;
import pe.edu.upc.serviceinterfaces.ITortaService;
import pe.edu.upc.util.TortaExporterPdf;

@Controller
@RequestMapping("/tortas")
public class TortaController {
	
	@Autowired
	private ITortaService tS;
	
	@GetMapping("/new")
	public String newTorta(Model model) {
		model.addAttribute("torta", new Torta());
		return "torta/torta";
	}
	
	@GetMapping("/list")
	public String listDistritos(Model model) {
		try {
			model.addAttribute("torta", new Torta());
			model.addAttribute("listaTortas", tS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "torta/listTortas";
	}
	
	@PostMapping("/save")
	public String saveTortas(@Valid Torta torta, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("torta", torta);
			return "torta/torta";
		} else {
			int rpta = tS.insert(torta);
			if(rpta > 0) {
				model.addAttribute("torta", torta);
				model.addAttribute("mensaje", "Ya existe una torta con el mismo nombre y diametro");
				return "torta/torta";
			} else {
			model.addAttribute("mensaje", "Se registró correctamente");
			status.setComplete();
			}
		}
		model.addAttribute("torta", new Torta());
		return "redirect:/tortas/list";
	}

	@RequestMapping("/delete/{id}")
	public String deleteTorta(@PathVariable int id) {
		tS.delete(id);
		return "torta/listTortas";
	}
	
	@RequestMapping("/update/{id}")
	public String updateTorta(@PathVariable int id, Model model, RedirectAttributes objRedirect) {
		Optional<Torta> torta = tS.listId(id);
		if (torta == null) {
			objRedirect.addFlashAttribute("mensaje", "Ocurrio un error");
			return "torta/torta";
		} else {
			model.addAttribute("torta", torta);
			return "torta/torta";
		}
	}
	
	@GetMapping("/exportarPDF")
	public void exportarPDFTortas(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=ListaTortas_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Torta> tortas = tS.list();
		
		TortaExporterPdf exporter = new TortaExporterPdf(tortas);
		exporter.exportar(response);
		
		
	}
}
