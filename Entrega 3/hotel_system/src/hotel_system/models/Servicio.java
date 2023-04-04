package hotel_system.models;

public abstract class Servicio implements Consumible {
	
	private Long id;

	public Servicio(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
