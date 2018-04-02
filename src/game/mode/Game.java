package game.mode;

import game.player.*;
import game.card.*;
import game.gui.AsideScreen;
import game.gui.BottomScreen;
import game.gui.ButtonLabel;
import game.gui.CenterScreen;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;

public class Game {
	private Player currentPlayer;
	private ArrayList<Player> storePlayers = new ArrayList<>();
	private int index, indexCard;
	private Hand currentPlayerHand;
	private Draw draw = new Draw();
	private ArrayList<Card> playedCard = new ArrayList<>();
	private ArrayList<Card> lastPlayedCard = new ArrayList<>();
	private Verificator verificator = new Verificator();
	private History history = new History();
	private int n = 0, mode;
	private CenterScreen cs;
	private AsideScreen as;
	private BottomScreen bs;
	private int tPlayers, hPlayers;
	private Player thisPlayer;
	private RoundCounter round = new RoundCounter();
	
	public Game(CenterScreen cs, AsideScreen as, BottomScreen bs, int tPlayers, int hPlayers){
		this.cs = cs;
		this.as = as;
		this.bs = bs;
		this.tPlayers = tPlayers;
		this.hPlayers = hPlayers;
	}

	public void start() {
		draw.init();
		
		CreatePlayers playersCreation = new CreatePlayers(tPlayers, hPlayers);
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
	
	public void managePlayers(Player thisPlayers) {
		as.getCurrentPlayerLabel().setText(storePlayers.get(n).getName()); //Displays player name
		compareRound(); //Display or not "tu n'y peux rien" button
		as.getHistory().setText(bs.getHistoryString()); //Update historique
		
		int yPos = 430, yOtherPos = 10;
        int xSet, xPosThree, xPosFour;
        Iterator<Player> pIterator = storePlayers.iterator();
        xSet = 368 + (currentPlayerHand.getSizeHand()*14);
        xPosThree = 456 + (currentPlayerHand.getSizeHand()*16);
        xPosFour = 536 + (currentPlayerHand.getSizeHand()*14);
        
        while(pIterator.hasNext()) {
            Player currentPlayer = pIterator.next();
            
//          System.out.println(storePlayers.size());
//    		System.out.println("Current :"+currentPlayer.getName());
//    		System.out.println("Store :"+storePlayers.get(n).getName());
//    		getN();
//    		System.out.println("--------------");
//    		
            currentPlayerHand = currentPlayer.getHand();

            switch (tPlayers) {
            	case 2:
            		xSet = 368 + (currentPlayerHand.getSizeHand()*14);
            		
            		if (storePlayers.get(n) == currentPlayer) {
            			yPos = 430;
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				cs.drawCard(c, xSet, yPos);
                			xSet -= 28;
            			}
            		}	
            		else {
            			yPos = 10;
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xSet, yPos);
                			xSet -= 28;
            			}      			
            		}
            		yPos -= 420;
            		break;
            	case 3:
            		if (storePlayers.get(n) == currentPlayer) {

            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				cs.drawCard(c, xSet, yPos);
                			xSet -= 28;
            			}
            		}	
            		else {
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xPosThree, yOtherPos);
                			xPosThree -= 15;
            			}
            			xPosThree -= 200;
            		}
            		break;
            	case 4:
            		if (storePlayers.get(n) == currentPlayer) {

            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				cs.drawCard(c, xSet, yPos);
                			xSet -= 28;
            			}
            		}	
            		else {
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xPosFour, yOtherPos);
                			xPosFour -= 11;
            			}
            			xPosFour -= 150;
            		}
            		break;
            	case 5:
            		if (storePlayers.get(n) == currentPlayer) {

            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				cs.drawCard(c, xSet, yPos);
                			xSet -= 28;
            			}
            		}	
            		else {
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				c.removeMouseListener(c.getListener());
            				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				cs.drawCard(c, xPosFour, yOtherPos);
                			xPosFour -= 10;
            			}
            			xPosFour -= 120;
            		}
            		break;
            }
        }
