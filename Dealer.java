package blackjack;
import java.util.ArrayList;




public class Dealer {
	public Card current_card;

	public ArrayList<Card> hand = new ArrayList<>();
	public Dealer() {
		this.hand = null;
	}
	
	public String get_str_hand() {
		return this.hand.toString();
	}
	
	public void hit(Card card) {
		this.hand.add(card);	
	}

	public int get_value() { //calculate value of hand
		int value = 0;
		int aces = 0;
		int val = 0;
		
		for (int i =0; i < this.hand.size(); i++) {
			current_card  = this.hand.get(i);
			val =  current_card.value;			
			if (val == 1) {
				aces+=1;
			}
			else {
				value += Math.min(val, 10);
			}		
		}
		if(aces == 0) {
			return value;
		}
		if (value + 11 > 21) {
			return (value + aces);
		}
		else if (aces ==1) {
			return (value + 11);
		}
		else if(value + 11 + (aces -1) <= 21) {
			return (value + 11 + (aces - 1));
		}
		else {
			return (value + aces);
		}		
	}
}
