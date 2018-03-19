package game.gui;

import game.mode.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOptions extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel container = new JPanel();
	
	//ComboBoxes
	private JComboBox<String> totalBox;
	private JComboBox<String> humanBox;
	
	//Buttons and text stuff
	private	JButton enterButton = new JButton("Enter");
	private JLabel playersLabel = new JLabel("Total players");
	private JLabel labelText = new JLabel("You can select up to 5 players.");
	private JLabel humanLabel = new JLabel("Human players");
	private int tPlayers;
	private int hPlayers;
	
	public GameOptions() {
		setTitle("Game Options");
		setSize(350, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		container.setLayout(null);
		add(container);

		String[] elements = new String[] {"", "2 players", "3 players", "4 players", "5 players"};
		totalBox = new JComboBox<String>(elements);
		
		String[] elements2 = new String[] {"", "1 player"};
		humanBox = new JComboBox<String>(elements2);
		
		
		playersLabel.setBounds(60, 0, 100, 30);
		totalBox.setBounds(160, 5, 100, 25);
		labelText.setBounds(75, 30, 300, 30);
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
						labelText.setText("Select a number of player.");
						break;
					case "2 players":
						labelText.setText("You can select up to 2 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 player");
						humanBox.addItem("2 players");
						switch (otherMsg) { 
							case "1 player":
								hPlayers = 1;
								break;
							case "2 players":
								hPlayers = 2;
								break;
							default:
								break;
						}
						tPlayers = 2;
						break;
					case "3 players":
						labelText.setText("You can select up to 3 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 player");
						humanBox.addItem("2 players");
						humanBox.addItem("3 players");
						switch (otherMsg) { 
							case "1 player":
								hPlayers = 1;
								break;
							case "2 players":
								hPlayers = 2;
								break;
							case "3 players":
								hPlayers = 3;
								break;
							default:
								break;
						}
						tPlayers = 3;
						break;
					case "4 players":
						labelText.setText("You can select up to 4 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 player");
						humanBox.addItem("2 players");
						humanBox.addItem("3 players");
						humanBox.addItem("4 players");
						switch (otherMsg) { 
							case "1 player":
								hPlayers = 1;
								break;
							case "2 players":
								hPlayers = 2;
								break;
							case "3 players":
								hPlayers = 3;
								break;
							case "4 players":
								hPlayers = 4;
								break;
							default:
								break;
						}
						tPlayers = 4;
						break;
					case "5 players":
						labelText.setText("You can select up to 5 players.");
						humanBox.removeAllItems();
						humanBox.addItem("1 player");
						humanBox.addItem("2 players");
						humanBox.addItem("3 players");					
						humanBox.addItem("4 players");
						humanBox.addItem("5 players");
						switch (otherMsg) { 
							case "1 player":
								hPlayers = 1;
								break;
							case "2 players":
								hPlayers = 2;
								break;
							case "3 players":
								hPlayers = 3;
								break;
							case "4 players":
								hPlayers = 4;
								break;
							case "5 players":
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
				setVisible(false);
	    		new GameBoardFrame(tPlayers, hPlayers);
	    	}
	    });
	    
		this.setVisible(true);
	}
	public int getTPlayers() {
		return tPlayers;
	}
}