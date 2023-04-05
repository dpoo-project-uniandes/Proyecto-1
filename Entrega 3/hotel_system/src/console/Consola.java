package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hotel_system.controllers.HotelManagementSystem;
import hotel_system.models.Rol;
import hotel_system.models.Usuario;

public class Consola {
	
	public static HotelManagementSystem hotelSystem = new HotelManagementSystem();
	
	public static void main(String[] args ) {
		bienvenida();
		boolean escogiendo = true;
		while(escogiendo) {
			try {
				printMenuIngreso();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {ingresar();}
				else if (opcion_seleccionada == 2) {registrar();}
				else if (opcion_seleccionada == 3) {escogiendo = false;}
				else {System.out.println("Recuerda que es un número entre 1 y 3");}
			}catch (NumberFormatException e) {
				System.out.println("Por favor digite un Número Válido entre 1 y 3");
			}
		}
		System.out.println("Gracias por usar nuestro sistema");
		
	}
	
	private static void registrar() {
		boolean registrando = true;
		while(registrando) {
			try {
				System.out.println("El nombre de usuario debe:\n-Debe empezar con una letra");
				System.out.println("-Ser de entre 4 a 12 caracteres");
				System.out.println("-Puede incluir guiones ");
				String user = input("Por favor ingrese el nombre de usuario");
				if (user.matches("^[a-zA-Z][-\\w!]{3,11}$")) {
					boolean obtenerContra = true;
					while (obtenerContra) {
						System.out.println("La contraseña debe:");
						System.out.println("-Ser de entre 6 a 14 caracteres");
						System.out.println("-Debe incluir al menos una letra, un número y un caracter especial (*,#,+,-,& etc.) ");
						String password = input("Por favor ingrese la contraseña");
						if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+,\\-./:;<=>?@\\[\\]^_`{|}~])[A-Za-z\\d!@#$%^&*()_+,\\-./:;<=>?@\\[\\]^_`{|}~]{6,14}$")) {
							boolean obtenerRol = true;
							while (obtenerRol) {
								System.out.println("Roles:\n1)Administrador\n2)Recepcionista");
								String rolEscogido = input("Ingresa un número entre 1 y 2");
								if (rolEscogido.equals("1")) {hotelSystem.registrar_usuario(user, password, Rol.ADMIN);
								obtenerContra = false;registrando = false; obtenerRol=false;break;}
								else if (rolEscogido.equals("2")) {hotelSystem.registrar_usuario(user, password, Rol.RECEPCIONISTA);
								obtenerContra = false;registrando = false; obtenerRol=false;break;}
								else {System.out.println("Recuerda que es un número entre 1 y 2");
								int opcion_seleccionada = Integer.parseInt(input("¿Deseas intentarlo de nuevo?\n1)Si\n2)No"));
								if (opcion_seleccionada == 1) {continue;}
								else if (opcion_seleccionada == 2) {obtenerContra = false;registrando = false; obtenerRol=false;}}
							}
						}
						else {System.out.println("La contraseña no es válida");
							System.out.println("La contraseña debe:");
							System.out.println("Ser de entre 6 a 14 caracteres");
							System.out.println("Debe incluir al menos una letra, un número y un caracter especial (*,#,+,-,& etc.) ");
							int opcion_seleccionada = Integer.parseInt(input("¿Deseas intentarlo de nuevo?\n1)Si\n2)No"));
							if (opcion_seleccionada == 1) {continue;}
							else if (opcion_seleccionada == 2) {obtenerContra = false;registrando = false;}
					}
					}
					}
				
				else {System.out.println("El usuario "+user+" no es un usuario válido en el sistema.");
					System.out.println("Debe empezar con una letra");
					int opcion_seleccionada = Integer.parseInt(input("¿Deseas intentarlo de nuevo?\n1)Si\n2)No"));
					if (opcion_seleccionada == 1) {continue;}
					else if (opcion_seleccionada == 2) {registrando = false;}
				}
			}catch (NumberFormatException e) {
				System.out.println("Por favor digite un Número Válido entre 1 y 2");
			}
		}
		
	}

	public static void ingresar() {
		boolean ingresando = true;
		while(ingresando) {
			try {
				String user = input("Por favor ingrese su nombre de usuario");
				if (hotelSystem.validad_usuario(user)) {
					boolean accediendo = true;
					while (accediendo) {
						String password = input("Por favor ingrese su contraseña");
						String rol = hotelSystem.validar_contraseña(user, password);
						if (rol != null) {
							if (rol.equals("ADMIN")) {admin();}
							else if (rol.equals("RECEPCIONISTA")) {recepcionista();}
						}
						else {System.out.println("La contraseña no coincide con el usuario "+user+".");
							int opcion_seleccionada = Integer.parseInt(input("¿Deseas intentarlo de nuevo?\n1)Si\n2)No"));
							if (opcion_seleccionada == 1) {continue;}
							else if (opcion_seleccionada == 2) {accediendo = false;}
					}
					}
					}
				
				else {System.out.println("El usuario "+user+" no es un usuario válido en el sistema.");
					int opcion_seleccionada = Integer.parseInt(input("¿Deseas intentarlo de nuevo?\n1)Si\n2)No"));
					if (opcion_seleccionada == 1) {continue;}
					else if (opcion_seleccionada == 2) {ingresando = false;}
				}
			}catch (NumberFormatException e) {
				System.out.println("Por favor digite un Número Válido entre 1 y 2");
			}
		}
		
	}

	private static void recepcionista() {
		printMenuRecepcionista();
		
	}

	private static void admin() {
		printMenuAdministrador();
		
	}

	public static void printMenuIngreso() {
		System.out.println("1) Ingresar ");
		System.out.println("2) Registrar usuario");
		System.out.println("3) Salir");

	}
	public static void printMenuRecepcionista() {
		System.out.println("1) Check in");
		System.out.println("2) Check out ");
		System.out.println("3) Log Out");

	}
	public static void printMenuAdministrador() {
		System.out.println("1) Modificar habitacion ");
		System.out.println("2) Administrar XD ");
		System.out.println("3) Log Out");

	}
	public static void bienvenida() {
		System.out.println("Hola! Este es el Hotel Management System");
		
	}
	
	public static String input(String message) {
		try
		{
			System.out.println(message+"\n->");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
}
