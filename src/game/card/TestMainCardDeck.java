package game.card;
import game.player.*;

public class TestMainCardDeck {

	public static void main(String[] args) {
		
		Draw draw = new Draw();
		History history = new History();
		Player j1 = new HumanPlayer("Joueur 1");
		Player j2 = new HumanPlayer("Joueur 2");
		Probability proba = new Probability(2);
		
		draw.init();
		for(int i=0; i<54;i++) {
			System.out.println(draw.getCard(i).getValue() +"    " + draw.getCard(i).getColor());
		}
		
		System.out.println("--------------------------------------");
		System.out.println(j1.getName() + " hand : ");
		for(int index = 0; index <5; index++) {
			j1.getHand().add(draw.getCard(0));
			draw.deleteCard(0);
			j2.getHand().add(draw.getCard(index));
			draw.deleteCard(0);
			System.out.println(j1.getHand().getCardHand(index).getValue());
		}
		
		System.out.println("--------------------------------------");
		System.out.println(j2.getName() + " hand : ");
		for(int index = 0; index < j2.getHand().getSizeHand(); index++) {
			System.out.println(j2.getHand().getCardHand(index).getValue());
		}
		
		System.out.println("--------------------------------------");
		EnumValue playedCard = j1.getHand().getCardHand(0).getValue();
		System.out.println("Card played by " + j1.getName() + " = " + playedCard);
		j1.getHand().removeCard(0);
		history.addCard(j1.getHand().getCardHand(0));
		proba.followRisk(playedCard, j1.getHand(), history);
		
	}
}
