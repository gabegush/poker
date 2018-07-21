package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Deck extends Card{
	private static List<Card> deck = new ArrayList<Card>();

    static {
		for (final Suits suit : Suits.values()) {
		    for (final CardValues rank : CardValues.values()) {
		    	Card tempCard = new Card(rank, suit);
		    	Deck.deck.add(tempCard);
		    }
		}
    }

    protected Deck(final CardValues rank, final Suits suit) {
    	super(rank, suit);
    }
 
    public static ArrayList<Card> newDeck() {
    	return new ArrayList<Card>(Deck.deck);
    }
}

