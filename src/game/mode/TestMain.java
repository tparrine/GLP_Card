package game.mode;

import game.card.Card;
import game.card.Draw;
import game.player.Hand;
import game.player.HumanPlayer;
import game.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TestMain {
    
    public static void main(String[] args) {
        
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Card> playedCard = new ArrayList<>();
        Player p1 = new HumanPlayer("J1");
        Player p2 = new HumanPlayer("J2");
        Player p3 = new HumanPlayer("J3");
        Player p4 = new HumanPlayer("J4");
        Player p5 = new HumanPlayer("J5");
        int nbrOfPlayers = 15;
        Draw draw = new Draw();
        draw.init();
        Game game = new Game(draw);
        int index;
        
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Nbr de joueur? : ");
            int str = sc.nextInt();
        
            switch (str) {
            case 2: 
                players.add(p1);
                players.add(p2);
                nbrOfPlayers = 2;
                break;
            case 3:
                players.add(p1);
                players.add(p2);
                players.add(p3);
                nbrOfPlayers = 3;
                break;
            case 4: 
                players.add(p1);
                players.add(p2);
                players.add(p3);
                players.add(p4);
                nbrOfPlayers = 4;
                break;
            case 5:
                players.add(p1);
                players.add(p2);
                players.add(p3);
                players.add(p4);
                players.add(p5);
                nbrOfPlayers = 5;
                break;
            default:
                System.err.println("erreur");
                break;
            }
        }while(nbrOfPlayers == 15);
        
        game.start(players);
        
        Iterator<Player> pIterator = players.iterator();
        while(pIterator.hasNext()) {
            Player currentPlayer = pIterator.next();
            Hand currentPlayerHand = currentPlayer.getHand();
            System.out.println("--------------------------------------");
            for(index=0; index< currentPlayerHand.getSizeHand();index++) {
                System.out.println(index + "-" + currentPlayerHand.getCardHand(index).getValue());
            }
        }
        System.out.println("Cb de cartes?");
        int str = sc.nextInt();
        int tmp = str;
        ArrayList<Integer> usedCard = new ArrayList<>(); 
        
        for(index = 1; index<tmp+1; index ++) {
        	do {
        		System.out.println("Carte numéro " +index+ " ? :");
        		str = sc.nextInt(); 		
        	}while(usedCard.contains(str));
        	usedCard.add(str);
        	playedCard.add(p1.getHand().getCardHand(str));
        }
       
        int mode = game.detectGameMode(playedCard);
		switch(mode){
			case 0:
				System.out.println("Mode de jeu: Simple");
				break;
			case 1:
				System.out.println("Mode de jeu: Double");
				break;
			case 2:
				System.out.println("Mode de jeu: Série de deux cartes");
				break;
			case 3:
				System.out.println("Mode de jeu: Triple");
				break;
			case 4:
				System.out.println("Mode de jeu: Série de trois cartes");
				break;
			case 5:
				System.out.println("Série de quatres cartes");
				break;
			case 6:
				System.out.println("Série de cinq cartes");
				break;
			default:
				System.err.println("erreur");
				break;
		}
		for(index = 1; index<=tmp; index++) {
			System.out.println("Carte joué n°" + index + " : ");
			System.out.println(playedCard.get(index-1).getValue());
		}
        sc.close();
    }

}

