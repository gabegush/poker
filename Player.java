package poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name = "";
	int stack = 100000;
	Card card1 = null;
	Card card2 = null;
	int highCard = 0;
	int kicker = 0;
	List<Card> cardSet = null;
	String highestRule = null;
	
	public Player() {
		this.name = null;
		this.stack = 0;
		this.card1 = null;
		this.card2 = null;
		this.highCard = 0;
		this.kicker = 0;
		this.cardSet = null;
		this.highestRule = null;
	}
	
	public Player(int stack) {
		this.name = null;
		this.stack = stack;
		this.card1 = null;
		this.card2 = null;
		this.highCard = 0;
		this.kicker = 0;
		this.cardSet = null;
		this.highestRule = null;
	}
	
	

	public Player(String name, int stack, Card card1, Card card2, int highCard, int kicker, ArrayList<Card> cardSet, String highestRule) {
		this.name = name;
		this.stack = stack;
		this.card1 = card1;
		this.card2 = card2;
		this.highCard = highCard;
		this.kicker = kicker;
		this.cardSet = cardSet;
		this.highestRule = highestRule;
	}

	@Override
	public String toString() {
		return name + ": [stack=" + stack + ", card1=" + card1 + ", card2=" + card2 + ", highCard="
				+ highCard + ", kicker=" + kicker + ", cardSet=" + cardSet + ", highestRule=" + highestRule + "]";
	}

	public int getKicker() {
		return kicker;
	}

	public void setKicker(int kicker) {
		this.kicker = kicker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public int getHighCard() {
		return highCard;
	}

	public void setHighCard(int highCard) {
		this.highCard = highCard;
	}


	public List<Card> getCardSet() {
		return cardSet;
	}

	public void setCardSet(ArrayList<Card> cardSet) {
		this.cardSet = cardSet;
	}
	
	public String getHighestRule() {
		return highestRule;
	}

	public void setHighestRule(String highestRule) {
		this.highestRule = highestRule;
	}

	public Card getCard1() {
		return card1;
	}
	
	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public Card getCard2() {
		return card2;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}
}
