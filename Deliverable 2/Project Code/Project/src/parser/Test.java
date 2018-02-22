package parser;

import java.util.List;

public class Test {
	
	// The acorn calendar breaks up all the classes into individual blocks.
	// For example csc369 does not have one block, but three, like in your schedule.
	// The parser returns a list of CalendarBlock object where each object represents a calendar block.
	// So there will be three different csc369 blocks in the list returned by the parser.
	// The CalendarBlock objects themselves contain all the info about the block.
	// e.g. start time, end time, name, description, etc...

	public static void main(String[] args) {
		String filePath = "C:\\Users\\user\\Desktop\\coursesCalendar.ics";
		List<CalendarBlock> blockList = Parser.getCalendarBlocks(filePath);
		for (CalendarBlock block : blockList) {
			System.out.println(block.toString());
			System.out.println("Description: " + block.getDescription());
			System.out.println("Start Date: " + block.getStartDate());
			System.out.println("Start Time: " + block.getStartTime());
			System.out.println("End Date: " + block.getEndDate());
			System.out.println("End Time: " + block.getEndTime());
			System.out.println("Location: " + block.getLocation());
			System.out.println("Code: " + block.getCode());
			System.out.println("Number: " + block.getNumber());
			System.out.println("Type: " + block.getType());
			System.out.println();
		}
	}
}
