package course;

public class Note {
	// Note ID used to distinguish notes.
	private static int noteID = 0;
	private int thisNoteID;
	private String note;
	private String date;
	
	public Note(String note) {
		noteID += 1;
		this.thisNoteID = noteID;
		this.note = note;
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

	@Override
	public String toString() {
		return "Note #" + this.thisNoteID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
