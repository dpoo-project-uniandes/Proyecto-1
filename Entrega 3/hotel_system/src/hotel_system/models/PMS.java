package hotel_system.models;
import java.io.BufferedReader;
import java.util.ArrayList;

public class PMS {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Habitacion> inventarioHabitaciones;
	private ArrayList<Reserva> reservas;
	private ArrayList<Estadia> registros;
	private ArrayList<Producto> inventarioProductos;
	private ArrayList<Servicio> inventarioServicios;
	
	
	public PMS() {
		this.usuarios = cargarUsuarios();
		this.inventarioHabitaciones = cargarHabitaciones();
		this.reservas = cargarReservas();
		this.registros = cargarEstadias();
		this.inventarioProductos = cargarProductos();
		this.inventarioServicios = cargarServicios();
	}
	
	private ArrayList<Usuario> cargarUsuarios(){
		return usuarios;
		
	}
	
	private ArrayList<Habitacion> cargarHabitaciones(){
		return inventarioHabitaciones;
		
	}
	private ArrayList<Reserva> cargarReservas(){
		return reservas;
		
	}
	private ArrayList<Estadia> cargarEstadias(){
		return registros;
		
	}
	private ArrayList<Producto> cargarProductos(){
		return inventarioProductos;
		
	}	
	private ArrayList<Servicio> cargarServicios(){
		return inventarioServicios;
		
	}
	
	public void registrar_usuario() {
		
	}
	
	
}
