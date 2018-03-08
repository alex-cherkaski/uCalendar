package frontend;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import course.Course;
import event.Event;
import tuple.Tuple;

@SuppressWarnings("serial")
public class MainPage extends JPanel{
	
	private JLabel[] timesLabel = new JLabel[12];
	private JLabel[] dayLabel = new JLabel[7];
	private JButton next;
	private JButton previous;
	private JLabel currentWeek;
	private HashMap<String, Integer> timeY = new HashMap<String, Integer>();
	private HashMap<String, Integer> dayX = new HashMap<String, Integer>();
	private final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

	public MainPage() {
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
		
		this.currentWeek = new JLabel("Place holder for current week", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		setGridBag(c1, 1, 0, 5, 1, 1, 1);
		this.add(this.currentWeek, c1);
		
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
		for(int i = 0; i < 7; i++) {
			this.dayLabel[i] = new JLabel(days[i], SwingConstants.CENTER);
			c3.fill = GridBagConstraints.BOTH;
			setGridBag(c3, 1, 1, 1, 1, i + 1, 2);
			this.dayX.put(days[i], i + 1);
			this.add(dayLabel[i], c3);
		}
		
	}
	
	private void setGridBag(GridBagConstraints c, double weightx, double weighty, int width, int height, int gridx, int gridy) {
		c.weightx = weightx;
		c.weighty = weighty;
		c.gridwidth = width;
		c.gridheight = height;
		c.gridx = gridx;
		c.gridy = gridy;
	}
	
	public void addClasses(List<Course> courses) {
		GridBagConstraints c5 = new GridBagConstraints();
		int i = 0;
		for (Course course : courses) {
			for(Tuple<String> block: course.getIntervalList()) {
				JButton button = new CourseButton(course, i, block);
				c5.fill = GridBagConstraints.BOTH;
				c5.weightx = 0;
				c5.weighty = 0;
				c5.gridheight = this.getTimeY().get(block.getItem2()) - this.getTimeY().get(block.getItem1());
				c5.gridx = this.getDayX().get(block.getItem3());
				c5.gridy = this.getTimeY().get(block.getItem1());
				this.add(button, c5);
			}
			i++;
		}
		this.revalidate();
		this.repaint();
	}
	
	public void addEvent(Event event) {
		GridBagConstraints c5 = new GridBagConstraints();

		for(Tuple<String> block: event.getIntervalList()) {
			JButton button = new EventButton(event);
			c5.fill = GridBagConstraints.BOTH;
			c5.weightx = 0;
			c5.weighty = 0;
			c5.gridheight = this.getTimeY().get(block.getItem2()) - this.getTimeY().get(block.getItem1());
			c5.gridx = this.getDayX().get(block.getItem3());
			c5.gridy = this.getTimeY().get(block.getItem1());
			this.add(button, c5);
		}

		this.revalidate();
		this.repaint();
	}
	
	public void deleteCourse(CourseButton button) {
		this.remove(button);
		this.revalidate();
		this.repaint();
	}
	
	public void deleteEvent(EventButton button) {
		this.remove(button);
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
}
