package hotel_system.models;

import java.sql.Date;

public class Pago {
	
	private Double monto;
	private Date fecha;
	
	public Pago(Double monto, Date fecha) {
		super();
		this.monto = monto;
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
