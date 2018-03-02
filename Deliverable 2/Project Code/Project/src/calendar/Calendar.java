package calendar;

import java.util.List;

import course.Course;
import event.Event;
import courseCollection.CourseCollection;
import eventsCollection.EventsCollection;

public class Calendar {
	private CourseCollection courses;
	private EventsCollection events;
	
	public Calendar(List<Course> courseList, List<Event> eventList) {
		if (courseList == null || eventList == null) {
			throw new NullPointerException();
		}
		this.courses = new CourseCollection(courseList);
		this.events = new EventsCollection(eventList);
	}
	
	// TODO: Method to identify conflicts inside this Calendar object.
	// TODO: Method to identify conflicts and free slots with another Calendar object.
	
	// TODO: Method to find free time slots inside this Calendar object.
	// TODO: Method to find mutually free time slots with another Calendar.
	
	public void removeEverything() {
		this.courses.removeAllCourses();
		this.events.removeAllEvents();
	}
	
	public CourseCollection getCourseList() {
		return this.courses;
	}
	
	public EventsCollection getEventList() {
		return this.events;
	}
}
