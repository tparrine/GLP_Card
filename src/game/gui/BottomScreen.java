package game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class BottomScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton play = new JButton("PLAY");
	private JButton cantPlay = new JButton("'Je n'y peux rien !'");
	private JLabel state = new JLabel(); //Display when something isn't going right
	private String historyString = "";

	public BottomScreen() {
		setLayout(null);
		setBounds(0, 590, 850, 80);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		play.setBounds(310, 610, 75, 27);
		
		Font font = new Font("Consolas", Font.BOLD, 15);
		play.setFont(font);
		
		cantPlay.setBounds(420, 610, 135, 27);
		state.setBounds(360, 640, 250, 27);
		state.setForeground(Color.RED);
		
		add(play);
		add(cantPlay);
		add(state);
		
		
				
		play.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
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
							case 2:
								historyString = historyString + "\n" + "Game mode: triple";
								break;
							case 3:
								historyString = historyString + "\n" + "Simple set of three cards";
								break;
							case 4:
								historyString = historyString + "\n" + "Simple set of four cards";
								break;
							case 5://Serie de deux paires
								historyString = historyString + "\n" + "Two double set";
								break;
							case 6:
								historyString = historyString + "\n" + "Simple set of five cards";
								break;
							case 7: //Serie de trois paires
								historyString = historyString + "\n" + "Three double set";
								break;
							case 8:
								historyString = historyString + "\n" + "BOMB! You can't play!";
								break;
							default:
								break;
						}
						historyString = historyString + "\n" + "-----------------------------";
						GameBoardFrame.game.gameRound();
					}
				}
				else {
					GameBoardFrame.game.gameRound();
				}
			}
		});
		
		cantPlay.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				GameBoardFrame.game.resetPlayedCard();
				GameBoardFrame.game.cantplay();
	    	} 
	    });
		
	}
	public JButton getCantPlayButton() {
		return cantPlay;
	}
	
	public JLabel getStateLabel() {
		return state;
	}
	
	public void setTextPositionLong() {
		state.setBounds(323, 640, 250, 27);
	}
	
	public void setTextPositionMid() {
		state.setBounds(375, 640, 250, 27);
	}
	
	public void setTextPositionShort() {
		state.setBounds(360, 640, 250, 27);
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