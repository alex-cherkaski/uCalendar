package parser;

public class CalendarBlock {
	private String code;
	private String type;
	private String number;
	private String startDate;
	private String startTime;
	private String description;
	private String location;
	private String endDate;
	private String endTime;
	
	public CalendarBlock(            				
			String code, 
			String type,
			String number, 
			String startDate, 
			String startTime,
			String description, 
			String location, 
			String endDate, 
			String endTime){
		
		this.code = code;
		this.type = type;
		this.number = number;
		this.startDate = startDate;
		this.startTime = startTime;
		this.description = description;
		this.location = location;
		this.endDate = endDate;
		this.startDate = startDate;
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "CalendarBlock [name=" + code + ", type=" + this.type + "]";
	}

	public String getCode() {
		return code;
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
	
	public String getStartTime() {
		return startTime;
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

	public String getEndTime() {
		return endTime;
	}
}
