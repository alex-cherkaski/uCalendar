package frontend;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import course.Course;
import event.Event;

public class FrontendStartup {
	
	private static CardLayout layout = new CardLayout();
	private static JPanel cards;
	private static MainPage main;
	private static CoursePage course;
	private static EventPage event;
	
	public static void switchCoursePage(Course c) {
		layout.show(cards, "course panel");
		course.setCourse(c);
	}
	
	public static void deleteCourseAndSwitch(Course course) {
		main.deleteCourse(course);
		switchMainPage();
	}
	
	public static void switchEventPage(Event c) {
		layout.show(cards, "event panel");
		event.setEvent(c);
	}
	
	public static void deleteEventAndSwitch(Event event) {
		main.deleteEvent(event);
		switchMainPage();
	}
	
	public static void switchMainPage() {
		layout.show(cards, "main panel");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		JFrame frame = new JFrame("Calendar");
		Container content = frame.getContentPane();
		cards = new JPanel(layout);
		
		main = new MainPage();
		main.setBackground(Color.white);
		cards.add(main, "main panel");
		
		course = new CoursePage();
		course.setBackground(Color.white);
		course.setVisible(false);
		cards.add(course, "course panel");
		
		event = new EventPage();
		event.setBackground(Color.white);
		event.setVisible(false);
		cards.add(event, "event panel");
		
		content.add(cards);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.pack();
		frame.setVisible(true);
	}

}