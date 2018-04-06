package game.card;
import java.util.ArrayList;

import game.gui.ProbabilityScreen;
import game.player.*;

public class Probability {
	private int probability, p1, p2, p3, p4, p5;
	private int nbrJoueurs;
	private int remainCard, remainCard2, remainCardJoker;
	private ProbabilityScreen ps;
	private boolean put;
	
	public Probability(ProbabilityScreen ps, boolean canPut){
		this.ps = ps;
		put = canPut;
	}
	
	public void followRisk(EnumValue playedCard, Hand hand, History history){
		//historyProba(playedCard, history);
		handProba(playedCard, hand);

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
				p1 --;
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
				p1 --;
			}
		}
	}
	
	public void handProba(ArrayList<Card> playedCard, Hand hand) { // Decrement for each card who follow the played Card in the player's hand
		for(int index = 0; index < playedCard.size(); index ++) {
			
		}
	}
	
	public int handProba(ArrayList<Card> playedCard,int mode, History history) { // Decrement for each card who follow the played Card in the player's hand
		Card card1, card2, card3, card4, card5;
		int valueCard1, valueCard2, valueCard3, valueCard4, valueCard5;
		switch (mode) {
			case 0:
				probability = 8; // 4 card with value +1 and  4 card two
				card1 = playedCard.get(0);
				valueCard1 = card1.getValue().getEnumValue();
				for (int index = 0; index < history.getSizeHistory()-1; index++) {
					int historyCardValue = history.getCardHistory(index).getValue().getEnumValue();
					if(historyCardValue - 1 == valueCard1) {
						probability --;
					}
					else if(historyCardValue == 20) {
						probability --;
					}
				}
				if(valueCard1 == 20) {
					probability = 0;
				}
				break;
			case 1:
				p1 = 4; //4 following card
				p2 = 4; // 4 card 2
				p3 = 4;
				card1 = playedCard.get(0);
				valueCard1 = card1.getValue().getEnumValue();
				for (int index = 0; index < history.getSizeHistory()-1; index++) {
					int historyCardValue = history.getCardHistory(index).getValue().getEnumValue();
					if(historyCardValue - 1 == valueCard1) {
						p1 --;
					}
					else if(historyCardValue == 20) {
						p2 --;
					}
					if(historyCardValue == 17) {
						p3 --;
					}
				}
				if(p1 + p3 <2) {
					p1 = 0;
				}
				else {
					p1 = p1 + p3;
				}
				if(p2 + p3<2) {
					p2 = 0;
				}
				else {
					p2 = p2 + p3;
				}
				if(valueCard1 == 20) {
					probability = 0;
				}
				else {
					probability = p1 + p2;
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				break;
		}
		
		return probability;
	}
	
	public void handProba2(ArrayList<Card> playedCard,int mode, History history) { 
		Card card1, card2, card3, card4, card5;
		int valueCard1, valueCard2, valueCard3, valueCard4, valueCard5;
		switch (mode) {
			case 0:
				remainCard = 4; // 4 card with value
				remainCard2 = 4;
				card1 = playedCard.get(0);
				valueCard1 = card1.getValue().getEnumValue();
				for (int index = 0; index < history.getSizeHistory(); index++) {
					int historyCardValue = history.getCardHistory(index).getValue().getEnumValue();
					if(historyCardValue - 1 == valueCard1) {
						System.err.println(history.getCardHistory(index).getValue());
						remainCard --;
					}
					else if(historyCardValue == 20) {
						remainCard2 --;
					}
				}
				break;
			case 1:
				remainCard = 4; // 4 card with value +1
				remainCard2 = 4;
				remainCardJoker = 2;
				card1 = playedCard.get(0);
				valueCard1 = card1.getValue().getEnumValue();
				for (int index = 0; index < history.getSizeHistory()-1; index++) {
					int historyCardValue = history.getCardHistory(index).getValue().getEnumValue();
					if(historyCardValue - 1 == valueCard1) {
						remainCard --;
					}
					else if(historyCardValue == 20) {
						remainCard2 --;
					}
					else if(historyCardValue == 17) {
						remainCardJoker --;
					}
				}
				break;
			case 2:
				remainCard = 4; // 4 card with value +1
				remainCard2 = 4;
				remainCardJoker = 2;
				card1 = playedCard.get(0);
				valueCard1 = card1.getValue().getEnumValue();
				for (int index = 0; index <= history.getSizeHistory() - 1; index++) {
					int historyCardValue = history.getCardHistory(index).getValue().getEnumValue();
					if(historyCardValue - 1 == valueCard1) {
						remainCard --;
					}
					else if(historyCardValue == 20) {
						remainCard2 --;
					}
					else if(historyCardValue == 17) {
						remainCardJoker --;
					}
				}
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				break;
		}
	}
	
	public String canPut() {
		if(put) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
	public int getRemainCard() {
		System.out.println(remainCard);
		return remainCard;
	}
}
