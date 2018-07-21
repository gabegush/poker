package poker;

import java.util.Comparator;

public class SortRule implements Comparator<Player>{
	static int value = 0;
	static int value2 = 0;

	public int compare(Player r1, Player r2) {
		
		for(Ruler rule: Ruler.values()) {
			if(Ruler.valueOf(rule.toString()).toString().equals(r1.getHighestRule())) {
				value = Ruler.valueOf(rule.toString()).ordinal();
				for(Ruler rule2: Ruler.values()) {
					if(Ruler.valueOf(rule2.toString()).toString().equals(r2.getHighestRule())) {
						value2 = Ruler.valueOf(rule2.toString()).ordinal();
					}
				}
			}
		}
		if(value>value2) {
			return 1;
		} else if(value<value2) {
			return -1;
		} else if(value==value2) {
			if(r1.getHighCard() > r2.getHighCard()) {
				return 1;
			} else if(r1.getHighCard() < r2.getHighCard()) {
				return -1;
			} else if(r1.getHighCard() == r2.getHighCard()) {
				if(r1.getKicker() > r2.getKicker()) {
					return 1;
				} else if(r1.getKicker() < r2.getKicker()) {
					return -1;
				}
			}
		}

		return 0;
	}
}