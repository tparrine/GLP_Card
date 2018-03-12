package game.gui;

import game.mode.CreatePlayers;
import game.player.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOptions extends JFrame {
	
	private JPanel container = new JPanel();
	
	//ComboBoxes
	private JComboBox totalBox;
	private JComboBox humanBox;
	
	//Buttons and text stuff
	private	JButton enterButton = new JButton("Enter");
	private JLabel playersLabel = new JLabel("Total players");
	private JLabel labelText = new JLabel("You can select up to 3 human players.");
	private JLabel humanLabel = new JLabel("Human players");
	private int tPlayers;
	private int hPlayers;
	private ArrayList<Player> arrayPlayers;
	
	public GameOptions() {
		this.setTitle("Game Options");
		this.setSize(350, 350);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setLayout(null);
		this.add(container);

		Object[] elements = new Object[] {"", "2 joueurs", "3 joueurs", "4 joueurs", "5 joueurs"};
		totalBox = new JComboBox(elements);
		
		Object[] elements2 = new Object[] {"", "1 joueur"};
		humanBox = new JComboBox(elements2);
		
		
		playersLabel.setBounds(60, 0, 100, 30);
		totalBox.setBounds(160, 5, 100, 25);
		labelText.setBounds(60, 30, 300, 30);
		humanLabel.setBounds(50, 60, 150, 30);
		humanBox.setBounds(160, 65, 100, 25);
		
	    container.add(playersLabel);
		container.add(totalBox);
		container.add(humanLabel);
		container.add(labelText);
		container.add(humanBox);

		
	    enterButton.setBounds(120, 250, 100, 30);
	    container.add(enterButton);
	    
	    totalBox.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JComboBox box = (JComboBox)e.getSource();
				String msg = (String)box.getSelectedItem();
				JComboBox otherBox = (JComboBox)e.getSource();
				String otherMsg = (String)otherBox.getSelectedItem();
				switch (msg) { 
					case "":
						labelText.setText("Euuh, nique ta mère.");
						break;
					case "2 joueurs":
						labelText.setText("You can select up to 2 player.");
						humanBox.removeAllItems();
						humanBox.addItem("1 joueur");
						humanBox.addItem("2 joueurs");
						switch (otherMsg) { 
							case "1 joueur":
								hPlayers = 1;
								break;
							case "2 joueurs":
								hPlayers = 2;
								break;
							default:
								break;
						}
						tPlayers = 2;
						break;
					case "3 joueurs":
						labelText.setText("You can select up to 3 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 joueur");
						humanBox.addItem("2 joueurs");
						humanBox.addItem("3 joueurs");
						switch (otherMsg) { 
							case "1 joueur":
								hPlayers = 1;
								break;
							case "2 joueurs":
								hPlayers = 2;
								break;
							case "3 joueurs":
								hPlayers = 3;
								break;
							default:
								break;
						}
						tPlayers = 3;
						break;
					case "4 joueurs":
						labelText.setText("You can select up to 4 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 joueur");
						humanBox.addItem("2 joueurs");
						humanBox.addItem("3 joueurs");
						humanBox.addItem("4 joueurs");
						switch (otherMsg) { 
							case "1 joueur":
								hPlayers = 1;
								break;
							case "2 joueurs":
								hPlayers = 2;
								break;
							case "3 joueurs":
								hPlayers = 3;
								break;
							case "4 joueurs":
								hPlayers = 4;
								break;
							default:
								break;
						}
						tPlayers = 4;
						break;
					case "5 joueurs":
						labelText.setText("You can select up to 5 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 joueur");
						humanBox.addItem("2 joueurs");
						humanBox.addItem("3 joueurs");					
						humanBox.addItem("4 joueurs");
						humanBox.addItem("5 joueurs");
						switch (otherMsg) { 
							case "1 joueur":
								hPlayers = 1;
								break;
							case "2 joueurs":
								hPlayers = 2;
								break;
							case "3 joueurs":
								hPlayers = 3;
								break;
							case "4 joueurs":
								hPlayers = 4;
								break;
							case "5 joueurs":
								hPlayers = 5;
								break;
							default:
								break;
						}
						tPlayers = 5;
						break;
					default:
						labelText.setText("Whoops, we seem to have an error.");
						break;
				}	
			}
	    });
	    	    
	    enterButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
	    		CreatePlayers players = new CreatePlayers(tPlayers, hPlayers);
	    		arrayPlayers = new ArrayList<>();
	    		arrayPlayers = players.newPlayer();
	    		new GameBoardFrame();
	    	}
	    });
	    
		this.setVisible(true);
	}
}