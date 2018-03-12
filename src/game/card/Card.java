package game.card;

import javax.swing.ImageIcon;
import game.gui.ButtonLabel;

public class Card {

	private EnumValue value;   //Value of the card (From 3 to Ace, then Joker and 2)
	private EnumColor color;  //Clubs - Diamonds - Hearts - Spades
	private String pathImage;
	private ImageIcon image;
	private ButtonLabel label;
	
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

	public ButtonLabel getImage() {
		image = new ImageIcon(pathImage);
		label = new ButtonLabel(image);
		label.setSize(100, 150);
		return label;
	}
}