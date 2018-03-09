package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import course.Course;
import notes.Note;

@SuppressWarnings("serial")
public class CoursePage extends JPanel {

	private Course course;
	private CourseJMenu jMenu;
	private JButton previous;
	private JLabel description;
	private JPanel displayPanel;
	private DefaultListModel<Note> listModel;
	private JList<Note> list;
	private JScrollPane scrollPanel;
	private String sortOperation;
	
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
		this.scrollPanel = new JScrollPane(this.list);
		this.displayPanel.add(this.scrollPanel, BorderLayout.CENTER);
		
		this.previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrontendStartup.switchMainPage();
			}
			
		});
		
		this.list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
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
	
	public void setCourse(Course course) {
		this.course = course;
		this.description.setText(this.course.getCourseCode());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		updateListModel(LocalDate.now().format(formatter));
		this.jMenu.setCourse(course);
	}

	public void addNote(String date, Note note) {
		this.course.addNote(date, note);
		updateListModel(date);
	}
	
	private void updateListModel(String date) {
		this.listModel.clear();
		List<Note> notes;
		if(this.sortOperation.equals("Oldest")){
			notes = this.course.getNotesIncreasing();
		}else if(this.sortOperation.equals("Newest")){
			notes = this.course.getNotesIncreasing();
		}else{
			notes = this.course.getNotes(date);
		}
		
		for(Note no: notes) {
			this.listModel.addElement(no);
		}
	}
}
