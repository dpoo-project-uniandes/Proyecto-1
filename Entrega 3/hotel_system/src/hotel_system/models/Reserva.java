package hotel_system.models;

import java.sql.Date;
import java.util.List;

import hotel_system.utils.Utils;

public class Reserva {
	Integer numero;
	Double tarifaTotal;
	EstadoReserva estado;
	Integer cantidadPersonas;
	Date fechaDeLlegada;
	Date fechaDeSalida;
	Date fechaDeCreacion;
	Titular titular;
	Estadia estadia;
	List<Habitacion> habitaciones;
	
	public Reserva(Date fechaDeLlegada,Date fechaDeSalida, Titular titular, List<Habitacion> habitaciones) {
		this.tarifaTotal=0.0;
		this.fechaDeLlegada=fechaDeLlegada;
		this.fechaDeCreacion=Utils.nowDate();
		this.fechaDeSalida = fechaDeSalida;
		this.titular = titular;
		this.estado= EstadoReserva.PENDIENTE;
		this.habitaciones = habitaciones;
	}
	public Double calcularTarifaTotal() {
		//TODO
		return 0.0;
	}
	
	public void confirmarReserva() {
		this.estado = EstadoReserva.CONFIRMADO;
		for (Habitacion hab: habitaciones) {
			hab.modificarDisponibilidad(fechaDeLlegada, fechaDeSalida, this);
		}

	}
	
	public void cancelarReserva() {
		this.estado = EstadoReserva.CANCELADO;
	}
	
	@Override
	public String toString() {
		return "Reserva [numero=" + numero +", estado="+estado.toString()+", tarifa total=" + tarifaTotal + ", cantidad de personas=" 
					+ cantidadPersonas+", fecha de creación="+ Utils.stringDate(fechaDeCreacion)
					+", fecha de llegada="+Utils.stringDate(fechaDeLlegada)+", fecha de salida=" + Utils.stringDate(fechaDeSalida) +"]";
	}
	
}
