package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RestartScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private GridBagLayout g = new GridBagLayout();
	private JLabel winner;
	
	public RestartScreen(String winner) {
		init();
		this.winner = new JLabel("Game over! " + winner + " win!");
		Font font = new Font("Arial", Font.BOLD, 25);
		this.winner.setFont(font);
		this.winner.setForeground(Color.black);
		addContent();
		setVisible(true);
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setTitle("Tu n'y peux rien !");
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(new JPanel());
		getContentPane().setBackground(Color.white);
		getContentPane().setLayout(g);
	}
	
	public void addContent() {
		JButton restart = new JButton("Restart");
		restart.setPreferredSize(new Dimension(100, 30));
		JButton quit = new JButton("Quit");
		quit.setPreferredSize(new Dimension(100, 30));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(20, 0, 15, 0);
		getContentPane().add(this.winner, gbc);
		
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		getContentPane().add(restart, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.EAST;
		getContentPane().add(quit, gbc);
	}
	
	public static void main(String[] args) {
		new RestartScreen("J1");
	}

}
