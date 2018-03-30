package game.mode;

public class RoundCounter {
	int round = 0;
	
	public RoundCounter() {
	}
	
	public void incrementRound() {
		round++;
	}
	
	public void resetRound() {
		round = 0;
	}
	
	public int getRound() {
		return round;
	}
	
	public boolean isFirstRound() {
		if(round==0) {
			return true;
		}
		else {
			return false;
		}
	}
}
