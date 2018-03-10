package game.card;

public enum EnumValue {
THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14), JOKER(17), TWO(20);
	private int value;
	
	EnumValue (int value) { 
		this.value = value;
	}
	
	public int getEnumValue() {
		return value;
	}
}
