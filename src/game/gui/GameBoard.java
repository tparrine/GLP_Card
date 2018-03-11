package game.gui;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class GameBoard extends JPanel{

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JPanel center = new JPanel();
	private JPanel bottom = new JPanel();
	private JPanel aside = new JPanel();
	private JButton play = new JButton("Jouer");
	private JLabel score = new JLabel("Score :");
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	public void initialize() {
		frame.setTitle("The game");
		frame.setSize(1100, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		center.setLayout(null);
		center.setBackground(new Color(0, 128, 0));
				
		bottom.setBounds(0, 590, 862, 82);
		bottom.setBorder(new BevelBorder(BevelBorder.RAISED));
		frame.add(bottom);
		
		bottom.add(play);
		
		aside.setBounds(860, 0, 260, 700);
		aside.setBorder((Border) new SoftBevelBorder(SoftBevelBorder.LOWERED));
		frame.add(aside);
		
		aside.add(score);
		
		Icon handCard = new ImageIcon("./resources/images/14_1.png");
		JLabel hand = new JLabel(handCard);
		hand.setBounds(30, 30, 100, 150);
		center.add(hand);
		
        Icon handCard2 = new ImageIcon("./resources/images/5_2.png");
        ButtonLabel label = new ButtonLabel(handCard2);
        label.setBounds(380, 30, 100, 150);
		center.add(label);
		//
        Icon handCard3 = new ImageIcon("./resources/images/9_3.png");
        ButtonLabel label2 = new ButtonLabel(handCard3);
        label2.setBounds(725, 30, 100, 150);
		center.add(label2);
		
		JLabel label3 = new JLabel("");
		label3.setBounds(380, 410, 100, 150);
        Icon handCard4 = new ImageIcon("./resources/images/12_0.png");
        label3.setIcon(handCard4);
		center.add(label3);
		
		JLabel deck = new JLabel("");
		deck.setBounds(725, 410, 100, 150);
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
    			try {
    				GameBoard window = new GameBoard();
    				window.initialize();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    }