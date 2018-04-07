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
	
	
	public void hp(EnumValue playedCard, History history) { //Increment for each card who follow the played Card in the History
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
	
	
	public void historyProba(ArrayList<Card> playedCard,int mode, History history) { 
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
						System.err.println(history.getCardHistory(index).getValue() +""+ history.getCardHistory(index).getColor());
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
