package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

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
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(50,150,100,30);
		this.add(nameLabel);
		
		JTextField nameTextBox = new JTextField();
		nameTextBox.setBounds(200,150,250,30);
		this.add(nameTextBox);
		
		String[] time = new String[13];
		int x = 9;
		for(int i = 0; i < 13; i++) {
			time[i] = String.format("%d:00", x);
			x++;
		}
		
		JLabel startLabel = new JLabel("Time:");
		startLabel.setBounds(50,200,100,30);
		this.add(startLabel);
		
		JComboBox<String> timeStartFromBox = new JComboBox<String>(time);
		timeStartFromBox.setBounds(200,200,100,30);
		this.add(timeStartFromBox);
		
		JLabel toLabel1 = new JLabel("To:");
		toLabel1.setBounds(320,200,30,30);
		this.add(toLabel1);
		
		JComboBox<String> timeEndBox = new JComboBox<String>(time);
		timeEndBox.setBounds(350,200,100,30);
		this.add(timeEndBox);
		
		
		JLabel dayLabel = new JLabel("Date:");
		dayLabel.setBounds(50,250,100,30);
		this.add(dayLabel);
		
		 String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		
		JComboBox<String> dateStartFromBox = new JComboBox<String>(days);
		dateStartFromBox.setBounds(200,250,100,30);
		this.add(dateStartFromBox);
		
		JLabel toLabel2 = new JLabel("To:");
		toLabel2.setBounds(320,250,30,30);
		this.add(toLabel2);
		
		JComboBox<String> dateEndBox = new JComboBox<String>(days);
		dateEndBox.setBounds(350,250,100,30);
		this.add(dateEndBox);
		
		String[] repeatList = {"NEVER", "DAILY", "WEEKLY", "MONTHLY"};
		
		JLabel repeatLabel = new JLabel("Repeat options:");
		repeatLabel.setBounds(50,300,100,30);
		this.add(repeatLabel);
		
		JComboBox<String> repeatBox = new JComboBox<String>(repeatList);
		repeatBox.setSelectedIndex(0);
		repeatBox.setBounds(200,300,250,30);
		this.add(repeatBox);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(200, 400, 100, 30);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(timeStartFromBox.getSelectedIndex() < timeEndBox.getSelectedIndex()) {
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String startDate = mainPage.getStartDay().format(formatter);
					String endDate = mainPage.getEndDay().format(formatter);
					
					Event event = new Event(repeatBox.getSelectedItem().toString(), startDate, endDate);
					event.addInterval(new Tuple<String>(timeStartFromBox.getSelectedItem().toString(), timeEndBox.getSelectedItem().toString(), dateStartFromBox.getSelectedItem().toString()));
					event.setName(nameTextBox.getText());
					mainPage.addEvent(event);
					eventFrame.dispose();
				}
			}
		});
		this.add(confirmButton);
		
	}
	
	

}
