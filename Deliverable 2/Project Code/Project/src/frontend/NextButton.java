package frontend;

import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class NextButton extends JButton {
	
	public NextButton() {
		this.setText(">");
		this.setBackground(null);
		this.setBorder(null);
		this.setBounds(935, 0, 60, 70);
		this.setFont(new Font("Serif", Font.PLAIN, 30));
		this.setFocusable(false);
	}

}
