package game.card;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

	private EnumValue value;   //Value of the card (From 3 to Ace, then Joker and 2)
	private EnumColor color;  //Clubs - Diamonds - Hearts - Spades
	private String pathImage;
	
	public Card(EnumColor color, EnumValue value) { 
		this.color = color;
		this.value = value;	
		pathImage = "./resources/images/"+value+"_"+color+".png";
	}

	public EnumColor getColor() {
		return color;
	}

	public EnumValue getValue() {
		return value;
	}

	public BufferedImage getImage() throws IOException {
		File file = new File(pathImage);
		return ImageIO.read(file);
	}
}