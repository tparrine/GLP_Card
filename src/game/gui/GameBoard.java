package game.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class GameBoard extends JPanel{

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JPanel center = new JPanel();
	private JPanel bottom = new JPanel();
	private JPanel aside = new JPanel();
	private JButton jouer = new JButton("Jouer");
	private JLabel score = new JLabel("Score :");
	

	/**
	 * Create the application.
	 */
	public GameBoard() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setSize(1100, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		center.setLayout(null);
		center.setBackground(new Color(0, 128, 0));
				
		bottom.setBounds(0, 590, 862, 82);
//		bottom.setBounds(x, y, width, height);
		bottom.setBorder(new BevelBorder(BevelBorder.RAISED));
		frame.add(bottom);
		
		bottom.add(jouer);
		
		aside.setBounds(860, 0, 260, 700);
		aside.setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		frame.add(aside);
		
		aside.add(score);
		
		Icon handCard = new ImageIcon("./resources/images/14_1.png");
		JLabel hand = new JLabel(handCard);
		hand.setBounds(80, 55, 100, 150);
		center.add(hand);
		
        Icon handCard2 = new ImageIcon("./resources/images/5_2.png");
        ButtonLabel label = new ButtonLabel(handCard2);
        label.setBounds(420, 55, 103, 150);
		center.add(label);
		//
        Icon handCard3 = new ImageIcon("./resources/images/9_3.png");
        ButtonLabel label2 = new ButtonLabel(handCard3);
        label2.setBounds(760, 55, 100, 150);
		center.add(label2);
		
		JLabel label3 = new JLabel("");
		label3.setBounds(420, 395, 100, 150);
        Icon handCard4 = new ImageIcon("./resources/images/12_0.png");
        label3.setIcon(handCard4);
		center.add(label3);
		
		JLabel deck = new JLabel("");
		deck.setBounds(760, 395, 100, 150);
        Icon cover = new ImageIcon("./resources/images/cover.gif");
        deck.setIcon(cover);
		center.add(deck);
		
		frame.add(center);
		frame.setVisible(true);
	}
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				GameBoard window = new GameBoard();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
}