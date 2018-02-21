package calendarParser;

import java.util.ArrayList;

public class Test {
	
	// The acorn calendar breaks up all the classes into individual blocks.
	// For example csc369 does not have one block, but three, like in your schedule.
	// The parser returns a list of CalendarBlock object where each object represents a calendar block.
	// The CalendarBlock objects themselves contain all the info about the block
	// e.g. start time, end time, name, description, etc...

	public static void main(String[] args) {
		// For testing the parser.
		Parser parser = new Parser();
		String filePath = "C:\\Users\\user\\Desktop\\coursesCalendar.ics";
		ArrayList<CalendarBlock> blockList = parser.getCalendarBlocks(filePath);
		for (CalendarBlock block : blockList) {
			System.out.println(block.toString());
		}
	}
}
