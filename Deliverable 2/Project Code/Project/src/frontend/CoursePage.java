package frontend;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import course.Course;
import tuple.Tuple;

@SuppressWarnings("serial")
public class CoursePage extends JPanel {

	private Course course;
	private JButton deleteCourse;
	private JButton previous;
	private JLabel description;
	private CourseButton currentButton;
	private Tuple<String> block;
	
	public CoursePage() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 0, 0);
		this.add(this.previous, c1);
		
		this.deleteCourse = new JButton("Delete");
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 2, 0);
		this.add(this.deleteCourse, c1);
		
		this.description = new JLabel("", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 0, 1, 1, 1, 0);
		this.add(this.description, c1);
		
		JPanel displayPanel = new JPanel();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 1, 3, 1, 0, 1);
		this.add(displayPanel, c1);
		
		this.previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrontendStartup.switchMainPage();
			}
			
		});
		
		this.deleteCourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrontendStartup.deleteCourseAndSwitch(currentButton);
			}
			
		});
	}
	
	private void setGridBag(GridBagConstraints c, double weightx, double weighty, int width, int height, int gridx, int gridy) {
		c.weightx = weightx;
		c.weighty = weighty;
		c.gridwidth = width;
		c.gridheight = height;
		c.gridx = gridx;
		c.gridy = gridy;
	}
	
	public void setCourse(Course course, CourseButton courseButton, Tuple<String> block) {
		this.course = course;
		this.description.setText(this.course.getCourseCode());
		this.currentButton = courseButton;
		this.block = block;
	}
}
