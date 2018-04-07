package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class AsideScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel currentPlayer = new JLabel();
	private JTextArea history = new JTextArea(6,18);
	private JScrollPane scroll = new JScrollPane(history);
	private JButton probs = new JButton("Probabilities");

	public AsideScreen(boolean pedagogic) {
		setBounds(864, 0, 230, 670);
		setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		setLayout(new GridBagLayout());
		
		JPanel first = new JPanel(new FlowLayout());
		JPanel second = new JPanel(new FlowLayout());
		JPanel third = new JPanel(new FlowLayout());
		
		JLabel player = new JLabel("Player: ");
		JLabel hist = new JLabel("History:");

		Font font = new Font("Consolas", Font.BOLD, 23);
		Font font1 = new Font("Deja Vu Sans Mono", Font.BOLD, 15);
		player.setFont(font);
		currentPlayer.setFont(font);
		currentPlayer.setForeground(Color.RED);
		hist.setFont(font1);
				
		scroll.setPreferredSize(new Dimension(180, 300));	
		history.setEditable(false);
		
		probs.setBounds(10, 635, 110, 27);
		
		if(!pedagogic) {
			probs.setVisible(false);
		}
		
		first.add(player);
		first.add(currentPlayer);
				
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
		
		gbc.gridy = 4;
		add(probs, gbc);
		
		probs.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				GameBoardFrame.game.probability();
	    	}
	    });
	}
	
	public JLabel getCurrentPlayerLabel() {
		return currentPlayer;
	}
	
	public JTextArea getHistory() {
		return history;
	}
}
