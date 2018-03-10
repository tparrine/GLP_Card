package game.card;
	
	public enum EnumColor {
		
	HEARTH(0), SPADE(1), DIAMOND(2), CLUB(3), JOKER(4);
	private int color;
	
	EnumColor (int color) { 
		this.color = color;
	}
	
	public int getEnumValue() {
		return color;
	}
}
