package game.mode;

import game.player.*;
import game.card.*;
import game.gui.AsideScreen;
import game.gui.BottomScreen;
import game.gui.ButtonLabel;
import game.gui.CenterScreen;
import game.gui.GameBoardFrame;
import game.gui.ProbabilityScreen;
import game.gui.RestartScreen;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	private int indexPlayersN = 0, mode = 666;
	private GameBoardFrame gbf;
	private CenterScreen cs;
	private AsideScreen as;
	private BottomScreen bs;
	private int tPlayers, hPlayers;
	private Player thisPlayer;
	private RoundCounter round = new RoundCounter();
	private  GiveUpCount giveUpCount = new GiveUpCount();
	private boolean peda;
	private int winnerScore;
	private ArrayList<Integer> test = new ArrayList<>();
	private Probability proba;
	private ProbabilityScreen ps;
	
	public Game(CenterScreen cs, AsideScreen as, BottomScreen bs, GameBoardFrame gbf, int tPlayers, int hPlayers, boolean peda) {
		this.gbf = gbf;//
		this.cs = cs;
		this.as = as;
		this.bs = bs;
		this.tPlayers = tPlayers;
		this.hPlayers = hPlayers;
		this.peda = peda;
	}

	public void start() {
		draw.init();
		CreatePlayers playersCreation = new CreatePlayers(tPlayers, hPlayers);
		storePlayers = playersCreation.newPlayer();
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			currentPlayer = pIterator.next();
			currentPlayerHand = currentPlayer.getPlayerHand();
			for(index=0; index < 5; index++) {
				currentPlayerHand.add(draw.getCard(0));
				draw.deleteCard(0);
			}
		}
	}	
	
	public void managePlayers() {
		as.getCurrentPlayerLabel().setText(storePlayers.get(indexPlayersN).getName()); //Displays player name
		compareRound(); //Display or not "tu n'y peux rien" button
		as.getDrawSize().setText(String.valueOf(draw.getDrawSize()));
		as.getHistory().setText(bs.getHistoryString()); //Update historique
		
		int yPos = 430, yOtherPos = 10;
        int xSet, xPosThree, xPosFour;
        Iterator<Player> pIterator = storePlayers.iterator();
        xSet = 368 + (currentPlayerHand.getSizeHand()*14);
        xPosThree = 456 + (currentPlayerHand.getSizeHand()*16);
        xPosFour = 596 + (currentPlayerHand.getSizeHand()*14);
        
        while(pIterator.hasNext()) {
            Player currentPlayer = pIterator.next();
            currentPlayerHand = currentPlayer.getPlayerHand();
            if(peda || currentPlayer.getName() == "IA") { //not implemented
    			triCard(currentPlayerHand);
    		}
            switch (tPlayers) {
            	case 2:
            		xSet = 368 + (currentPlayerHand.getSizeHand()*14);
            		
            		if (storePlayers.get(indexPlayersN) == currentPlayer) {
            			yPos = 430;
            			if(storePlayers.get(indexPlayersN).getName() == "IA") { //not implemented
            				for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            					ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
                				c.removeMouseListener(c.getListener());
                				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
                				cs.drawCard(c, xSet, yPos);
                    			xSet -= 28;
                			}
            			}
            			else {
	            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
	            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
	            				cs.drawCard(c, xSet, yPos);
	                			xSet -= 28;
	            			}
            			}
            		}	
            		else {
            			yPos = 10;
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				c.removeMouseListener(c.getListener());
            				if(!peda) {
            					c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				}
            				cs.drawCard(c, xSet, yPos);
                			xSet -= 28;
            			}      			
            		}
            		yPos -= 420;
            		break;
            	case 3:
            		xSet = 368 + (currentPlayerHand.getSizeHand()*14);
            		if (storePlayers.get(indexPlayersN) == currentPlayer) {
            			if(storePlayers.get(indexPlayersN).getName() == "IA") { //not implemented
            				for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            					ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
                				c.removeMouseListener(c.getListener());
                				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
                				cs.drawCard(c, xSet, yPos);
                    			xSet -= 28;
                			}
            			}
            			else {
	            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
	            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
	            				cs.drawCard(c, xSet, yPos);
	                			xSet -= 28;
	            			}
            			}
            		}	
            		else {
            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
            				c.removeMouseListener(c.getListener());
            				if(!peda) {
            					c.setIcon(new ImageIcon("./resources/images/cover.gif"));
            				} 
            				cs.drawCard(c, xPosThree, yOtherPos);
                			xPosThree -= 15;
            			}
            			xPosThree -= 200;
            		}
            		break;
            	case 4:
            		xSet = 368 + (currentPlayerHand.getSizeHand()*14);
            		if (storePlayers.get(indexPlayersN) == currentPlayer) {
            			if(storePlayers.get(indexPlayersN).getName() == "IA") { //not implemented
            				for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            					ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
                				c.removeMouseListener(c.getListener());
                				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
                				cs.drawCard(c, xSet, yPos);
                    			xSet -= 28;
                			}
            			}
            			else {
	            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
	            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
	            				cs.drawCard(c, xSet, yPos);
	                			xSet -= 28;
	            			}
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
            		xSet = 368 + (currentPlayerHand.getSizeHand()*14);
            		if (storePlayers.get(indexPlayersN) == currentPlayer) {
            			if(storePlayers.get(indexPlayersN).getName() == "IA") { //not implemented
            				for(index=0; index<currentPlayerHand.getSizeHand();index++) {
            					ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
                				c.removeMouseListener(c.getListener());
                				c.setIcon(new ImageIcon("./resources/images/cover.gif"));
                				cs.drawCard(c, xSet, yPos);
                    			xSet -= 28;
                			}
            			}
            			else {
	            			for(index=0; index<currentPlayerHand.getSizeHand();index++) {
	            				ButtonLabel c = currentPlayerHand.getCardHand(index).getImage();
	            				cs.drawCard(c, xSet, yPos);
	                			xSet -= 28;
	            			}
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
		Card card1, card2, card3, card4, card5, card6;
		//incrementN();
//		triCard(playedCard);
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
				if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if(verificator.verifyEqual(card1, card2)) {
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
					mode = 2;
					return 2; // 2 => Triple game
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyEqual(card2, card3)) { // If first Card is Joker
					mode = 2;
					return 2;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card3)) { // If Second Card is Joker
					mode = 2;
					return 2; 
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2)) { // If Third Card is Joker
					mode = 2;
					return 2; 
				}
				else {
					card1 = playedCard.get(0); //First card value
					card2 = playedCard.get(1); //Second card value
					card3 = playedCard.get(2); // Third card value
					if(verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) {
						mode = 3;
						return 3; // 3 => Set of 3 cardsIterator<Player> pIterator = players.iterator();
					}
					else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3)) { // If the first card is a Joker
						mode = 3;
						return 3;
					}
					else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3)) { // If the second card is a Joker
						mode = 3;
						return 3;
					}
					else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2)) { // If the third card is a Joker
						mode = 3;
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
					mode = 4;
					return 4; // 5 => Set of 4 cards
				}
				else if(card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card4)) { // If the first card is a Joker
					mode = 4;
					return 4;
				}
				else if(card2.getValue() == EnumValue.JOKER && verificator.verifyFollowJoker(card1, card3) && verificator.verifyFollow(card3, card4)) { // If the second card is a Joker
					mode = 4;
					return 4;
				}
				else if(card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollowJoker(card2, card4)) { // If the third card is a Joker
					mode = 4;
					return 4;
				}
				else if(card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card2) && verificator.verifyFollow(card2, card3)) { // If the fourth card is a Joker
					mode = 4;
					return 4;
				}
				else if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3)) {//If two double follow
					mode = 5; // 5 => Double set
					return 5; 
				}
				else if (card1.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyEqual(card3, card4)) {//If two double follow (joker=card1)
					mode = 5;
					return 5;
				}
				else if (card2.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card3) && verificator.verifyEqual(card3, card4)) {//If two double follow (joker=card2)
					mode = 5;
					return 5;
				}
				else if (card3.getValue() == EnumValue.JOKER && verificator.verifyFollow(card1, card4) && verificator.verifyEqual(card1, card2)) {//If two double follow (joker=card3)
					mode = 5;
					return 5;
				}
				else if (card4.getValue() == EnumValue.JOKER && verificator.verifyFollow(card2, card3) && verificator.verifyEqual(card1, card2)) {//If two double follow (joker=card4)
					mode = 5;
					return 5;
				}
				else if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
					round.resetRound();
					mode = 8;
					return 8;
				}
				else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
					round.resetRound();
					mode = 8;
					return 8;
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
			case 6:
				card1 = playedCard.get(0); //First card value
				card2 = playedCard.get(1); //Second card value
				card3 = playedCard.get(2); // Third card value
				card4 = playedCard.get(3); // Fourth card value
				card5 = playedCard.get(4); // Fifth card value
				card6 = playedCard.get(5); // Sixth card value
				if (verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {
					mode = 8;
					return 8;
				}
				else if (verificator.verifyIfContainsJoker(card1, card2, card3, card4, card5, card6)) {//If there is a joker in the 6 cards
					if (card1.getValue() == EnumValue.JOKER && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {//Card 1 = joker
						mode = 7;
						return 7;
					}
					else if (card2.getValue() == EnumValue.JOKER && verificator.verifyEqual(card3, card4) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card1, card3) && verificator.verifyFollow(card4, card5)) {//Card 2 = joker
						mode = 7;
						return 7;
					}
					else if (card3.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card4) && verificator.verifyFollow(card4, card5)) {//Card 3 = joker
						mode = 7;
						return 7;
					}
					else if (card4.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card5, card6) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card3, card5)) {//Card 4 = joker
						mode = 7;
						return 7;
					}
					else if (card5.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card6)) {//Card 5 = joker
						mode = 7;
						return 7;
					}
					else if (card6.getValue() == EnumValue.JOKER && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card3, card4) && verificator.verifyFollow(card2, card3) && verificator.verifyFollow(card4, card5)) {//Card 6 = joker
						mode = 7;
						return 7;
					}
				}
				break;
			default: 
				break;
			}
		bs.setTextPositionLong();
		bs.getStateLabel().setText("Try again, you can't select those cards.");
		mode = 666;
		return 666;
	}
	
	public void pick(ArrayList<Player> storePlayers) {
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			if (draw.getDrawSize() > 0) {
				currentPlayer = pIterator.next();
				currentPlayerHand = currentPlayer.getPlayerHand();
				currentPlayerHand.add(draw.getCard(0));
				//history.addCard(draw.getCard(0));
				draw.deleteCard(0);
			}
			else { //Avoid error while picking after deck = 0
				break;
			}
		}
	}

	
	public void gameRound() {//Give up count A FIXER 
		cs.removeAll();
		round.incrementRound();
		thisPlayer = storePlayers.get(indexPlayersN);
		cs.updateUI();
		if(round.getRound() != 1) {
			bs.getStateLabel().setText("");
			if(canPut()) {
				giveUpCount.resetGiveUp();
				bs.writeHistory();
				putCard();
				if (storePlayers.get(indexPlayersN).getPlayerHand().getSizeHand()==0) {
					gameOver();
				}
				else if (round.getRound()!=0) {
					incrementN();
				}
				sleepFrame();
			}
			else if (canPut() == false) {
				bs.setTextPositionShort();
				bs.getStateLabel().setText("You can't follow, try again.");
				resetPlayedCard();
			}
		}
		else {
			bs.writeHistory();
			giveUpCount.resetGiveUp();
			putCard();
			if (storePlayers.get(indexPlayersN).getPlayerHand().getSizeHand()==0) {
				gameOver();
			}
			else if (mode!=8) {
				incrementN();
				sleepFrame();
			}
			else if (mode == 8) {
				round.resetRound();
			}
		}
		managePlayers();
		affPlayedCard();
		cs.updateUI();
		playedCard.removeAll(playedCard);
	}
	
	public void cantplay() {
		sleepFrame();
		cs.removeAll();
		round.incrementRound();
		incrementN();
		thisPlayer = storePlayers.get(indexPlayersN);
		if (giveUpCount.getGiveUp() >= storePlayers.size()-2) { //Pass round
			mode = 666;
			pick(storePlayers);
			lastPlayedCard.removeAll(lastPlayedCard);
			giveUpCount.resetGiveUp();
			round.resetRound();
		}
		giveUpCount.incrementGiveUp();
		managePlayers();
		affPlayedCard();
		cs.updateUI();
		playedCard.removeAll(playedCard);
	}
	
	public boolean canPut() {
		Card card1, card2, card3, card4, card5, card6;
		Card lastCard1, lastCard2, lastCard3, lastCard4, lastCard5, lastCard6;
//		triCard(playedCard);
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
							round.resetRound();
							bs.writeBomb();
							return true;
						}
						else if (verificator.verifyEqual(lastCard3, lastCard4) && verificator.verifyEqualJoker(lastCard1) && verificator.verifyEqualJoker(lastCard2) && verificator.verifyFollow(lastCard3, card3)) {
							round.resetRound();
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
							round.resetRound();
							return true;
						}
					}
				}
				else if (playedCard.size() == 2) {
					card1 = playedCard.get(0);
					card2 = playedCard.get(1);
					if(verificator.verifyJokerBomb2Card(card1, card2)) {
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
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
						round.resetRound();
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
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if(verificator.verifyJokerBomb(card1, card2, card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqual(card2, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card1, card3) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card2) && verificator.verifyEqual(card3, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card1, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card3) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card2)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card3) && verificator.verifyEqual(card2, card4)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card1) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card2, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
					else if (verificator.verifyEqualJoker(card2) && verificator.verifyEqualJoker(card4) && verificator.verifyEqual(card1, card3)) {
						round.resetRound();
						bs.writeBomb();
						return true;
					}
				}
				break;
			case 8:
				return false;
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
			storePlayers.get(indexPlayersN).getPlayerHand().removeCard(playedCard.get(index));
			history.addCard(playedCard.get(index));
		}
	}
	
	public void affPlayedCard() {
		int xDiscard = 382;// + (playedCard.size()*8);
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
		if(indexPlayersN == storePlayers.size()-1) {
			indexPlayersN = 0;
		}
		else {
			indexPlayersN++;
		}
	}
	
	public RoundCounter getRound() {
		return round;
	}
		
	public int getIndex(int x) {
		indexCard = ((368 + (storePlayers.get(indexPlayersN).getPlayerHand().getSizeHand()*14) - x)/28);
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
		return storePlayers.get(indexPlayersN).getPlayerHand();
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
	
	public int getIndexPlayersN() {
		return indexPlayersN;
	}
	
	public void resetPlayedCard() {
		playedCard.removeAll(playedCard);
	}

	public void gameOver() {
		gbf.setVisible(false);
		makeScores(storePlayers.get(indexPlayersN));
		new RestartScreen(storePlayers.get(indexPlayersN).getName(), storePlayers, winnerScore, test, storePlayers.get(indexPlayersN));
	}
	
	public void makeScores(Player winner) {
		Score objectScore1, objectScore2, objectScore3, objectScore4, objectScore5;
		switch (storePlayers.size()) {
			case 2:
				if (storePlayers.get(0) == winner) {
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					winnerScore = 100 + objectScore2.getPoints();
				}
				else {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					winnerScore = 100 + objectScore1.getPoints();
				}
				break;
			case 3:
				if (storePlayers.get(0) == winner) {
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					winnerScore = 100 + objectScore2.getPoints() + objectScore3.getPoints();
				}
				else if (storePlayers.get(1) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore3.getPoints();
				}
				else if (storePlayers.get(2) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore2.getPoints();
				}
				break;
			case 4:
				if (storePlayers.get(0) == winner) {
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore3= new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					winnerScore = 100 + objectScore2.getPoints() + objectScore3.getPoints() + objectScore4.getPoints();
				}
				else if (storePlayers.get(1) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore3.getPoints() + objectScore4.getPoints();
				}
				else if (storePlayers.get(2) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore2.getPoints() + objectScore4.getPoints();
				}
				else if (storePlayers.get(3) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore2.getPoints() + objectScore3.getPoints();
				}
				break;
			case 5:
				if (storePlayers.get(0) == winner) {
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore3= new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					objectScore5 = new Score(storePlayers.get(4));
					test.add(objectScore5.getScore());
					winnerScore = 100 + objectScore2.getPoints() + objectScore3.getPoints() + objectScore4.getPoints() + objectScore5.getPoints();
				}
				else if (storePlayers.get(1) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					objectScore5 = new Score(storePlayers.get(4));
					test.add(objectScore5.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore3.getPoints() + objectScore4.getPoints() + objectScore5.getPoints();
				}
				else if (storePlayers.get(2) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					objectScore5 = new Score(storePlayers.get(4));
					test.add(objectScore5.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore2.getPoints() + objectScore4.getPoints() + objectScore5.getPoints();
				}
				else if (storePlayers.get(3) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					objectScore5 = new Score(storePlayers.get(4));
					test.add(objectScore5.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore2.getPoints() + objectScore3.getPoints() + objectScore5.getPoints();
				}
				else if (storePlayers.get(4) == winner) {
					objectScore1 = new Score(storePlayers.get(0));
					test.add(objectScore1.getScore());
					objectScore2 = new Score(storePlayers.get(1));
					test.add(objectScore2.getScore());
					objectScore3 = new Score(storePlayers.get(2));
					test.add(objectScore3.getScore());
					objectScore4 = new Score(storePlayers.get(3));
					test.add(objectScore4.getScore());
					winnerScore = 100 + objectScore1.getPoints() + objectScore2.getPoints() + objectScore3.getPoints() + objectScore4.getScore();
				}
				break;
			default: 
				break;
		}
	}
	
	public Probability getProba() {
		return proba;
	}
	
	public void probability() {
			if(playedCard.size() > 0) {
				if(mode != 666) {
					if(canPut()) {
						proba = new Probability(ps, true, history, draw, storePlayers);
					}
					else {
						proba = new Probability(ps, false, history, draw, storePlayers);
					}
					proba.historyProba(playedCard,mode,history, indexPlayersN);
				}
				else {
					detectGameMode();
					if(mode != 666) {
						proba = new Probability(ps, true, history, draw, storePlayers);
					}
					else {
						proba = new Probability(ps, false, history, draw, storePlayers);
					}
					proba.historyProba(playedCard,mode,history, indexPlayersN);
					mode = 666;
				}
				ps = new ProbabilityScreen(playedCard, storePlayers);
			}
			else {
				bs.setTextPositionMid();
				bs.getStateLabel().setText("Please select a card");
			}
	}
	
	public int getWinnerScore() {
		return winnerScore;
	}
	
	public void triCard(Hand currentPlayerHand) {
		 Collections.sort(currentPlayerHand.getHand());
	}
	
	public void triCard(ArrayList<Card> playedCard) {
		 Collections.sort(playedCard);
	}
	
	public void sleepFrame() {
		try {
 			Thread.sleep(500);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
	}
	
	public void startMusic() {
		File clap = new File("./resources/song.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(clap));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
		}
	}
}