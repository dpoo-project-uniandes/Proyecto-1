package hotel_system.models;

import java.sql.Date;
import java.util.List;

import hotel_system.utils.Utils;

public class Estadia {
	
	Long id;
	Reserva reserva;
	Date fechaIngreso;
	Date fechaSalida;
	Factura facturaTotal;
	List<Factura> facturas;
	List<Huesped> huespedes;
	
	public Estadia(Reserva reserva, Date fechaIngreso, Date fechaSalida, List<Huesped> huespedes) {
		this.id = Utils.generateId();
		this.reserva = reserva;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.facturaTotal = new Factura(reserva.getTitular());
		this.huespedes = huespedes;
		this.facturas = List.of();
	}
	
	public void facturarEstadia() {

	}
	
	public void cargarFactura(Factura factura) {
		this.facturas.add(factura);
	}
	
	public List<Huesped> getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(List<Huesped> huespedes) {
		this.huespedes = huespedes;
	}

	public void cargarConsumo(Consumible consumo) {
		this.facturaTotal.agregarConsumible(consumo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
