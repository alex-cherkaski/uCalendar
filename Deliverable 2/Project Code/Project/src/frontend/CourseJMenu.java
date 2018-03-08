package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import calendar.Calendar;
import course.CourseBuilder;
import event.Event;
import parser.Parser;

public class CourseJMenu extends JMenuBar{

	public CourseJMenu(CoursePage coursePage) {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem fileChooser = new JMenuItem("Open File");
		fileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile(coursePage);
			}
		});
		fileMenu.add(fileChooser);
		this.add(fileMenu);
	}
	
	private void importFile(CoursePage access) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		
		if(fileChooser.getSelectedFile() != null){
			String filePath = fileChooser.getSelectedFile().getPath();
			
		}
	}

}
