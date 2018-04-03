package game.gui;

import javax.swing.JFrame;

import game.mode.Game;

public class GameBoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	CenterScreen cs;
	AsideScreen as;
	BottomScreen bs;
	static Game game;
	
	public GameBoardFrame(int tPlayers, int hPlayers) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setResizable(false);
		setTitle("The game");
		init();
		
		game = new Game(cs, as, bs, this, tPlayers, hPlayers);
		game.start();
		game.managePlayers(game.getStorePlayers().get(0));
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
	
	public static Game getGame() {
		return game;
	}
}