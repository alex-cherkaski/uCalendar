package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import course.Course;
import tuple.Tuple;

@SuppressWarnings("serial")
public class CourseButton extends JButton {
	
	private Course course;
	private Tuple<String> block;
	private static Color[] colors = {Color.cyan, Color.green, Color.magenta, Color.pink, Color.yellow, Color.lightGray, Color.red};
	private int cCode;
	
	public CourseButton(Course course, int cCode, Tuple<String> block) {
		this.course = course;
		this.cCode = cCode;
		this.block = block;
		this.setText(this.course.getCourseCode());
		this.setFocusable(false);
		this.setBackground(colors[this.cCode]);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.switchCoursePage(course, CourseButton.this, block);
				
			}
			
		});
	}

}
