package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import event.Event;
import notes.Note;

@SuppressWarnings("serial")
public class EventPage extends JPanel{

	private Event event;
	private JPanel displayPanel;
	private JScrollPane scrollPane;
	private JList<Note> list;
	private DefaultListModel<Note> listModel;
	private JButton deleteEvent;
	private JButton previous;
	private JLabel description;
	private EventButton currentButton;
	
	public EventPage() {
		this.setLayout(new GridBagLayout());
		EventJMenu menu = new EventJMenu(this);
		JPanel menuPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT );
		menuPanel.setLayout(flow);
		menuPanel.add(menu);
		
		GridBagConstraints c0 = new GridBagConstraints();
		c0.fill = GridBagConstraints.BOTH;
		setGridBag(c0, 0.5, 0, 8, 1, 0, 0);
		this.add(menuPanel, c0);
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 0, 1);
		this.add(this.previous, c1);
		
		this.deleteEvent = new JButton("Delete");
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 2, 1);
		this.add(this.deleteEvent, c1);
		
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
		
		this.deleteEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrontendStartup.deleteEventAndSwitch(currentButton);
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
	
	public void setEvent(Event event, EventButton eventButton) {
		this.event = event;
		this.description.setText(this.event.getName());
		this.currentButton = eventButton;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		updateListModel(LocalDate.now().format(formatter));
	}

	public void addNote(String date, Note note) {
		this.event.addNote(date, note);
		updateListModel(date);
	}
	
	private void updateListModel(String date) {
		this.listModel.clear();
		for(Note no: this.event.getNotes(date)) {
			this.listModel.addElement(no);
		}
	}
}
