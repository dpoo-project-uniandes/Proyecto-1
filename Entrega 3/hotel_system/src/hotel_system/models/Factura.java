package hotel_system.models;

import java.util.List;

import hotel_system.utils.Utils;

public class Factura {
	
	private Huesped titular;
	private Double valorTotal;
	private Pago pago;
	private List<Consumible> consumibles;
	
	public Factura(Huesped titular, List<Consumible> consumibles) {
		this.titular = titular;
		this.valorTotal = calcularValorTotal(consumibles);
		this.consumibles = consumibles;
	}
	
	public Factura(Huesped titular) {
		this.titular = titular;
		this.valorTotal = 0.0;
		this.consumibles = List.of();
	}
	
	private Double calcularValorTotal(List<Consumible> consumibles) {
		Double valorTotal = 0.0;
		for (Consumible consumible : consumibles) {
			valorTotal += consumible.valor();
		}
		return valorTotal;
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
	
	public void agregarConsumible(Consumible consumible) {
		this.consumibles.add(consumible);
	}
	
	public void eliminarConsumible(Consumible consumible) {
		this.consumibles.remove(consumible);
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
