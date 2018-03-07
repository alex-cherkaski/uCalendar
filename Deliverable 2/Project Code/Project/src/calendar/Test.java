package calendar;

import java.util.ArrayList;
import java.util.List;

import course.Course;
import course.CourseBuilder;
import event.Event;
import parser.CalendarBlock;
import parser.Parser;
import tuple.Tuple;
import utilities.CalendarFunctions;

public class Test {

	public static void main(String[] args) {
		//String filePath = "C:\\Users\\user\\Desktop\\coursesCalendar.ics";
		String filePath = "C:\\Users\\Christopher\\Desktop\\coursesCalendar.ics";
		List<CalendarBlock> blockList = Parser.getCalendarBlocks(filePath);
		List<Course> courseList = CourseBuilder.getCourseMap(blockList);
		
		String filePath2 = "C:\\Users\\Christopher\\Desktop\\alexcoursesCalendar.ics";
		List<CalendarBlock> blockList2 = Parser.getCalendarBlocks(filePath2);
		List<Course> courseList2 = CourseBuilder.getCourseMap(blockList2);

		Event eventGen = new Event("NEVER", "06-03-2018", "06-03-2018"); //needs to relfect new event
		List<Event> eventList = new ArrayList<Event>();
		Tuple<String> test = CalendarFunctions.stringToTuple("(11:00, 15:00, Monday)");
		eventGen.addInterval(test);
		eventList.add(eventGen);
		
		Calendar testObject = new Calendar(courseList, eventList);
		Calendar testObject2 = new Calendar(courseList2, eventList);


		List<Tuple<String>> result = CalendarFunctions.calendarConflict(testObject);
		List<Tuple<String>> result2 = CalendarFunctions.otherConflict(testObject, testObject2);
		
		System.out.println("one calendars");
		
		if (result == null) {
			System.out.println("No Conflicts");
		} else {
			for (int i = 0; i<result.size();i++) {
				System.out.println(result.get(i));
			}
		}
		
		System.out.println("two calendars");
		
		if (result2 == null) {
			System.out.println("No Conflicts");
		} else {
			for (int i = 0; i<result2.size();i++) {
				System.out.println(result2.get(i));
			}
		}
	}
}
