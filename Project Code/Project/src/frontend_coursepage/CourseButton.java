package frontend_coursepage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import course.Course;
import frontend.FrontendStartup;
import parser.CalendarBlock;
import tuple.Tuple;

@SuppressWarnings("serial")
public class CourseButton extends JButton {
	
	private Course course;
	private static Color[] colors = {Color.cyan, Color.green, Color.magenta, Color.pink, Color.yellow, Color.lightGray};
	private int cCode;
	private Tuple<String> block;
	
	public CourseButton(Course course, int cCode, Tuple<String> block) {
		this.course = course;
		this.block = block;
		this.cCode = cCode;
		this.setText(this.course.getCourseCode());
		this.setFocusable(false);
		this.setBackground(colors[this.cCode]);
		CalendarBlock currBlock = null;
		for(CalendarBlock block2: course.getBlockList()) {
			if(block2.getDayOfTheWeek().equals(block.getItem3())) {
				currBlock = block2;
			}
		}
		String buttonText = "<html>" + course.getCourseCode() + "<br>" + currBlock.getNumber() + "<br>" + "Location: " + currBlock.getLocation() + "</html>";
		String description = "<html>" + "Description:" + "<br>" + "<br>"  + course.getCourseDescription() + "</html>";
		this.setText(buttonText);
		this.setToolTipText(description);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.switchCoursePage(course);
				
			}
			
		});
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	public Tuple<String> getBlock(){
		return this.block;
	}
	
	public void changeToOriginalColour() {
		this.setBackground(colors[this.cCode]);
	}
}
