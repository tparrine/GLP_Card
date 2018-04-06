package game.mode;

import java.util.ArrayList;
import java.util.Collections;

import game.card.Card;
import game.card.EnumValue;
import game.gui.BottomScreen;

public class Tests {
	
	private BottomScreen bs;
	private Verificator verificator = new Verificator();
	private String historyString ="";
	private Boolean isBomb = false;
	
	public Tests(BottomScreen bs) {
		this.bs = bs;
	}
	
	public int detectGameMode(ArrayList<Card> playedCard) {
		historyString ="";
		bs.getStateLabel().setText("");
		Card card1, card2, card3, card4, card5, card6;
		triCard(playedCard);
		switch(playedCard.size()) {
			case 1:
				card1 = playedCard.get(0);
				if(card1.getValue() != EnumValue.JOKER) {
					historyString = historyString + "\n" + "Game mode: simple";
					return 0; // 0 => Simple
				}
				break;
			
			case 2:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				if(verificator.verifyEqual(card1, card2)) {
					historyString = historyString + "\n" + "Game mode: double";
					return 1; // 1 => Double
				}
				else if(card1.getValue() == EnumValue.JOKER || card2.getValue() == EnumValue.JOKER){
					historyString = historyString + "\n" + "Game mode: double";
					return 1;
				}
				break;	
			
			case 3:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card1, card3)) {
					historyString = historyString + "\n" + "Game mode: Triple";
					return 2; // 2 => Triple game
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyEqual(card2, card3)) { // If first Card is Joker
					historyString = historyString + "\n" + "Game mode: Triple";
					return 2;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card3)) { // If Second Card is Joker
					historyString = historyString + "\n" + "Game mode: triple";
					return 2; 
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2)) { // If Third Card is Joker
					historyString = historyString + "\n" + "Game mode: triple";
					return 2; 
				}
				else {
					if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) {
						historyString = historyString + "\n" + "Game mode: Set of 3 Cards";
						return 3; // 3 => Set of 3 cardsIterator<Player> pIterator = players.iterator();
					}
					else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3)) { // If the first card is a Joker
						historyString = historyString + "\n" + "Game mode: Set of 3 Cards";
						return 3;
					}
					else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3)) { // If the second card is a Joker
						historyString = historyString + "\n" + "Game mode: Set of 3 Cards";
						return 3;
					}
					else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2)) { // If the third card is a Joker
						historyString = historyString + "\n" + "Game mode: Set of 3 Cards";
						return 3;
					}
				}
				break;
			case 4:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				card4 = playedCard.get(3); // Fourth card value
				if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card2, card3)) {
					historyString = historyString + "\n" + "Game mode: Set of 4 Cards";
					return 4; // 5 => Set of 4 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the first card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 4 Cards";
					return 4;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4)) { // If the second card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 4 Cards";
					return 4;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4)) { // If the third card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 4 Cards";
					return 4;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) { // If the fourth card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 4 Cards";
					return 4;
				}
				else if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3)) {//If two double follow
					historyString = historyString + "\n" + "Game mode: Double set of 2 Cards";
					return 5; 
				}
				else if (card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyEqual(card3, card4)) {//If two double follow (joker=card1)
					historyString = historyString + "\n" + "Game mode: Double set of 2 Cards";
					return 5;
				}
				else if (card2.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card3) && verificator.verifyEqual(card3, card4)) {//If two double follow (joker=card2)
					historyString = historyString + "\n" + "Game mode: Double set of 2 Cards";
					return 5;
				}
				else if (card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card4) && verificator.verifyEqual(card1, card2)) {//If two double follow (joker=card3)
					historyString = historyString + "\n" + "Game mode: Double set of 2 Cards";
					return 5;
				}
				else if (card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyEqual(card1, card2)) {//If two double follow (joker=card4)
					historyString = historyString + "\n" + "Game mode: Double set of 2 Cards";
					return 5;
				}
				break;
			case 5:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				card4 = playedCard.get(3); // Fourth card value
				card5 = playedCard.get(4); // Fifth card value
				if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) {
					historyString = historyString + "\n" + "Game mode: Set of 5 Cards";
					return 6; // 6 => Set of 5 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) { // If the first card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 5 Cards";
					return 6;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) { // If the second card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 5 Cards";
					return 6;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4) && verificator.verifyFollow(card4, card5)) { // If the third card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 5 Cards";
					return 6;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollowJoker(card3, card5)) { // If the fourth card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 5 Cards";
					return 6;
				}
				else if(card5.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the fifth card is a Joker
					historyString = historyString + "\n" + "Game mode: Set of 5 Cards";
					return 6;
				}
				break;
			case 6:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				card4 = playedCard.get(3); // Fourth card value
				card5 = playedCard.get(4); // Fifth card value
				card6 = playedCard.get(5); // Sixth card value
				if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {
					historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
					return 8;
				}
				else if (verificator.verifyIfContainsJoker(card1, card2, card3, card4, card5, card6)) {//If there is a joker in the 6 cards
					if (card1.getValue() == EnumValue.JOKER && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {//Card 1 = joker
						historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
						return 7;
					}
					else if (card2.getValue() == EnumValue.JOKER && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card1, card3) && verificator.verifyFollow(card4, card5)) {//Card 2 = joker
						historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
						return 7;
					}
					else if (card3.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card4) && verificator.verifyFollow(card4, card5)) {//Card 3 = joker
						historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
						return 7;
					}
					else if (card4.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card5)) {//Card 4 = joker
						historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
						return 7;
					}
					else if (card5.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card6)) {//Card 5 = joker
						historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
						return 7;
					}
					else if (card6.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {//Card 6 = joker
						historyString = historyString + "\n" + "Game mode: Double set of 3 Cards";
						return 7;
					}
				}
				break;
			default: 
				break;
			}
		bs.getStateLabel().setText("Try again, you can't select those cards.");
		return 666;
	}
	
	public boolean canPut(ArrayList<Card> playedCard, ArrayList<Card> lastPlayedCard, int mode) {
		Card card1, card2, card3, card4, card5, card6;
		Card lastCard1, lastCard2, lastCard3, lastCard4, lastCard5, lastCard6;
		triCard(playedCard);
		switch(mode) {
			case 0://SIMPLE
				if(playedCard.size() == 1) {
					card1 = playedCard.get(0);
					lastCard1 = lastPlayedCard.get(0);
					if(verificator.verifyFollow(lastCard1, card1)) {  
						return true;
					}
					else if (card1.getValue() == EnumValue.TWO && lastCard1.getValue() != EnumValue.TWO) {
						return true;
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 1://DOUBLE
				if(playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					if(verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard2, card2)) {
						return true;
					}
					else if ((card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, card2))) {
						return true;
					}
					else if ((card2.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, card1))) {
						return true;
					}
					else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, card2) && verificator.verifyEqual(card1, card2)) {
						return true;
					}
					else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, card2) && verificator.verifyEqualJoker(card1)) {
						return true;
					}
					else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, card1) && verificator.verifyEqualJoker(card2)) {
						return true;
					}
					else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, card1) && verificator.verifyEqual(card1, card2)) {
						return true;
					}
					else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, card2) && verificator.verifyEqualJoker(card1)) {
						return true;
					}
					else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, card1) && verificator.verifyEqualJoker(card2)) {
						return true;
					}
					else if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 2://TRIPLE
				if(playedCard.size() == 3) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					lastCard3 = lastPlayedCard.get(2);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3) ) {
						if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card1)) {
								return true;
							}
						}
						else if (lastCard2.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
								return true;
							}
						}
						else if (lastCard3.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
								return true;
							}
						}
					}
					else if(card1.getValue()== EnumValue.JOKER && verificator.verifyEqual(card2, card3) ) {
						if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card2)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
								return true;
							}
						}
						else if (lastCard2.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card2)) {
								return true;
							}
						}
						else if (lastCard3.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card2)) {
								return true;
							}
						}
					}
					else if(card2.getValue()== EnumValue.JOKER && verificator.verifyEqual(card1, card3) ) {
						if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card1)) {
								return true;
							}
						}
						else if (lastCard2.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
								return true;
							}
						}
						else if (lastCard3.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
								return true;
							}
						}
					}
					else if(card3.getValue()== EnumValue.JOKER && verificator.verifyEqual(card1, card2) ) {
						if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
								return true;
							}
						}
						else if (lastCard2.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card2)) {
								return true;
							}
						}
						else if (lastCard3.getValue() == EnumValue.JOKER) {
							if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card2)) {
								return true;
							}
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 3://Set of three
				if (playedCard.size() == 3) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					lastCard3 = lastPlayedCard.get(2);
					if (verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) {
						if (verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard2, card2) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
					}
					else if (card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 4://Set of four
				if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					lastCard3 = lastPlayedCard.get(2);
					lastCard4 = lastPlayedCard.get(3);
					if (verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) {
						if (verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard2, card2) && verificator.verifyFollow(lastCard3, card3) && verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollowJoker(lastCard2, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard4.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollowJoker(lastCard2, lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard4.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
					}
					else if (card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollowJoker(lastCard2, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard4.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollowJoker(lastCard2, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard4.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4)) {
						if (verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(lastCard1, lastCard3) && verificator.verifyFollow(lastCard3, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollowJoker(lastCard2, lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard4.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, lastCard2) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 5:
				if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					lastCard3 = lastPlayedCard.get(2);
					lastCard4 = lastPlayedCard.get(3);
					if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3)) {
						if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyEqual(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqual(lastCard1, lastCard2) && verificator.verifyEqualJoker(lastCard3) && verificator.verifyEqualJoker(lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							isBomb = true;
							bs.writeBomb();
							return true;
						}
						else if (verificator.verifyEqual(lastCard3, lastCard4) && verificator.verifyEqualJoker(lastCard1) && verificator.verifyEqualJoker(lastCard2) && verificator.verifyFollow(lastCard3, card3)) {
							isBomb = true;
							bs.writeBomb();
							return true;
						}
						else if (lastCard1.getValue() == EnumValue.JOKER && verificator.verifyEqual(lastCard3, lastCard4) && verificator.verifyFollow(lastCard2, lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard2.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (lastCard3.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (lastCard4.getValue() == EnumValue.JOKER && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
					}
					else if (verificator.verifyEqual(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyEqualJoker(card4)) {
						if (verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard1) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (verificator.verifyEqual(card1, card2) && verificator.verifyFollow(card2, card4) && verificator.verifyEqualJoker(card3)) {
						if (verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard1) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card1, card3) && verificator.verifyEqualJoker(card2)) {
						if (verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard1) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard2) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard3) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard4) && verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
					}
					else if (verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3) && verificator.verifyEqualJoker(card1)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard1) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard2) && verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard3) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyEqualJoker(lastCard4) && verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						if (verificator.verifyFollow(lastCard3, card3)) {
							bs.writeBomb();
							isBomb = true;
							return true;
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 6:
				if(playedCard.size() == 5) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					card5 = playedCard.get(4);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					lastCard3 = lastPlayedCard.get(2);
					lastCard4 = lastPlayedCard.get(3);
					lastCard5 = lastPlayedCard.get(4);
					if (verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}						
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollowJoker(card3, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card5) && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 7:
				if (playedCard.size() == 6) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					card5 = playedCard.get(4);
					card6 = playedCard.get(5);
					lastCard1 = lastPlayedCard.get(0);
					lastCard2 = lastPlayedCard.get(1);
					lastCard3 = lastPlayedCard.get(2);
					lastCard4 = lastPlayedCard.get(3);
					lastCard5 = lastPlayedCard.get(4);
					lastCard6 = lastPlayedCard.get(5);
					if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard6, card6)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollowJoker(lastCard1, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard6, card6)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card1, card3) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard6, card6)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card1, card4) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard6, card6)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card1, card3) && verificator.verifyFollow(card3, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard6, card6)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card5) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card1, card3) && verificator.verifyFollow(card4, card6)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard6, card6)) {
							return true;
						}
					}
					else if (verificator.verifyEqualJoker(card6) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card1, card3) && verificator.verifyFollow(card4, card5)) {
						if (verificator.verifyFollow(lastCard1, card1)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard2, card2)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard3, card3)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard4, card4)) {
							return true;
						}
						else if (verificator.verifyFollow(lastCard5, card5)) {
							return true;
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				else if (playedCard.size() == 4) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					card3 = playedCard.get(2);
					card4 = playedCard.get(3);
					if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						isBomb = true;
						bs.writeBomb();
						return true;
					}
				}
				break;
			default:
				break;
		}
		return false;
	}
	
	public boolean getIsBomb() {
		return isBomb;
	}
	
	public String getHistory () {
		return historyString;
	}
	
	public void triCard(ArrayList<Card> playedCard) {
		 Collections.sort(playedCard);
		 System.out.println(playedCard.toString());
	}
	
}
