package frontend;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import course.Course;
import course.CourseBuilder;
import parser.CalendarBlock;
import parser.Parser;
import tuple.Tuple;

@SuppressWarnings("serial")
public class MainJMenu extends JMenuBar{

	private List<CalendarBlock> blockList;
	private List<Course> courseList;
	
	public MainJMenu(MainPage mainPage) {
		JMenuItem fileChooser = new JMenuItem("file chooser");
		fileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);
				if(fileChooser.getSelectedFile() != null){
					String filePath = fileChooser.getSelectedFile().getPath();
					blockList = Parser.getCalendarBlocks(filePath);
					courseList = CourseBuilder.getCourseMap(blockList);
					GridBagConstraints c5 = new GridBagConstraints();
					
					int i = 0;
					for (Course course : courseList) {
						for(Tuple<String> block: course.getIntervalList()) {
							JButton button = new CourseButton(course, i);
							c5.fill = GridBagConstraints.BOTH;
							c5.weightx = 0;
							c5.weighty = 0;
							c5.gridheight = mainPage.getTimeY().get(block.getItem2()) - mainPage.getTimeY().get(block.getItem1());
							c5.gridx = mainPage.getDayX().get(block.getItem3());
							c5.gridy = mainPage.getTimeY().get(block.getItem1());
							mainPage.add(button, c5);
						}
						i++;
					}
				}
				mainPage.revalidate();
				mainPage.repaint();
			}
		});
		this.add(fileChooser);
	}

}
