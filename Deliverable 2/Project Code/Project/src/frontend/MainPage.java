package frontend;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import calendar.Calendar;
import course.Course;
import event.Event;
import tuple.Tuple;
import utilities.CalendarFunctions;

@SuppressWarnings("serial")
public class MainPage extends JPanel{
	
	private Calendar calendar;
	private LocalDate startDay;
	private LocalDate endDay;
	private LocalDate currDay;
	private JLabel[] timesLabel = new JLabel[12];
	private JLabel[] dayLabel = new JLabel[7];
	private JButton next;
	private JButton previous;
	private JLabel currentWeek;
	private HashMap<String, Integer> timeY = new HashMap<String, Integer>();
	private HashMap<String, Integer> dayX = new HashMap<String, Integer>();
	private final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private ArrayList<CourseButton> courseButtons;
	private ArrayList<EventButton> eventButtons;

	public MainPage() {
		this.calendar = new Calendar(new ArrayList<Course>(), new ArrayList<Event>());
		this.courseButtons = new ArrayList<CourseButton>();
		this.eventButtons = new ArrayList<EventButton>();
		this.setLayout(new GridBagLayout());
		JMenuBar menu = new MainJMenu(this);
		JPanel menuPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT );
		menuPanel.setLayout(flow);
		menuPanel.add(menu);
		
