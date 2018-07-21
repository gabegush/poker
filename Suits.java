package poker;

public enum Suits {
	CLUBS, HEARTS, DIAMONDS, SPADES;
}

enum Ruler {
	ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD;
	
	private String rule;
	
	private Ruler() {
	}
	
	private Ruler(String rule) {
		this.rule = rule;
	}

	public String getRule() {
		return rule;
	}
	

}
