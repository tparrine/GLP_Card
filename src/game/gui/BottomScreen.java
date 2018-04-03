package game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class BottomScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton play = new JButton("Play");
	private JButton cantPlay = new JButton("'Je n'y peux rien !'");
	private JLabel state = new JLabel(); //Display when something isn't going right
	private int giveUpCount = 0; //Count "tu n'y peux rien"
	private String historyString = "";
	
	public BottomScreen() {
		setLayout(null);
		setBounds(0, 590, 850, 80);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		play.setBounds(390, 595, 75, 27);
		cantPlay.setBounds(343, 630, 175, 27);
		state.setBounds(355, 650, 250, 27);
		state.setForeground(Color.RED);
		
		add(play);
		add(cantPlay);
		add(state);
				
		play.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
//				giveUpCount = 0;
				if(GameBoardFrame.game.getRound().isFirstRound()) {	
					int mode = GameBoardFrame.game.detectGameMode();
					if (mode != 666) {
						switch(mode) {
							case 0:
								historyString = historyString + "\n" + "Game mode: simple";
								break;
							case 1:
								historyString = historyString + "\n" + "Game mode: double";
								break;
							case 3:
								historyString = historyString + "\n" + "Game mode: triple";
								break;
							case 4:
								historyString = historyString + "\n" + "Simple set of three cards";
								break;
							case 5:
								historyString = historyString + "\n" + "Simple set of four cards";
								break;
							case 6:
								historyString = historyString + "\n" + "Simple set of five cards";
								break;
							case 7: //Serie de deux doublons
								historyString = historyString + "\n" + "Two double set";
								break;
							case 8://Serie de trois doublons
								historyString = historyString + "\n" + "Three double set";
								break;
							default:
								break;
						}
						historyString = historyString + "\n" + "-----------------------------";
						GameBoardFrame.game.gameRound(giveUpCount);
					}
				}
				else {
					GameBoardFrame.game.gameRound(giveUpCount);
				}
			}
		});
		
		cantPlay.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (giveUpCount % GameBoardFrame.game.getStorePlayers().size() == 0) { //If "tu n'y peux rien" clicked during the whole round
					giveUpCount = 0;
				}
				giveUpCount++;
				GameBoardFrame.game.resetPlayedCard();
				GameBoardFrame.game.gameRound(giveUpCount);
	    	}
	    });
	}
	public JButton getCantPlayButton() {
		return cantPlay;
	}
	
	public JLabel getStateLabel() {
		return state;
	}
	
	public String getHistoryString() {
		return historyString;
	}
	
	public void writeHistory() {
		for(int index=0; index<GameBoardFrame.game.getPlayedCard().size(); index++) {
			historyString = historyString + "\n" + (GameBoardFrame.game.getPlayedCard().get(index).getValue());
		}
		historyString = historyString + "\n" + "-----------------------------";
	}
	
	public void writeBomb() {//Displays bomb on history
		historyString = historyString + "\n" + "BOMB!";
	}
}
