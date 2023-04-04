package hotel_system.models;

import java.util.ArrayList;
import java.util.List;

public class Producto implements Consumible{
	
	private Long id;
	private String nombre;
	private Double precio;
	
	public Producto(Long id, String nombre, Double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
  
	public Factura facturar(Huesped titular) {
		List<Consumible> consumibles = new ArrayList<>();
		consumibles.add(this);
		Double valorTotal = this.getPrecio();
	    Factura factura = new Factura(titular, valorTotal, consumibles);
	    factura.procesarPago();
	    return factura;
	}

}
