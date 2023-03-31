package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consola {
	
	public static void main(String[] args ) {
		
	}
	
	public String input(String message) {
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
