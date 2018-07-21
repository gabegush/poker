package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static ArrayList<Card> setStages = new ArrayList<Card>();
	static boolean isNotThree = false;
	static int pot = 0;

	public static void main(String[] args) {		
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> active = new ArrayList<Player>();
		final int numPlayers = 4;
		for(int i = 0; i < numPlayers; i++) {
			Player player = new Player(100000);
			players.add(player);
		}
		active.addAll(players);
		
		active.get(0).setName("Phil Ivey");
		active.get(1).setName("Dan Negreanu");
		active.get(2).setName("Gabe Argush");
		active.get(3).setName("Tom Dwan");
		
		// create shuffled deck
		ArrayList<Card> d = Deck.newDeck();
		Collections.shuffle(d);
		Collections.shuffle(d);
		Collections.shuffle(d);

		

		dealCards(d, active);
		
		flop(d);
		
		turn(d);
		
		river(d);
		
		
		
		for(Player player: active) {
			ArrayList<Card> set = new ArrayList<Card>();
			set.addAll(setStages);
			set.add(player.getCard1());
			set.add(player.getCard2());
			player.setCardSet(set);
			player.setStack(player.getStack() - 1000);
			pot += 1000;
			System.out.println(player.getCardSet());
		}
		
		if(active.size() > 1) {
			pairs(active);
			bet(players, active);
			System.out.println("Pot: " + pot);

			winner(active);
	
		} else {
			System.out.println(active.get(0).getName() + " wins!");
			active.get(0).setStack(active.get(0).getStack() + pot);
		}
		pot = 0;
		for(Player player: players) {
			System.out.println(player.getName() + " " + player.getStack());

		}
		//System.out.println(players);
	}
	
	public static void dealCards(ArrayList<Card> d, ArrayList<Player> active) {
		for(Player player: active) {
			player.setCard1(d.get(0));
			d.remove(0);
			player.setCard2(d.get(0));
			d.remove(0);
			
			if((player.getCard1().getCardValue().ordinal()) >= (player.getCard2().getCardValue().ordinal())){
				player.setHighCard(player.getCard1().getCardValue().ordinal() + 2);
				player.setKicker(player.getCard2().getCardValue().ordinal() + 2);
			} else {
				player.setHighCard(player.getCard2().getCardValue().ordinal() + 2);
				player.setKicker(player.getCard1().getCardValue().ordinal() + 2);
			}
		}

	}
	public static void flop(ArrayList<Card> d) {
		d.remove(0);
		setStages.add(d.get(0));
		setStages.add(d.get(1));
		setStages.add(d.get(2));
		d.remove(0);
		d.remove(0);
		d.remove(0);
	}

	public static void turn(ArrayList<Card> d) {
		d.remove(0);
		setStages.add(d.get(0));
		d.remove(0);
	}

	public static void river(ArrayList<Card> d) {
		d.remove(0);
		setStages.add(d.get(0));
		d.remove(0);
	}

	private static void pairs(ArrayList<Player> active) {
		String status = "";	

		for(int k = 0; k < active.size(); k++) {
			int counter = 0;
			@SuppressWarnings("unused")
			int startValue = 0;
			String condition = "";
			boolean flush = false;
			boolean straight = false;
			boolean royal = false;
			ArrayList<Integer> values = new ArrayList<Integer>();

			for(int r = 0; r < active.get(k).getCardSet().size(); r++) {
				values.add(active.get(k).getCardSet().get(r).getCardValue().ordinal() + 2);
			}

			Collections.sort(values);
			System.out.println("\n=======" + active.get(k).getName() + "=======");
			System.out.println(values + "\n");

			
			for (int i = 0; i < 3; i++) {
				if(values.get(i) == (values.get(i+1) - 1)) {	// straight	
					if((values.get(i+1)) == (values.get(i+2) - 1)){
						if((values.get(i+2)) == (values.get(i+3) - 1)){
							if((values.get(i+3)) == (values.get(i+4) - 1)){
								straight = true;
								startValue = values.get(i);
								condition = " with highest card " + (values.get(i+4)).toString();
								if(values.get(i+4) == 14) {
									royal = true;
									
								}
							}
						}
					}
				}
			}
			
			
			
			@SuppressWarnings("unused")
			Card tester = new Card();
			for(int i = 0; i < active.get(k).getCardSet().size(); i++) {
				Card temp = active.get(k).getCardSet().get(i);
				int counter2 = 0;
				
				for (int j = i + 1; j < active.get(k).getCardSet().size(); j++) {
					if (temp.getCardValue().equals(active.get(k).getCardSet().get(j).getCardValue())) {
						//System.out.println(active.get(k).getName() + " has a match, at "+ active.get(k).getCardSet().get(i)+ 
						//		" and " + active.get(k).getCardSet().get(j));
						condition = " of " + active.get(k).getCardSet().get(i).getCardValue() + "S";
						tester = active.get(k).getCardSet().get(j);
						counter++;
					} 
					if (temp.getSuit().equals(active.get(k).getCardSet().get(j).getSuit())){	// flush
						counter2++;
						if(counter2 >= 4 && counter < 4) {
							flush = true;
							condition = "of" + active.get(k).getCardSet().get(j).getSuit();
						}
					} 
				} 
				
				if(flush && straight && royal){
					counter = 100;
					condition = " of " + active.get(k).getCardSet().get(1).getSuit();
				} else if(flush && straight) {
					counter = 40;
					condition = " of " + active.get(k).getCardSet().get(1).getSuit();
				} else if (flush && !straight) {
					counter = 30;
					condition = " with " + active.get(k).getCardSet().get(1).getSuit();
				} else if (straight && !flush) {
					counter = 20;
				}

			}
			

			
			
			
			
			switch(counter) {
			case 0:
				status = "HIGH_CARD";
				break;
			case 1: 
				status = "ONE_PAIR";
				break;
			case 2:
				status = "TWO_PAIR";
				break;
			case 3:
				boolean check = false;
				for(int i = 2; i < CardValues.values().length + 2; i++) {
					if(Collections.frequency(values, i) == 3) {
						check = true;
					} else {}
				}
//				if(check) {
//					status = "THREE_OF_A_KIND";
//				} else {
//					status = "TWO_PAIR";
//					
//				}
				
				status = (check) ? "THREE_OF_A_KIND" : "TWO_PAIR";
				
				
				break;
			case 4: case 5:
				status = "FULL_HOUSE";
				break;
			case 6: case 7:
				status = "FOUR_OF_A_KIND";	// shows up for two 3 of a kind; 3 2 and 2
				break;
			case 20:
				status = "STRAIGHT";
				break;
			case 30:
				status = "FLUSH";
				break;
			case 40:
				status = "STRAIGHT_FLUSH";
				break;
			case 100:
				status = "ROYAL_FLUSH";
				break;
			default:
				System.out.println("something else");
			}
			System.out.println(status + condition + "\n");
			active.get(k).setHighestRule(status);
		}
	}
	
	
	public static void bet(ArrayList<Player> players, ArrayList<Player> active) {
		for(Player player: players) {
			if (active.contains(player)) {

				if ((((((player.getCard1().getCardValue().ordinal() + 2)
						+ (player.getCard2().getCardValue().ordinal() + 2)) / 2 >= 9)
						|| player.getCard1().getSuit().equals(player.getCard2().getSuit())
						|| Ruler.valueOf(player.getHighestRule()).ordinal() < 8)
						&& player.getStack() > 10000) || active.size() == 1) {
					int bet = player.getStack() / 10;
					player.setStack(player.getStack() - bet);
					pot += bet;
					System.out.println(player.getName() + " bets.");
				} else {
					fold(active, player);
				} 
			}
		}
	}
	
	public static void fold(ArrayList<Player> active, Player player) {
		System.out.println(player.getName() + " folds.");
		active.remove(player);
	}

	public static void winner(ArrayList<Player> active) {
		System.out.println("Before: " + active);
		Collections.sort(active, new SortRule());
		Collections.sort(active, new SortRule());
		System.out.println("After: " + active);
		System.out.println("\nWinner is: " + active.get(0).getName());
		active.get(0).setStack(active.get(0).getStack() + pot);
	}
}