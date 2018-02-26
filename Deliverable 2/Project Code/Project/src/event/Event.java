package event;

import java.util.List;

import course.Note;
import notes.NotesCollection;
import tuple.Tuple;

public class Event {
	private static int eventID = 0;
	private String name;
	private String description;
	private String startDate;
	private String endDate;
	private List<Tuple<String>> intervalList;
	// How to repeat the event when the Calendar is rendered.
	private enum Repeat{NEVER, DAILY, WEEKLY, MONTHLY}
	private Repeat toRepeat;
	private NotesCollection notesCollection;
	
	public Event(Repeat toRepeat) {
		eventID += 1;
		this.toRepeat = toRepeat;
		this.notesCollection = new NotesCollection();
	}

	public int getEventID() {
		return eventID;
	}

	public Repeat getToRepeat() {
		return toRepeat;
	}

	public void setToRepeat(Repeat toRepeat) {
		this.toRepeat = toRepeat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
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
