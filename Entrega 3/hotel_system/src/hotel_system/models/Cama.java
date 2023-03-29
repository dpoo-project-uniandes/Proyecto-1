package hotel_system.models;

public class Cama {
	
	private Double largo;
	private Double ancho;
	private Integer infantes;
	private Integer adultos;
	private TipoCama tipo;
	
	public Cama(Double largo, Double ancho, Integer infantes, Integer adultos, TipoCama tipo) {
		super();
		this.largo = largo;
		this.ancho = ancho;
		this.infantes = infantes;
		this.adultos = adultos;
		this.tipo = tipo;
	}

	public Double getLargo() {
		return largo;
	}

	public void setLargo(Double largo) {
		this.largo = largo;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Integer getInfantes() {
		return infantes;
	}

	public void setInfantes(Integer infantes) {
		this.infantes = infantes;
	}

	public Integer getAdultos() {
		return adultos;
	}

	public void setAdultos(Integer adultos) {
		this.adultos = adultos;
	}

	public TipoCama getTipo() {
		return tipo;
	}
}
