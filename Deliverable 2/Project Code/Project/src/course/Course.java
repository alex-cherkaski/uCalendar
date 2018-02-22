package course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.CalendarBlock;

public class Course {
	private String courseCode;
	private List<CalendarBlock> blockList;
	private Map<String, List<String>> notesMap = new HashMap<String, List<String>>();
	
	public Course(List<CalendarBlock> blockList) {
		this.blockList = blockList;
		this.courseCode = this.blockList.get(0).getCode();
	}
	
	/*
	 * Adds a string that represents course notes to the the course.
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
		return "Course [courseCode=" + courseCode + "]";
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	public List<CalendarBlock> getBlockList() {
		return blockList;
	}

	public Map<String, List<String>> getNotesMap() {
		return notesMap;
	}
}
