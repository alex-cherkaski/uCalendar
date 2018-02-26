package calendar;

import java.util.ArrayList;
import java.util.List;

import course.Course;
import event.Event;

public class Day {
	ArrayList<Course> courses;
	ArrayList<Event> events;
	
	public Day() {
		this.courses = new ArrayList<Course>();
		this.events = new ArrayList<Event>();
	}
	
	public Day(List<Course> courseList, List<Event> eventList) {
		for (Course course : courseList) {
			this.courses.add(course);
		}
		for (Event event : eventList) {
			this.events.add(event);
		}
	}
	
	public Course getCourse(String courseCode){
		for (Course course : this.courses) {
			if (course.toString().equals(courseCode)) {
				return course;
			}
		}
		return null;
	}
	
	public Event getEvent(int eventID) {
		for (Event event : this.events) {
			if (event.getEventID() == eventID) {
				return event;
			}
		}
		return null;
	}
	
	public boolean addCourse(Course course) {
		
	}
	
	public boolean addEvent(Event event) {
		
	}
}
