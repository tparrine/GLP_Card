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
	private JButton play = new JButton("PLAY");
	private JButton cantPlay = new JButton("'Je n'y peux rien !'");
	private JButton probs = new JButton("Probabilities");
	private JLabel state = new JLabel(); //Display when something isn't going right
	private String historyString = "";

	public BottomScreen(boolean pedagogic) {
		setLayout(null);
		setBounds(0, 590, 850, 80);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		play.setBounds(310, 610, 75, 27);
		cantPlay.setBounds(420, 610, 135, 27);
		probs.setBounds(10, 635, 110, 27);
		state.setBounds(355, 650, 250, 27);
		state.setForeground(Color.RED);
		
		add(play);
		add(cantPlay);
		add(state);
		add(probs);
		
		if(!pedagogic) {
			probs.setVisible(false);
		}
				
		play.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if(GameBoardFrame.game.getRound().isFirstRound()) {
					int mode = 666;
					mode = GameBoardFrame.game.detectGameMode();
					if(mode != 666) {
						historyString += GameBoardFrame.game.getTestHistory();
						historyString = historyString + "\n" + "-----------------------------";
						GameBoardFrame.game.gameRound();
					}
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
