package game.mode;

import game.player.*;
import game.card.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
	private Player currentPlayer;
	private int index;
	private Hand currentPlayerHand;
	private Draw draw;
	private Verificator verifiactor;
	
	public Game(Draw draw){
		this.draw = draw;
	}
	
	public void start(ArrayList<Player> players) {
		Iterator<Player> pIterator = players.iterator();
		while(pIterator.hasNext()) {
			currentPlayer = pIterator.next();
			currentPlayerHand = currentPlayer.getHand();
			for(index=0; index < 5; index++) {
				currentPlayerHand.add(draw.getCard(0));
				draw.deleteCard(0);
			}
		}
	}
	
	public int detectGameMode(ArrayList<Card> playedCards) {
		Card card1, card2, card3, card4, card5;
		verifiactor = new Verificator();
		switch(playedCards.size()) {
			case 1:
				card1 = playedCards.get(0);
				if(card1.getValue() != EnumValue.JOKER) {
					return 0; // 0 => Simple
				}
				break;
			
			case 2:
				card1 = playedCards.get(0); //First card value
				card2 = playedCards.get(1); //Second card value
				if(verifiactor.verifyEqual(card1, card2)) {
					return 1; // 1 => Double
				}
				else if(card1.getValue() == EnumValue.JOKER || card2.getValue() == EnumValue.JOKER){
					return 1;
				}
				break;	
			
			case 3:
				card1 = playedCards.get(0); //First card value
				card2 = playedCards.get(1); //Second card value
				card3 = playedCards.get(2); // Third card value
				if(verifiactor.verifyEqual(card1, card2) && verifiactor.verifyEqual(card1, card3)) {
					return 3; // 3 => Triple game
				}
				else if(card1.getValue() == EnumValue.JOKER && verifiactor.verifyEqual(card2, card3)) { // If first Card is Joker
					return 3;
				}
				else if(card2.getValue() == EnumValue.JOKER && verifiactor.verifyEqual(card1, card3)) { // If Second Card is Joker
					return 3; 
				}
				else if(card3.getValue() == EnumValue.JOKER && verifiactor.verifyEqual(card1, card2)) { // If Third Card is Joker
					return 3; 
				}
				else {
					card1 = playedCards.get(0); //First card value
					card2 = playedCards.get(1); //Second card value
					card3 = playedCards.get(2); // Third card value
					if(verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollow(card2, card3)) {
						return 4; // 4 => Set of 3 cards
					}
					else if(card1.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card2, card3)) { // If the first card is a Joker
						return 4;
					}
					else if(card2.getValue() == EnumValue.JOKER && verifiactor.verifyFollowJoker(card1, card3)) { // If the second card is a Joker
						return 4;
					}
					else if(card3.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card1, card2)) { // If the third card is a Joker
						return 4;
					}
				}
				break;
			case 4:
				card1 = playedCards.get(0); //First card value
				card2 = playedCards.get(1); //Second card value
				card3 = playedCards.get(2); // Third card value
				card4 = playedCards.get(3); // Fourth card value
				if(verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollow(card2, card3) && verifiactor.verifyFollow(card2, card3)) {
					return 5; // 5 => Set of 4 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card2, card3) && verifiactor.verifyFollow(card3, card4)) { // If the first card is a Joker
					return 5;
				}
				else if(card2.getValue() == EnumValue.JOKER && verifiactor.verifyFollowJoker(card1, card3) && verifiactor.verifyFollow(card3, card4)) { // If the second card is a Joker
					return 5;
				}
				else if(card3.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollowJoker(card2, card4)) { // If the third card is a Joker
					return 5;
				}
				else if(card4.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollow(card2, card3)) { // If the fourth card is a Joker
					return 5;
				}
				break;
			case 5:
				card1 = playedCards.get(0); //First card value
				card2 = playedCards.get(1); //Second card value
				card3 = playedCards.get(2); // Third card value
				card4 = playedCards.get(3); // Fourth card value
				card5 = playedCards.get(4); // Fifth card value
				if(verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollow(card2, card3) && verifiactor.verifyFollow(card3, card4) && verifiactor.verifyFollow(card4, card5)) {
					return 6; // 6 => Set of 5 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card2, card3) && verifiactor.verifyFollow(card3, card4) && verifiactor.verifyFollow(card4, card5)) { // If the first card is a Joker
					return 6;
				}
				else if(card2.getValue() == EnumValue.JOKER && verifiactor.verifyFollowJoker(card1, card3) && verifiactor.verifyFollow(card3, card4) && verifiactor.verifyFollow(card4, card5)) { // If the second card is a Joker
					return 6;
				}
				else if(card3.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollowJoker(card2, card4) && verifiactor.verifyFollow(card4, card5)) { // If the third card is a Joker
					return 6;
				}
				else if(card4.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollow(card2, card3) && verifiactor.verifyFollowJoker(card3, card5)) { // If the fourth card is a Joker
					return 6;
				}
				else if(card5.getValue() == EnumValue.JOKER && verifiactor.verifyFollow(card1, card2) && verifiactor.verifyFollow(card2, card3) && verifiactor.verifyFollow(card3, card4)) { // If the fifth card is a Joker
					return 6;
				}
				break;
			default: 
				break;
			}
		System.out.println("Try again.");
		return 666;
	}
		
	
}
