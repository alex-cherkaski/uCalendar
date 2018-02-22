package course;

import java.util.List;

import parser.CalendarBlock;
import parser.Parser;
import tuple.Tuple;

public class Test {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\user\\Desktop\\coursesCalendar.ics";
		List<CalendarBlock> blockList = Parser.getCalendarBlocks(filePath);
		List<Course> courseList = CourseBuilder.getCourseMap(blockList);
		for (Course course : courseList) {
			System.out.println(course.toString());
			for (Tuple<String> tuple : course.getIntervalList()) {
				System.out.println(tuple.toString());
			}
		}
	}
}
