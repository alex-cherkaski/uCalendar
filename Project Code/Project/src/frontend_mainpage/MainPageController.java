package frontend_mainpage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import calendar.Calendar;
import course.Course;
import course.CourseBuilder;
import event.Event;
import frontend.FrontEndUtilities;
import frontend_eventpage.EventWindowPanel;
import parser.CalendarBlock;
import parser.Parser;
import tuple.Tuple;
import utilities.CalendarFunctions;

public class MainPageController {
	
	private static MainPage mainPage;
	public static final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public static void setMain(MainPage main) {
		mainPage = main;
		setActionListener();
	}

	public static void setActionListener() {
		mainPage.getNextButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDate startDay = mainPage.getStartDay();
				LocalDate endDay = mainPage.getEndDay();
				mainPage.setStartDay(startDay.plusWeeks(1));
				mainPage.setEndDay(endDay.plusWeeks(1));
				
				updateWeek();
				updateDisplayEventButton();
			}
			
		});
		
		mainPage.getPreviousButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDate startDay = mainPage.getStartDay();
				LocalDate endDay = mainPage.getEndDay();
				mainPage.setStartDay(startDay.minusWeeks(1));
				mainPage.setEndDay(endDay.minusWeeks(1));
				
				updateWeek();
				updateDisplayEventButton();
			}
			
		});
		
		mainPage.getMenuBar().getAddEventItem().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openEventCreationWindow();
			}
		});
		
		mainPage.getMenuBar().getFileChooserItem().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile();
			}
		});
	}
	
	public static void importFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		
		if(fileChooser.getSelectedFile() != null){
			String filePath = fileChooser.getSelectedFile().getPath();
			List<CalendarBlock> blockList = Parser.getCalendarBlocks(filePath);
			List<Course> courseList = CourseBuilder.getCourseMap(blockList);
			List<Event> events = new ArrayList<Event>();
			Calendar calendar = new Calendar(courseList, events);
			MainPageController.addClasses(calendar);
		}
	}
	
	public static void openEventCreationWindow() {
		JFrame eventFrame = new JFrame();
		JPanel panel = new EventWindowPanel(mainPage, eventFrame);
		eventFrame.getContentPane().add(panel);
		
		eventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		eventFrame.setSize(500, 500);
		eventFrame.setResizable(false);
		eventFrame.setLocationRelativeTo(null);
		eventFrame.setVisible(true);
	}

	public static void addClasses(Calendar calendar2) {
		List<Event> eventList = mainPage.getCalendar().getEventList();
		mainPage.setCalendar(calendar2);
		for(Event event: eventList) {
			mainPage.getCalendar().addEvent(event);
		}
		
		GridBagConstraints c5 = new GridBagConstraints();
		
		int i = 0;
		for (Course course : mainPage.getCalendar().getCourseList()) {
			for(Tuple<String> block: course.getIntervalList()) {
				CourseButton button = new CourseButton(course, i, block);
				c5.fill = GridBagConstraints.BOTH;
				c5.weightx = 0;
				c5.weighty = 0;
				c5.gridheight = mainPage.getTimeY().get(block.getItem2()) - mainPage.getTimeY().get(block.getItem1());
				c5.gridx = mainPage.getDayX().get(block.getItem3());
				c5.gridy = mainPage.getTimeY().get(block.getItem1());
				mainPage.add(button, c5);
				mainPage.getCourseButtons().add(button);
			}
			i++;
		}
		
		updateDisplayEventButton();
		
	}
	
	public static void addEvent(Event event) {
		mainPage.getCalendar().addEvent(event);
		
		updateDisplayEventButton();

	}
	
	public static void updateDisplayEventButton() {
		GridBagConstraints c5 = new GridBagConstraints();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		for(EventButton button: mainPage.getEventButtons()) {
			mainPage.remove(button);
		}

		mainPage.getEventButtons().clear();
		
		List<Tuple<String>> conflicts = CalendarFunctions.calendarConflict(mainPage.getCalendar());
		
		for(Event event: mainPage.getCalendar().getEventFromAToB(mainPage.getStartDay().format(formatter), mainPage.getEndDay().format(formatter))) {
			for(Tuple<String> block: event.getIntervalList()){
				EventButton button = new EventButton(event);
				if(conflicts != null && conflicts.contains(block)) {
					
					button.setBackground(Color.red);
				}
				c5.fill = GridBagConstraints.BOTH;
				c5.weightx = 0;
				c5.weighty = 0;
				c5.gridheight = mainPage.getTimeY().get(block.getItem2()) - mainPage.getTimeY().get(block.getItem1());
				c5.gridx = mainPage.getDayX().get(block.getItem3());
				c5.gridy = mainPage.getTimeY().get(block.getItem1());
				mainPage.add(button, c5);
				mainPage.getEventButtons().add(button);
			}
		}

		
		for(CourseButton button: mainPage.getCourseButtons()) {
			if(conflicts != null && conflicts.contains(button.getBlock())) {
				button.setBackground(Color.red);
			}else if(button.getBackground() == Color.red) {
				button.changeToOriginalColour();
			}
		}
		
		mainPage.revalidate();
		mainPage.repaint();
	}
	
	public static void deleteCourse(Course course) {
		for(CourseButton button: mainPage.getCourseButtons()) {
			if(button.getCourse().equals(course)) {
				mainPage.remove(button);
			}
		}
		mainPage.getCalendar().removeCourse(course);
		updateDisplayEventButton();
	}
	
	public static void deleteEvent(Event event) {
		for(EventButton button: mainPage.getEventButtons()) {
			if(button.getEvent().equals(event)) {
				mainPage.remove(button);
			}
		}
		mainPage.getCalendar().removeEvent(event);
		updateDisplayEventButton();
	}
	
	public static void updateWeek() {
		GridBagConstraints c = new GridBagConstraints();
		LocalDate y = mainPage.getStartDay();
		for(int i = 0; i < 7; i++) {
			mainPage.getDayLabels()[i].setText(String.format("<html>" + days[i] + "<br>" + "<center>" + y.getDayOfMonth() + "</center>" + "</html>"));
			y = y.plusDays(1);
			c.fill = GridBagConstraints.BOTH;
			FrontEndUtilities.setGridBag(c, 1, 1, 1, 1, i + 1, 2);
			mainPage.getDayX().put(days[i], i + 1);
			mainPage.add(mainPage.getDayLabels()[i], c);
		}
		
		mainPage.revalidate();
		mainPage.repaint();
	}

}