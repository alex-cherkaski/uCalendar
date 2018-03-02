package course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
	// Note ID used to distinguish notes.
	private static int noteID = 0;
	private int thisNoteID;
	private String note;
	// Date used to fingerprint when the note was created. 
	private DateFormat dateFormat;
	private Date date;
	
	public Note(String note) {
		noteID += 1;
		this.thisNoteID = noteID;
		this.note = note;
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.date = new Date();
	}
	
	public Note() {
		noteID += 1;
		this.thisNoteID = noteID;
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.date = new Date();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static int getNoteID() {
		return noteID;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "Note #" + this.thisNoteID;
	}
	
}
