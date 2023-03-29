package hotel_system.models;

public class Huesped {
	
	private String nombre;
	private Long dni;
	private Integer edad;
	
	public Huesped(String nombre, Long dni, Integer edad) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.edad = edad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
}
