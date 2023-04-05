package hotel_system.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hotel_system.models.Disponibilidad;
import hotel_system.models.Estadia;
import hotel_system.models.Factura;
import hotel_system.models.Habitacion;
import hotel_system.models.Producto;
import hotel_system.models.ProductoRestaurante;
import hotel_system.models.Reserva;
import hotel_system.models.Restaurante;
import hotel_system.models.Rol;
import hotel_system.models.Servicio;
import hotel_system.models.Spa;
import hotel_system.models.TipoHabitacion;
import hotel_system.models.Titular;
import hotel_system.models.Usuario;
import hotel_system.utils.Utils;
import services.FileManager;

public class HotelManagementSystem {
	
	private Map<String,Usuario> usuarios;
	private List<Habitacion> inventarioHabitaciones;
	private List<TipoHabitacion> opcionesHabitacion;
	private List<Reserva> reservas;
	private List<Estadia> registros;
	private List<Producto> inventarioProductos;
	private HashMap<String, Servicio> inventarioServicios;
	
	public HotelManagementSystem()  {
		cargarUsuarios();
		try {
			cargarTipoHabitaciones();
			cargarHabitaciones();
			//this.reservas = cargarReservas();
			//this.registros = cargarEstadias();
			//this.setInventarioProductos(cargarProductos());
			cargarServicios();
		} catch (Exception e) {
			System.out.println("El sistema no puedo iniciarlizarse, intente nuevamente");
			e.printStackTrace();
		}
	}
	
	private void cargarUsuarios(){
		usuarios= new HashMap<String,Usuario>();
	}
	
	private void cargarTipoHabitaciones() throws Exception {
		List<TipoHabitacion> opcionesHabitaciones = new ArrayList<TipoHabitacion>();
		List<Map<String, String>> datos = FileManager.cargarArchivoCSV("tipo_habitaciones.csv");
		for (Map<String, String> dato : datos) {
			String alias = dato.get("alias");
			Boolean conBalcon = Boolean.parseBoolean(dato.get("balcon"));
			Boolean conVista = Boolean.parseBoolean(dato.get("vista"));
			Boolean conCocina = Boolean.parseBoolean(dato.get("cocina"));
			Integer camasSencillas = Integer.parseInt(dato.get("camas_sencilla"));
			Integer camasDobles = Integer.parseInt(dato.get("camas_doble"));
			Integer camasQueen = Integer.parseInt(dato.get("camas_queen"));
			Integer capacidad = Integer.parseInt(dato.get("capacidad"));
			Double precio = Double.parseDouble(dato.get("precio"));
			TipoHabitacion tipoHabitacion = new TipoHabitacion(alias, capacidad, conBalcon, conVista, conCocina, camasSencillas, camasDobles, camasQueen, precio);
			opcionesHabitaciones.add(tipoHabitacion);
		}
		this.opcionesHabitacion = opcionesHabitaciones;
	}
	
	private void cargarHabitaciones() throws Exception{
		List<Habitacion> habitaciones = new ArrayList<>();
		List<Map<String, String>> datos = FileManager.cargarArchivoCSV("habitaciones.csv");
		for(Map<String, String> dato : datos) {
			Integer numeroHabitacion = Integer.parseInt(dato.get("numero_habitacion"));
			TipoHabitacion tipo = this.opcionesHabitacion.stream()
					.filter(th -> th.getAlias().equals(dato.get("tipo_habitacion")))
					.findAny()
					.get();
			List<Disponibilidad> disponibilidad = cargarDisponibilidades(numeroHabitacion, tipo.getPrecio());
			Habitacion habitacion = new Habitacion(numeroHabitacion, tipo, disponibilidad);
			habitaciones.add(habitacion);
		}
		this.setInventarioHabitaciones(habitaciones);
	}
	
	private List<Disponibilidad> cargarDisponibilidades(Integer numeroHabitacion, Double precio) throws Exception {
		FileManager.eliminarArchivo("disponibilidades.csv");
		FileManager.agregarLineasCSV("disponibilidades.csv", List.of(List.of("numero_habitacion","fecha","estado","precio")));
		List<Disponibilidad> disponibilidad = new ArrayList<>();
		List<List<String>> datos = new ArrayList<>();
		Date hoy = Utils.nowDate();
		for(int d = 1; d <= 365; d++) {
			LocalDate tomorrow = hoy.toLocalDate().plusDays(d);
			List<String> lista = List.of(numeroHabitacion.toString(), Utils.stringLocalDate(Date.valueOf(tomorrow)), "true", precio.toString());
			datos.add(lista);
			disponibilidad.add(new Disponibilidad(precio, true, Date.valueOf(tomorrow)));
		}
		FileManager.agregarLineasCSV("disponibilidades.csv", datos);
		return disponibilidad;
	}
	
