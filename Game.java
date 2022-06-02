package blackjack;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

	public int minimum_bet = 1;
	public Player player;
	public Dealer dealer;
	public float bet;
	public Deck deck;
	public float new_balance;
	
	
	public Game(Player player, Dealer dealer) {
		this.player = player;
		this.dealer = dealer;
		this.bet = 0;
		this.deck = new Deck();
	}
	
	public void place_bet() {
		while (true) {
			System.out.println("Place your bet: ");
			Scanner sc = new Scanner(System.in);
			float bet_made = sc.nextFloat(); // later fix against invalid input
			
			if (bet_made > this.player.balance) {
				System.out.println("you do do have sufficient funds");
			}
			else if (bet_made < this.minimum_bet) {
				System.out.println("The minimum bet is " + this.minimum_bet +".");
			}
			else {
				this.bet = bet_made;
				new_balance = this.player.balance - this.bet;
				this.player.balance = new_balance;
				break;	
			}
		}		
	}
	
	public void deal_starting_cards() {
		ArrayList <Card> cards_player = new ArrayList<>(this.deck.deal(2));
		ArrayList <Card> cards_dealer = new ArrayList<>(this.deck.deal(2));
		this.player.hand = cards_player;
		this.dealer.hand = cards_dealer;
		this.dealer.hand.get(1).hidden = true;
		System.out.println("You are dealt: " + this.player.get_str_hand());
		System.out.println("The dealer is dealt: " + this.dealer.get_str_hand());
	}
	
	
	public boolean handle_blackjack() {
		
		if (this.player.get_value() != 21) {
			return false;
		}
		
		if (this.dealer.get_value() == 21) {
			//this.player.setBalance(this.bet);
			this.player.balance = this.bet;
			System.out.println("Both you and the dealer have blackjack, you tie. Your bet has been returned.");
			return true;
		}
		System.out.println("passed second");
		this.player.balance = this.player.balance + (this.bet * 2.5f);

		System.out.println("Blackjack! You win " + (this.bet * 1.5f) + ":");
		return true;
	}
	
	public boolean confirm_start() {
		System.out.println("You are starting with " + this.player.balance +", would you like to play? ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		if (input.equals("y") || input.equals("yes") || input.equals("start")) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean player_turn() {
		boolean hit;
		while (true) {
			hit = this.get_player_hit_or_stay();
			if (!hit) {
				break;
			}
			Card new_card;
			new_card = this.deck.deal(1).get(0);  
			this.player.hit(new_card);
			
			System.out.println("You are dealt: " + new_card);
			System.out.println("You now have, " + this.player.get_str_hand());
			
			if (this.player.get_value() > 21) {
				return true; //player lost
			}		
		}
		return false;	
	}
	
	public boolean dealer_turn() {
		this.dealer.hand.get(1).hidden = false;
		System.out.println("The dealer has: "+ this.dealer.get_str_hand());
		while (this.dealer.get_value() <= 16) {
			Card dealer_card;
			dealer_card = this.deck.deal(1).get(0);  
			this.dealer.hit(dealer_card);
			
			System.out.println("The dealer hits and is dealt: " + dealer_card);
			System.out.println("The dealer has: " + this.dealer.get_str_hand());
		}
		if (this.dealer.get_value() > 21) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	public boolean get_player_hit_or_stay() {
		String hit_or_stay; 
		
		while (true) {
			System.out.println("Would you like to hit or stay? ");
			Scanner sc = new Scanner(System.in);
			hit_or_stay = sc.next().toLowerCase(); 
			if (hit_or_stay.equals("hit") || hit_or_stay.equals("stay")) {
				break;
			}
			System.out.println("That is not valid option");
		}
		return (hit_or_stay.equals("hit"));
	}
	
	public void determine_winner() {
		int player_value = this.player.get_value();
		int dealer_value = this.dealer.get_value();
		
		if (dealer_value < player_value) {
			this.player.balance = this.player.balance + (this.bet * 2f);
			System.out.println("You win $"+ this.bet +"!");
		}
		else if (dealer_value > player_value) {
			System.out.println("The dealer wins, you loose $"+this.bet);
		}
		else {
			this.player.balance = this.player.balance + this.bet;
			System.out.println("You tie. your bet has been returned.");
		}
	}
	
	public void reset_round() {
		this.deck = new Deck();
		this.player.hand = null;
		this.dealer.hand = null;
		this.bet = 0;
	}
	
	public void start_round() {
		this.place_bet();
		this.deal_starting_cards();
		if (this.handle_blackjack()) {
			return;
		}
		
		boolean player_lost = this.player_turn();
		if (player_lost) {
			System.out.println("Your hand value is over 21 and you loose $"+this.bet + ":(");
			return;
		}
		boolean dealer_lost = this.dealer_turn();
		if (dealer_lost) {
			this.player.balance = this.player.balance + (this.bet *2f);
			System.out.println("The dealer busts, you win $"+this.bet + ":)");
			return;
		}
		this.determine_winner();
		this.reset_round();
	}
	
	public void start_game() {
		while(this.player.balance >  0) {
			if (!this.confirm_start()) {
				System.out.println("You left the game with " + this.player.balance);
				break;
			}
			this.start_round();
		}
		if (this.player.balance <= 0) {
			System.out.println("You've ran out of money, Please restart this program to try again");
		}
	}

}
