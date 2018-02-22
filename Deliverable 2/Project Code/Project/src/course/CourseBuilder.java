package course;

import java.util.ArrayList;
import java.util.List;

import parser.CalendarBlock;

public class CourseBuilder {
	
	/*
	 * Constructs a HashMap where every key is a course code and every value is a course object.
	 * @param blockList a list of CalendarBlock objects constructed by Parser.
	 * @return a Map that maps course names to course objects.
	 */
	public static List<Course> getCourseMap(List<CalendarBlock> blockList) {
		List<Course> courseList = new ArrayList<Course>();
		List<CalendarBlock> courseBlocks = new ArrayList<CalendarBlock>();
		CalendarBlock previousBlock = blockList.get(0);
		for (CalendarBlock block : blockList) {
			if (block.getCode().equals(previousBlock.getCode())) {
				courseBlocks.add(block);
			}
			else {
				courseList.add(new Course(courseBlocks));
				courseBlocks.clear();
				courseBlocks.add(block);
			}
			previousBlock = block;
		}
		// Last set of calendar blocks never gets added.
		courseList.add(new Course(courseBlocks));
		return courseList;
	}
}
