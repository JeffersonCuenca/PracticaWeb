package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tortas")
public class Torta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTorta;

	@NotEmpty(message = "Ingrese nombre de la torta")
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la torta no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la torta no puede contener un número")
	@Column(name = "nombreTorta", nullable = false, length = 150)
	private String nombreTorta;

	@NotEmpty(message = "Seleccione la cantidad de porciones de la torta")
	@Column(name = "porcionesTorta", nullable = false, length = 150)
	private String porcionesTorta;

	@DecimalMin("10.00")
	@NotNull(message = "Ingrese diametro de la torta")
	private Double diametroTorta;

	@DecimalMin("0.00")
	@NotNull(message = "Ingrese el precio de la torta")
	private Double precioTorta;

	public Torta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Torta(int idTorta, @NotEmpty(message = "Ingrese nombre de la torta") String nombreTorta,
			@NotEmpty(message = "Ingrese la cantidad de porciones de la torta") String porcionesTorta,
			@DecimalMin("10.00") @NotNull(message = "Ingrese diametro de la torta") Double diametroTorta,
			@DecimalMin("0.00") @NotNull(message = "Ingrese el precio de la torta") Double precioTorta) {
		super();
		this.idTorta = idTorta;
		this.nombreTorta = nombreTorta;
		this.porcionesTorta = porcionesTorta;
		this.diametroTorta = diametroTorta;
		this.precioTorta = precioTorta;
	}

	public int getIdTorta() {
		return idTorta;
	}

	public void setIdTorta(int idTorta) {
		this.idTorta = idTorta;
	}

	public String getNombreTorta() {
		return nombreTorta;
	}

	public void setNombreTorta(String nombreTorta) {
		this.nombreTorta = nombreTorta;
	}

	public String getPorcionesTorta() {
		return porcionesTorta;
	}

	public void setPorcionesTorta(String porcionesTorta) {
		this.porcionesTorta = porcionesTorta;
	}

	public Double getDiametroTorta() {
		return diametroTorta;
	}

	public void setDiametroTorta(Double diametroTorta) {
		this.diametroTorta = diametroTorta;
	}

	public Double getPrecioTorta() {
		return precioTorta;
	}

	public void setPrecioTorta(Double precioTorta) {
		this.precioTorta = precioTorta;
	}

}
