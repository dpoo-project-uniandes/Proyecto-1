package utils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
	
	
	public static final LocalDateTime nowDate() {
		LocalDateTime now = LocalDateTime.now();
		return now;
	}
	
	public static final String stringDate(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return dtf.format(date);
	}
}
