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
				GameBoardFrame.getGame().tourJeu();
	    	}
	    });
		
	}
}
