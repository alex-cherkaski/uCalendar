package frontend_eventpage;

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

import event.Event;
import frontend.FrontEndUtilities;
import frontend_mainpage.PreviousButton;
import notes.Note;

@SuppressWarnings("serial")
public class EventPage extends JPanel{

	private Event event;
	private EventJMenu jMenu;
	private JPanel displayPanel;
	private JScrollPane scrollPane;
	private JList<Note> noteList;
	private DefaultListModel<Note> listModel;
	private JButton previous;
	private JLabel description;
	private String sortOperation;
	private JButton addNote;
	private JButton deleteNote;
	private JButton uploadNote;
	private JComboBox<String> sortBox;
	private JTextArea noteTextArea;
	
	public EventPage() {
		this.setLayout(new GridBagLayout());
		this.jMenu = new EventJMenu(this);
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
		FrontEndUtilities.setGridBag(c1, 0, 0, 1, 1, 0, 1);
		this.add(this.previous, c1);
		
		this.description = new JLabel("", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 1, 0, 2, 1, 1, 1);
		this.add(this.description, c1);
		
		this.displayPanel = new JPanel();
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 0, 1, 1, 1, 0, 2);
		this.add(displayPanel, c1);
		
		this.displayPanel.setLayout(new BorderLayout());
		this.listModel = new DefaultListModel<Note>();
		this.noteList = new JList<Note>(this.listModel);
		this.noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.noteList.setVisibleRowCount(-1);
		this.scrollPane = new JScrollPane(this.noteList);
		this.displayPanel.add(this.scrollPane, BorderLayout.CENTER);
		
		this.noteTextArea = new JTextArea();
		noteTextArea.setEditable(false);
		JScrollPane noteDisplayScrollPane = new JScrollPane(noteTextArea);
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 1, 1, 1, 1, 1, 2);
		this.add(noteDisplayScrollPane, c1);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		buttonPanel.setLayout(new GridLayout(20 , 1));
		buttonPanel.setBackground(Color.white);
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 0, 0, 1, 1, 2, 2);
		this.add(buttonPanel, c1);
		
		this.addNote = new JButton("Add Note");
		buttonPanel.add(this.addNote);
		
		this.deleteNote = new JButton("Delete Note");
		buttonPanel.add(this.deleteNote);
		
		this.uploadNote = new JButton("Upload Note");
		buttonPanel.add(this.uploadNote);
		
		String[] sortOptions = {"Oldest", "Newest"};
		
		this.sortBox = new JComboBox<String>(sortOptions);
		sortBox.setSelectedIndex(0);
		this.sortOperation = sortBox.getSelectedItem().toString();
		buttonPanel.add(this.sortBox);
		
	}
	
	public void setEvent(Event event) {
		this.event = event;
		this.description.setText(this.event.getName());
		EventPageController.updateListModel();
		this.noteTextArea.setText("");
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
	
	public JButton getUploadNoteButton() {
		return this.uploadNote;
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

	public Event getEvent() {
		return this.event;
	}

	public JList<Note> getNoteList() {
		return this.noteList;
	}

	public DefaultListModel<Note> getListModel() {
		return this.listModel;
	}
	
	public EventJMenu getJMenuBar() {
		return this.jMenu;
	}
	
	public JTextArea getNoteDisplayTextArea() {
		return this.noteTextArea;
	}
}
