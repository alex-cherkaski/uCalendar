package frontend_coursepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private JList<Note> noteList;
	private DefaultListModel<Note> listModel;
	private JButton previous;
	private JLabel description;
	private String sortOperation;
	private JButton addNote;
	private JButton deleteNote;
	private JComboBox<String> sortBox;
	private JTextArea noteTextArea;
	private DefaultListModel<Note> listModelDropBox;
	private JList<Note> noteListDropBox;
	
	public CoursePage() {
		this.setLayout(new GridBagLayout());
		this.jMenu = new CourseJMenu(this);
		JPanel menuPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT );
		menuPanel.setLayout(flow);
		menuPanel.add(jMenu);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 0, 0, 8, 1, 0, 0);
		this.add(menuPanel, c);
		
		this.previous = new PreviousButton();
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 0.05, 0, 1, 1, 0, 1);
		this.add(this.previous, c);
		
		this.description = new JLabel("", SwingConstants.CENTER);
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 1, 0, 2, 1, 1, 1);
		this.add(this.description, c);
		
		this.displayPanel = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 1, 1, 3, 1, 0, 2);
		this.add(displayPanel, c);
		
		this.displayPanel.setLayout(new BorderLayout());
		JPanel notePanel = new JPanel(new GridLayout(2, 1));
		JPanel localNotePanel = new JPanel(new GridBagLayout());
		notePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		localNotePanel.setBackground(Color.white);
		localNotePanel.setOpaque(true);
		
		JLabel localNoteLabel = new JLabel("Local Notes:");
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 1, 0, 1, 1, 0, 0);
		localNotePanel.add(localNoteLabel);
		
		this.listModel = new DefaultListModel<Note>();
		this.noteList = new JList<Note>(this.listModel);
		this.noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(this.noteList);
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 1, 1, 1, 1, 0, 1);
		localNotePanel.add(scrollPane, c);
		notePanel.add(localNotePanel);
		
		JPanel dropBoxNotePanel = new JPanel(new GridBagLayout());
		dropBoxNotePanel.setBackground(Color.white);
		dropBoxNotePanel.setOpaque(true);
		
		JLabel dropBoxNoteLabel = new JLabel("DropBox Notes:");
		c.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c, 1, 0, 1, 1, 0, 0);
		dropBoxNotePanel.add(dropBoxNoteLabel);
		
		this.listModelDropBox = new DefaultListModel<Note>();
		this.noteListDropBox = new JList<Note>(this.listModelDropBox);
		this.noteListDropBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPaneDropBox = new JScrollPane(this.noteListDropBox);
		FrontEndUtilities.setGridBag(c, 1, 1, 1, 1, 0, 1);
		dropBoxNotePanel.add(scrollPaneDropBox, c);
		notePanel.add(dropBoxNotePanel);
		this.displayPanel.add(notePanel, BorderLayout.LINE_START);
		
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
		buttonPanel.setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width * 2, buttonPanel.getPreferredSize().height));
		
		scrollPane.setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width, scrollPane.getPreferredSize().height));
		scrollPaneDropBox.setPreferredSize(scrollPane.getPreferredSize());
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
