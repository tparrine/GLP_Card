package game.gui;

import game.card.TestMainCardDeck;
import game.mode.Testround;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOptions extends JFrame {
	
	private JPanel container = new JPanel();
	
	//ComboBoxes
	private JComboBox totalBox;
	private JComboBox humanBoxV1;
	private JComboBox humanBoxV2;
	private JComboBox humanBoxV3;
	
	//Buttons and text stuff
	private	JButton enterButton = new JButton("Enter");
	private JLabel playersLabel = new JLabel("Total players");
	private JLabel labelText = new JLabel("You can select up to 3 human players.");
	private JLabel humanLabel = new JLabel("Human players");
	private int value;
//	private JTextField jtf2 = new JTextField("");
	
	public GameOptions() {
		this.setTitle("Game Options");
		this.setSize(350, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setLayout(null);
		this.add(container);

		Object[] elements = new Object[] {"2 joueurs", "3 joueurs", "4 joueurs", "5 joueurs"};
		totalBox = new JComboBox(elements);
		
		Object[] elements2 = new Object[] {"1 joueur", "2 joueurs", "3 joueurs", "4 joueurs", "5 joueurs"};
		humanBoxV1 = new JComboBox(elements2);
		
		
		playersLabel.setBounds(60, 0, 100, 30);
		totalBox.setBounds(160, 5, 100, 25);
		labelText.setBounds(60, 30, 300, 30);
		humanLabel.setBounds(50, 60, 150, 30);
		humanBoxV1.setBounds(160, 65, 100, 25);
		
	    container.add(playersLabel);
		container.add(totalBox);
		container.add(humanLabel);
		container.add(labelText);
		container.add(humanBoxV1);

		
	    enterButton.setBounds(120, 250, 100, 30);
	    container.add(enterButton);
	    
	    totalBox.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JComboBox box = (JComboBox)e.getSource();
				String msg = (String)box.getSelectedItem();
				switch (msg) { 
				case "2 joueurs":
					labelText.setText("You can select up to 2 player.");
					humanBoxV1.removeAllItems();
					humanBoxV1.addItem("1 joueur");
					humanBoxV1.addItem("2 joueur");
					value = 2;
					break;
				case "3 joueurs":
					labelText.setText("You can select up to 3 players.");
					humanBoxV1.removeAllItems();
					humanBoxV1.addItem("1 joueur");
					humanBoxV1.addItem("2 joueurs");
					humanBoxV1.addItem("3 joueurs");
					value = 3;
					break;
				case "4 joueurs":
					labelText.setText("You can select up to 4 players.");
					humanBoxV1.removeAllItems();
					humanBoxV1.addItem("1 joueur");
					humanBoxV1.addItem("2 joueurs");
					humanBoxV1.addItem("3 joueurs");
					humanBoxV1.addItem("4 joueurs");
					value = 4;
					break;
				case "5 joueurs":
					labelText.setText("You can select up to 5 players.");
					humanBoxV1.removeAllItems();
					humanBoxV1.addItem("1 joueur");
					humanBoxV1.addItem("2 joueurs");
					humanBoxV1.addItem("3 joueurs");					
					humanBoxV1.addItem("4 joueurs");
					humanBoxV1.addItem("5 joueurs");
					value = 5;
					break;
				default:
					labelText.setText("Whoops, we seem to have an error.");
					break;
				}
			}
	    });
	    
	      
	    enterButton.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {
	    		new Testround(value);
	    	}
	    });
	    
		this.setVisible(true);
	}
}
