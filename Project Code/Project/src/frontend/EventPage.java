package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import dropbox.DropboxSingleton;
import event.Event;
import notes.Note;

@SuppressWarnings("serial")
public class EventPage extends JPanel{

	private Event event;
	private EventJMenu jMenu;
	private JPanel displayPanel;
	private JScrollPane scrollPane;
	private JList<Note> list;
	private DefaultListModel<Note> listModel;
	private JButton previous;
	private JLabel description;
	private String sortOperation;
	private JLabel noteDisplayLabel;
	
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
		setGridBag(c0, 0, 0, 8, 1, 0, 0);
		this.add(menuPanel, c0);
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0, 0, 1, 1, 0, 1);
		this.add(this.previous, c1);
		
		this.description = new JLabel("", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 0, 2, 1, 1, 1);
		this.add(this.description, c1);
		
		this.displayPanel = new JPanel();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0, 1, 1, 1, 0, 2);
		this.add(displayPanel, c1);
		
		this.displayPanel.setLayout(new BorderLayout());
		this.listModel = new DefaultListModel<Note>();
		this.list = new JList<Note>(this.listModel);
		this.scrollPane = new JScrollPane(this.list);
		this.displayPanel.add(this.scrollPane, BorderLayout.CENTER);
		
		this.previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrontendStartup.switchMainPage();
			}
			
		});
		
		this.noteDisplayLabel = new JLabel();
		this.noteDisplayLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 1, 1, 1, 1, 2);
		this.add(this.noteDisplayLabel, c1);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		buttonPanel.setLayout(new GridLayout(20 , 1));
		buttonPanel.setBackground(Color.white);
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0, 0, 1, 1, 2, 2);
		this.add(buttonPanel, c1);
		
		JButton addNote = new JButton("Add Note");
		addNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile();
			}
		});
		buttonPanel.add(addNote);
		
		JButton deleteNote = new JButton("Delete Note");
		deleteNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteNote();
			}
			
		});
		buttonPanel.add(deleteNote);
		
		// upload the selected note to dropbox.
		JButton shareNote = new JButton("Share Note");
		shareNote.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            shareNote();
          }
		  
		});
		buttonPanel.add(shareNote);
		
		String[] sortOptions = {"Oldest", "Newest"};
		
		JComboBox<String> sortBox = new JComboBox<String>(sortOptions);
		sortBox.setSelectedIndex(0);
		this.sortOperation = sortBox.getSelectedItem().toString();
		buttonPanel.add(sortBox);
		
		sortBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	sortOperation = sortBox.getSelectedItem().toString();
            }
        });
	}
	
	private void setGridBag(GridBagConstraints c, double weightx, double weighty, int width, int height, int gridx, int gridy) {
		c.weightx = weightx;
		c.weighty = weighty;
		c.gridwidth = width;
		c.gridheight = height;
		c.gridx = gridx;
		c.gridy = gridy;
	}
	
	public void setEvent(Event event) {
		this.event = event;
		this.description.setText(this.event.getName());
		updateListModel();
		this.jMenu.setEvent(event);
	}
	
	private void importFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		
		if(fileChooser.getSelectedFile() != null){
			Note note = new Note(fileChooser.getSelectedFile().getName());
			note.setNoteFilePath(fileChooser.getSelectedFile().getAbsolutePath());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			this.addNote(LocalDate.now().format(formatter), note);
		}
	}
	
	public void addNote(String date, Note note) {
		this.event.addNote(date, note);
		updateListModel();
	}
	
	public void deleteNote() {
		Note note = this.list.getSelectedValue();
		this.event.removeNote(note.getNoteDate(), note);
		updateListModel();
	}
	
	private void shareNote() {
	  Note note = this.list.getSelectedValue();
	  // TODO: change upload path from root directory.
	  DropboxSingleton.getInstance().uploadFile(note.getNoteFilePath(), "/");
	}
	
	private void updateListModel() {
		this.listModel.clear();
		List<Note> notes;
		if(this.sortOperation.equals("Oldest")){
			notes = this.event.getNotesIncreasing();
		}else {
			notes = this.event.getNotesIncreasing();
		}
		
		for(Note no: notes) {
			this.listModel.addElement(no);
		}
	}
}
