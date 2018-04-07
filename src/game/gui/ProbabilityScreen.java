package game.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import game.card.Card;
import game.card.Probability;
import game.player.Player;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProbabilityScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel cards;
	private String stringPlayedCard = "";
	private ArrayList<Card> playedCard = new ArrayList<>();
	private Probability proba;
	private JLabel canPut, card1;
	private JLabel playedCards;
	
	public ProbabilityScreen(ArrayList<Card> playedCard, ArrayList<Player> storePlayers) {
		init();
		this.playedCard = playedCard;
		getPlayedCard();
		this.cards = new JLabel("Played cards " + stringPlayedCard);
		Font font = new Font("Arial", Font.ITALIC, 15);
		this.cards.setFont(font);
		this.cards.setForeground(Color.black);
		addContent();
		
		setVisible(true);
	}
	
	public void init() {
		setLayout(null);
		setSize(300, 300);
		setTitle("Probabilities");
		setLocationRelativeTo(null);
	}
	
	public void getPlayedCard() {
		for(int index = 0; index <= playedCard.size() -1; index ++ ) {
			stringPlayedCard += " - ";
			stringPlayedCard += playedCard.get(index).getValue();
		}
	}
	
	public void addContent() {
		proba = GameBoardFrame.game.getProba();
		
		playedCards = new JLabel();
		canPut = new JLabel();
		card1 = new JLabel();
		
		playedCards.setBounds(50, 20, 300, 50); 
		add(playedCards);
		playedCards.setText("Played cards: " + stringPlayedCard);
		
		canPut.setBounds(105, 90, 200, 50);
		add(canPut);
		canPut.setText("Can put? " +proba.canPut());
		
		card1.setBounds(88, 160, 300, 50);
		add(card1);
		card1.setText("Following risk: " +proba.getRemainCard());
		
		
	}
}
