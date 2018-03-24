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
	private JLabel noteDisplayLabel;
	private JButton addNote;
	private JButton deleteNote;
	private JComboBox<String> sortBox;
	
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
		this.scrollPane = new JScrollPane(this.noteList);
		this.displayPanel.add(this.scrollPane, BorderLayout.CENTER);
		
		this.noteDisplayLabel = new JLabel();
		this.noteDisplayLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 1, 1, 1, 1, 1, 2);
		this.add(this.noteDisplayLabel, c1);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		buttonPanel.setLayout(new GridLayout(20 , 1));
		buttonPanel.setBackground(Color.white);
		c1.fill = GridBagConstraints.BOTH;
		FrontEndUtilities.setGridBag(c1, 0, 0, 1, 1, 2, 2);
		this.add(buttonPanel, c1);
		
		this.addNote = new JButton("Add Note");
		buttonPanel.add(addNote);
		
		this.deleteNote = new JButton("Delete Note");
		buttonPanel.add(deleteNote);
		
		String[] sortOptions = {"Oldest", "Newest"};
		
		this.sortBox = new JComboBox<String>(sortOptions);
		sortBox.setSelectedIndex(0);
		this.sortOperation = sortBox.getSelectedItem().toString();
		buttonPanel.add(sortBox);
		
	}
	
	public void setEvent(Event event) {
		this.event = event;
		this.description.setText(this.event.getName());
		EventPageController.updateListModel();
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
}
