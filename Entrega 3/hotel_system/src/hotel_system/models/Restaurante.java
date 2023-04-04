package hotel_system.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurante extends Servicio {
	
	private List<ProductoRestaurante> productos;

	public Restaurante(Long id, List<ProductoRestaurante> productos) {
		super(id);
		this.productos = productos;
	}
	
	public void agregarProducto(ProductoRestaurante producto) {
		this.productos.add(producto);
	}
	
	public void eliminarProducto(Long id) {
		for(ProductoRestaurante p : productos) {
			if(p.getId().equals(id)) {
				this.productos.remove(p);
				return;
			}
		}
	}

	public List<ProductoRestaurante> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoRestaurante> productos) {
		this.productos = productos;
	}
	@Override
	public Factura facturar(Huesped titular) {
	    Double valorTotal = 0.0;
	    List<Consumible> consumibles = new ArrayList<>(this.productos);
	    for (ProductoRestaurante producto : this.productos) {
	        valorTotal += producto.getPrecio();
	    }
	    Factura factura = new Factura(titular, valorTotal, consumibles);
	    factura.procesarPago();
	    return factura;
	}

}
