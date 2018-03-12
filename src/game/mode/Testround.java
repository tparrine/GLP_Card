package game.mode;
import java.util.ArrayList;
import java.util.Iterator;

import game.card.*;
import game.player.*;


public class Testround {
	
	ArrayList<Player> players = new ArrayList<>();
	Player p1 = new HumanPlayer("J1");
	Player p2 = new HumanPlayer("J2");
	Player p3 = new HumanPlayer("J3");
	Player p4 = new HumanPlayer("J4");
	Player p5 = new HumanPlayer("J5");
	ArrayList<Card> playedCard = new ArrayList<>();
	
	public Testround(int nbPlayers) {
		
		Game game = new Game();
		
		switch (nbPlayers) {
			case 1: 
				players.add(p1);
				break;
			case 2: 
				players.add(p1);
				players.add(p2);
				break;
			case 3:
				players.add(p1);
				players.add(p2);
				players.add(p3);
				break;
			case 4: 
				players.add(p1);
				players.add(p2);
				players.add(p3);
				players.add(p4);
				break;
			case 5:
				players.add(p1);
				players.add(p2);
				players.add(p3);
				players.add(p4);
				players.add(p5);
				break;
			default:
				break;
		}
		
		game.start(players);
		
		Iterator<Player> pIterator = players.iterator();
		while(pIterator.hasNext()) {
			Player currentPlayer = pIterator.next();
			Hand currentPlayerHand = currentPlayer.getHand();
			System.out.println("--------------------------------------");
			for(int index=0; index< currentPlayerHand.getSizeHand();index++) {
				System.out.println(currentPlayerHand.getCardHand(index).getValue());
			}
		}
		System.out.println("--------------------------------------");
		Card c1 = new Card(EnumColor.DIAMOND, EnumValue.FIVE);
		Card c2 = new Card(EnumColor.DIAMOND, EnumValue.FIVE);
		Card c3 = new Card(EnumColor.DIAMOND, EnumValue.FIVE);
		playedCard.add(c1);
		playedCard.add(c2);
		playedCard.add(c3);
		int mode = game.detectGameMode(playedCard);
		switch(mode){
			case 0:
				System.out.println("Mode de jeu: Simple");
				break;
			case 1:
				System.out.println("Mode de jeu: Double");
				break;
			case 2:
				System.out.println("Mode de jeu: S�rie de deux cartes");
				break;
			case 3:
				System.out.println("Mode de jeu: Triple");
				break;
			case 4:
				System.out.println("Mode de jeu: S�rie de trois cartes");
				break;
			case 5:
				System.out.println("S�rie de quatres cartes");
				break;
			case 6:
				System.out.println("S�rie de cinq cartes");
				break;
		}
	}

}

