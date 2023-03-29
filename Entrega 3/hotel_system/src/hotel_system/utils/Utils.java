package hotel_system.utils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	public static final Date nowDate() {
		return Date.valueOf(LocalDateTime.now().toLocalDate());
	}
	
	public static final String stringDate(Date date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return dtf.format(date.toLocalDate());
	}
}
