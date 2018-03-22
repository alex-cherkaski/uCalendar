package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import course.Course;

@SuppressWarnings("serial")
public class CourseJMenu extends JMenuBar{
	
	private Course course;
	
	public CourseJMenu(CoursePage coursePage) {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem deleteCourse = new JMenuItem("Delete this course");
		deleteCourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.deleteCourseAndSwitch(course);
			}
		});
		fileMenu.add(deleteCourse);
		this.add(fileMenu);
	}
	
	public void setCourse(Course course){
		this.course = course;
	}

}
