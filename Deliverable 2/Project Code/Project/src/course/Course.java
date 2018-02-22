package course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.CalendarBlock;
import tuple.Tuple;

public class Course {
	private String courseCode;
	private String startDate;
	private String endDate;
	private String courseDescription;
	private List<Tuple<String>> intervalList;
	private List<CalendarBlock> blockList;
	private Map<String, List<String>> notesMap = new HashMap<String, List<String>>();
	
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
	}
	
	private String formatDate(String date) {
		// "20180404" -> "04-04-2018"
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(date.substring(4, 6));
		strBuilder.append("-");
		strBuilder.append(date.substring(2, 4));
		strBuilder.append("-");
		strBuilder.append(date.substring(0, 2));
		return strBuilder.toString();
	}
	
	private String formatTime(String time) {
		// "160000" -> "16:00"
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(time.substring(0, 2));
		strBuilder.append(":");
		strBuilder.append(time.substring(2, 4));
		return strBuilder.toString();
	}
	
	private List<Tuple<String>> constructIntervals(List<CalendarBlock> blockList) {
		List<Tuple<String>> result = new ArrayList<Tuple<String>>();
		for (CalendarBlock block : blockList) {
			String startTime = this.formatTime(block.getStartTime());
			String endTime = this.formatTime(block.getEndTime());
			result.add(new Tuple<String>(startTime, endTime));
		}
		return result;
	}
	
	/*
	 * Adds a string that is a string of notes to the the course.
	 * Notes are separated by date of the class that they were taken on.
	 * @param date the date is a string that is used as a key to map to a list
	 *             of notes belonging to the date that they were taken on.
	 * @param notes the notes to be recorded for the specified lecture, tutorial, or practical.
	 * @return void
	 */
	public void addNotes(String date, String notes) {
		if (date == null || notes == null) {
			throw new NullPointerException();
		}
		if (!this.notesMap.containsKey(date)) {
			throw new IllegalArgumentException();
		}
		if (this.notesMap.containsKey(date)) {
			notesMap.get(date).add(notes);
		}
		else {
			List<String> noteList = new ArrayList<String>();
			noteList.add(notes);
			notesMap.put(date, noteList);
		}
	}
	
	/*
	 * Returns the set of notes corresponding to the date.
	 * @param date the date for which we want to retrieve the notes.
	 * @return a list of strings where every string is a seperate note.
	 */
	public List<String> getNotes(String date) {
		if (date == null) {
			throw new NullPointerException();
		}
		if (!this.notesMap.containsKey(date)) {
			throw new IllegalArgumentException();
		}
		return this.notesMap.get(date);
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

	public Map<String, List<String>> getNotesMap() {
		return notesMap;
	}
}
