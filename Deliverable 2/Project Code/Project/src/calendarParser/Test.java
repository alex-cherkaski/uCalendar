package calendarParser;

import java.util.ArrayList;

public class Test {

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
