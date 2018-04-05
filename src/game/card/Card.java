package game.card;

import javax.swing.ImageIcon;
import game.gui.ButtonLabel;
import game.player.Hand;

public class Card implements Comparable<Card> {

	private EnumValue value;   //Value of the card (From 3 to Ace, then Joker and 2)
	private EnumColor color;  //Clubs - Diamonds - Hearts - Spades
	private String pathImage;
	private ImageIcon image;
	private ButtonLabel label;
	
	public Card(EnumColor color, EnumValue value){ 
		this.color = color;
		this.value = value;	
		pathImage = "./resources/images/"+value.getEnumValue()+"_"+color.getEnumValue()+".png";
	}

	public EnumColor getColor() {
		return color;
	}

	public EnumValue getValue() {
		return value;
	}

	public ButtonLabel getImage() {
		image = new ImageIcon(pathImage);
		label = new ButtonLabel(image, value, color);
		label.setSize(100, 150);
		return label;
	}
	
	@Override
	public String toString() {
		return "Card [value=" + value + ", color=" + color + "]";
	}

	@Override
	public int compareTo(Card c) {
		return (this.value.getEnumValue() - c.value.getEnumValue() );
	}
	
}