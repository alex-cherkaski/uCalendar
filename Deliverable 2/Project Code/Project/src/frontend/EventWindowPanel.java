package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import event.Event;
import tuple.Tuple;


@SuppressWarnings("serial")
public class EventWindowPanel extends JPanel{

	public EventWindowPanel(MainPage mainPage, JFrame eventFrame) {
		this.setLayout(null);
		
		JLabel introLabel = new JLabel("Create Event");
		introLabel.setBounds(200, 50, 100, 30);
		this.add(introLabel);
		
		JLabel startLabel = new JLabel("Start Time:");
		startLabel.setBounds(100,100,100,30);
		this.add(startLabel);
		
		JTextField startTextBox = new JTextField();
		startTextBox.setBounds(250,100,150,30);
		this.add(startTextBox);
		
		JLabel endLabel = new JLabel("End Time:");
		endLabel.setBounds(100,150,100,30);
		this.add(endLabel);
		
		JTextField endTextBox = new JTextField();
		endTextBox.setBounds(250,150,150,30);
		this.add(endTextBox);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setBounds(100,200,100,30);
		this.add(dayLabel);
		
		JTextField dayTextBox = new JTextField();
		dayTextBox.setBounds(250,200,150,30);
		this.add(dayTextBox);
		
		String[] repeatList = {"NEVER", "DAILY", "WEEKLY", "MONTHLY"};
		
		JLabel repeatLabel = new JLabel("Repeat options:");
		repeatLabel.setBounds(100,250,100,30);
		this.add(repeatLabel);
		
		JComboBox<String> repeatBox = new JComboBox<String>(repeatList);
		repeatBox.setSelectedIndex(0);
		repeatBox.setBounds(250,250,150,30);
		this.add(repeatBox);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(200, 350, 100, 30);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Event event = new Event(repeatBox.getSelectedItem().toString(), dayTextBox.getText(), dayTextBox.getText());
				event.addInterval(new Tuple<String>(startTextBox.getText(), endTextBox.getText(), dayTextBox.getText()));
				mainPage.addEvent(event);
				eventFrame.dispose();
			}
		});
		this.add(confirmButton);
		
	}
	
	

}
