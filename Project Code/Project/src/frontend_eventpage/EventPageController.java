package frontend_eventpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFileChooser;

import dropbox.DropboxSingleton;
import frontend.FrontendStartup;
import notes.Note;

public class EventPageController {
	
	private static EventPage eventPage;
	
	public static void setEventPage(EventPage evtPage) {
		eventPage = evtPage;
		setActionListener();
	}
	
	public static void setActionListener() {
		eventPage.getPreviousButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrontendStartup.switchMainPage();
			}
			
		});
		
		eventPage.getAddNoteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile();
			}
		});
		
		eventPage.getDeleteNoteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteNote();
			}
			
		});
		
		eventPage.getUploadNoteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				uploadNote();
			}
		});
		
		eventPage.getSortBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	eventPage.setSortOperation(eventPage.getSortBox().getSelectedItem().toString());
            }
        });
		
		eventPage.getJMenuBar().getDeleteEventButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.deleteEventAndSwitch(eventPage.getEvent());
			}
		});
	}
	
	public static void uploadNote() {
		Note note = eventPage.getNoteList().getSelectedValue();
	 	// TODO: change upload path from root directory.
		DropboxSingleton.getInstance().uploadFile(note.getNoteFilePath(), "/" + note.getNote());
	}
	
	public static void importFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		
		if(fileChooser.getSelectedFile() != null){
			Note note = new Note(fileChooser.getSelectedFile().getName());
			note.setNoteFilePath(fileChooser.getSelectedFile().getAbsolutePath());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			addNote(LocalDate.now().format(formatter), note);
		}
	}
	
	public static void addNote(String date, Note note) {
		eventPage.getEvent().addNote(date, note);
		updateListModel();
	}
	
	public static void deleteNote() {
		Note note = eventPage.getNoteList().getSelectedValue();
		eventPage.getEvent().removeNote(note.getNoteDate(), note);
		updateListModel();
	}
	
	public static void updateListModel() {
		eventPage.getListModel().clear();
		List<Note> notes;
		if(eventPage.getSortOperation().equals("Oldest")){
			notes = eventPage.getEvent().getNotesIncreasing();
		}else {
			notes = eventPage.getEvent().getNotesIncreasing();
		}
		
		for(Note no: notes) {
			eventPage.getListModel().addElement(no);
		}
	}

}
