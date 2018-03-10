package game.player;

import game.card.*;

public abstract class Player {
	private Score score;
	private String name;
	private Hand hand;
	
	public Player(String name) {
		hand = new Hand();
		score = new Score();
		this.name = name;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public String getName() {
		return name;
	}
	
	public Score getScore() {
		return score;
	}
}