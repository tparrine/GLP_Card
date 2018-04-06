package game.mode;

import game.player.*;
import game.card.*;
import game.gui.AsideScreen;
import game.gui.BottomScreen;
import game.gui.ButtonLabel;
import game.gui.CenterScreen;
import game.gui.GameBoardFrame;
import game.gui.RestartScreen;
import java.util.ArrayList;
import java.util.Collections;
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
	private int indexPlayersN = 0, mode;
	private GameBoardFrame gbf;
	private CenterScreen cs;
	private AsideScreen as;
	private BottomScreen bs;
	private int tPlayers, hPlayers;
	private Player thisPlayer;
	private RoundCounter round = new RoundCounter();
	private  GiveUpCount giveUpCount = new GiveUpCount();
	private boolean peda;
	private Tests tests;
	
	public Game(CenterScreen cs, AsideScreen as, BottomScreen bs, GameBoardFrame gbf, int tPlayers, int hPlayers, boolean peda) {
		this.gbf = gbf;//
		this.cs = cs;
		this.as = as;
		this.bs = bs;
		this.tPlayers = tPlayers;
		this.hPlayers = hPlayers;
		this.peda = peda;
		tests = new Tests(bs);
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
            if(peda || currentPlayer.getName() == "IA") {
    			triCard(currentPlayerHand);
    		}
            switch (tPlayers) {
            	case 2:
            		xSet = 368 + (currentPlayerHand.getSizeHand()*14);
            		
            		if (storePlayers.get(indexPlayersN) == currentPlayer) {
            			yPos = 430;
            			if(storePlayers.get(indexPlayersN).getName() == "IA") {
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
            			if(storePlayers.get(indexPlayersN).getName() == "IA") {
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
            			if(storePlayers.get(indexPlayersN).getName() == "IA") {
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
            			if(storePlayers.get(indexPlayersN).getName() == "IA") {
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
	
	public void detectGameMode() {
		mode = tests.detectGameMode(playedCard);
	}
	
	public void pick(ArrayList<Player> storePlayers) {
		Iterator<Player> pIterator = storePlayers.iterator();
		while(pIterator.hasNext()) {
			if (draw.getDrawSize() > 0) {
				currentPlayer = pIterator.next();
				currentPlayerHand = currentPlayer.getPlayerHand();
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

	
	public void gameRound() {//Give up count A FIXER 
		cs.removeAll();
		round.incrementRound();
		thisPlayer = storePlayers.get(indexPlayersN);
		cs.updateUI();
		if(round.getRound() != 1) {
			bs.getStateLabel().setText("");
			if(tests.canPut(playedCard, lastPlayedCard, mode)) {
				if(tests.getIsBomb()) {
					round.resetRound();
					giveUpCount.resetGiveUp();
					bs.writeHistory();
					putCard();
					if (storePlayers.get(indexPlayersN).getPlayerHand().getSizeHand()==0) {
						gameOver();
					}
				}
				else {
					giveUpCount.resetGiveUp();
					bs.writeHistory();
					putCard();
					if (storePlayers.get(indexPlayersN).getPlayerHand().getSizeHand()==0) {
						gameOver();
					}
					incrementN();
				}
			}
			else if (tests.canPut(playedCard, lastPlayedCard, mode) == false) {
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
			incrementN();
		}
		managePlayers();
		affPlayedCard();
		cs.updateUI();
		playedCard.removeAll(playedCard);
	}
	
	public void cantplay() {
		System.out.println("before" + giveUpCount.getGiveUp());
		cs.removeAll();
		round.incrementRound();
		incrementN();
		thisPlayer = storePlayers.get(indexPlayersN);
		if (giveUpCount.getGiveUp() >= storePlayers.size()-2) { //Pass round
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
		System.out.println("after" + giveUpCount.getGiveUp());
		
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
		new RestartScreen(storePlayers.get(indexPlayersN).getName(), storePlayers);
	}
	
	public void triCard(Hand currentPlayerHand) {
		 Collections.sort(currentPlayerHand.getHand());
		 System.out.println(currentPlayerHand.getHand().toString());
	}
	
	public void triCard(ArrayList<Card> playedCard) {
		 Collections.sort(playedCard);
		 System.out.println(playedCard.toString());
	}
	
	public String getTestHistory() {
		return tests.getHistory();
	}
}
