package notes;

public class NoteReaderTest {

	public static void main(String[] args) {
		
		NoteReader noteReader = new NoteReader();
		String note = noteReader.getNoteContents("C:\\Users\\user\\Desktop\\Bugs.txt");
		System.out.println(note);
	}

}