	private void cargarReservas() throws Exception{
		this.reservas = new ArrayList<Reserva>();
		List<Map<String, String>> datos = FileManager.cargarArchivoCSV("reservas.csv");
		for (Map<String, String> dato:datos) {
			Titular titular = setTitular(dato.get("titular"));
			
			this.reservas.add(new Reserva(Utils.stringToDate(dato.get("fechaLlegada")),
			Utils.stringToDate("fechaSalida"), titular, Integer.parseInt(dato.get("cantidadPersonas")), 
			inventarioHabitaciones)); 
		}
		
		
	}
	private Titular setTitular(String string) {
		String[] datos = string.split(",");
		return new Titular(datos[0], 
				datos[2], 
				Integer.parseInt(datos[1]), 
				datos[3], 
				datos[4]);
	}
	private List<Estadia> cargarEstadias(){
		return registros;
		
	}
	public Integer calcularCapacidadTotal(List<Integer> ids) {
		Integer count = 0;
		for (Integer id:ids) {
			count+=inventarioHabitaciones.stream().filter(hab -> hab.getNumero() == id)
			.findAny().get().getTipo().getCapacidad();
		}
		return count;
	}
	
	public void reservar(String nombre, String email, String dni, String telefono
			,Integer edad, Integer cantidad, List<Integer> opcionHab, String llegada, String salida ) {
		
		Titular titular = new Titular(nombre, dni, edad, email, telefono);
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		for (Integer num:opcionHab) {
			habitaciones.add(inventarioHabitaciones.stream().filter(hab -> hab.getNumero() == num)
					.findAny().get());
		}
		reservas.add(new Reserva(Utils.stringToDate(llegada), Utils.stringToDate(salida), titular, cantidad, habitaciones));
		//Reserva reserva = new Reserva();
	}
	public Integer seleccionarHab(Integer select) {
		String alias = opcionesHabitacion.get(select).getAlias();
		return inventarioHabitaciones.stream()
				.filter(hab -> hab.getTipo().getAlias().equals(alias))
				.findAny().get().getNumero();
	}
	private List<Producto> cargarProductos() throws Exception {
		List<Producto> productosCargados = new ArrayList<>();
	    List<Map<String, String>> datos = FileManager.cargarArchivoCSV("productos.csv");
	    for (Map<String, String> dato : datos) {
	    	Long id = Long.parseLong(dato.get("id"));
	        String nombre = dato.get("nombre");
	        Double precio = Double.parseDouble(dato.get("precio"));
	        Producto producto = new Producto(id, nombre, precio);
	        productosCargados.add(producto);
	        }
	    this.setInventarioProductos(productosCargados);
	    return productosCargados;
	    }

	public void cargarServicios() throws Exception {
        HashMap<String, Servicio> serviciosCargados = new HashMap<>();
        
        List<ProductoRestaurante> productosRestaurante = new ArrayList<>();
        List<Map<String, String>> datosRestaurante = FileManager.cargarArchivoCSV("restaurante.csv");
        for (Map<String, String> dato : datosRestaurante) {
            Long id = Long.parseLong(dato.get("id"));
            String nombre = dato.get("nombre");
            Double precio = Double.parseDouble(dato.get("precio"));
            String fechaInicial = dato.get("fechaInicial");
            String fechaFinal = dato.get("fechaFinal");
            List<Date> fechas = new ArrayList<>();
            fechas.add(Utils.stringToDate(fechaInicial));
            fechas.add(Utils.stringToDate(fechaFinal));
            Boolean alCuarto = Boolean.parseBoolean(dato.get("alCuarto"));
            String tipo = dato.get("tipo");
            ProductoRestaurante producto = new ProductoRestaurante(id, nombre, precio, fechas, alCuarto, tipo);
            productosRestaurante.add(producto);
        }
        serviciosCargados.put("Servicio Restaurante", new Restaurante((long) 000001, productosRestaurante));

        List<Producto> productosSpa = new ArrayList<Producto>();
        List<Map<String, String>> datosSpa = FileManager.cargarArchivoCSV("productos_spa.csv");
        for (Map<String, String> dato : datosSpa) {
            Long id = Long.parseLong(dato.get("id"));
            String nombre = dato.get("nombre");
            Double precio = Double.parseDouble(dato.get("precio"));
            productosSpa.add(new Producto(id, nombre, precio));
        }
        Spa producto = new Spa((long) 000002, productosSpa);
        serviciosCargados.put("Servicio Spa", producto);

        this.inventarioServicios = serviciosCargados;
    }

