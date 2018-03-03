package calendar;

import java.util.Collections;
import java.util.List;

import course.Course;
import event.Event;

public class Calendar {
	private List<Course> courseList;
	private List<Event> eventList;
	
	public Calendar(List<Course> courseList, List<Event> eventList) {
		if (courseList == null || eventList == null) {
			throw new NullPointerException();
		}
		this.courseList = courseList;
		this.eventList = eventList;
	}
	
	// TODO: Method to identify conflicts inside this Calendar object.
	// TODO: Method to identify conflicts and free slots with another Calendar object.
	// TODO: Method to find free time slots inside this Calendar object.
	
	public void addCourse(Course course) {
		if (course == null) {
			throw new NullPointerException();
		}
		if (!this.courseList.contains(course)) {
			this.courseList.add(course);
		}
	}
	
	public void removeCourse(Course course) {
		if (course == null) {
			throw new NullPointerException();
		}
		if (!this.courseList.contains(course)) {
			throw new IllegalArgumentException();
		}
		this.courseList.remove(course);
	}
	
	public void addEvent(Event event) {
		if (event == null) {
			throw new NullPointerException();
		}
		if (!this.eventList.contains(event)) {
			this.eventList.add(event);
		}
	}
	
	public void removeEvent(Event event) {
		if (event == null) {
			throw new NullPointerException();
		}
		if (!this.eventList.contains(event)) {
			throw new IllegalArgumentException();
		}
		this.eventList.remove(event);
	}
	
	public void removeAllCourses() {
		this.courseList.clear();
	}
	
	public void removeAllEvents() {
		this.eventList.clear();
	}
	
	public void removeEverything() {
		this.courseList.clear();
		this.eventList.clear();
	}
	
	public List<Course> getCourseList() {
		return this.courseList;
	}
	
	public List<Event> getEventList() {
		return this.eventList;
	}
	
	/*
	 * Dates are in the form of YYYYMMDD and will return a list of all courses between
	 * the 2 given dates. 
	 */
	public List<Course> getCourseFromAToB(String start, String end) {
		List<Course> result = Collections.emptyList();
		int startMonth = Integer.parseInt(start.substring(4, 6));
		int endMonth = Integer.parseInt(end.substring(4, 6));
		int startDate = Integer.parseInt(start.substring(6));
		int endDate = Integer.parseInt(end.substring(6));
		int courseDate;
		int courseMonth;
		for(Course x: this.courseList) {
			courseDate = Integer.parseInt(x.getStartDate().substring(6));
			courseMonth = Integer.parseInt(x.getStartDate().substring(4, 6));
			if(startMonth == endMonth) {
				if((courseMonth == startMonth) && (courseDate > startDate - 1) &&  (courseDate < endDate + 1)) {
					result.add(x);
				}
			} else {
				if(courseMonth == startMonth) {
					if(courseDate > startDate - 1) {
						result.add(x);
					}
				} else if(courseMonth == endMonth) {
					if(courseDate < endDate + 1) {
						result.add(x);
					}
				}	
			}
		}
		return result;
	}
	
	/*
	 * Dates are in the form of YYYYMMDD and will return a list of all events between
	 * the 2 given dates. 
	 */
	public List<Event> getEventFromAToB(String start, String end) {
		List<Event> result = Collections.emptyList();
		int startMonth = Integer.parseInt(start.substring(4, 6));
		int endMonth = Integer.parseInt(end.substring(4, 6));
		int startDate = Integer.parseInt(start.substring(6));
		int endDate = Integer.parseInt(end.substring(6));
		int courseDate;
		int courseMonth;
		for(Event x: this.eventList) {
			courseDate = Integer.parseInt(x.getStartDate().substring(6));
			courseMonth = Integer.parseInt(x.getStartDate().substring(4, 6));
			if(startMonth == endMonth) {
				if((courseMonth == startMonth) && (courseDate > startDate - 1) &&  (courseDate < endDate + 1)) {
					result.add(x);
				}
			} else {
				if(courseMonth == startMonth) {
					if(courseDate > startDate - 1) {
						result.add(x);
					}
				} else if(courseMonth == endMonth) {
					if(courseDate < endDate + 1) {
						result.add(x);
					}
				}	
			}
		}
		return result;
	}
}