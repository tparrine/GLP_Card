package game.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	//Created container
	private	JPanel container = new JPanel();

	//Buttons created
	private	JButton playButton = new JButton("Play");
	private JButton helpButton = new JButton("Help");
	private JButton quitButton = new JButton("Quit");

	
	public GameMenu() {
		setTitle("Game Menu");
		setSize(300, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		container.setLayout(null);
		add(container);
		
	    playButton.setBounds(75, 50, 150, 50);
	    container.add(playButton);
	    helpButton.setBounds(75, 150, 150, 50);
	    container.add(helpButton);
	    quitButton.setBounds(75, 250, 150, 50);
		container.add(quitButton);

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				setVisible(false);
				new GameOptions();
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
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
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);    
	}
}