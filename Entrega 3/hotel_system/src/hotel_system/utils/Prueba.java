package hotel_system.utils;

import java.sql.Date;

import services.SecValidation;

public class Prueba {

	public static void main(String[] args) {
		Date date = Utils.stringToDate("2023/04/07");
		System.out.println(date.getClass().getSimpleName());
		System.out.println(Utils.nowDate());
		System.out.println("2023-4-7".toString().matches("^\\d{4}[/-]\\d{1,2}[/-]\\d{1,2}$" ));
		System.out.println(date.toString().replace("-", "/"));
		System.out.println(SecValidation.checkDate(new String(date.toString().replace("-", "/"))));
	}

}
