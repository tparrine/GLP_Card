package game.player;

public class HumanPlayer implements Player {
	private Hand hand = new Hand();
	private Score score = new Score();
	private String name;
	
	public HumanPlayer (String name) {
		this.name = name;
	}
	
	public Hand getHand() {
		return hand;
	}
	public Score getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
}
