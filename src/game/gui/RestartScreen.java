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
import game.player.Player;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RestartScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private GridBagLayout g = new GridBagLayout();
	private JLabel winner;
	
	public RestartScreen(String winner, ArrayList<Player> storePlayers, int winnerScore, ArrayList<Integer> test, Player winnerPlayer) {
		init();
		this.winner = new JLabel("Game over! " + winner + " won!");
		Font font = new Font("Arial", Font.BOLD, 25);
		this.winner.setFont(font);
		this.winner.setForeground(Color.black);
		addContent(storePlayers, winnerScore, test, winnerPlayer);
		setVisible(true);
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setTitle("Congratulations!");
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(new JPanel());
		getContentPane().setLayout(g);
	}
	
	public void addContent(ArrayList<Player> storePlayers, int winnerScore, ArrayList<Integer> test, Player winnerPlayer) {
		JButton restart = new JButton("Restart");
		restart.setPreferredSize(new Dimension(100, 30));
		JButton quit = new JButton("Quit");
		quit.setPreferredSize(new Dimension(100, 30));
		JLabel score1 = new JLabel();
		JLabel score2 = new JLabel();
		JLabel score3 = new JLabel();
		JLabel score4 = new JLabel();
		JLabel score5 = new JLabel();
		
		switch (storePlayers.size()) {
			case 2:
				if (storePlayers.get(0) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +winnerScore);
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(0));
				}
				else {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +winnerScore);
				}
				break;
			case 3:
				if (storePlayers.get(0) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +winnerScore);
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(0));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(1));
				}
				else if (storePlayers.get(1) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +winnerScore);
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(1));
				}
				else if (storePlayers.get(2) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(1));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +winnerScore);
				}
				break;
			case 4:
				if (storePlayers.get(0) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +winnerScore);
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(0));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(1));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(2));
				}
				else if (storePlayers.get(1) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +winnerScore);
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(1));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(2));
				}
				else if (storePlayers.get(2) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(1));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +winnerScore);
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(2));
				}
				else if (storePlayers.get(3) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(1));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(2));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +winnerScore);
				}
				break;
			case 5:
				if (storePlayers.get(0) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +winnerScore);
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(0));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(1));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(2));
					score5.setText("Score " +storePlayers.get(4).getName() + " : " +test.get(3));
				}
				else if (storePlayers.get(1) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +winnerScore);
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(1));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(2));
					score5.setText("Score " +storePlayers.get(4).getName() + " : " +test.get(3));
				}
				else if (storePlayers.get(2) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(1));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +winnerScore);
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(2));
					score5.setText("Score " +storePlayers.get(4).getName() + " : " +test.get(3));
				}
				else if (storePlayers.get(3) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(1));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(2));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +winnerScore);
					score5.setText("Score " +storePlayers.get(4).getName() + " : " +test.get(3));
				}
				else if (storePlayers.get(4) == winnerPlayer) {
					score1.setText("Score " +storePlayers.get(0).getName() + " : " +test.get(0));
					score2.setText("Score " +storePlayers.get(1).getName() + " : " +test.get(1));
					score3.setText("Score " +storePlayers.get(2).getName() + " : " +test.get(2));
					score4.setText("Score " +storePlayers.get(3).getName() + " : " +test.get(3));
					score5.setText("Score " +storePlayers.get(4).getName() + " : " +winnerScore);
				}
				break;
			default:
				break;
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(-30, 0, 10, 0);
		getContentPane().add(this.winner, gbc);
		
		gbc.gridy = 1;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score1, gbc);
		
		gbc.gridy = 2;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score2, gbc);
		
		gbc.gridy = 3;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score3, gbc);
		
		gbc.gridy = 4;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score4, gbc);
		
		gbc.gridy = 5;
		gbc.insets = new Insets(30, 0, 0, 0);
		getContentPane().add(score5, gbc);
		
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		getContentPane().add(restart, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.EAST;
		getContentPane().add(quit, gbc);
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				setVisible(false);
				new GameMenu();
	    	}
	    });
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
	    	}
	    });
	}
//	
//	public static void main(String[] args) {
//		new RestartScreen("J1", GameBoardFrame.getGame().getStorePlayers());
//	}
}
