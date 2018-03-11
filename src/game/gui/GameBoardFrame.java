package game.gui;

import javax.swing.JFrame;

public class GameBoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	CenterScreen cs;
	AsideScreen as;
	BottomScreen bs;
	
	public GameBoardFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setResizable(false);
		setTitle("The game");
		
		init();
	}
	
	public void init() {
		setLocationRelativeTo(null);
		
		as = new AsideScreen();
		add(as);
		
		cs = new CenterScreen();
		add(cs);
		
		bs = new BottomScreen();
		add(bs);

		setVisible(true);
	}
	
    /**
     * Launch the application.
     */
	
    public static void main(String[] args) {
    	new GameBoardFrame();
    }
}