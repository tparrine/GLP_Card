package game.card;

public class CardDeck {
	private Card[] deck;
	private static final int TOTAL_CARDS= 54;
	
	public CardDeck(){
		deck = new Card[TOTAL_CARDS];
	}
	
	public void InitPackage() { //Sets the Values and Types for each card in the deck
		int cardNumber = 0;
		for (EnumColor color : EnumColor.values()) {
			for(EnumValue value : EnumValue.values()) {
				if (color.getEnumValue()!=4 && value.getEnumValue()!=17) { //Exclude Joker
					deck[cardNumber] = new Card(color, value);
					cardNumber ++;
				}
			}
			
		}
		//Initializes Jokers
		deck[52] = new Card(EnumColor.JOKER,EnumValue.JOKER);
		deck[53] = new Card(EnumColor.JOKER,EnumValue.JOKER);
		
		
	}

	public Card getCard(int cardNumber) {
		return deck[cardNumber];
	}
	
	public EnumValue getCardValue(Card card) {
		return card.getValue();
	}
	
	public EnumColor getCardColor(Card card) {
		return card.getColor();
	}
	
}
