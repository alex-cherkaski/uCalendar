package course;

import java.util.ArrayList;
import java.util.List;

import parser.CalendarBlock;

public class CourseBuilder {
	
	/*
	 * Constructs a list of courses based on the block list constructed by the parser.
	 * @param blockList a list of CalendarBlock objects constructed by Parser.
	 * @return a list of Course objects.
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
