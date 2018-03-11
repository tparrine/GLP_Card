package game.gui;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class BottomScreen extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton play = new JButton("Jouer");
	
	public BottomScreen() {
		setLayout(null);
		setBounds(0, 590, 850, 80);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		play.setBounds(395, 595, 67, 27);
		add(play);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
