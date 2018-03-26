package game.gui;

import java.awt.Color;

import javax.swing.JPanel;

public class CenterScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public CenterScreen() {
		setLayout(null);
		setBackground(new Color(0, 128, 0));
		setBounds(0, 0, 864, 590);
		
	}
	
	public void drawCard(ButtonLabel label, int x, int y) {
		label.setLocation(x, y);
		add(label);
	}
}