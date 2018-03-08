package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import event.Event;
import tuple.Tuple;

@SuppressWarnings("serial")
public class EventButton extends JButton {
		
	private Event event;
	private Tuple<String> block;
	
	public EventButton(Event event, Tuple<String> block) {
		this.event = event;
		this.block = block;
		this.setText(this.event.getName());
		this.setFocusable(false);
		this.setBackground(Color.orange);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrontendStartup.switchEventPage(event, EventButton.this, block);
			}
			
		});
	}
}
