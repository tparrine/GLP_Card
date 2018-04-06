package game.player;

public class Balanced implements IAPlayer {
	
	private Hand hand = new Hand();
//	private Score score = new Score();
	private String name;
	
	public Balanced (String name) {
		this.name = name;
	}
	
	public Hand getPlayerHand() {
		return hand;
	}
//	public Score getScore() {
//		return score;
//	}
	public String getName() {
		return name;
	}

}