//		System.out.println("--------------------------------------");
	}
	
	public int detectGameMode() {
		bs.getStateLabel().setText("");
		Card card1, card2, card3, card4, card5;
		//incrementN();
		switch(playedCard.size()) {
			case 1:
				card1 = playedCard.get(0);
				if(card1.getValue() != EnumValue.JOKER) {
					mode = 0;
					return 0; // 0 => Simple
				}
				break;
			
			case 2:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				if(verificator.verifyEqual(card1, card2)) {
					mode = 1;
					return 1; // 1 => Double
				}
				else if(card1.getValue() == EnumValue.JOKER || card2.getValue() == EnumValue.JOKER){
					mode = 1;
					return 1;
				}
				break;	
			
			case 3:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card1, card3)) {
					mode = 3;
					return 3; // 3 => Triple game
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyEqual(card2, card3)) { // If first Card is Joker
					mode = 3;
					return 3;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card3)) { // If Second Card is Joker
					mode = 3;
					return 3; 
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2)) { // If Third Card is Joker
					mode = 3;
					return 3; 
				}
				else {
					card1 = playedCard.get(0); //First card value
					card2 = playedCard.get(1); //Second card value
					card3 = playedCard.get(2); // Third card value
					if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) {
						mode = 4;
						return 4; // 4 => Set of 3 cardsIterator<Player> pIterator = players.iterator();
					}
					else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3)) { // If the first card is a Joker
						mode = 4;
						return 4;
					}
					else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3)) { // If the second card is a Joker
						mode = 4;
						return 4;
					}
					else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2)) { // If the third card is a Joker
						mode = 4;
						return 4;
					}
				}
				break;
			case 4:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				card4 = playedCard.get(3); // Fourth card value
				if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card2, card3)) {
					mode = 5;
					return 5; // 5 => Set of 4 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the first card is a Joker
					mode = 5;
					return 5;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4)) { // If the second card is a Joker
					mode = 5;
					return 5;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4)) { // If the third card is a Joker
					mode = 5;
					return 5;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) { // If the fourth card is a Joker
					mode = 5;
					return 5;
				}
				else if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3)) {//If two double follow
					mode = 7;
					return 7;
				}
				else if (card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyEqual(card3, card4)) {//If two double follow (joker=card1)
					mode = 7;
					return 7;
				}
				else if (card2.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card3) && verificator.verifyEqual(card3, card4)) {//If two double follow (joker=card2)
					mode = 7;
					return 7;
				}
				else if (card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card4) && verificator.verifyEqual(card1, card2)) {//If two double follow (joker=card3)
					mode = 7;
					return 7;
				}
				else if (card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyEqual(card1, card2)) {//If two double follow (joker=card4)
					mode = 7;
					return 7;
				}
				break;
			case 5:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				card4 = playedCard.get(3); // Fourth card value
				card5 = playedCard.get(4); // Fifth card value
				if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) {
					mode = 6;
					return 6; // 6 => Set of 5 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) { // If the first card is a Joker
					mode = 6;
					return 6;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4) && verificator.verifyFollow(card4, card5)) { // If the second card is a Joker
					mode = 6;
					return 6;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4) && verificator.verifyFollow(card4, card5)) { // If the third card is a Joker
					mode = 6;
					return 6;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollowJoker(card3, card5)) { // If the fourth card is a Joker
					mode = 6;
					return 6;
				}
				else if(card5.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the fifth card is a Joker
					mode = 6;
					return 6;
				}
				break;
			default: 
				break;
			}
		bs.getStateLabel().setText("Try again, you can't select those cards.");
		mode = 666;
		return 666;
	}
	
	public void pick(ArrayList<Player> storePlayers) {
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			if (draw.getDrawSize() > 0) {
				currentPlayer = pIterator.next();
				currentPlayerHand = currentPlayer.getHand();
				currentPlayerHand.add(draw.getCard(0));
				history.addCard(draw.getCard(0));
				draw.deleteCard(0);
			}
			else { //Avoid error while picking after deck = 0
				System.out.println("You can't pick.");
				break;
			}
		}
		System.out.println("Draw size: " +draw.getDrawSize());
	}

	
	public void gameRound(int giveUpCount) {
		cs.removeAll();
		round.incrementRound();
		thisPlayer = storePlayers.get(n);
		cs.updateUI();
		if(round.getRound() != 1) {
			if (giveUpCount >= storePlayers.size()) { //Pass round
				pick(storePlayers);
				lastPlayedCard.removeAll(lastPlayedCard);
				round.resetRound();
			}
			else { //Round keep up
				bs.getStateLabel().setText("");
				if (giveUpCount == 0) {
					if(canPut()) {
						bs.writeHistory();
						putCard();
						incrementN();
					}
					else if (canPut() == false) {
						bs.getStateLabel().setText("You can't follow, try again.");
					}
				}
				else {
					incrementN();
				}
			}
		}
		else {
			bs.writeHistory();
			putCard();
			incrementN();
		}
//		System.out.println(thisPlayer.getName());
		managePlayers(thisPlayer);
		affPlayedCard();
		cs.updateUI();
		playedCard.removeAll(playedCard);
	}
	
	public boolean canPut() {
		Card card1, card2, card3, card4, card5;
		Card lastCard1, lastCard2, lastCard3, lastCard4, lastCard5;
		switch(mode) {
			case 0:
				card1 = playedCard.get(0);
				lastCard1 = lastPlayedCard.get(0);
				if(verificator.verifyFollow(lastCard1, card1)) {  
					return true;
				}
				else if (card1.getValue() == EnumValue.TWO) {
					return true;
				}
				break;
			case 1: //A completer
				card1 = playedCard.get(0);
				card2 = playedCard.get(1);
				lastCard1 = lastPlayedCard.get(0);
				lastCard2 = lastPlayedCard.get(1);
				if(verificator.verifyFollow(lastCard1, card1) && verificator.verifyFollow(lastCard2, card2)) {
					return true;
				}
				else if ((card1.getValue() == EnumValue.JOKER || card2.getValue() == EnumValue.JOKER) && verificator.verifyFollow(lastCard1, card1)) {
					return true;
				}
//				else if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
//				round.resetRound();
//				String stringHistory = bs.getHistoryString();
//				stringHistory = stringHistory + "\n" +"BOMB!";
//			}
				break;
			case 2:// condition temporaire juste pour pas avoir d'erreur
				card1 = playedCard.get(0);
				card2 = playedCard.get(1);
				card3 = playedCard.get(2);
				lastCard1 = lastPlayedCard.get(0);
				lastCard2 = lastPlayedCard.get(1);
				lastCard3 = lastPlayedCard.get(2);
				if(1 == 1) {
					return true;
				}
//				else if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
//					round.resetRound();
//					String stringHistory = bs.getHistoryString();
//					stringHistory = stringHistory + "\n" +"BOMB!";
//				}	
				break;
			case 3:
				if(1 == 1) {
					return true;
				}
//				else if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
//					round.resetRound();
//					String stringHistory = bs.getHistoryString();
//					stringHistory = stringHistory + "\n" +"BOMB!";
//				}
				break;
			case 4:
				if(1 == 1) {
					return true;
				}
//				else if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
//					round.resetRound();
//					String stringHistory = bs.getHistoryString();
//					stringHistory = stringHistory + "\n" +"BOMB!";
//				}
				break;
			case 5:
				if(1 == 1) {
					return true;
				}
//				else if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
//					round.resetRound();
//					String stringHistory = bs.getHistoryString();
//					stringHistory = stringHistory + "\n" +"BOMB!";
//				}
				break;
			case 6:
				if(1 == 1) {
					return true;
				}
//				else if(verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card2, card3)) {//If 4 cards identical
//					round.resetRound();
//					String stringHistory = bs.getHistoryString();
//					stringHistory = stringHistory + "\n" +"BOMB!";
//				}
				break;
			default:
				break;
		}
		return false;
		
	}
	public void putCard() {
		lastPlayedCard.removeAll(lastPlayedCard);
		for(index = 0; index < playedCard.size(); index++) {
			lastPlayedCard.add(playedCard.get(index));
		}
		for(index = playedCard.size()-1; index >= 0; index--) {
			storePlayers.get(n).getHand().removeCard(playedCard.get(index));
			history.addCard(playedCard.get(index));
		}
	}
	
	public void affPlayedCard() {
		int xDiscard = 382 + (playedCard.size()*8);
		for(index = lastPlayedCard.size()-1; index >= 0; index--) {
			ButtonLabel c = lastPlayedCard.get(index).getImage();
			c.removeMouseListener(c.getListener());
			cs.drawCard(c, xDiscard, 220);
			xDiscard -= 20;
		}
	}
	
	public void compareRound() {
		if (round.isFirstRound()) {
			bs.getCantPlayButton().setVisible(false); //Button tu n'y peux rien invisible
		}
		else {
			bs.getCantPlayButton().setVisible(true);
		}
	}
	
	public void incrementN() {
		if(n == storePlayers.size()-1) {
			n = 0;
		}
		else {
			n++;
		}
	}
	
	public RoundCounter getRound() {
		return round;
	}
		
	public int getIndex(int x) {
		indexCard = ((368 + (storePlayers.get(n).getHand().getSizeHand()*14) - x)/28);
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
		return storePlayers.get(n).getHand();
	}
	
	public ArrayList<Card> getPlayedCard() {
		return playedCard;
	}
	
	public ArrayList<Player> getStorePlayers() {
		return storePlayers;
	}
	
	public Player getThisPlayer() {
		return thisPlayer;
	}

}
