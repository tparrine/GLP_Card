package game.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JLabel;
import game.card.Card;
import game.card.EnumColor;
import game.card.EnumValue;

public class ButtonLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private boolean selected = false;
	private ArrayList<Card> playedCard = new ArrayList<>();
	int index, x;
	
	public ButtonLabel(Icon icon, EnumValue value, EnumColor color) {
		setIcon(icon);
				
		x = getX();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	index = ((368 + (CenterScreen.getCurrentPlayerHand().getSizeHand()*15) - getX())/30);
                if(selected == false){
                	setLocation(getX(), getY()-20);
                	playedCard.add(CenterScreen.getCurrentPlayerHand().getCardHand(index));
                	selected = true;
                }
                else {
                	setLocation(getX(), getY()+20);
                	playedCard.remove(CenterScreen.getCurrentPlayerHand().getCardHand(index));
                	selected = false;
                }
            	System.out.println(playedCard.size());
            }
        });
	}
}