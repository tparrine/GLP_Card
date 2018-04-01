package game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class BottomScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton play = new JButton("Jouer");
	private JButton cantPlay = new JButton("Je n'y peux rien !");
	private int giveUpCount = 0;//Count "tu n'y peux rien"
	
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
					int mode = GameBoardFrame.game.detectGameMode(GameBoardFrame.game.getPlayedCard());
					if (mode != 666) {
						switch(mode){
							case 0:
								System.out.println("Mode de jeu: Simple");
								break;
							case 1:
								System.out.println("Mode de jeu: Double");
								break;
							case 2:
								System.out.println("Mode de jeu: Set of two cards");
								break;
							case 3:
								System.out.println("Mode de jeu: Triple");
								break;
							case 4:
								System.out.println("Mode de jeu: Set of three cards");
								break;
							case 5:
								System.out.println("Set of four cards");
								break;
							case 6:
								System.out.println("Set of five cards");
								break;
							default:
								break;
						}
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
				giveUpCount++;
				if (giveUpCount % GameBoardFrame.game.getStorePlayers().size() == 0) {
					giveUpCount = 0;
				}
				System.out.println("Nom des cartes cliquées :");
				for(int index=0; index<GameBoardFrame.game.getPlayedCard().size(); index++) {
					System.out.println(GameBoardFrame.game.getPlayedCard().get(index).getValue());
				}
				GameBoardFrame.game.gameRound(giveUpCount);
	    	}
	    });
	}
}
