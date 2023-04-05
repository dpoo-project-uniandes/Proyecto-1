package hotel_system.models;

import java.sql.Date;
import java.util.List;

import hotel_system.utils.Utils;

public class Reserva {
	
	private Integer numero;
	private Double tarifaTotal;
	private EstadoReserva estado;
	private Integer cantidadPersonas;
	private Date fechaDeLlegada;
	private Date fechaDeSalida;
	private Date fechaDeCreacion;
	private Titular titular;
	private Estadia estadia;
	private List<Habitacion> habitaciones;
	
	public Reserva(Date fechaDeLlegada,Date fechaDeSalida, Titular titular, Integer cantidad,List<Habitacion> habitaciones) {
		this.tarifaTotal=0.0;
		this.fechaDeLlegada=fechaDeLlegada;
		this.fechaDeCreacion=Utils.nowDate();
		this.estadia = null;
		this.cantidadPersonas = cantidad;
		this.fechaDeSalida = fechaDeSalida;
		this.titular = titular;
		this.estado= EstadoReserva.PENDIENTE;
		this.habitaciones = habitaciones;
	}
	
	public Double calcularTarifaTotal() {
		//TODO
		return 0.0;
	}
	
	public void confirmarReserva(Estadia estadia) {
		this.estado = EstadoReserva.CONFIRMADO;
		for (Habitacion hab: habitaciones) {
			hab.modificarDisponibilidad(fechaDeLlegada, fechaDeSalida, this);
		}
		this.estadia = estadia;

	}
	private int generateID() {
		return cantidadPersonas;
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
	public String toCsv() {
		return  numero+";"+tarifaTotal+";"+estado.toString()+""+cantidadPersonas+";"+fechaDeCreacion+";"+fechaDeLlegada
				+";"+fechaDeSalida +";"+titular.toString()+";"+((estadia == null)? "null":""+estadia.getId()+";"+strHabitaciones());
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	private String strHabitaciones() {
		String msg="";
		for (Habitacion hab : habitaciones) {
			msg+=","+hab.getNumero();
		}return new String(msg.substring(1));
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Double getTarifaTotal() {
		return tarifaTotal;
	}
	
	public void setTarifaTotal(Double tarifaTotal) {
		this.tarifaTotal = tarifaTotal;
	}
	
	public EstadoReserva getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	
	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}
	
	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	
	public Date getFechaDeLlegada() {
		return fechaDeLlegada;
	}
	
	public void setFechaDeLlegada(Date fechaDeLlegada) {
		this.fechaDeLlegada = fechaDeLlegada;
	}
	
	public Date getFechaDeSalida() {
		return fechaDeSalida;
	}
	
	public void setFechaDeSalida(Date fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}
	
	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}
	
	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	public Titular getTitular() {
		return titular;
	}
	
	public void setTitular(Titular titular) {
		this.titular = titular;
	}
	
	public Estadia getEstadia() {
		return estadia;
	}
	
	public void setEstadia(Estadia estadia) {
		this.estadia = estadia;
	}
	
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
}
