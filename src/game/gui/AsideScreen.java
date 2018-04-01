package game.gui;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class AsideScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel score = new JLabel("Score: ");
	private JLabel scorePlayer = new JLabel("Score");
	private JLabel player = new JLabel("Player: ");
	private JLabel currentPlayer = new JLabel();
	private JLabel hist = new JLabel("History:");
	private JTextArea history = new JTextArea(6,18);
	private JScrollPane scroll = new JScrollPane(history);

	public AsideScreen() {
		setBounds(864, 0, 230, 670);
//		setBackground(Color.white);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		setLayout(new GridBagLayout());
		
		JPanel first = new JPanel(new FlowLayout());
		JPanel second = new JPanel(new FlowLayout());
		JPanel third = new JPanel(new FlowLayout());  //Use this panel to display an other information
		
//		Color w = Color.white;
		
		scroll.setPreferredSize(new Dimension(180, 300));
		
		history.setEditable(false);
		
//		first.setBackground(w);
		first.add(player);
		first.add(currentPlayer);
		
//		second.setBackground(w);
		second.add(score);
		second.add(scorePlayer);
		
//		third.setBackground(w);
		third.add(scroll);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(first, gbc);
		
		gbc.gridy = 1;
		gbc.insets = new Insets(6, 0, 10, 0);
		add(second, gbc);
		
		gbc.gridy = 2;
		add(hist);
		
		gbc.gridy = 3;
		add(scroll, gbc);
		
	}
	
	public JLabel getCurrentPlayerLabel() {
		return currentPlayer;
	}
	
	public JTextArea getHistory() {
		return history;
	}
}
