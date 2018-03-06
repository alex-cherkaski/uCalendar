package calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import course.Course;
import event.Event;
import tuple.Tuple;

public class Calendar implements java.io.Serializable {
	
	/*
	 * slice_range taken from:
	 * https://stackoverflow.com/questions/17307761/is-there-a-java-equivalent-to-pythons-easy-string-splicing
	 */
	public static String slice_start(String s, int startIndex) {
	    if (startIndex < 0) startIndex = s.length() + startIndex;
	    return s.substring(startIndex);
	}

	public static String slice_end(String s, int endIndex) {
	    if (endIndex < 0) endIndex = s.length() + endIndex;
	    return s.substring(0, endIndex);
	}

	public static String slice_range(String s, int startIndex, int endIndex) {
	    if (startIndex < 0) startIndex = s.length() + startIndex;
	    if (endIndex < 0) endIndex = s.length() + endIndex;
	    return s.substring(startIndex, endIndex);
	}
	
	public static int day(String day) {
		switch(day){
	    case "Sun":
	        return 0;
	    case "Mon":
	        return 1;
	    case "Tue":
	        return 2;
	    case "Wed":
	        return 3;
	    case "Thu":
	        return 4;
	    case "Fri":
	        return 5;
	    case "Sat":
	        return 6;
	    }
		return -1;
	}
	
	private static final long serialVersionUID = 1L;
	private List<Course> courseList;
	private List<Event> eventList;
	
	public Calendar(List<Course> courseList, List<Event> eventList) {
		if (courseList == null || eventList == null) {
			throw new NullPointerException();
		}
		this.courseList = courseList;
		this.eventList = eventList;
	}
	
	//may need to be retested with newer versions
	public List<String> calendarConflict(){
		List<String> intervals = collectCourse(getCourseList());
		//intervals.addAll(collectEvent(getEventList())); //this will work (in theory(, removed for testing
		List<String> result = conflict(intervals); 
		if (result.size() == 0) {
			return null;
		}
		return result;
	}

	//may need to be retested with newer versions
	// TODO: Method to identify conflicts and free slots with another Calendar object.
	public List<String> otherConflict(Calendar other){ //can i do this? with same name?
		List<String> intervals = collectCourse(getCourseList());
		//intervals.addAll(collectEvent(getEventList())); //this will work (in theory(, removed for testing
		intervals.addAll(collectCourse(other.getCourseList()));
		//intervals.addAll(collectEvent(other.getEventList()));
		List<String> result = conflict(intervals);
		return result;

	}
	
	private List<String> collectCourse(List<Course> list){
		List<String> result = new ArrayList<String>();
		for (Course course : list) {
			String courseName = course.getCourseCode();
			String temp = "";
			List<Tuple<String>> interval = course.getIntervalList();
			for (Tuple<String> intval : interval) {
				temp += intval + ", " + courseName;
				result.add(temp);
				temp = "";
			}
		}
		return result;
	}
	
	private List<String> collectEvent(List<Event> list){
		List<String> result = new ArrayList<String>();
		for (Event event : list) {
			String eventName = Integer.toString(event.getThisEventID());
			String temp = "";
			List<Tuple<String>> interval = event.getIntervalList();
			for (Tuple<String> intval : interval) {
				temp += intval + ", " + eventName;
				result.add(temp);
				temp = "";
			}
		}
		return result;
	}

	private List<String> conflict(List<String> intervals){
		List<String> conflicts = new ArrayList<String>();
		Collections.sort(intervals, new Comparator<String>() {			
			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				String day0 = Integer.toString(day(slice_range(arg0, 15, 18)));
				String day1 = Integer.toString(day(slice_range(arg1, 15, 18)));
				int day = day0.compareTo(day1);
				if (day == 0) {
					String start0 = slice_range(arg0, 1, 6);
					//System.out.println("start0 day " + start0);
					String start1 = slice_range(arg1, 1, 6);
					//System.out.println("start1 day " + start1);
					return start0.compareTo(start1);	
				}
				return day;
			}
		});
		for (int i = 0;i<(intervals.size()-1);i++) {
			String end = slice_range(intervals.get(i), 8, 13);
			String start = slice_range(intervals.get(i+1), 1, 6);
			String endDay = Integer.toString(day(slice_range(intervals.get(i), 15, 18)));
			String startDay = Integer.toString(day(slice_range(intervals.get(i+1), 15, 18)));
			int ends = start.compareTo(end); //if this item finish after next start
			int days = endDay.compareTo(startDay);
			if (ends < 0 && days == 0) {
				if (!conflicts.contains(intervals.get(i))) {
					conflicts.add(intervals.get(i));
				}
				if (!conflicts.contains(intervals.get(i+1))) {
					conflicts.add(intervals.get(i+1));
					}
			}
		}
		return conflicts;
	}
	
	// TODO: Method to identify conflicts inside this Calendar object. DONE
	// TODO: Method to identify conflicts and free slots with another Calendar object. DONE
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
	public ArrayList<Course> getCourseFromAToB(String start, String end) {
		ArrayList<Course> result = new ArrayList<Course>();
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
	public ArrayList<Event> getEventFromAToB(String start, String end) {
		ArrayList<Event> result = new ArrayList<Event>();
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
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Calendar:" + "\n");
		stringBuilder.append("Courses: ");
		stringBuilder.append(Arrays.toString(this.courseList.toArray()));
		stringBuilder.append("\n");
		stringBuilder.append("Events: ");
		stringBuilder.append(Arrays.toString(this.eventList.toArray()));
		return stringBuilder.toString();
	}
}