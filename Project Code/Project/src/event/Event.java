package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import notes.Note;
import notes.NotesCollection;
import tuple.Tuple;

public class Event implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// EventID is used to uniquely identify this event.
	private static int eventID = 0;
	private int thisEventID;
	private String name;
	private String description;
	private String startDate;
	private String endDate;
	// List of tuples (start time, end time, day)
	private List<Tuple<String>> intervalList;
	private List<String> membersList;
	// How to repeat the event when the Calendar is rendered.
//  {"NEVER", "DAILY", "WEEKLY", "MONTHLY"};
	private String toRepeat;
	private NotesCollection notesCollection;
	private Map<Integer, Integer> calendarDates;
	
	public Event(String toRepeat, String startDate, String endDate) {
		eventID += 1;
		this.thisEventID = eventID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.toRepeat = toRepeat;
		this.notesCollection = new NotesCollection();
		intervalList = new ArrayList<Tuple<String>>();
		membersList = new ArrayList<String>();
		this.calendarDates = new HashMap<Integer, Integer>();
		this.calendarDates.put(1, 31);
		this.calendarDates.put(2, 28);
		this.calendarDates.put(3, 31);
		this.calendarDates.put(4, 30);
		this.calendarDates.put(5, 31);
		this.calendarDates.put(6, 30);
		this.calendarDates.put(7, 31);
		this.calendarDates.put(8, 31);
		this.calendarDates.put(9, 30);
		this.calendarDates.put(10, 31);
		this.calendarDates.put(11, 30);
		this.calendarDates.put(12, 31);
	}

	public int getThisEventID() {
		return this.thisEventID;
	}

	public String getToRepeat() {
		return toRepeat;
	}

	public void setToRepeat(String toRepeat) {
		this.toRepeat = toRepeat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Tuple<String>> getIntervalList() {
		return intervalList;
	}

	public void setIntervalList(List<Tuple<String>> intervalList) {
		this.intervalList = intervalList;
	}
	
	public void addInterval(Tuple<String> interval) {
		this.intervalList.add(interval);
	}
	
	public void removeInterval(Tuple<String> interval) {
		this.intervalList.remove(interval);
	}
	
	public void setMembers(List<String> membersList) {
		this.membersList = membersList;
	}
	
	public void addMember(String member) {
		this.membersList.add(member);
	}
	
	public void removeMember(String member) {
		this.membersList.remove(member);
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
	
	public List<Note> getNotesIncreasing(){
		return this.notesCollection.getAllNotesIncreasing();
	}
	
	public List<Note> getNotesDecreasing(){
		return this.notesCollection.getAllNotesDecreasing();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + thisEventID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (thisEventID != other.thisEventID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventID: " + thisEventID;
	}
}