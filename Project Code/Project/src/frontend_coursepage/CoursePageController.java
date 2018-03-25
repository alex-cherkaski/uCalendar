package frontend_coursepage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frontend.FrontendStartup;
import frontend_coursepage.CoursePage;
import notes.Note;
import notes.NoteReader;

public class CoursePageController {

	private static CoursePage coursePage;
	
	public static void setCoursePage(CoursePage crsPage) {
		coursePage = crsPage;
		setActionListener();
	}
	
	public static void setActionListener() {
		coursePage.getPreviousButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				coursePage.getNoteList().clearSelection();
				coursePage.getNoteDisplayTextArea().setText("");
				FrontendStartup.switchMainPage();
			}
			
		});
		
		coursePage.getAddNoteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile();
			}
		});
		
		coursePage.getDeleteNoteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteNote();
			}
			
		});
		
		coursePage.getSortBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	coursePage.setSortOperation(coursePage.getSortBox().getSelectedItem().toString());
            }
        });
		
		coursePage.getJMenuBar().getDeleteCourseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.deleteCourseAndSwitch(coursePage.getCourse());
			}
		});
		
		coursePage.getNoteList().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!coursePage.getListModel().isEmpty() && !coursePage.getNoteList().isSelectionEmpty()) {
					displayNoteOnTextArea();
				}
			}
		});
	}
	
	public static void displayNoteOnTextArea() {
		coursePage.getNoteDisplayTextArea().setText(NoteReader.getNoteContents(coursePage.getNoteList().getSelectedValue().getNoteFilePath()));
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
		coursePage.getCourse().addNote(date, note);
		updateListModel();
	}
	
	public static void deleteNote() {
		Note note = coursePage.getNoteList().getSelectedValue();
		coursePage.getCourse().removeNote(note.getNoteDate(), note);
		updateListModel();
	}
	
	public static void updateListModel() {
		coursePage.getListModel().clear();
		List<Note> notes;
		if(coursePage.getSortOperation().equals("Oldest")){
			notes = coursePage.getCourse().getNotesIncreasing();
		}else {
			notes = coursePage.getCourse().getNotesIncreasing();
		}
		
		for(Note no: notes) {
			coursePage.getListModel().addElement(no);
		}
		
		coursePage.revalidate();
		coursePage.repaint();
	}

}
