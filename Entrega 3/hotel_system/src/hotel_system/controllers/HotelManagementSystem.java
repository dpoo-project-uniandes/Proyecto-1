package hotel_system.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import hotel_system.models.Estadia;
import hotel_system.models.Habitacion;
import hotel_system.models.Producto;
import hotel_system.models.Reserva;
import hotel_system.models.Rol;
import hotel_system.models.Servicio;
import hotel_system.models.Usuario;

public class HotelManagementSystem {
	
	private HashMap<String,Usuario> usuarios;
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
	
	private HashMap<String, Usuario> cargarUsuarios(){
		return new HashMap<String,Usuario>();
		
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
	
	public boolean validad_usuario(String user) {
		return usuarios.containsKey(user);
	}
	public Usuario validar_contraseña(String user, String password) {
		if (usuarios.get(user).getPassword().equals(password)) return usuarios.get(user); 
		else return null;
	}
	
	public void registrar_usuario(String user, String password, Rol rol) {
		usuarios.put(user, new Usuario(user, password, rol));
	}
}
