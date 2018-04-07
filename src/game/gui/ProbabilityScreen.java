package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import game.card.Card;
import game.card.Probability;
import game.player.Player;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class ProbabilityScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private GridBagLayout g = new GridBagLayout();
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
		this.cards = new JLabel("Played Card " + stringPlayedCard);
		Font font = new Font("Arial", Font.ITALIC, 15);
		this.cards.setFont(font);
		this.cards.setForeground(Color.black);
		addContent();
		
		setVisible(true);
	}
	
	public void init() {
		setLayout(null);
		setBounds(290, 180, 300, 300);
		setTitle("Probabilities");
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
		
		playedCards.setBounds(75, 10, 300, 50); 
		add(playedCards);
		playedCards.setText("Played cards : " + stringPlayedCard);
		
		canPut.setBounds(100, 50 ,200, 50);
		add(canPut);
		canPut.setText("Can put ? : " + proba.canPut());
		
		card1.setBounds(90,100,300,50);
		add(card1);
		card1.setText("Following risk : " + proba.getRemainCard());
		
		
	}
}
