package game.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import game.card.Card;
import game.card.EnumColor;
import game.card.EnumValue;
//import game.player.Player;

public class ButtonLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private boolean selected = false;
	private Card card;
	
	public ButtonLabel(Icon icon, EnumValue value, EnumColor color) {
		setIcon(icon);
		card = new Card(color, value);
		
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(selected == false){
           //     	setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
                	setLocation(getX(), getY()-20);
                	System.out.println(card.getValue() +" - " + card.getColor());
                	selected = true;
                }
                else {
           //     	setBorder(null);
                	setLocation(getX(), getY()+20);
                	selected = false;
                }
            }
        });
	}
	
//	public void setPlayer(Player p) {
//		this.player = p;
//	}
}