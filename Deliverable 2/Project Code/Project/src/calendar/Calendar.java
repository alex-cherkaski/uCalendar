package calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar {
	
	public Calendar () {
	}
	
	public String getDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}
	
	public String getYear() {
		String [] parts = getDate().split("/");
		return parts[0];
	}
	
	public String getMonth() {
		String [] parts = getDate().split("/");
		return parts[1];
	}
	
	public String getDay() {
		String [] parts = getDate().split("/");
		return parts[2];
	}
}
