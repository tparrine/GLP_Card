package game.mode;

public class GiveUpCount {
	private int giveUpCount;
	
	public GiveUpCount() {
		giveUpCount = 0;
	}
	
	public void incrementGiveUp() {
		giveUpCount++;
	}
	public void resetGiveUp() {
		giveUpCount = 0;
	}
	public int getGiveUp() {
		return giveUpCount;
	}

}

