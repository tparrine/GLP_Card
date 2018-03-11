package game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CenterScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
//	private BufferedImage bg;
	private BufferedImage image;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage image4;
	private BufferedImage image5;
	
	public CenterScreen() {
		setBackground(new Color(0, 128, 0));
		setBounds(0, 0, 864, 590);
		
		try {
//			bg = ImageIO.read(new File("C:\\Users\\HassanPC\\Desktop\\gf.jpg"));
			image = ImageIO.read(new File("./resources/images/14_1.png"));
			image2 = ImageIO.read(new File("./resources/images/5_2.png"));
			image3 = ImageIO.read(new File("./resources/images/9_3.png"));
			image4 = ImageIO.read(new File("./resources/images/12_0.png"));
			image5 = ImageIO.read(new File("./resources/images/cover.gif"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.drawImage(bg, 0, 0, 865, 1000, null);
		g.drawImage(image, 30, 30, null);
		g.drawImage(image2, 380, 30, null);
		g.drawImage(image3, 725, 30, null);
		g.drawImage(image4, 380, 410, null);
		g.drawImage(image5, 725, 410, null);
}

}
