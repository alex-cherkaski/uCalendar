package event;

import java.util.List;

import notes.Note;
import notes.NotesCollection;
import tuple.Tuple;

public class Event {
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
	private enum Repeat{NEVER, DAILY, WEEKLY, MONTHLY}
	private Repeat toRepeat;
	private NotesCollection notesCollection;
	
	public Event(Repeat toRepeat, String startDate, String endDate) {
		eventID += 1;
		this.thisEventID = eventID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.toRepeat = toRepeat;
		this.notesCollection = new NotesCollection();
	}

	public int getThisEventID() {
		return this.thisEventID;
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
		return "Event [thisEventID=" + thisEventID + "]";
	}
}