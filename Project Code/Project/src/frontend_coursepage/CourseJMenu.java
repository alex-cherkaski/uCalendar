package frontend_coursepage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class CourseJMenu extends JMenuBar{
	
	private JMenuItem deleteCourse;
	
	public CourseJMenu(CoursePage coursePage) {
		JMenu fileMenu = new JMenu("File");
		
		this.deleteCourse = new JMenuItem("Delete this course");
		fileMenu.add(deleteCourse);
		this.add(fileMenu);
	}
	
	public JMenuItem getDeleteCourseButton() {
		return this.deleteCourse;
	}

}
