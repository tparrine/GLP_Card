package game.mode;

import game.player.*;
import game.card.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
	private Player currentPlayer;
	private static ArrayList<Player> storePlayers = new ArrayList<>();
	private int index;
	private Hand currentPlayerHand;
	private Draw draw = new Draw();
	private Verificator verificator;
	private History history = new History();
	
	public Game(){
		draw.init();
	}
	
	public void start(int totalPlayers, int humanPlayers) {
		CreatePlayers playersCreation = new CreatePlayers(totalPlayers, humanPlayers);
		storePlayers = playersCreation.newPlayer();
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			currentPlayer = pIterator.next();
			currentPlayerHand = currentPlayer.getHand();
			for(index=0; index < 5; index++) {
				currentPlayerHand.add(draw.getCard(0));
				draw.deleteCard(0);
//				currentPlayerHand.getCardHand(0);
//				currentPlayerHand.getSizeHand();
			}
		}
	}
	
	public int detectGameMode(ArrayList<Card> playedCards) {
		Card card1, card2, card3, card4, card5;
		verificator = new Verificator();
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
				if(verificator.verifyEqual(card1, card2)) {
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
				if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card1, card3)) {
					return 3; // 3 => Triple game
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyEqual(card2, card3)) { // If first Card is Joker
					return 3;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card3)) { // If Second Card is Joker
					return 3; 
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2)) { // If Third Card is Joker
					return 3; 
				}
				else {
					card1 = playedCards.get(0); //First card value
					card2 = playedCards.get(1); //Second card value
					card3 = playedCards.get(2); // Third card value
					if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) {
						return 4; // 4 => Set of 3 cardsIterator<Player> pIterator = players.iterator();
					}
					else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3)) { // If the first card is a Joker
						return 4;
					}
					else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3)) { // If the second card is a Joker
						return 4;
					}
					else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2)) { // If the third card is a Joker
						return 4;
					}
				}
				break;
			case 4:
				card1 = playedCards.get(0); //First card value
				card2 = playedCards.get(1); //Second card value
				card3 = playedCards.get(2); // Third card value
				card4 = playedCards.get(3); // Fourth card value
				if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card2, card3)) {
					return 5; // 5 => Set of 4 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the first card is a Joker
					return 5;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4)) { // If the second card is a Joker
					return 5;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4)) { // If the third card is a Joker
					return 5;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) { // If the fourth card is a Joker
					return 5;
				}
				break;
			case 5:
				card1 = playedCards.get(0); //First card value
				card2 = playedCards.get(1); //Second card value
				card3 = playedCards.get(2); // Third card value
				card4 = playedCards.get(3); // Fourth card value
				card5 = playedCards.get(4); // Fifth card value
				if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) {
					return 6; // 6 => Set of 5 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) { // If the first card is a Joker
					return 6;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) { // If the second card is a Joker
					return 6;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4) && verificator.verifyFollow(card4, card5)) { // If the third card is a Joker
					return 6;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollowJoker(card3, card5)) { // If the fourth card is a Joker
					return 6;
				}
				else if(card5.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the fifth card is a Joker
					return 6;
				}
				break;
			default: 
				break;
			}
		System.out.println("Try again.");
		return 666;
	}
	
	public void pick(ArrayList<Player> storePlayers) {
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			currentPlayer = pIterator.next();
			currentPlayerHand = currentPlayer.getHand();
			currentPlayerHand.add(draw.getCard(0));
			history.addCard(draw.getCard(0));
			draw.deleteCard(0);
		}
	}
	
	public static ArrayList<Player> getStorePlayers() {
		return storePlayers;
	}	
}
