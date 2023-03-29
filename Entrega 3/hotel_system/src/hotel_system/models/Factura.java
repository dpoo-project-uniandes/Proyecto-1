package hotel_system.models;

import java.util.List;

import utils.Utils;

public class Factura {
	
	private Huesped titular;
	private Double valorTotal;
	private Pago pago;
	private List<Consumible> consumibles;
	
	public Factura(Huesped titular, Double valorTotal, List<Consumible> consumibles) {
		this.titular = titular;
		this.consumibles = consumibles;
	}
	
	public String generarFactura() {
		return String.format("%s;%d;%s;%s;%.2f;%s",
				this.titular.getNombre(),
				this.titular.getDni(),
				Utils.stringDate(Utils.nowDate()), 
				consumibles.toString(), 
				valorTotal,
				pago != null);
	}
	
	public void procesarPago() {
		this.pago = new Pago(valorTotal);
	}

	public Huesped getTitular() {
		return titular;
	}

	public void setTitular(Huesped titular) {
		this.titular = titular;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public List<Consumible> getConsumibles() {
		return consumibles;
	}

	public void setConsumibles(List<Consumible> consumibles) {
		this.consumibles = consumibles;
	}
}
