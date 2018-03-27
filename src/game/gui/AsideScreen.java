package game.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class AsideScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	private GridLayout g = new GridLayout(5, 1);
	private JLabel score = new JLabel("Score :");
	private JLabel scorePlayer = new JLabel("Score");
	private JLabel player = new JLabel("Player :");              //
	private JLabel currentPlayer = new JLabel("Current Player"); //Display the current player
	private JLabel hist = new JLabel("History :");
	private JTextArea history = new JTextArea("Test historique looooool\n bdvbidsbubgribfdiuv\n\n\negzftzev\n\n\nTest",6,18);
	private JScrollPane scroll = new JScrollPane(history);

	public AsideScreen() {
		setBounds(864, 0, 230, 670);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		setLayout(g);
		
		JPanel first = new JPanel(new FlowLayout());
		JPanel second = new JPanel(new FlowLayout());
		JPanel third = new JPanel(new FlowLayout());  //Use this panel to display an other information
		
		first.add(player);
		first.add(currentPlayer);
		add(first);
		
		second.add(score);
		second.add(scorePlayer);
		add(second);
		
		history.setEditable(false);
		third.add(hist);
		third.add(scroll);
		add(third);
	}
}
