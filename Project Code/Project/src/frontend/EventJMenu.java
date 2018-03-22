package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import event.Event;

@SuppressWarnings("serial")
public class EventJMenu extends JMenuBar{

	private Event event;
	
	public EventJMenu(EventPage eventPage) {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem deleteEvent = new JMenuItem("Delete this event");
		deleteEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.deleteEventAndSwitch(event);
			}
		});
		fileMenu.add(deleteEvent);
		this.add(fileMenu);
	}
	
	public void setEvent(Event event){
		this.event = event;
	}

}
