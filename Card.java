package poker;

public class Card {
	private Suits suit;
	private CardValues cardValue;
	
	public Card (CardValues cardValue, Suits suit) {
		this.cardValue = cardValue;
		this.suit = suit;
	}

	public Card() {
		this.cardValue = null;
		this.suit = null;
	}

	public Suits getSuit() {
		return suit;
	}

	public void setSuit(Suits suit) {
		this.suit = suit;
	}

	public CardValues getCardValue() {
		return cardValue;
	}

	public void setCardValue(CardValues cardValue) {
		this.cardValue = cardValue;
	}

	@Override
	public String toString() {
		return cardValue + " of " + suit;
	}
	
	
	
}
