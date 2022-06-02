package blackjack;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.*;
import java.util.List;
import java.util.Objects;


public class Deck {
	public ArrayList<Card> cards = new ArrayList<Card>();

	public Deck() {
		this.cards = new ArrayList();
		this.create_deck();	
		this.shuffle();
	}
	
	public void create_deck(){
		for (int suit = 0; suit < 4; suit++) {
			for (int card = 1; card < 14; card ++) {
				Card new_card = new Card(suit, card);
				this.cards.add(new_card);	
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	public ArrayList<Card> deal(int num_cards) {
		ArrayList<Card> dealt_cards = new ArrayList<Card>();
		for (int idx = 0; idx < num_cards; idx++) {
			Card top_card = (Card) this.cards.get(num_cards -1 -idx);
			this.cards.remove(num_cards -1 -idx);
			dealt_cards.add(top_card);
		}
		return dealt_cards;
	}
}
