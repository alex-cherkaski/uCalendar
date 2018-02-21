package calendarParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import schedule.Course;

public class Parser {
	public Parser() {}
	
	public ArrayList<Course> constructSchedule(String fileName) {
		ArrayList<Course> courseList = new ArrayList<Course>();
		if (fileName == null) {
			throw new IllegalArgumentException();
		}
		String line = null;
		String courseName = null;
		String lecSection = null;
		String courseStartDate = null;
		String courseDescription = null;
		String courseLocation = null;
		String courseEndDate = null;
		String courseStartTime = null;
		String courseEndTime = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            while (line != null) {
            	if (line.contains("SUMMARY") && line.contains("LEC")) {
            		courseName = line.split(":")[1].split(" ")[0];
            		lecSection = line.split(":")[1].split(" ")[1];
            	}
            	if (line.contains("DTSTART")) {
            		courseStartDate = line.split(":")[1].split("T")[0];
            		courseStartTime = line.split(":")[1].split("T")[1];
            	}
            	if (line.contains("DESCRIPTION")) {
            		courseDescription = line.split("\n")[0].split(":")[1];
            	}
            	if (line.contains("Location")) {
            		courseLocation = line.split(":")[1];
            	}
            	if (line.contains("RRULE:FREQ=WEEKLY;WKST=MO;UNTIL")) {
            		courseEndDate = line.split("=")[1].split("T")[0];
            		courseEndTime = line.split("=")[1].split("T")[1];
            		// If we are here then we have all the necessary information.
            		Course course = new Course(
            				courseName, 
            				lecSection, 
            				courseStartDate, 
            				courseDescription, 
            				courseLocation, 
            				courseEndDate, 
            				courseStartTime, 
            				courseEndTime);
            		// We found a tutorial.
            		if (courseList.contains(course)) {
            			
            		}
            		else {
            			courseList.add(course);
            		}
            	}
            }
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
		return courseList;
	}
}