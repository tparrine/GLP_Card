package game.mode;

import game.card.Card;
import game.card.Draw;
import game.player.Hand;
import game.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TestMain {
    
    public static void main(String[] args) {
        
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Card> playedCard = new ArrayList<>();
        int nbrOfPlayers, nbrOfHumanPlayers;
        CreatePlayers players2;
        Draw draw = new Draw();
        draw.init();
        Game game = new Game(draw);
        int index;
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("Nbr de joueur? : ");
            nbrOfPlayers = sc.nextInt();
            System.out.println("Nbr d'humain? : ");
            nbrOfHumanPlayers = sc.nextInt();    
        }while(nbrOfHumanPlayers > nbrOfPlayers && nbrOfHumanPlayers<1 && nbrOfPlayers>5);
        players2 = new CreatePlayers(nbrOfPlayers, nbrOfHumanPlayers);
        players = players2.newPlayer();
        
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
        		System.out.println("Carte num�ro " +index+ " ? :");
        		str = sc.nextInt(); 		
        	}while(usedCard.contains(str));
        	usedCard.add(str);        	        	
        	playedCard.add(players.get(0).getHand().getCardHand(str));
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
			default:
				System.err.println("erreur");
				break;
		}
		for(index = 1; index<=tmp; index++) {
			System.out.println("Carte jou� n�" + index + " : ");
			System.out.println(playedCard.get(index-1).getValue());
		}
        sc.close();
    }

}

