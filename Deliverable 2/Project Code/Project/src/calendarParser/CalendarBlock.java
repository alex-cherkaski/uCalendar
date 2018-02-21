package calendarParser;

public class CalendarBlock {
	private String name;
	private String type;
	private String number;
	private String startDate;
	private String description;
	private String location;
	private String endDate;
	private String startTime;
	private String endTime;
	
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
	
	@Override
	public String toString() {
		return "CalendarBlock [name=" + name + ", type=" + this.type + "]";
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
}