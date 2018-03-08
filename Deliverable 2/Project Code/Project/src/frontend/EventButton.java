package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import event.Event;

@SuppressWarnings("serial")
public class EventButton extends JButton {
		
	private Event event;
	
	public EventButton(Event event) {
		this.event = event;
		this.setText(this.event.getDescription());
		this.setFocusable(false);
		this.setBackground(Color.orange);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.switchEventPage(event);
			}
			
		});
	}
}
