package game.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import game.card.EnumColor;
import game.card.EnumValue;

public class ButtonLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private boolean selected = false;
//	private ArrayList<Card> playedCard = new ArrayList<>();
	private MouseAdapter m;
	int index, x;
	
	public ButtonLabel(Icon icon, EnumValue value, EnumColor color) {
		setIcon(icon);
		
        addMouseListener(m = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
        		x = getX();
            	index = GameBoardFrame.getGame().getIndex(x);
                if(selected == false){
                	setLocation(getX(), getY()-20);
                	GameBoardFrame.getGame().playedCardList(GameBoardFrame.getGame().getCurrentPlayerHand().getCardHand(index));
                	selected = true;
                }
                else {
                	setLocation(getX(), getY()+20);
                	GameBoardFrame.getGame().playedCardList(GameBoardFrame.getGame().getCurrentPlayerHand().getCardHand(index));
                	selected = false;
                }
//            	System.out.println("index: " +GameBoardFrame.getGame().getIndex(x));
            }
        });
	}
	
	public MouseAdapter getListener() {
		return m;
	}
}