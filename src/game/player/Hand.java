package game.player;

import java.util.ArrayList;

import game.card.Card;

public class Hand {
	private ArrayList<Card> hand;
	private ArrayList<Card> selectedCard;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	//Removes all the cards from the hand
	public void clear() {
		hand.clear();
	}
	
	//Adds a card to the hand
	public void add(Card card) {
		hand.add(card);
	}
	
	//Returns the number of cards in the hand
	public int getSizeHand() {
		return hand.size();
	}
	
	//Checks if the hand is empty or not
	public boolean isEmpty() {
		return hand.isEmpty();
	}
	
	public Card getCardHand(int index) {
		return hand.get(index);
	}
	
	public void removeCard(int index) {
		hand.remove(index);
	}
	
	//Add card to the ArrayList of card the player has selected
	public void addSelectCard(Card card) {
		selectedCard.add(card);
	}
	
	public void removeSelectCard(Card card) {
		selectedCard.remove(card);
	}
	
	public void clearCards() {
		selectedCard.clear();
	}
}