	public List<Producto> getServiciosSpa(){
		Spa spa = (Spa)inventarioServicios.get("Servicio Spa");
		return spa.getProductosYServicios();
	}
	public List<ProductoRestaurante> getServiciosRestaurante(){
		Restaurante res = (Restaurante)inventarioServicios.get("Servicio Restaurante");
		return res.getProductos();
	}
	public Reserva getReservaById(String id) {
		Optional<Reserva> reservacion = reservas.stream().filter(res -> (""+res.getNumero()).equals(id))
						.findAny();
		if (reservacion.isPresent()) {return reservacion.get();}
		else {return null;}
	}    
	public Estadia getEstadiaById(String id) {
		Optional<Estadia> registro = registros.stream().filter(reg -> (""+reg.getId()).equals(id))
				.findAny();
		if (registro.isPresent()) {return registro.get();}
		else {return null;}
	}
	public Reserva getReservaByTitular(String nom) {
		Optional<Reserva> reservacion = reservas.stream().filter(res -> res.getTitular().getNombre().equals(nom))
				.findAny();
		if (reservacion.isPresent()) {return reservacion.get();}
		else {return null;}
	}
	public Estadia getEstadiaByTitular(String nom) {
		Optional<Estadia> registro = registros.stream().filter(reg -> (reg.getReserva().getTitular()
				.getNombre().equals(nom))).findAny();
		if (registro.isPresent()) {return registro.get();}
		else {return null;}
	}
	public Reserva getReservaByHabitacion(String id) {
		Optional<Habitacion> reservacion = inventarioHabitaciones.stream().filter(hab -> (hab.getReservaActual().getNumero()+"").equals(id))
				.findAny();
		if (reservacion.isPresent()) {return reservacion.get().getReservaActual();}
		else {return null;}
	}
	public Estadia getEstadiaByHabitacion(String id) {
		Optional<Habitacion> reservacion = inventarioHabitaciones.stream().filter(hab -> (hab.getReservaActual().getNumero()+"").equals(id))
				.findAny();
		if (reservacion.isPresent()) {return reservacion.get().getReservaActual().getEstadia();}
		else {return null;}
	}
	public boolean validad_usuario(String user) {
		return usuarios.containsKey(user);
	}
	public String validar_contraseña(String user, String password) {
		if (usuarios.get(user).getPassword().equals(password)) return usuarios.get(user).getRol().toString(); 
		else return null;
	}
	
	public void registrar_usuario(String user, String password, Rol rol) {
		usuarios.put(user, new Usuario(user, password, rol));
	}

	public List<Habitacion> getInventarioHabitaciones() {
		return inventarioHabitaciones;
	}

	public void setInventarioHabitaciones(List<Habitacion> inventarioHabitaciones) {
		this.inventarioHabitaciones = inventarioHabitaciones;
	}

	public List<Producto> getInventarioProductos() {
		return inventarioProductos;
	}

	public void setInventarioProductos(List<Producto> inventarioProductos) {
		this.inventarioProductos = inventarioProductos;
	}

	
	public HashMap<String, Servicio> getInventarioServicios() {
		return inventarioServicios;
	}

	public void setInventarioServicios(HashMap<String, Servicio> inventarioServicios) {
		this.inventarioServicios = inventarioServicios;
	}

	public List<TipoHabitacion> getOpcionesHabitacion() {
		return opcionesHabitacion;
	}

	public void setOpcionesHabitacion(List<TipoHabitacion> opcionesHabitacion) {
		this.opcionesHabitacion = opcionesHabitacion;
	}

	public void seleccionarProductoSpa(int cons, String hab, Boolean pagar) {
		Producto consumo = getServiciosSpa().get(cons);
		Estadia estadia = getEstadiaByHabitacion(hab);
		if (pagar) {
			estadia.cargarFactura(new Factura(estadia.getReserva().getTitular(), null));
		}
		else {
			estadia.cargarConsumo(consumo);
		}
	}

	public void seleccionarProductoRestaurante(int cons, String hab, Boolean pagar) {
		ProductoRestaurante consumo = getServiciosRestaurante().get(cons);
		Estadia estadia = getEstadiaByHabitacion(hab);
		if (pagar) {
			estadia.cargarFactura(new Factura(estadia.getReserva().getTitular(), null));
		}
		else {
			estadia.cargarConsumo(consumo);
		}
	}

	public void seleccionarProducto(int cons, String hab, Boolean pagar) {
		Producto consumo = getInventarioProductos().get(cons);
		Estadia estadia = getEstadiaByHabitacion(hab);
		if (pagar) {
			estadia.cargarFactura(new Factura(estadia.getReserva().getTitular(), null));
		}
		else {
			estadia.cargarConsumo(consumo);
		}		
	}
}
