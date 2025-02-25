package game.mode;

import game.card.Card;
import game.card.EnumValue;

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
	
	public boolean verifyEqualJoker(Card c1) {
		if(c1.getValue() == EnumValue.JOKER) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyJokerBomb(Card c1, Card c2, Card c3, Card c4) {
		if(c1.getValue() == EnumValue.JOKER && c2.getValue() == EnumValue.JOKER) {
			return true;
		}
		else if(c1.getValue() == EnumValue.JOKER && c3.getValue() == EnumValue.JOKER) {
			return true;
		}
		else if(c1.getValue() == EnumValue.JOKER && c4.getValue() == EnumValue.JOKER) {
			return true;
		}
		else if(c2.getValue() == EnumValue.JOKER && c3.getValue() == EnumValue.JOKER) {
			return true;
		}
		else if(c2.getValue() == EnumValue.JOKER && c4.getValue() == EnumValue.JOKER) {
			return true;
		}
		else if(c3.getValue() == EnumValue.JOKER && c4.getValue() == EnumValue.JOKER) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyIfContainsJoker(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6) {
		if (c1.getValue() == EnumValue.JOKER || c2.getValue() == EnumValue.JOKER || c3.getValue() == EnumValue.JOKER || c4.getValue() == EnumValue.JOKER || c5.getValue() == EnumValue.JOKER || c6.getValue() == EnumValue.JOKER) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyJokerBomb2Card(Card c1, Card c2) {
		if(c1.getValue() == EnumValue.JOKER && c2.getValue() == EnumValue.JOKER) {
			return true;
		}
		else {
			return false;
		}
	}
}
