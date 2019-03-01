package game.card;
import java.util.ArrayList;
import java.util.Iterator;

import game.gui.ProbabilityScreen;
import game.player.*;

public class Probability {
	private int playersCard;
	private int remainCard1, remainCard2, remainCard3, remainCard4;
	private ProbabilityScreen ps;
	private boolean put;
	private Card card;
	private History history;
	private Draw draw;
	private String probability;
	private ArrayList<Player> storePlayer;
	private int remainCard5;
	
	public Probability(ProbabilityScreen ps, boolean canPut, History history, Draw draw, ArrayList<Player> storePlayer){
		this.storePlayer = storePlayer;
		this.draw = draw;
		this.ps = ps;
		put = canPut;
		this.history = history;
	}
	
	public void countPlayersCard(int indexPlayerN) {
		playersCard = 0;
		Iterator<Player> ite = storePlayer.iterator();
		while(ite.hasNext()) {
			Player p = ite.next();
			if(p != storePlayer.get(indexPlayerN)) {
				Hand hand = p.getPlayerHand();
				Iterator<Card> card = hand.getHand().iterator();
				while(card.hasNext()) {
					card.next();
					playersCard++;
				}
			}
		}
		System.err.println(playersCard);
	}
	public void historyProba(ArrayList<Card> playedCard,int mode, History history, int IndexPlayerN) { 
		Card card1, card2, card3, card4, card5;
		int valueCard1, valueCard2, valueCard3, valueCard4, valueCard5;
		countPlayersCard(IndexPlayerN);
		int size = draw.getDrawSize() - playersCard;
		switch (mode) {
			case 0:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(put) {
					probability = remainCard1 +"/"+ size;
				}
				else {
					probability = "null";
				}
				break;
			case 1:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(remainCard1<2) {
					remainCard1 = 0;
				}
				else {
					remainCard1 = remainCard1 /2;
				}
				if(put) {
					probability = remainCard1 +"/"+ size; 
				}
				else {
					probability = "null";
				}
				break;
			case 2:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(remainCard1<3) {
					remainCard1 = 0;
				}
				else {
					remainCard1 = remainCard1 /3;
				}
				if(put) {
					probability = remainCard1 +"/"+ size;
				}
				else {
					probability = "null";
				}
				break;
			case 3:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(put) {
					card2 = playedCard.get(1);
					remainCard2 = remainCard(card2);
					card3 = playedCard.get(2);
					remainCard3 = remainCard(card3);
					if(remainCard1 == 0 || remainCard2 == 0 || remainCard3 == 0) {
						probability = "null";
					}
					else {
						int prob = (remainCard1 + remainCard2 + remainCard3)/3;
						probability =prob + "/"+size;
					}
				}
				else {
					probability = "null";
				}
				break;
			case 4:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(put) {
					card2 = playedCard.get(1);
					remainCard2 = remainCard(card2);
					card3 = playedCard.get(2);
					remainCard3 = remainCard(card3);
					card4 = playedCard.get(3);
					remainCard4 = remainCard(card4);
					System.out.println(remainCard1 + remainCard2 + remainCard3 + remainCard4);
					if(remainCard1 == 0 || remainCard2 == 0 || remainCard3 == 0 || remainCard4 == 0) {
						probability = "null";
					}
					else {
						int prob = (remainCard1 + remainCard2 + remainCard3)/4;
						probability =""+ prob;
					}
				}
				else {
					probability = "null";
				}
				break;
			case 5:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(put) {
					card3 = playedCard.get(2);
					remainCard3 = remainCard(card3);
					if(remainCard1 <2 || remainCard3 <2) {
						probability = "null";
					}
					else {
						int prob = (remainCard1 + remainCard3)/4;
						probability =""+ prob;
					}
				}
				else {
					probability = "null";
				}
				break;
			case 6:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(put) {
					card2 = playedCard.get(1);
					remainCard2 = remainCard(card2);
					card3 = playedCard.get(2);
					remainCard3 = remainCard(card3);
					card4 = playedCard.get(3);
					remainCard4 = remainCard(card4);
					card5 = playedCard.get(4);
					remainCard5 = remainCard(card5);
					if(remainCard1 == 0 || remainCard2 == 0 || remainCard3 == 0 || remainCard4 == 0 || remainCard5 == 0) {
						probability = "null";
					}
					else {
						int prob = (remainCard1 + remainCard2 + remainCard3 + remainCard4 + remainCard5)/5;
						probability =""+ prob;
					}
				}
				else {
					probability = "null";
				}
				break;
			case 7:
				card = playedCard.get(0);
				remainCard1 = remainCard(card);
				if(put) {
					card3 = playedCard.get(2);
					remainCard3 = remainCard(card3);
					card5 = playedCard.get(4);
					remainCard5 = remainCard(card5);
					if(remainCard1 <2 || remainCard3 <2 || remainCard5 <2) {
						probability = "null";
					}
					else {
						int prob = (remainCard1 + remainCard3 + remainCard5)/6;
						probability =""+ prob;
					}
				}
				else {
					probability = "null";
				}
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
	
	public int remainCard(Card card) {
		int remainCard = 4; // 4 card with value
		int valueCard = card.getValue().getEnumValue();
		for (int index = 0; index < history.getSizeHistory(); index++) {
			int historyCardValue = history.getCardHistory(index).getValue().getEnumValue();
			if(historyCardValue - 1 == valueCard) {
				remainCard --;
			}
		}
		return remainCard;
	}
	
	public String getRemainCard() {
		return probability;
	}
}
