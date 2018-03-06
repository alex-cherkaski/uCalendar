package frontend;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		this.add(menu);
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		this.previous = new PreviousButton();
		c1.fill = GridBagConstraints.BOTH;
		c1.weightx = 0.5;
		c1.weighty = 0;
		c1.gridx = 0;
		c1.gridy = 1;
		this.add(this.previous, c1);
		
		this.next = new NextButton();
		c1.fill = GridBagConstraints.BOTH;
		c1.weightx = 0.5;
		c1.weighty = 0;
		c1.gridx = 7;
		c1.gridy = 1;
		this.add(this.next, c1);
		
		this.currentWeek = new JLabel("Place holder for current week", SwingConstants.CENTER);
		c1.fill = GridBagConstraints.BOTH;
		c1.weightx = 1;
		c1.weighty = 0;
		c1.gridwidth = 5;
		c1.gridx = 1;
		c1.gridy = 1;
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
			c2.weightx = 1;
			c2.weighty = 1;
			c2.gridx = 0;
			c2.gridy = 3 + i;
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
			c3.weightx = 1;
			c3.weighty = 1;
			c3.gridx = i + 1;
			c3.gridy = 2;
			this.dayX.put(days[i], i + 1);
			this.add(dayLabel[i], c3);
		}
		
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
