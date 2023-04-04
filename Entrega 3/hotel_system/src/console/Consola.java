package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hotel_system.controllers.HotelManagementSystem;

public class Consola {
	public static HotelManagementSystem PMS = new HotelManagementSystem();
	
	public static void main(String[] args ) {
		bienvenida();
		printMenuIngreso();
		boolean ingresando = true;
		while(ingresando) {
			try {
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {ingresar();}
				else if (opcion_seleccionada == 2) {registrar();}
				else if (opcion_seleccionada == 3) {ingresando = false;}
				else {System.out.println("Recuerda que es un número entre 1 y 3");}
			}catch (NumberFormatException e) {
				System.out.println("Por favor digite un Número Válido entre 1 y 3");
			}
		}
		System.out.println("Gracias por usar nuestro sistema");
		
	}
	
	private static void registrar() {
		// TODO Auto-generated method stub
		
	}

	public static void ingresar() {
		// TODO Auto-generated method stub
		
	}

	public static void printMenuIngreso() {
		System.out.println("1) Ingresar ");
		System.out.println("2) Registrar ");
		System.out.println("3) Salir");

	}
	public static void printMenuRecepcionista() {
		System.out.println("1) Ingresar ");
		System.out.println("2) Registrar ");
		System.out.println("3) Salir");

	}
	public static void printMenuAdministrador() {
		System.out.println("1) Ingresar ");
		System.out.println("2) Registrar ");
		System.out.println("3) Salir");

	}
	public static void bienvenida() {
		System.out.println("Hola!. Este es el Property Management System");
		
	}
	
	public static String input(String message) {
		try
		{
			System.out.println(message+"\n>");
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
