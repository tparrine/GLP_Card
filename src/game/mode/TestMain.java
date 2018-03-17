package game.mode;

import game.card.Card;
import game.card.EnumValue;
import game.player.Hand;
import game.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TestMain {
    
    public static void main(String[] args) {
        
//        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Card> playedCard = new ArrayList<>();
        int nbrOfPlayers, nbrOfHumanPlayers;
//        CreatePlayers players2;
        Game game = new Game();
        int index;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Total players? : ");
            nbrOfPlayers = sc.nextInt();
            System.out.println("Human players? : ");
            nbrOfHumanPlayers = sc.nextInt();    
        } while(nbrOfHumanPlayers > nbrOfPlayers && nbrOfHumanPlayers<1 && nbrOfPlayers>5);
//        players2 = new CreatePlayers(nbrOfPlayers, nbrOfHumanPlayers);
//        players = players2.newPlayer();
        
        game.start(nbrOfPlayers, nbrOfHumanPlayers);
//        game.pick(players);
        
        Iterator<Player> pIterator = Game.getStorePlayers().iterator(); // FAIT
        
        while(pIterator.hasNext()) {
            Player currentPlayer = pIterator.next();
            Hand currentPlayerHand = currentPlayer.getHand();
            System.out.println("--------------------------------------");
            for(index=0; index< currentPlayerHand.getSizeHand();index++) {
                System.out.println(index + "-" + currentPlayerHand.getCardHand(index).getValue());
            }
        } //FAIT
        System.out.println("How many cards?");
        int str = sc.nextInt();
        int tmp = str;
        ArrayList<Integer> usedCard = new ArrayList<>(); 
        
        for(index = 1; index<tmp+1; index ++) {
        	do {
        		System.out.println("Card number " +index+ " ? :");
        		str = sc.nextInt(); 		
        	} while(usedCard.contains(str));
        	usedCard.add(str);        	        	
			playedCard.add(Game.getStorePlayers().get(0).getHand().getCardHand(str));
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
				System.out.println("Mode de jeu: Set of two cards");
				break;
			case 3:
				System.out.println("Mode de jeu: Triple");
				break;
			case 4:
				System.out.println("Mode de jeu: Set of three cards");
				break;
			case 5:
				System.out.println("Set of four cards");
				break;
			case 6:
				System.out.println("Set of five cards");
				break;
			default:
				System.err.println("Error");
				break;
		}
		for(index = 1; index<=tmp; index++) {
			System.out.println("Card played number" + index + " : ");
			System.out.println(playedCard.get(index-1).getValue());
		}
        sc.close();
        
        LinkedHashMap<Integer,EnumValue> test = new LinkedHashMap<Integer,EnumValue>();  
        Player currentPlayer = game.getStorePlayers().get(0);
        Hand currentPlayerHand = currentPlayer.getHand();
        for(index = 0; index<3; index++) {
	        test.put(index,currentPlayerHand.getCardHand(index).getValue());  
        }
        for(Map.Entry testL:test.entrySet()){  
        	   System.out.println(testL.getKey()+" "+testL.getValue());  
         }  
    }
}

