package game.card;
import game.player.*;

public class Probability {
	private int probability;
	private int probability2;
	private int nbrJoueurs;
	
	public Probability(int nbrJoueurs){
		probability = 4;
		probability2 = 4;
		this.nbrJoueurs = nbrJoueurs;
	}
	
	public void followRisk(EnumValue playedCard, Hand hand, History history){
		//historyProba(playedCard, history);
		handProba(playedCard, hand);
		System.out.println("Number of card that can follow this in draw and other players hand = " + probability);
		System.out.println("Number of 2 remaining = " + probability2);
	}
	
	public void historyProba(EnumValue playedCard, History history) { //Increment for each card who follow the played Card in the History
		int index;
		int historySize = history.getSizeHistory() - 1; //removed the last card played (PlayedCard)
		Card sameCard; // Compared card for find same
		EnumValue sameCardValue;
		int followCardValue;
		
		int playedCardValue = playedCard.getEnumValue() + 1; // The EnumValue +1 (following) for the played card
	
		for (index = 0; index < historySize; index++) {
			
			sameCard = history.getCardHistory(index);
			sameCardValue = sameCard.getValue();
			followCardValue = sameCardValue.getEnumValue(); //The EnumValue for each card in the players hand
			
			if(playedCardValue == followCardValue) {
					probability --;
			}
			if(followCardValue == 20) {
				probability2 --;
			}
		}
	}
	
	public void handProba(EnumValue playedCard, Hand hand) { // Decrement for each card who follow the played Card in the player's hand
		int index;
		int handSize = hand.getSizeHand();
		int followCardValue;
		Card sameCard;
		EnumValue sameCardValue;
		
		int playedCardValue = playedCard.getEnumValue() + 1; // The EnumValue +1 (following) for the played card
		for (index = 0; index < handSize; index++) {
			
			sameCard = hand.getCardHand(index);
			sameCardValue = sameCard.getValue();
			followCardValue = sameCardValue.getEnumValue(); //The EnumValue for each card in the players hand
			
			if(playedCardValue == followCardValue) {
				probability --;
			}
			if(followCardValue == 20) {
				probability2 --;
			}
		}
	}
	
}
