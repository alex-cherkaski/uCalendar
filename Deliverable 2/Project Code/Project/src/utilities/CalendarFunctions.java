package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import calendar.Calendar;
import course.Course;
import event.Event;
import tuple.Tuple;

public class CalendarFunctions {
	/*
	 * String splice, similar to Python splicing
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
	
	/*
	 * Converts a representation of a day of the week (first three letters) to a number
	 */
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
	
	public static List<Tuple<String>> getFreePeriods(Calendar obj1, Calendar obj2) {
	  List<Tuple<String>> conflicts = otherConflict(obj1, obj2);
	  List<Tuple<String>> freePeriods = new ArrayList<>();
	  
	  for (Tuple<String> conflict : conflicts) {
	    
	  }
	  return null;
	}
		/*
		 * Given a Calendar object, will return of list of Tuples (start time, end time, day) that are conflicting
		 * with each other
		 */
		public static List<Tuple<String>> calendarConflict(Calendar obj){
			List<String> intervals = collectCourse(obj.getCourseList());
			intervals.addAll(collectEvent(obj.getEventList())); //this will work (in theory(, removed for testing
			List<String> result = conflict(intervals); 
			if (result.size() == 0) {
				return null;
			}
			List<Tuple<String>> resultTuple = new ArrayList<Tuple<String>>();
			for (int i = 0;i<result.size();i++) {
				resultTuple.add(stringToTuple(result.get(i)));
			}
			return resultTuple;
		}

		/*
		 * Given two Calendar objects, will return of list of Tuples (start time, end time, day) that are conflicting
		 * between the two
		 */
		public static List<Tuple<String>> otherConflict(Calendar obj1, Calendar obj2){ //can i do this? with same name?
			List<String> intervals = collectCourse(obj1.getCourseList());
			intervals.addAll(collectEvent(obj1.getEventList())); //this will work (in theory(, removed for testing
			intervals.addAll(collectCourse(obj2.getCourseList()));
			intervals.addAll(collectEvent(obj2.getEventList()));
			List<String> result = conflict(intervals);
			List<Tuple<String>> resultTuple = new ArrayList<Tuple<String>>();
			for (int i = 0;i<result.size();i++) {
				resultTuple.add(stringToTuple(result.get(i)));
			}
			return resultTuple;
		}
		
		/*
		 * Helper function for conflict functions
		 */
		private static List<String> collectCourse(List<Course> list){
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
		
		/*
		 * Helper function for conflict functions
		 */
		private static List<String> collectEvent(List<Event> list){
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
		
		/*
		 * Helper function for conflict functions
		 */
		private static List<String> conflict(List<String> intervals){
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
		
		/*
		 * Given a string in the form of: (13:00, 15:00, Monday), literally anything else
		 * returns a new Tuple<String>, formatted as expected
		 */
		public static Tuple<String> stringToTuple(String event){			
			String[] tuple = event.split(",");
			Tuple<String> result = new Tuple<String>(slice_range(tuple[0],1,6),slice_range(tuple[1],1,6),slice_range(tuple[2],1,tuple[2].length()-1));
			return result;
		}
		
		/*
		 * Returns the given time as a float. E.g. "16:30" -> 16.5.
		 */
		public static float timeStringToFloat(String time) {
		  String[] parts = time.split(":");
		  float result = Float.parseFloat(parts[0]) + (Float.parseFloat(parts[1]) / 60);
		  return result;
		}
		
		/*
		 * Returns the given time as a string. E.g. 16.5 -> "16:30".
		 */
		public static String timeFloatToString(float time) {
		  int hours = (int) time;
		  int minutes = (int) ((time - hours) * 60);
		  
		  return new String(hours + ":" + minutes);
		}
		
		/**
		 * Splits the given interval, removing the period between start and end.
		 */
		public static List<Tuple<String>> splitInterval(Tuple<String> interval, String start, String end) {
		  float intervalStart = timeStringToFloat(interval.getItem1());
		  float intervalEnd = timeStringToFloat(interval.getItem2());
		  
		  float splitStart = timeStringToFloat(start);
		  float splitEnd = timeStringToFloat(end);
		  
		  List<Tuple<String>> result = new ArrayList<>();
		  
		  if (intervalStart != splitStart) {
		    result.add(new Tuple<String>(interval.getItem1(), start, interval.getItem3()));
		  }
		  
		  if (intervalEnd != splitEnd) {
		    result.add(new Tuple<String>(end, interval.getItem2(), interval.getItem3()));
		  }
		  
		  return result;
		}
}
