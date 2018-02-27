package frontend;

import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PreviousButton extends JButton {
	
	public PreviousButton() {
		this.setText("<");
		this.setBackground(null);
		this.setBorder(null);
		this.setBounds(0, 0, 60, 70);
		this.setFont(new Font("Serif", Font.PLAIN, 30));
		this.setFocusable(false);
	}

}
