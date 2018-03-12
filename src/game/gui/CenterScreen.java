package game.gui;

import java.awt.Color;
import javax.swing.JPanel;
import game.card.*;
import game.player.*;

public class CenterScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int x, y;
	
	public CenterScreen() {
		setBackground(new Color(0, 128, 0));
		setBounds(0, 0, 864, 590);
		

		}
	
	public void drawCard(Card card) {
		
//		g.drawImage(image, 30, 30, null);
//		g.drawImage(currentPlayerHand.getCardHand(index).getImage(), 30, 30, null);
//		g.drawImage(image2, 380, 30, null);
//		g.drawImage(image3, 725, 30, null);
//		g.drawImage(image4, 380, 410, null);
//		g.drawImage(image5, 725, 410, null);
	}
}