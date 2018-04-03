package game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	private JRadioButton p1, p2, p3, p4, p5;
	
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
		
		String[] elements2 = new String[] {""};
		//humanBox = new JComboBox<String>(elements2);
		 	p1 = new JRadioButton("1 player");
		    p1.setActionCommand("1 player");
		    p1.setSelected(true);

		    p2 = new JRadioButton("2 players");
		    p2.setActionCommand("2 players");

		    p3 = new JRadioButton("3 players");
		    p3.setActionCommand("3 players");

		    p4 = new JRadioButton("4 players");
		    p4.setActionCommand("4 players");

		    p5 = new JRadioButton("5 players");
		    p5.setActionCommand("5 players");

		    //Group the radio buttons.
		    ButtonGroup group = new ButtonGroup();
		    group.add(p1);
		    group.add(p2);
		    group.add(p3);
		    group.add(p4);
		    group.add(p5);
		
		
		playersLabel.setBounds(60, 0, 100, 30);
		totalBox.setBounds(160, 5, 100, 25);
		labelText.setBounds(75, 30, 300, 30);
		p1.setBounds(75,55,400,30);
		p2.setBounds(75,80,400,30);
		p3.setBounds(75,105,400,30);
		p4.setBounds(75,130,400,30);
		p5.setBounds(75,155,400,30);
		//humanLabel.setBounds(50, 60, 150, 30);
		//humanBox.setBounds(160, 65, 100, 25);
		    
		    
	    container.add(playersLabel);
		container.add(totalBox);
		container.add(humanLabel);
		container.add(labelText);
		container.add(p1);
		container.add(p2);
		container.add(p3);
		container.add(p4);
		container.add(p5);
		//container.add(humanBox);
		
	    enterButton.setBounds(120, 250, 100, 30);
	    container.add(enterButton);
	    
	    totalBox.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				p3.setEnabled(true);
				p4.setEnabled(true);
				p5.setEnabled(true);
				JComboBox Box = (JComboBox)e.getSource();
				String msg = (String)Box.getSelectedItem();
				switch (msg) { 
					case "":
						labelText.setText("Select a number of player.");
						break;
					case "2 players":
						tPlayers = 2;
						p3.setEnabled(false);
						p4.setEnabled(false);
						p5.setEnabled(false);
						break;
					case "3 players":
						tPlayers = 3;
						p4.setEnabled(false);
						p5.setEnabled(false);
						break;
					case "4 players":
						tPlayers = 4;
						p5.setEnabled(false);
						break;
					case "5 players":
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
				getSelectedButton();
	    		new GameBoardFrame(tPlayers, hPlayers);
	    	}
	    });
	    
		this.setVisible(true);
	}
	
	public void getSelectedButton() {
		if(p1.isSelected()) {
			hPlayers = 1;
		}
		else if(p2.isSelected()) {
			hPlayers = 2;
		}
		else if(p3.isSelected()) {
			hPlayers = 3;
		}
		else if(p4.isSelected()) {
			hPlayers = 4;
		}
		else if(p5.isSelected()) {
			hPlayers = 5;
		}
	}
	
	public int getTPlayers() {
		return tPlayers;
	}
}