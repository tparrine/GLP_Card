package game.mode;

import java.util.ArrayList;
import java.util.Random;

import game.player.Adventurer;
import game.player.Balanced;
import game.player.Defensive;
import game.player.HumanPlayer;
import game.player.Player;

public class CreatePlayers {
	
	private ArrayList<Player> players = new ArrayList<>();
	private Player p1 , p2, p3, p4, p5;
    private int nbrOfPlayers, nbrOfHumanPlayers;
    
	public CreatePlayers(int nbrOfPlayers, int nbrOfHumanPlayers) {
		this.nbrOfPlayers = nbrOfPlayers;
		this.nbrOfHumanPlayers = nbrOfHumanPlayers;
	}

	public ArrayList<Player> newPlayer(){
		newHumanPlayer();
		newIAPlayer();
		return players;
	}
	
	public ArrayList<Player> newHumanPlayer(){
		switch(nbrOfHumanPlayers) {
		case 1:
			p1 = new HumanPlayer("J1");
			players.add(p1);
			break;
		case 2:
			p1 = new HumanPlayer("J1");
			players.add(p1);
			p2 = new HumanPlayer("J2");
			players.add(p2);
			break;
		case 3:
			p1 = new HumanPlayer("J1");
			players.add(p1);
			p2 = new HumanPlayer("J2");
			players.add(p2);
			p3 = new HumanPlayer("J3");
			players.add(p3);
			break;
		case 4:
			p1 = new HumanPlayer("J1");
			players.add(p1);
			p2 = new HumanPlayer("J2");
			players.add(p2);
			p3 = new HumanPlayer("J3");
			players.add(p3);
			p4 = new HumanPlayer("J4");
			players.add(p4);
			break;
		case 5:
			p1 = new HumanPlayer("J1");
			players.add(p1);
			p2 = new HumanPlayer("J2");
			players.add(p2);
			p3 = new HumanPlayer("J3");
			players.add(p3);
			p4 = new HumanPlayer("J4");
			players.add(p4);
			p5 = new HumanPlayer("J5");
			players.add(p5);
			break;
		default:
			System.err.println("Erreur");
		}
		return players;
	}
	
	public ArrayList<Player> newIAPlayer(){
		int IAPlayers = nbrOfPlayers - nbrOfHumanPlayers;
		switch(IAPlayers) {
		case 0:
			break;
		case 1:
			p2 = selectType(p5);
			players.add(p5);
			break;
		case 2:
			p4  = selectType(p4);
			players.add(p4);
			p5 = selectType(p5);
			players.add(p5);
			break;
		case 3:
			p3 = selectType(p3);
			players.add(p3);
			p4 = selectType(p4);
			players.add(p4);
			p5 = selectType(p5);
			players.add(p5);
			break;
		case 4:
			p2 = selectType(p2);
			players.add(p2);
			p3 = selectType(p3);
			players.add(p3);
			p4 = selectType(p4);
			players.add(p4);
			p5 = selectType(p5);
			players.add(p5);
			break;
		default:
			System.err.println("Erreur");
		}
		return players;
	}
	
	public Player selectType(Player p) {
		Random rand = new Random();
		int type = rand.nextInt(3);
		switch(type) {
		case 0:
			p = new Adventurer("IA");
			break;
		case 1:
			p = new Balanced("IA");
			break;
		case 2:
			p = new Defensive("IA");
			break;
		}
		return p;
	}
}
