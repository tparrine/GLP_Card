package game.player;

import java.util.Iterator;
import game.card.EnumValue;
import game.gui.GameBoardFrame;


public class Score {
	private final static int BASELINE_SCORE = 100;
	private final static int MIN_SCORE = 0;
	private int score;
	
	public Score () {
		score = BASELINE_SCORE;
	}
	
	public void IncrementerScore (int x) {
		score += x;
	}
	
	public void DecrementerScore (int x) {
		if(score - x > MIN_SCORE) {
			score -= x;
		}
		else {
			score = 0;
			System.out.println("The player doesn't have any points left.");
		}
	}
	
	public void containsJoker() {
		int multiplicateur;
		Iterator<Player> pIterator = GameBoardFrame.getGame().getStorePlayers().iterator();
//		Iterator<Card> cardHand = GameBoardFrame.getGame().getStorePlayers().get(0).getHand().getCardHand(0);
//		Iterator<Card> otherIterator = cardHand;
		while (pIterator.hasNext()) {
//			while (otherIterator.hasNext()) {
				if (GameBoardFrame.getGame().getStorePlayers().get(GameBoardFrame.getGame().getIndexPlayersN()).getHand().getCardHand(0).getValue() == EnumValue.JOKER) {
					multiplicateur = 2;
				}
				if (GameBoardFrame.getGame().getStorePlayers().get(GameBoardFrame.getGame().getIndexPlayersN()).getHand().getCardHand(0).getValue() == EnumValue.JOKER && GameBoardFrame.getGame().getStorePlayers().get(GameBoardFrame.getGame().getIndexPlayersN()).getHand().getCardHand(1).getValue() == EnumValue.JOKER) {
					multiplicateur = 4;
				}
//			}
		}
	}
	
	public int getScore() {
		return score;
	}

}
