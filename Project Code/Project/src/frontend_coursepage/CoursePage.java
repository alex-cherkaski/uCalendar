package frontend_coursepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import course.Course;
import frontend.FrontEndUtilities;
import frontend_coursepage.CourseJMenu;
import frontend_coursepage.CoursePageController;
import frontend_mainpage.PreviousButton;
import notes.Note;

@SuppressWarnings("serial")
public class CoursePage extends JPanel {

	private Course course;
	private CourseJMenu jMenu;
	private JPanel displayPanel;
	private JScrollPane scrollPane;
	private JList<Note> noteList;
	private DefaultListModel<Note> listModel;
	private JButton previous;
	private JLabel description;
	private String sortOperation;
	private JButton addNote;
	private JButton deleteNote;
	private JComboBox<String> sortBox;
	private JTextArea noteTextArea;
	
	public CoursePage() {
		this.setLayout(new GridBagLayout());
		this.jMenu = new CourseJMenu(this);
		JPanel menuPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT );
		menuPanel.setLayout(flow);
		menuPanel.add(jMenu);
		
		GridBagConstraints c0 = new GridBagConstraints();
		c0.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c0, 0, 0, 8, 1, 0, 0);
		this.add(menuPanel, c0);
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 0.05, 0, 1, 1, 0, 1);
		this.add(this.previous, c1);
		
		this.description = new JLabel("", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 1, 0, 2, 1, 1, 1);
		this.add(this.description, c1);
		
		this.displayPanel = new JPanel();
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 1, 1, 3, 1, 0, 2);
		this.add(displayPanel, c1);
		
		this.displayPanel.setLayout(new BorderLayout());
		this.listModel = new DefaultListModel<Note>();
		this.noteList = new JList<Note>(this.listModel);
		this.noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scrollPane = new JScrollPane(this.noteList);
		this.displayPanel.add(this.scrollPane, BorderLayout.LINE_START);
		
		this.noteTextArea = new JTextArea();
		noteTextArea.setEditable(false);
		JScrollPane noteDisplayScrollPane = new JScrollPane(noteTextArea);
		this.displayPanel.add(noteDisplayScrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		buttonPanel.setLayout(new GridLayout(20 , 1));
		buttonPanel.setBackground(Color.white);
		this.displayPanel.add(buttonPanel, BorderLayout.LINE_END);
		
		this.addNote = new JButton("Add Note");
		buttonPanel.add(this.addNote);
		
		this.deleteNote = new JButton("Delete Note");
		buttonPanel.add(this.deleteNote);
		
		String[] sortOptions = {"Oldest", "Newest"};
		
		this.sortBox = new JComboBox<String>(sortOptions);
		sortBox.setSelectedIndex(0);
		this.sortOperation = sortBox.getSelectedItem().toString();
		buttonPanel.add(this.sortBox);
		
		this.scrollPane.setPreferredSize(buttonPanel.getPreferredSize());
	}
	
	public void setCourse(Course course) {
		this.course = course;
		this.description.setText(this.course.getCourseCode());
		CoursePageController.updateListModel();
	}
	
	public JButton getPreviousButton() {
		return this.previous;
	}
	
	public JButton getAddNoteButton() {
		return this.addNote;
	}
	
	public JButton getDeleteNoteButton() {
		return this.deleteNote;
	}
	
	public JComboBox<String> getSortBox(){
		return this.sortBox;
	}
	
	public void setSortOperation(String op) {
		this.sortOperation = op;
	}
	
	public String getSortOperation() {
		return this.sortOperation;
	}

	public Course getCourse() {
		return this.course;
	}

	public JList<Note> getNoteList() {
		return this.noteList;
	}

	public DefaultListModel<Note> getListModel() {
		return this.listModel;
	}
	
	public CourseJMenu getJMenuBar() {
		return this.jMenu;
	}
	
	public JTextArea getNoteDisplayTextArea() {
		return this.noteTextArea;
	}
}
