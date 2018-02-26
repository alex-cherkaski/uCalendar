package notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import course.Note;

public class NotesCollection {
	private Map<String, List<Note>> notesMap = new HashMap<String, List<Note>>();
	
	public NotesCollection() {}
	
	/*
	 * Adds a Note object to the current collection of notes.
	 * Notes are separated by date of the class that they were taken on.
	 * @param date a String object that is used as a key to map to a list
	 *             of notes belonging to the date that they were taken on.
	 * @param note Note object to be recorded for the specified lecture, tutorial, or practical.
	 * @return void
	 */
	public void addNote(String date, Note note) {
		if (date == null || note == null) {
			throw new NullPointerException();
		}
		if (this.notesMap.containsKey(date)) {
			notesMap.get(date).add(note);
		}
		else {
			List<Note> noteList = new ArrayList<Note>();
			noteList.add(note);
			notesMap.put(date, noteList);
		}
	}
	
	/*
	 * Deletes the selected note from the set of notes taken on a specific date, if possible.
	 * @param date A String object representing the date the note was taken.
	 * @param note The Note object to be deleted.
	 * @return void
	 */
	public void deleteNote(String date, Note note) {
		if (date == null || note == null) {
			throw new NullPointerException();
		}
		if (!this.notesMap.containsKey(date)) {
			throw new IllegalArgumentException();
		}
		if (!this.notesMap.get(date).contains(note)) {
			throw new IllegalArgumentException();
		}
		this.notesMap.get(date).remove(note);
		if (notesMap.get(date).isEmpty()) {
			this.notesMap.remove(date);
		}
	}
	
	/*
	 * Returns the list of Note objects corresponding to the date.
	 * @param date String object representing the date for which we want 
	 * 		  to retrieve the notes.
	 * @return a list of Note objects.
	 */
	public List<Note> getNotes(String date) {
		if (date == null) {
			throw new NullPointerException();
		}
		if (!this.notesMap.containsKey(date)) {
			return new ArrayList<Note>();
		}
		return this.notesMap.get(date);
	}

}
