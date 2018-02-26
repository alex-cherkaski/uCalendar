package frontend;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import course.Course;
import course.CourseBuilder;
import parser.CalendarBlock;
import parser.Parser;
import tuple.Tuple;

@SuppressWarnings("serial")
public class MainPage extends JPanel{
	
	private List<CalendarBlock> blockList;
	private List<Course> courseList;
	private JLabel[] timesLabel = new JLabel[12];
	private JLabel[] dayLabel = new JLabel[5];
	private JButton next;
	private JButton previous;
	private JLabel currentWeek;
	private HashMap<String, Integer> timeY = new HashMap<String, Integer>();
	private HashMap<String, Integer> dayX = new HashMap<String, Integer>();
	private final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

	public MainPage() {
		this.setLayout(null);
		
		this.previous = new PreviousButton();
		this.add(this.previous);
		this.next = new NextButton();
		this.add(this.next);
		
		this.currentWeek = new JLabel("Place holder for current week");
		this.currentWeek.setBounds(400, 0, 300, 70);
		this.add(this.currentWeek);
		
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
		
			this.timesLabel[i] = new JLabel(s);
			this.timesLabel[i].setBounds(0, 130 + (i * 70), 60, 70);
			s = String.format("%d:00", x);
			this.timeY.put(s, 130 + (i * 70));
			this.add(this.timesLabel[i]);
			x++;
		}
		this.timeY.put("21:00", 970);
		
		for(int i = 0; i < 5; i++) {
			this.dayLabel[i] = new JLabel(days[i]);
			this.dayLabel[i].setBounds(120 + (i * 188), 75, 100, 70);
			this.dayX.put(days[i], 61 + (i * 188));
			this.add(dayLabel[i]);
		}
		
		JButton fileChooser = new JButton("file chooser");
		fileChooser.setBounds(100, 0, 200, 30);
		fileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);
				if(fileChooser.getSelectedFile() != null){
					String filePath = fileChooser.getSelectedFile().getPath();
					blockList = Parser.getCalendarBlocks(filePath);
					courseList = CourseBuilder.getCourseMap(blockList);
					
					int i = 0;
					for (Course course : courseList) {
						for(Tuple<String> block: course.getIntervalList()) {
							JButton button = new CourseButton(course, i);
							button.setBounds(dayX.get(block.getItem3()), timeY.get(block.getItem1()), 187, timeY.get(block.getItem2()) - timeY.get(block.getItem1()));
							MainPage.this.add(button);
						}
						i++;
					}
				}
				MainPage.this.repaint();;
			}
		});
		this.add(fileChooser);
	}
	
	
	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 12; i++) {
        	g.drawLine(0, 130 + (i * 70), 1000, 130 + (i * 70));
        }
        
        g.drawLine(60, 130, 60, 1000);
    }
}
