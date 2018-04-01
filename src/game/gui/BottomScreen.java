package game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class BottomScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton play = new JButton("Play");
	private JButton cantPlay = new JButton("'Je n'y peux rien !'");
	private int giveUpCount = 0; //Count "tu n'y peux rien"
	private String historyString = "";
	
	public BottomScreen() {
		setLayout(null);
		setBounds(0, 590, 850, 80);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		play.setBounds(390, 595, 75, 27);
		cantPlay.setBounds(343, 630, 175, 27);
		add(play);
		add(cantPlay);
				
		play.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				giveUpCount = 0;
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
								historyString = historyString + "\n" + "Set of two cards";
								break;
							case 3:
								historyString = historyString + "\n" + "Game mode: triple";
								break;
							case 4:
								historyString = historyString + "\n" + "Set of three cards";
								break;
							case 5:
								historyString = historyString + "\n" + "Set of four cards";
								break;
							case 6:
								historyString = historyString + "\n" + "Set of five cards";
								break;
							default:
								break;
						}
						historyString = historyString + "\n" + "-----------------------------";
						for(int index=0; index<GameBoardFrame.game.getPlayedCard().size(); index++) {
							historyString = historyString + "\n" + (GameBoardFrame.game.getPlayedCard().get(index).getValue());
						}
						historyString = historyString + "\n" + "----------";
						GameBoardFrame.game.gameRound(giveUpCount);
					}
				}
				else {
					historyString = historyString + "\n" + "-----------------------------";
					for(int index=0; index<GameBoardFrame.game.getPlayedCard().size(); index++) {
						historyString = historyString + "\n" + (GameBoardFrame.game.getPlayedCard().get(index).getValue());
					}
					historyString = historyString + "\n" + "----------";
					GameBoardFrame.game.gameRound(giveUpCount);
				}
//				}
//				else {
//					System.out.println("Nom des cartes cliquées :");
//					for(int index=0; index<GameBoardFrame.game.getPlayedCard().size(); index++) {
//						System.out.println(GameBoardFrame.game.getPlayedCard().get(index).getValue());
//					}
//					GameBoardFrame.game.gameRound(giveUpCount);
//				}
//			}
			}});
		
		cantPlay.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (giveUpCount % GameBoardFrame.game.getStorePlayers().size() == 0) { //If "tu n'y peux rien" clicked during the whole round
					giveUpCount = 0;
				}
				giveUpCount++;
				
				GameBoardFrame.game.gameRound(giveUpCount);
	    	}
	    });
	}
	public JButton getCantPlayButton() {
		return cantPlay;
	}
	
	public String getHistoryString() {
		return historyString;
	}
}
