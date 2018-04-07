package game.gui;

import javax.swing.JFrame;

import game.mode.Game;

public class GameBoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	CenterScreen cs;
	AsideScreen as;
	BottomScreen bs;
	static Game game;
	private boolean peda;
	
	public GameBoardFrame(int tPlayers, int hPlayers, boolean peda, boolean song) {
		this.peda = peda;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setResizable(false);
		setTitle("The game");
		init();
		
		game = new Game(cs, as, bs, this, tPlayers, hPlayers, peda); 
		game.start();
		if (song) {
			game.startMusic();
		}
		game.managePlayers();
	}
	
	public void init() {
		setLocationRelativeTo(null);
		
		as = new AsideScreen(peda);
		add(as);
		
		cs = new CenterScreen();
		add(cs);
		
		bs = new BottomScreen();
		add(bs);
		
		setVisible(true);
	}
	
	public static Game getGame() {
		return game;
	}
}