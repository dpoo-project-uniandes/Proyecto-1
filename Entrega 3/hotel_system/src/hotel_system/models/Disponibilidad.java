package hotel_system.models;

import java.sql.Date;
import java.util.Objects;

import hotel_system.utils.Utils;

public class Disponibilidad {
	
	private Double precio;
	private Boolean estado;
	private Date fecha;
	private Reserva reserva;
	
	public Disponibilidad(Double precio, Boolean estado, Date fecha) {
		super();
		this.precio = precio;
		this.estado = estado;
		this.fecha = fecha;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (Date.class == obj.getClass()) {
			System.out.println(Utils.stringDate((Date) obj));
			System.out.println(Utils.stringDate(fecha));
			return this.fecha.equals(obj);
		}
		if (getClass() != obj.getClass()) {
			System.out.println(obj.getClass());
			return false;
		}
		System.out.println(Utils.stringDate(fecha));
		System.out.println(Utils.stringDate(((Disponibilidad) obj).getFecha()));
		Disponibilidad other = (Disponibilidad) obj;
		return Objects.equals(fecha, other.fecha);
	}

	@Override
	public String toString() {
		return "Disponibilidad [precio=" + precio + ", estado=" + estado + ", fecha=" + fecha + ", reserva=" + reserva
				+ "]";
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}
