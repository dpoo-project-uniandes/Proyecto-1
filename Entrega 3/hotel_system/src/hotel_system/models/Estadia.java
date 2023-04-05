package hotel_system.models;

public class Estadia {
	Integer id;

	Reserva reserva;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
}
