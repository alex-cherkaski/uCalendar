package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import course.Course;

@SuppressWarnings("serial")
public class CoursePage extends JPanel {

	private Course course;
	private JLabel courseLabel;
	
	public CoursePage() {
		this.setLayout(null);
		
		courseLabel = new JLabel();
		courseLabel.setBounds(400, 0, 300, 70);
		this.add(courseLabel);
		
		JButton backButton = new JButton("<");
		backButton.setBackground(null);
		backButton.setBorder(null);
		backButton.setBounds(0, 0, 60, 70);
		backButton.setFont(new Font("Serif", Font.PLAIN, 30));
		backButton.setFocusable(false);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrontendStartup.switchMainPage();
			}
			
		});
		this.add(backButton);
	}
	
	public void setCourse(Course course) {
		this.course = course;
		this.courseLabel.setText(this.course.getCourseCode());
		
	}
}
