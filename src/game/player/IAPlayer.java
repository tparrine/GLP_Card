package game.player;

public interface IAPlayer extends Player{

	public Hand getPlayerHand();
	
	public String getName();
	
	public Score getScore();

}
