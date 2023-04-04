package hotel_system.models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import hotel_system.utils.Utils;

public class Habitacion {
	
	private Integer numero;
	private TipoHabitacion tipo;
	private List<Disponibilidad> disponibilidad;
	
	public Habitacion(Integer numero, TipoHabitacion tipo, List<Disponibilidad> disponibilidad) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.disponibilidad = disponibilidad;
	}
	
	private List<Integer> rangoDeFechasAIndices(Date desde, Date hasta) {
		List<Integer> indexes = new ArrayList<>();
		try {
			Integer desdeIndex = this.disponibilidad.indexOf(desde);
			Integer hastaIndex = Utils.sustractDates(desde, hasta);
			for(int i = desdeIndex; i < hastaIndex; i++) {
				indexes.add(i);
			}
		} catch(Exception e) {}
		return indexes;
	}
	
	public Boolean consultarDisponibilidad(Date desde, Date hasta) {
		for(Integer i : rangoDeFechasAIndices(desde, hasta)) {
			if(!this.disponibilidad.get(i).getEstado()) {
				break;
			}
			return true;
		}
		return false;
	}
	
	public Boolean modificarDisponibilidad(Date desde, Date hasta, Reserva reserva) {
		if (consultarDisponibilidad(desde, hasta)) {
			for(Integer i : rangoDeFechasAIndices(desde, hasta)) {
				Disponibilidad disponibilidad = this.disponibilidad.get(i);
				disponibilidad.setEstado(false);
				disponibilidad.setReserva(reserva);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Habitacion [numero=" + numero + ", tipo=" + tipo + ", disponibilidad=" + disponibilidad.size() + "]";
	}

	public void agregarDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad.add(disponibilidad);
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoHabitacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoHabitacion tipo) {
		this.tipo = tipo;
	}
}
