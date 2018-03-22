package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import calendar.Calendar;
import course.Course;
import course.CourseBuilder;
import event.Event;
import parser.CalendarBlock;
import parser.Parser;

@SuppressWarnings("serial")
public class MainJMenu extends JMenuBar{

	private List<CalendarBlock> blockList;
	private List<Course> courseList;
	
	public MainJMenu(MainPage mainPage) {
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem addEvent = new JMenuItem("Add new event");
		addEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEvent(mainPage);
			}
		});
		
		JMenuItem fileChooser = new JMenuItem("Import Course Calendar");
		fileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile(mainPage);
			}
		});
		fileMenu.add(fileChooser);
		editMenu.add(addEvent);
		this.add(fileMenu);
		this.add(editMenu);
	}
	
	private void importFile(MainPage access) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		
		if(fileChooser.getSelectedFile() != null){
			String filePath = fileChooser.getSelectedFile().getPath();
			blockList = Parser.getCalendarBlocks(filePath);
			courseList = CourseBuilder.getCourseMap(blockList);
			List<Event> events = new ArrayList<Event>();
			Calendar calendar = new Calendar(courseList, events);
			access.addClasses(calendar);
		}
	}
	
	private void addEvent(MainPage mainPage) {
		JFrame eventFrame = new JFrame();
		JPanel panel = new EventWindowPanel(mainPage, eventFrame);
		eventFrame.getContentPane().add(panel);
		
		eventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		eventFrame.setSize(500, 500);
		eventFrame.setResizable(false);
		eventFrame.setLocationRelativeTo(null);
		eventFrame.setVisible(true);
	}

}
