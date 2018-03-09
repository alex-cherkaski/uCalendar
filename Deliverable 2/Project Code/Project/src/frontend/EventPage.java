package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		setGridBag(c0, 0.5, 0, 8, 1, 0, 0);
		this.add(menuPanel, c0);
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 0, 1);
		this.add(this.previous, c1);
		
		String[] sortOptions = {"Oldest", "Newest"};
		
		JComboBox<String> sortBox = new JComboBox<String>(sortOptions);
		sortBox.setSelectedIndex(0);
		this.sortOperation = sortBox.getSelectedItem().toString();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 2, 1);
		this.add(sortBox, c1);
		
		this.description = new JLabel("", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 0, 1, 1, 1, 1);
		this.add(this.description, c1);
		
		this.displayPanel = new JPanel();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 1, 3, 1, 0, 2);
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		updateListModel(LocalDate.now().format(formatter));
		this.jMenu.setEvent(event);
	}

	public void addNote(String date, Note note) {
		this.event.addNote(date, note);
		updateListModel(date);
	}
	
	private void updateListModel(String date) {
		this.listModel.clear();
		List<Note> notes;
		if(this.sortOperation.equals("Oldest")){
			notes = this.event.getNotesIncreasing();
		}else if(this.sortOperation.equals("Newest")){
			notes = this.event.getNotesIncreasing();
		}else{
			notes = this.event.getNotes(date);
		}
		
		for(Note no: notes) {
			this.listModel.addElement(no);
		}
	}
}
