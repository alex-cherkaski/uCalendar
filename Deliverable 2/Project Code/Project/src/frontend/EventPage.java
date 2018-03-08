package frontend;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import event.Event;
import tuple.Tuple;

@SuppressWarnings("serial")
public class EventPage extends JPanel{

	private Event event;
	private JButton deleteEvent;
	private JButton previous;
	private JLabel description;
	private EventButton currentButton;
	private Tuple<String> block;
	
	public EventPage() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 0, 0);
		this.add(this.previous, c1);
		
		this.deleteEvent = new JButton("Delete");
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.1, 0, 1, 1, 2, 0);
		this.add(this.deleteEvent, c1);
		
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
		
		this.deleteEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrontendStartup.deleteEventAndSwitch(currentButton);
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
	
	public void setEvent(Event event, EventButton eventButton, Tuple<String> block) {
		this.event = event;
		this.description.setText(this.event.getName());
		this.currentButton = eventButton;
		this.block = block;
	}

}
