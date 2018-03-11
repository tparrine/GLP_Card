package game.gui;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class AsideScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel score = new JLabel("Score :");

	public AsideScreen() {
		setBounds(864, 0, 230, 670);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		add(score);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
