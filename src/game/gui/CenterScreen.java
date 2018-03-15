package game.gui;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JPanel;
import game.mode.Game;
import game.player.*;

public class CenterScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	int index;
	public CenterScreen() {
		setBackground(new Color(0, 128, 0));
		setBounds(0, 0, 864, 590);
		
		Iterator<Player> pIterator = Game.getStorePlayers().iterator();
        while(pIterator.hasNext()) {
            Player currentPlayer = pIterator.next();
            Hand currentPlayerHand = currentPlayer.getHand();
            System.out.println("--------------------------------------");
            for(index=0; index< currentPlayerHand.getSizeHand();index++) {
                drawCard(currentPlayerHand.getCardHand(index).getImage());
            }
        }
	}
	
	public void drawCard(ButtonLabel label) {
		label.setBounds(30, 30, 100, 150);
		add(label);
	}
}