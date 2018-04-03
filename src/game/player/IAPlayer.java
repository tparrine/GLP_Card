package game.player;

public interface IAPlayer extends Player{

	public Hand getHand();
	
	public String getName();
	
	public Score getScore();

}
