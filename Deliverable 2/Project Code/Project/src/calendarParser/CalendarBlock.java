package calendarParser;

public class CalendarBlock {
	String name;
	String type;
	String number;
	String startDate;
	String description;
	String location;
	String endDate;
	String startTime;
	String endTime;
	
	public CalendarBlock(            				
			String name, 
			String type,
			String number, 
			String startDate, 
			String description, 
			String location, 
			String endDate, 
			String startTime, 
			String endTime){
		
		this.name = name;
		this.type = type;
		this.number = number;
		this.startDate = startDate;
		this.description = description;
		this.location = location;
		this.endDate = endDate;
		this.startDate = startDate;
		this.endTime = endTime;
	}
}
