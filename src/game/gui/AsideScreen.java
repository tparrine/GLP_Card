package game.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class AsideScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	private GridLayout g = new GridLayout(10, 1);
	private JLabel score = new JLabel("Score :");
	private JLabel scorePlayer = new JLabel("Score");
	private JLabel player = new JLabel("Player :");              //
	private JLabel currentPlayer = new JLabel("Current Player"); //Display the current player

	public AsideScreen() {
		setBounds(864, 0, 230, 670);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		setLayout(g);
		
		JPanel first = new JPanel(new FlowLayout());
		JPanel second = new JPanel(new FlowLayout());
		//JPanel third = new JPanel(new FlowLayout());  //Use this panel to display an other information
		
		first.add(score);
		first.add(scorePlayer);
		add(first);
		
		second.add(player);
		second.add(currentPlayer);
		add(second);
		
	}
}
