package blackjack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		float starting_balance = 500;
		Player player = new Player(starting_balance);
		Dealer dealer = new Dealer();
		Game game = new Game(player,dealer);
		
		
		System.out.println("Welcome to Blackjack!");
		System.out.println("");
		game.start_game();		
	}

}
