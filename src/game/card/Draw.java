package game.card;

import java.util.ArrayList;
import java.util.Collections;

public class Draw {
	
	private ArrayList<Card> draw;
	private CardDeck deck;
	
	public Draw () {
		draw = new ArrayList<Card>();
		deck = new CardDeck();
		deck.InitPackage();
	}
	
	public boolean videPioche () {
		return draw.isEmpty();
	}
	
	public void init () {
		int i;

		for (i=0 ; i<54 ; i++){		 
			draw.add(deck.getCard(i));
		}
		Collections.shuffle(draw);
	}
	
	
	public ArrayList<Card> getDraw () {
		return draw;
	}
	
	public Card getCard(int x) {
		return draw.get(x);
	}
	
	public int getDrawSize() {
		return draw.size();
	}
	
	public void deleteCard(int index) {
		draw.remove(index);
	}

}
