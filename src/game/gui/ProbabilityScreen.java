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

public class ProbabilityScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private GridBagLayout g = new GridBagLayout();
	private JLabel cards;
	private String stringPlayedCard = "";
	private ArrayList<Card> playedCard = new ArrayList<>();
	private Probability proba;
	
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
		setSize(400, 400);
		setTitle("Probability");
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(new JPanel());
		getContentPane().setLayout(g);
	}
	
	public void getPlayedCard() {
		for(int index = 0; index <= playedCard.size() -1; index ++ ) {
			stringPlayedCard += " - ";
			stringPlayedCard += playedCard.get(index).getValue();
		}
	}
	
	public void addContent() {
		proba = GameBoardFrame.game.getProba();
		
	
		JLabel canPut = new JLabel();
		JLabel score1 = new JLabel();
		JLabel score2 = new JLabel();
		JLabel score3 = new JLabel();
		JLabel score4 = new JLabel();
		JLabel score5 = new JLabel();
		
		canPut.setText("Can put ? : " + proba.canPut());
		score1.setText("Remain card who can follow " + playedCard.get(0).getValue() + " : " + proba.getRemainCard());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(-30, 0, 10, 0);
		getContentPane().add(this.cards, gbc);
		
		gbc.gridy = 1;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(canPut, gbc);
		
		gbc.gridy = 2;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score1, gbc);
		
		gbc.gridy = 3;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score2, gbc);
		
		gbc.gridy = 4;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score3, gbc);
		
		gbc.gridy = 5;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score4, gbc);
		
		gbc.gridy = 6;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score5, gbc);

		
	}
}
