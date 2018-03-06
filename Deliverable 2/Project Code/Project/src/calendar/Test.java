package calendar;

import java.util.ArrayList;
import java.util.List;

import course.Course;
import course.CourseBuilder;
import event.Event;
import event.Event.Repeat;
import parser.CalendarBlock;
import parser.Parser;

public class Test {

	public static void main(String[] args) {
		//String filePath = "C:\\Users\\user\\Desktop\\coursesCalendar.ics";
		String filePath = "C:\\Users\\Christopher\\Desktop\\coursesCalendar.ics";
		List<CalendarBlock> blockList = Parser.getCalendarBlocks(filePath);
		List<Course> courseList = CourseBuilder.getCourseMap(blockList);
		
		String filePath2 = "C:\\Users\\Christopher\\Desktop\\alexcoursesCalendar.ics";
		List<CalendarBlock> blockList2 = Parser.getCalendarBlocks(filePath2);
		List<Course> courseList2 = CourseBuilder.getCourseMap(blockList2);

		Event eventGen = new Event(Repeat.NEVER); //needs to relfect new event
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(eventGen);
		
		Calendar testObject = new Calendar(courseList, eventList);
		Calendar testObject2 = new Calendar(courseList2, eventList);

		//List<Course> courses = testObject.getCourseList();
		//List<Course> courses2 = testObject2.getCourseList();

		List<String> result = testObject.calendarConflict();
		List<String> result2 = testObject.otherConflict(testObject2);
		
		if (result == null) {
			System.out.println("No Conflicts");
		} else {
			for (int i = 0; i<result.size();i++) {
				System.out.println(result.get(i));
			}
		}
		
		if (result2 == null) {
			System.out.println("No Conflicts");
		} else {
			for (int i = 0; i<result2.size();i++) {
				System.out.println(result2.get(i));
			}
		}
		
		

	}
}
