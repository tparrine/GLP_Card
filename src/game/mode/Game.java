package game.mode;

import game.player.*;
import game.card.*;
import game.gui.ButtonLabel;
import game.gui.CenterScreen;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Game {
	private Player currentPlayer;
	private static ArrayList<Player> storePlayers = new ArrayList<>();
	private int index, indexCard;
	private Hand currentPlayerHand;
	private Draw draw = new Draw();
	private ArrayList<Card> playedCard = new ArrayList<>();
	private Verificator verificator;
	private History history = new History();
	
	public Game(){
	}
	
	public void start(int totalPlayers, int humanPlayers) {
		draw.init();
		
		CreatePlayers playersCreation = new CreatePlayers(totalPlayers, humanPlayers);
		storePlayers = playersCreation.newPlayer();
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			currentPlayer = pIterator.next();
			currentPlayerHand = currentPlayer.getHand();
			for(index=0; index < 5; index++) {
				currentPlayerHand.add(draw.getCard(0));
				draw.deleteCard(0);
			}
		}
	}	
	
	public void managePlayers(CenterScreen cs, int tPlayers) {
		
        int yPos = 430;
        int xPos, xPosThree, xPosFour, xPosFive;
        Iterator<Player> pIterator = storePlayers.iterator();
        xPos = 368 + (currentPlayerHand.getSizeHand()*15);
        xPosThree = 546 + (currentPlayerHand.getSizeHand()*15);
        xPosFour = 526 + (currentPlayerHand.getSizeHand()*15);
        xPosFive = 636 + (currentPlayerHand.getSizeHand()*15);
        
        while(pIterator.hasNext()) {
            Player currentPlayer = pIterator.next();
            currentPlayerHand = currentPlayer.getHand();

            switch (tPlayers) {
            	case 2:
            		xPos = 368 + (currentPlayerHand.getSizeHand()*15);	
            		for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            			ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            			if(yPos != 430) {
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            			}
            			cs.drawCard(c, xPos, yPos);
            			xPos -= 30;
            		}
            		yPos -= 420;
            		break;
            	case 3:
            		for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            			ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            			if(yPos != 430) {
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xPosThree, yPos);
            				xPosThree -= 20;
            			}
            			else {
            				cs.drawCard(c, xPos, yPos);
            				xPos -= 30;
            			}         
            		}
            		if (currentPlayer == storePlayers.get(0)) {
            			yPos -= 420;
            		}
            		else {
            			xPosThree -= 300;
            		}
            		break;
            	case 4:
            		for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            			ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            			if(yPos != 430) {
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xPosFour, yPos);
            				xPosFour -= 10;
            			}
            			else {
            				cs.drawCard(c, xPos, yPos);
            				xPos -= 30;
            			}         
            		}
            		if (currentPlayer == storePlayers.get(0)) {
            			yPos -= 420;
            		}
            		else {
            			xPosFour -= 150;
            		}
            		break;
            	case 5:
            		for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            			ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            			if(yPos != 430) {
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xPosFive, yPos);
            				xPosFive -= 7;
            			}
            			else {
            				cs.drawCard(c, xPos, yPos);
            				xPos -= 30;
            			}   	      
            		}
            		if (currentPlayer == storePlayers.get(0)) {
            			yPos -= 420;
            		}
            		else {
            			xPosFive -= 170;
            		}
            		break;
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
	
	public int getIndex(int x) {
		indexCard = ((368 + (currentPlayerHand.getSizeHand()*15) - x)/30);
		return indexCard;
	}
	
	public void playedCardList(Card card) {
		if (playedCard.contains(card)) {
			playedCard.remove(card);
		}
		else {
			playedCard.add(card);
		}
	}
	
	public Hand getCurrentPlayerHand() {
		return currentPlayerHand;
	}
	
	public ArrayList<Card> getPlayedCard() {
		return playedCard;
	}
	
	public ArrayList<Player> getStorePlayers() {
		return storePlayers;
	}
}
