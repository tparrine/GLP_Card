package game.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
	
	//Added different functionalities
	private JMenuBar jmb = new JMenuBar();
	private JMenu jmFile = new JMenu("File");
	private	JMenu jmHelp = new JMenu("Help");
	private JMenuItem jmiExit = new JMenuItem("Exit");
	private JMenuItem jmiAbout = new JMenuItem("About");

	//Created container
	private	JPanel container = new JPanel();

	//Buttons created
	private	JButton playButton = new JButton("Play");
	private JButton helpButton = new JButton("Help");
	private JButton quitButton = new JButton("Quit");

	
	public Main() {
		this.setTitle("Game Menu");
		this.setSize(400, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setLayout(null);
	    this.add(container);
		
	    playButton.setBounds(115, 125, 150, 50);
	    container.add(playButton);
	    helpButton.setBounds(115, 225, 150, 50);
	    container.add(helpButton);
	    quitButton.setBounds(115, 325, 150, 50);
		container.add(quitButton);

		playButton.addActionListener(new PlayAction());
		helpButton.addActionListener(new HelpAction());
		quitButton.addActionListener(new QuitAction());

		
		jmb.add(jmFile);
		jmb.add(jmHelp);

		jmHelp.add(jmiAbout);
		jmFile.add(jmiExit);

		jmiExit.addActionListener(this);
		jmiAbout.addActionListener(this);
		
		
		setJMenuBar(jmb);
		
		this.setVisible(true);    
	}
	
	//Action listeners
	static class HelpAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if(Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)){
				try {
					File f = new File("./resources/readme.txt");
					java.awt.Desktop.getDesktop().open(f);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	static class QuitAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}


	static class PlayAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			new GameOptions();
		}
	}
	public static void main(String args[]) {
		new Main();
	}
}