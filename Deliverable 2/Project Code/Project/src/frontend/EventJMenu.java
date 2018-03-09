package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import notes.Note;

@SuppressWarnings("serial")
public class EventJMenu extends JMenuBar{

	public EventJMenu(EventPage eventPage) {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem fileChooser = new JMenuItem("Open File");
		fileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile(eventPage);
			}
		});
		fileMenu.add(fileChooser);
		this.add(fileMenu);
	}
	
	private void importFile(EventPage access) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		
		if(fileChooser.getSelectedFile() != null){
			Note note = new Note(fileChooser.getSelectedFile().getName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			access.addNote(LocalDate.now().format(formatter), note);
			
		}
	}

}