		GridBagConstraints c0 = new GridBagConstraints();
		c0.fill = GridBagConstraints.BOTH;
		setGridBag(c0, 0.5, 0, 8, 1, 0, 0);
		this.add(menuPanel, c0);
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.5, 0, 1, 1, 0, 1);
		this.add(this.previous, c1);
		
		this.next = new NextButton();
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 0.5, 0, 1, 1, 7, 1);
		this.add(this.next, c1);
		
		this.currDay = LocalDate.now();

	    // Go forward to get saturday
	    this.endDay = this.currDay;
	    while (endDay.getDayOfWeek() != DayOfWeek.SATURDAY) {
	    	endDay = endDay.plusDays(1);
	    }

	    // Go backwards to get Sunday
	    this.startDay = this.currDay;
	    while (startDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
	    	startDay = startDay.minusDays(1);
	    }
		
		this.currentWeek = new JLabel(this.currDay.toString(), SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 0, 5, 1, 1, 1);
		this.add(this.currentWeek, c1);
		
		this.next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currDay = currDay.plusWeeks(1);
				startDay = startDay.plusWeeks(1);
				endDay = endDay.plusWeeks(1);
				
				updateWeek();
				if(!courseButtons.isEmpty()) {
					updateDisplayEventButton();
				}
				
			}
			
		});
		
		this.previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currDay = currDay.minusWeeks(1);
				startDay = startDay.minusWeeks(1);
				endDay = endDay.minusWeeks(1);
				
				updateWeek();
				if(!courseButtons.isEmpty()) {
					updateDisplayEventButton();
				}
			}
			
		});
		
		GridBagConstraints c2 = new GridBagConstraints();
		int x = 9;
		String s;
		for(int i = 0; i < 12; i++) {
			if(x > 12) {
				s = String.format("%d:00 pm", x - 12);
			}else {
				if(x == 12) {
					s = String.format("%d:00 pm", x);
				}else {
					s = String.format("%d:00 am", x);
				}
				
			}
		
			this.timesLabel[i] = new JLabel(s, SwingConstants.CENTER);
			c2.fill = GridBagConstraints.BOTH;
			setGridBag(c2, 0.5, 0.5, 1, 1, 0, 3 + i);
			s = String.format("%d:00", x);
			this.timeY.put(s, 3 + i);
			this.add(this.timesLabel[i], c2);
			x++;
		}
		this.timeY.put("21:00", 15);
		
		GridBagConstraints c3 = new GridBagConstraints();
		LocalDate y = this.startDay;
		
		for(int i = 0; i < 7; i++) {
			this.dayLabel[i] = new JLabel(String.format("<html>" + days[i] + "<br>" + "<center>" + y.getDayOfMonth() + "</center>" + "</html>"), SwingConstants.CENTER);
			y = y.plusDays(1);
			c3.fill = GridBagConstraints.BOTH;
			setGridBag(c3, 1, 1, 1, 1, i + 1, 2);
			this.dayX.put(days[i], i + 1);
			this.add(dayLabel[i], c3);
		}
	}
	
	public void updateCurrentTime(){
		this.currDay = LocalDate.now();
	}
	
	private void updateWeek() {
		GridBagConstraints c = new GridBagConstraints();
		LocalDate y = this.startDay;
		for(int i = 0; i < 7; i++) {
			this.dayLabel[i].setText(String.format("<html>" + days[i] + "<br>" + "<center>" + y.getDayOfMonth() + "</center>" + "</html>"));
			y = y.plusDays(1);
			System.out.println(this.startDay);
			c.fill = GridBagConstraints.BOTH;
			setGridBag(c, 1, 1, 1, 1, i + 1, 2);
			this.dayX.put(days[i], i + 1);
			this.add(dayLabel[i], c);
		}
		
		this.currentWeek.setText(this.currDay.toString());
		
		this.revalidate();
		this.repaint();
	}
	
	private void setGridBag(GridBagConstraints c, double weightx, double weighty, int width, int height, int gridx, int gridy) {
		c.weightx = weightx;
		c.weighty = weighty;
		c.gridwidth = width;
		c.gridheight = height;
		c.gridx = gridx;
		c.gridy = gridy;
	}
	
	public void addClasses(Calendar calendar2) {
		List<Event> eventList = this.calendar.getEventList();
		this.calendar = calendar2;
		for(Event event: eventList) {
			this.calendar.addEvent(event);
		}
		
		GridBagConstraints c5 = new GridBagConstraints();
		
		int i = 0;
		for (Course course : this.calendar.getCourseList()) {
			for(Tuple<String> block: course.getIntervalList()) {
				CourseButton button = new CourseButton(course, i, block);
				c5.fill = GridBagConstraints.BOTH;
				c5.weightx = 0;
				c5.weighty = 0;
				c5.gridheight = this.getTimeY().get(block.getItem2()) - this.getTimeY().get(block.getItem1());
				c5.gridx = this.getDayX().get(block.getItem3());
				c5.gridy = this.getTimeY().get(block.getItem1());
				this.add(button, c5);
				this.courseButtons.add(button);
			}
			i++;
		}
		
		updateDisplayEventButton();
		
	}
	
	public void addEvent(Event event) {
		this.calendar.addEvent(event);
		
		updateDisplayEventButton();

	}
	
	private void updateDisplayEventButton() {
		GridBagConstraints c5 = new GridBagConstraints();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		for(EventButton button: this.eventButtons) {
			this.remove(button);
		}

		this.eventButtons.clear();
		
		List<Tuple<String>> conflicts = CalendarFunctions.calendarConflict(this.calendar);
		
		for(Tuple<String> block: this.calendar.getCourseFromAToB(this.startDay.format(formatter), this.endDay.format(formatter))) {
			EventButton button = new EventButton(event);
			if(conflicts != null && conflicts.contains(block)) {
				button.setBackground(Color.red);
			}
			c5.fill = GridBagConstraints.BOTH;
			c5.weightx = 0;
			c5.weighty = 0;
			c5.gridheight = this.getTimeY().get(block.getItem2()) - this.getTimeY().get(block.getItem1());
			c5.gridx = this.getDayX().get(block.getItem3());
			c5.gridy = this.getTimeY().get(block.getItem1());
			this.add(button, c5);
			this.eventButtons.add(button);
		}
		
		for(CourseButton button: this.courseButtons) {
			if(conflicts != null && conflicts.contains(button.getBlock())) {
				button.setBackground(Color.red);
			}
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public void deleteCourse(Course course) {
		for(CourseButton button: this.courseButtons) {
			if(button.getCourse().equals(course)) {
				this.remove(button);
			}
		}
		this.calendar.removeCourse(course);
		this.revalidate();
		this.repaint();
	}
	
	public void deleteEvent(Event event) {
		for(EventButton button: this.eventButtons) {
			if(button.getEvent().equals(event)) {
				this.remove(button);
			}
		}
		this.calendar.removeEvent(event);
		this.revalidate();
		this.repaint();
	}
	
	
	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 12; i++) {
        	g.drawLine(0, this.timesLabel[i].getY(), this.getWidth(), this.timesLabel[i].getY());
        }
        
        g.drawLine(this.timesLabel[0].getWidth(), this.timesLabel[0].getY(), this.timesLabel[0].getWidth(), this.getHeight());
    }
	
	public HashMap<String, Integer> getTimeY(){
		return this.timeY;
	}
	
	public HashMap<String, Integer> getDayX(){
		return this.dayX;
	}
	
	public LocalDate getStartDay() {
		return this.startDay;
	}
	
	public LocalDate getEndDay() {
		return this.endDay;
	}
	
	public LocalDate getCurrentDate() {
		return this.currDay;
	}
}
