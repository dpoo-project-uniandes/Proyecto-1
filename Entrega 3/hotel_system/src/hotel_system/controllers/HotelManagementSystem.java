package hotel_system.controllers;
import java.util.ArrayList;

import hotel_system.models.Estadia;
import hotel_system.models.Habitacion;
import hotel_system.models.Producto;
import hotel_system.models.Reserva;
import hotel_system.models.Servicio;
import hotel_system.models.Usuario;

public class HotelManagementSystem {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Habitacion> inventarioHabitaciones;
	private ArrayList<Reserva> reservas;
	private ArrayList<Estadia> registros;
	private ArrayList<Producto> inventarioProductos;
	private ArrayList<Servicio> inventarioServicios;
	
	
	public HotelManagementSystem() {
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
