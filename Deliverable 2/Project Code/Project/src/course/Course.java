package course;

import java.util.ArrayList;
import java.util.List;
import notes.NotesCollection;
import parser.CalendarBlock;
import tuple.Tuple;

public class Course {
	private String courseCode;
	private String startDate;
	private String endDate;
	private String courseDescription;
	private List<Tuple<String>> intervalList;
	private List<CalendarBlock> blockList;
	private NotesCollection notesCollection;
	
	public Course(List<CalendarBlock> blockList) {
		if (blockList == null) {
			throw new NullPointerException();
		}
		if (blockList.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.blockList = blockList;
		this.courseCode = this.blockList.get(0).getCode();
		this.startDate = this.formatDate(this.blockList.get(this.blockList.size() - 1).getStartDate());
		this.endDate = this.formatDate(this.blockList.get(this.blockList.size() - 1).getEndDate());
		this.intervalList = this.constructIntervals(blockList);
		this.courseDescription = this.blockList.get(0).getDescription();
		this.notesCollection = new NotesCollection();
	}
	
	/*
	 * Reformats a string in the form of YYYYMMDD to DD-MM-YYYY.
	 * @param date a string object representing a date in the format YYYYMMDD.
	 * @return a string object in the format representing date in the format DD-MM-YYYY. 
	 */
	private String formatDate(String date) {
		// "20180404" -> "04-04-2018"
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(date.substring(6, 8));
		strBuilder.append("-");
		strBuilder.append(date.substring(4, 6));
		strBuilder.append("-");
		strBuilder.append(date.substring(0, 4));
		return strBuilder.toString();
	}
	
	/*
	 * Reformats a string in the form of YYYYMMDD to a specific day of the week.
	 * Using Zeller's rule to calculate day of the week.
	 * @param date a string object representing a date in the format YYYYMMDD.
	 * @return a string that represents the day of the week.
	 */
	private String formatDay(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6);
		
		int k = Integer.parseInt(day);
		int m = Integer.parseInt(month) - 2;
		if(m < 1) {
			m = 12 + m;
		}
		if(m == 11 || m == 12) {
			year = Integer.toString(Integer.parseInt(year) - 1);
		}
		int d = Integer.parseInt(year.substring(2));
		int c = Integer.parseInt(year.substring(0, 2));
		int f = (k + ((13 * m - 1)/5) + d + (d/4) + (c/4) - (2 * c)) % 7;
		String result = "";
		switch(f) {
		case 1:
			result = "Monday";
			break;
		case 2:
			result = "Tuesday";
			break;
		case 3:
			result = "Wednesday";
			break;
		case 4:
			result = "Thursday";
			break;
		case 5:
			result = "Friday";
			break;
		case 6:
			result = "Saturday";
			break;
		case 0:
			result = "Sunday";
			break;
		}
		return result;
	}
	
	/*
	 * Reformats a string in the form of HHMMSS to HH:MM.
	 * @param time a string object representing time in the format HHMMSS.
	 * @return a string object in the format representing time in the format HH:MM. 
	 */
	private String formatTime(String time) {
		// "160000" -> "16:00"
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(time.substring(0, 2));
		strBuilder.append(":");
		strBuilder.append(time.substring(2, 4));
		return strBuilder.toString();
	}
	
	/*
	 * Constructs a list of Tuple objects where every Tuple contains the start 
	 * and end times of a calendar block as well as the day of the week the block is on.
	 * @param blockList a list of CalendarBlock objects belonging to this course.
	 * @return a list of Tuple objects containing start and end times of a particular block.
	 */
	private List<Tuple<String>> constructIntervals(List<CalendarBlock> blockList) {
		List<Tuple<String>> result = new ArrayList<Tuple<String>>();
		for (CalendarBlock block : blockList) {
			String startTime = this.formatTime(block.getStartTime());
			String endTime = this.formatTime(block.getEndTime());
			String day = this.formatDay(block.getStartDate());
			result.add(new Tuple<String>(startTime, endTime, day));
		}
		return result;
	}
	
	
	@Override
	public String toString() {
		return this.courseCode;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public List<Tuple<String>> getIntervalList() {
		return intervalList;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void addNote(String date, Note note) {
		this.notesCollection.addNote(date, note);
	}
	
	public void removeNote(String date, Note note) {
		this.notesCollection.deleteNote(date, note);
	}
	
	public List<Note> getNotes(String date) {
		return this.notesCollection.getNotes(date);
	}
}
