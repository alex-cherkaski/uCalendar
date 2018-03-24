package frontend_mainpage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class MainJMenu extends JMenuBar{
	
	private JMenuItem addEvent;
	private JMenuItem fileChooser;
	
	public MainJMenu(MainPage mainPage) {
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		this.addEvent = new JMenuItem("Add new event");
		editMenu.add(addEvent);
		
		this.fileChooser = new JMenuItem("Import Course Calendar");
		fileMenu.add(fileChooser);
		
		this.add(fileMenu);
		this.add(editMenu);
	}
	
	public JMenuItem getAddEventItem() {
		return this.addEvent;
	}
	
	public JMenuItem getFileChooserItem() {
		return this.fileChooser;
	}

}
