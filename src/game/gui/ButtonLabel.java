package game.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;
import game.card.Card;
import game.card.EnumColor;
import game.card.EnumValue;
import game.mode.Game;

public class ButtonLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private boolean selected = false;
	private Card card;
	private ArrayList<Card> playedCard = new ArrayList<>();
	private int index;
	
	public ButtonLabel(Icon icon, EnumValue value, EnumColor color) {
		setIcon(icon);
		card = new Card(color, value);
		
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	index = CenterScreen.getIndex();
                if(selected == false){
                	setLocation(getX(), getY()-20);
                	playedCard.add(Game.getStorePlayers().get(0).getHand().getCardHand(index));
                	System.out.println(playedCard.size());
                	selected = true;
                }
                else {
                	setLocation(getX(), getY()+20);
                	playedCard.remove(Game.getStorePlayers().get(0).getHand().getCardHand(index));
                	System.out.println(playedCard.size());
                	selected = false;
                }
            }
        });
	}
	
//	public void setPlayer(Player p) {
//		this.player = p;
//	}
}