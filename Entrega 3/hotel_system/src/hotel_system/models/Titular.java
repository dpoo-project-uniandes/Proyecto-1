package hotel_system.models;

public class Titular extends Huesped {
	
	private String email;
	private Integer telefono;
	
	
	public Titular(String nombre, Long dni, Integer edad, String email, Integer telefono) {
		super(nombre, dni, edad);
		this.email = email;
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getTelefono() {
		return telefono;
	}


	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
}
