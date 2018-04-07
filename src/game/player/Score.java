package game.player;

//import java.util.ArrayList;
//import java.util.Collections;
import java.util.Iterator;
import game.card.Card;
import game.card.EnumValue;


public class Score {
	private final static int BASELINE_SCORE = 100;
//	private final static int MIN_SCORE = 0;
	private int score;
	private int multiplicateur = 1;
//	private int frequency[];
	private int points;
//	private ArrayList<Card> pairCard = new ArrayList<>();
	
	public Score (Player player) {
		score = BASELINE_SCORE;
		points = player.getPlayerHand().getSizeHand();
		testJoker(player);
//		testPair(player);
		points = points * multiplicateur;
		score = score - points;
	}
	
//	public void IncrementerScore (int x) {
//		score += x;
//	}
	
//	public void DecrementerScore (int x) {
//		if(score - x > MIN_SCORE) {
//			score -= x;
//		}
//		else {
//			score = 0;
//			System.out.println("The player doesn't have any points left.");
//		}
//	}
	
	public void testJoker(Player player) {
		Iterator<Card> cardHand = player.getPlayerHand().getHand().iterator();
		while (cardHand.hasNext()) {
			Card currentCard = cardHand.next();
			if (currentCard.getValue() == EnumValue.JOKER) {
				multiplicateur = 2;
			}
		}
	}
	
//	public void testPair(Player player) {
//		Iterator<Card> cardHand = player.getPlayerHand().getHand().iterator();
//		while (cardHand.hasNext()) {
//			pairCard.add(cardHand.next());
//		}
//		for (int i = 0; i<=14 ; i++) {
//			frequency[i] = Collections.frequency(pairCard, EnumValue.JOKER);
//			if (frequency[i] >= 2 && multiplicateur == 2) {
//				multiplicateur = multiplicateur*2;
//			}
//		}
//	}
	
	public int getScore() {
		return score;
	}

	public int getPoints() {
		return points;
	}
}
