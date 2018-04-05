package game.player;

public interface Player {
//	private Score score;
//	private String name;
//	private Hand hand;
//	
//	public Player(String name) {
//		hand = new Hand();
//		score = new Score();
//		this.name = name;
//	}
	
	public Hand getPlayerHand();
	
	public String getName();
	
	public Score getScore();
}