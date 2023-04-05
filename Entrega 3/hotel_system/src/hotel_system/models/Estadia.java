package hotel_system.models;

import java.sql.Date;
import java.util.List;

import hotel_system.utils.Utils;

public class Estadia {
	
	Integer id;
	Reserva reserva;
	Date fechaIngreso;
	Date fechaSalida;
	Factura facturaTotal;
	List<Factura> facturas;
	
	public Estadia(Integer id, Reserva reserva, Date fechaIngreso, Date fechaSalida) {
		super();
		this.id = id;
		this.reserva = reserva;
		this.fechaIngreso = Utils.nowDate();
		this.fechaSalida = fechaSalida;
		this.facturaTotal = new Factura(reserva.getTitular());
		this.facturas = List.of();
	}
	
	public void facturarEstadia() {

	}
	
	public void cargarFactura(Factura factura) {
		this.facturas.add(factura);
	}
	
	public void cargarConsumo(Consumible consumo) {
		this.facturaTotal.agregarConsumible(consumo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
}
