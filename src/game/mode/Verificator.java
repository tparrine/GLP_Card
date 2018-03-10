package game.mode;

import game.card.Card;

public class Verificator {
	public Verificator() {
		
	}
	
	public boolean verifyFollow(Card c1, Card c2){
		int c1Value = c1.getValue().getEnumValue();
		int c2Value = c2.getValue().getEnumValue();
		if(c1Value + 1 == c2Value){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean verifyFollowJoker(Card c1, Card c2){
		int c1Value = c1.getValue().getEnumValue();
		int c2Value = c2.getValue().getEnumValue();
		if(c1Value + 2 == c2Value){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean verifyEqual(Card c1, Card c2){
		int c1Value = c1.getValue().getEnumValue();
		int c2Value = c2.getValue().getEnumValue();
		if(c1Value == c2Value){
			return true;
		}
		else{
			return false;
		}
	}
}
