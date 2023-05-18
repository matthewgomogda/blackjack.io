//import java.util.Scanner;
//import java.sql.*;
//
//public class BlackjackGame {
//	
//	private Scanner ki = new Scanner(System.in);
//	private int users = 1; 
//	//private Player[] players;
//	private Player player1;
//	private Deck deck;
//	private Dealer dealer = new Dealer();
//
//	// Starts game 
//	public void initializeGame(String username){
//		System.out.println("Welcome to Blackjack!");
//		System.out.println("");
//		System.out.println("  BLACKJACK RULES: ");
//		System.out.println("	-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
//		System.out.println("	-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
//		System.out.println("	-The players cards are added up for their total.");
//		System.out.println("	-Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.");
//		System.out.println("	-Dealer “Hits” until they equal or exceed 17.");
//		System.out.println("	-The goal is to have a higher card total than the dealer without going over 21.");
//		System.out.println("	-If the player total equals the dealer total, it is a “Push” and the hand ends."); 
//		System.out.println("	-Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21.");
//		System.out.println("");
//		System.out.println("");
//		
//		// creates amount of players
////		do {
////			System.out.print("How many people are playing (1-6)? ");
////			users = ki.nextInt();
////		
//
////		} while (users > 6 || users < 0);
////
////		players = new Player[users];
//		deck = new Deck();
////
////		// Ask and assign user name
////		for (int i = 0; i < users; i++) {
////			System.out.print("What is player " + (i + 1) + "'s name? ");
////			names = ki.next();
////			player1 = new Player();
////			player1.setName(names);
////		}
//		
//		player1 = new Player(username);
//
//		
//	}
//	
//	// Shuffle deck
//	public void shuffle() throws InvalidDeckPositionException, InvalidCardSuitException, InvalidCardValueException {
//		deck.shuffle();
//		
//	}
//
//	// Gets bets 
//	public void getBets(){
//		int betValue;
//		
//		for (int i =0; i < users; i++) {  	
//			if (player1.getBank() > 0) {
//			do {
//				System.out.print("How much do you want to bet " + player1.getName()  + (" (1-" + player1.getBank()) + ")? " );
//				betValue = ki.nextInt();
//				player1.setBet(betValue);
//			} while (!( betValue > 0 && betValue <= player1.getBank()));
//			System.out.println("");
//			}else {
//				player1.restockBank();
//			}
//
//		}
//
//	}
//	
//	// Deals cards 
//	public void dealCards(){
//		for (int j = 0; j < 2; j++) {
//			for (int i =0; i < users; i++) {
//				if(player1.getBank() > 0)
//				{
//				player1.addCard(deck.nextCard());
//				}
//			}
//
//			dealer.addCard(deck.nextCard());
//		}
//	}
//	
//	// Initial check for dealer or player Blackjack
//	public void checkBlackjack(){
//		//System.out.println();
//
//		if (dealer.isBlackjack() ) {
//			System.out.println("Dealer has BlackJack!");
//			for (int i =0; i < users; i++) {
//				if (player1.getTotal() == 21 ) {
//					System.out.println(player1.getName() + " pushes");
//					player1.push();
//				} else {
//					System.out.println(player1.getName() + " loses");
//					player1.loss();
//				}	
//			}
//		} else {
//			if (dealer.peek() ) {
//				System.out.println("Dealer peeks and does not have a BlackJack");
//			}
//
//			for (int i =0; i < users; i++) {
//				if (player1.getTotal() == 21 ) {
//					System.out.println(player1.getName() + " has blackjack!");
//					player1.blackjack();
//				}
//			}
//		}		
//	}
//	
//	// This takes the user commands to hit or stand
//	public void hitOrStand() {
//		String command;
//		char c;
//		for (int i = 0; i < users; i++) {
//			if ( player1.getBet() > 0 ) {
//				System.out.println();
//				System.out.println(player1.getName() + " has " + player1.getHandString());
//
//				do {
//					do {
//						System.out.print(player1.getName() + " (H)it or (S)tand? ");
//						command = ki.next();
//						c = command.toUpperCase().charAt(0);
//					} while ( ! ( c == 'H' || c == 'S' ) );
//					if ( c == 'H' ) {
//						player1.addCard(deck.nextCard());
//						System.out.println(player1.getName() + " has " + player1.getHandString());
//					}
//				} while (c != 'S' && player1.getTotal() <= 21 );
//			}
//		}
//	}
//	
//	// Code for the dealer to play
//	public void dealerPlays() {
//		boolean isSomePlayerStillInTheGame = false;
//		for (int i =0; i < users && isSomePlayerStillInTheGame == false; i++){
//			if (player1.getBet() > 0 && player1.getTotal() <= 21 ) {
//				isSomePlayerStillInTheGame = true;
//			}
//		}
//		if (isSomePlayerStillInTheGame) {
//			dealer.dealerPlay(deck);
//		}
//	}
//	
//	// This code calculates all possible outcomes and adds or removes the player bets
//	public void settleBets() {
//		System.out.println();
//
//		for (int i = 0; i < users; i++) {
//			if (player1.getBet() > 0 ) {
//				if( player1.getTotal() > 21 ) {
//					System.out.println(player1.getName() + " has busted");
//					player1.loss();
//				} else if ( player1.getTotal() == dealer.calculateTotal() ) {
//					System.out.println(player1.getName() + " has pushed");
//					player1.push();
//				} else if ( player1.getTotal() < dealer.calculateTotal() && dealer.calculateTotal() <= 21 ) {
//					System.out.println(player1.getName() + " has lost");
//					player1.loss();
//				} else if (player1.getTotal() == 21) {
//					System.out.println(player1.getName() + " has won with blackjack!");
//					player1.blackjack();
//				} else {
//					System.out.println(player1.getName() + " has won");
//					player1.win();
//					
//				}
//			}
//		}
//
//	}
//
//	// This prints the players hands
//	public void printStatus() {
//		for (int i = 0; i < users; i++) {
//			if(player1.getBank() > 0)
//			{
//			System.out.println(player1.getName() + " has " + player1.getHandString());
//			}
//		}
//		System.out.println("Dealer has " + dealer.getHandString(true, true));
//	}
//	
//	// This prints the players banks and tells the player if s/he is out of the game
//	public void printMoney() {
//		for (int i = 0; i < users; i++) {
//			if(player1.getBank() > 0)
//			{
//			System.out.println(player1.getName() + " has " + player1.getBank());
//			}
//			if(player1.getBank() == 0)
//			{
//			System.out.println(player1.getName() + " has " + player1.getBank() + " and is out of the game.");
//			player1.removeFromGame();
//			}
//		}
//	}
//
//	// This code resets all hands
//	public void clearHands() {
//		for (int i = 0; i < users; i++) {
//			player1.clearHand();
//		}
//		dealer.clearHand();
//
//	}
//	
//	// This decides to force the game to end when all players lose or lets players choose to keep playing or not
//	public boolean playAgain(String username) {
//		String command;
//		char c;
//		Boolean playState = true;
//		do {
//			System.out.println("");
//			System.out.print("Please enter (Y) to play again, (N) to quit, or (S) to show statistics: ");
//			command = ki.next();
//			c = command.toUpperCase().charAt(0);
//			if(c == 'S') {
//				final String url = "jdbc:mysql:///blackjackusers";
//				final String conuser = "root";
//				final String conpass = "SSJGoku1!";
//				Connection con;
//				Statement statement;
//				ResultSet rs;
//				try {
//					con = DriverManager.getConnection(url,conuser,conpass);
//					statement = con.createStatement();
//					rs = statement.executeQuery("SELECT * FROM blackjackusers.users WHERE username = '" + username + "'");
//					rs.next();
//					System.out.println("Games Played " + rs.getInt("GamesPlayed"));
//					System.out.println("Games Won " + rs.getInt("GamesWon"));
//					System.out.println("Games Lost " + rs.getInt("GamesLost"));
//					System.out.println("Amount Wagered " + rs.getInt("AmountWagered"));
//					System.out.println("Net Win " + rs.getInt("NetWin"));
//
//
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} while ( ! ( c == 'Y' || c == 'N' ) );
//		if(c == 'N')
//		{
//			playState = false;
//		}
//		
//		return playState;
//	}
//	
//	// This says true or false to forcing the game to end
//	public boolean forceEnd() {
//		boolean end = false;
//		int endCount = 0;
//		
//		for (int i = 0; i < users; i++) {
//			if(player1.getBank() == -1)
//			{
//				endCount++;
//			}
//		}
//		if(endCount == users)
//		{
//			end = true;
//		}
//		if(end)
//		{
//			System.out.println("");
//			System.out.println("All players have lost and the game ends.");
//		}
//		
//		return end;
//	}
//	
//	// This is the endgame code for when all players are out of the game or players decide to stop playing
//		public void endGame() {
//			double endAmount;
//			String endState = " no change.";
//			System.out.println("");
//			for (int i = 0; i < users; i++) {
//				if(player1.getBank() == -1)
//				{
//					player1.resetBank();
//				}
//				endAmount = player1.getBank() - 100.0;
//				if(endAmount > 0)
//				{
//					endState = " gain of ";
//				}
//				else if(endAmount < 0)
//				{
//					endState = " loss of ";
//				}
//				System.out.println(player1.getName() + " has ended the game with " + player1.getBank() + ".");
//				if(endState != " no change.")
//				{
//				System.out.println("A" + endState + Math.abs(endAmount) + ".");
//				}
//				else
//				{
//				System.out.println("No change from their starting value.");	
//				}
//				System.out.println("");
//			}
//			System.out.println("");
//			System.out.println("");
//			System.out.println("Thank you for playing!");
//		}
//
//
//} //End class




import java.util.Scanner;


public class BlackjackGame {
	
	private Scanner ki = new Scanner(System.in);
	private int users = 1; 
	//private Player[] players;
	private Player player1;
	private Deck deck;
	private Dealer dealer = new Dealer();

	// Starts game 
	public void initializeGame(String username){
		System.out.println("Welcome to Blackjack!");
		System.out.println("");
		System.out.println("  BLACKJACK RULES: ");
		System.out.println("	-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
		System.out.println("	-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
		System.out.println("	-The players cards are added up for their total.");
		System.out.println("	-Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.");
		System.out.println("	-Dealer “Hits” until they equal or exceed 17.");
		System.out.println("	-The goal is to have a higher card total than the dealer without going over 21.");
		System.out.println("	-If the player total equals the dealer total, it is a “Push” and the hand ends."); 
		System.out.println("	-Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21.");
		System.out.println("");
		System.out.println("");
		
		// creates amount of players
//		do {
//			System.out.print("How many people are playing (1-6)? ");
//			users = ki.nextInt();
//		

//		} while (users > 6 || users < 0);
//
//		players = new Player[users];
		deck = new Deck();
//
//		// Ask and assign user name
//		for (int i = 0; i < users; i++) {
//			System.out.print("What is player " + (i + 1) + "'s name? ");
//			names = ki.next();
//			player1 = new Player();
//			player1.setName(names);
//		}
		
		player1 = new Player(username);

		
	}
	
	// Shuffle deck
	public void shuffle() throws InvalidDeckPositionException, InvalidCardSuitException, InvalidCardValueException {
		deck.shuffle();
		
	}

	// Gets bets 
	public void getBets(){
		int betValue;
		
		for (int i =0; i < users; i++) {  	
			if (player1.getBank() > 0) {
			do {
				System.out.print("How much do you want to bet " + player1.getName()  + (" (1-" + player1.getBank()) + ")? " );
				betValue = ki.nextInt();
				player1.setBet(betValue);
			} while (!( betValue > 0 && betValue <= player1.getBank()));
			System.out.println("");
			}else {
				player1.restockBank();
			}

		}

	}
	
	// Deals cards 
	public void dealCards(){
		for (int j = 0; j < 2; j++) {
			for (int i =0; i < users; i++) {
				if(player1.getBank() > 0)
				{
				player1.addCard(deck.nextCard());
				}
			}

			dealer.addCard(deck.nextCard());
		}
	}
	
	// Initial check for dealer or player Blackjack
	public void checkBlackjack(){
		//System.out.println();

		if (dealer.isBlackjack() ) {
			System.out.println("Dealer has BlackJack!");
			for (int i =0; i < users; i++) {
				if (player1.getTotal() == 21 ) {
					System.out.println(player1.getName() + " pushes");
					player1.push();
				} else {
					System.out.println(player1.getName() + " loses");
					player1.loss();
				}	
			}
		} else {
			if (dealer.peek() ) {
				System.out.println("Dealer peeks and does not have a BlackJack");
			}

			for (int i =0; i < users; i++) {
				if (player1.getTotal() == 21 ) {
					System.out.println(player1.getName() + " has blackjack!");
					player1.blackjack();
				}
			}
		}		
	}
	
	// This takes the user commands to hit or stand
	public void hitOrStand() {
		String command;
		char c;
		for (int i = 0; i < users; i++) {
			if ( player1.getBet() > 0 ) {
				System.out.println();
				System.out.println(player1.getName() + " has " + player1.getHandString());

				do {
					do {
						System.out.print(player1.getName() + " (H)it or (S)tand? ");
						command = ki.next();
						c = command.toUpperCase().charAt(0);
					} while ( ! ( c == 'H' || c == 'S' ) );
					if ( c == 'H' ) {
						player1.addCard(deck.nextCard());
						System.out.println(player1.getName() + " has " + player1.getHandString());
					}
				} while (c != 'S' && player1.getTotal() <= 21 );
			}
		}
	}
	
	// Code for the dealer to play
	public void dealerPlays() {
		boolean isSomePlayerStillInTheGame = false;
		for (int i =0; i < users && isSomePlayerStillInTheGame == false; i++){
			if (player1.getBet() > 0 && player1.getTotal() <= 21 ) {
				isSomePlayerStillInTheGame = true;
			}
		}
		if (isSomePlayerStillInTheGame) {
			dealer.dealerPlay(deck);
		}
	}
	
	// This code calculates all possible outcomes and adds or removes the player bets
	public void settleBets() {
		System.out.println();

		for (int i = 0; i < users; i++) {
			if (player1.getBet() > 0 ) {
				if( player1.getTotal() > 21 ) {
					System.out.println(player1.getName() + " has busted");
					player1.loss();
				} else if ( player1.getTotal() == dealer.calculateTotal() ) {
					System.out.println(player1.getName() + " has pushed");
					player1.push();
				} else if ( player1.getTotal() < dealer.calculateTotal() && dealer.calculateTotal() <= 21 ) {
					System.out.println(player1.getName() + " has lost");
					player1.loss();
				} else if (player1.getTotal() == 21) {
					System.out.println(player1.getName() + " has won with blackjack!");
					player1.blackjack();
				} else {
					System.out.println(player1.getName() + " has won");
					player1.win();
					
				}
			}
		}

	}

	// This prints the players hands
	public void printStatus() {
		for (int i = 0; i < users; i++) {
			if(player1.getBank() > 0)
			{
			System.out.println(player1.getName() + " has " + player1.getHandString());
			}
		}
		System.out.println("Dealer has " + dealer.getHandString(true, true));
	}
	
	// This prints the players banks and tells the player if s/he is out of the game
	public void printMoney() {
		for (int i = 0; i < users; i++) {
			if(player1.getBank() > 0)
			{
			System.out.println(player1.getName() + " has " + player1.getBank());
			}
			if(player1.getBank() == 0)
			{
			System.out.println(player1.getName() + " has " + player1.getBank() + " and is out of the game.");
			player1.removeFromGame();
			}
		}
	}

	// This code resets all hands
	public void clearHands() {
		for (int i = 0; i < users; i++) {
			player1.clearHand();
		}
		dealer.clearHand();

	}
	
	// This decides to force the game to end when all players lose or lets players choose to keep playing or not
	public boolean playAgain() {
		String command;
		char c;
		Boolean playState = true;
		do {
			System.out.println("");
			System.out.print("Do you want to play again (Y)es or (N)o? ");
			command = ki.next();
			c = command.toUpperCase().charAt(0);
		} while ( ! ( c == 'Y' || c == 'N' ) );
		if(c == 'N')
		{
			playState = false;
		}
		
		return playState;
	}
	
	// This says true or false to forcing the game to end
	public boolean forceEnd() {
		boolean end = false;
		int endCount = 0;
		
		for (int i = 0; i < users; i++) {
			if(player1.getBank() == -1)
			{
				endCount++;
			}
		}
		if(endCount == users)
		{
			end = true;
		}
		if(end)
		{
			System.out.println("");
			System.out.println("All players have lost and the game ends.");
		}
		
		return end;
	}
	
	// This is the endgame code for when all players are out of the game or players decide to stop playing
		public void endGame() {
			double endAmount;
			String endState = " no change.";
			System.out.println("");
			for (int i = 0; i < users; i++) {
				if(player1.getBank() == -1)
				{
					player1.resetBank();
				}
				endAmount = player1.getBank() - 100.0;
				if(endAmount > 0)
				{
					endState = " gain of ";
				}
				else if(endAmount < 0)
				{
					endState = " loss of ";
				}
				System.out.println(player1.getName() + " has ended the game with " + player1.getBank() + ".");
				if(endState != " no change.")
				{
				System.out.println("A" + endState + Math.abs(endAmount) + ".");
				}
				else
				{
				System.out.println("No change from their starting value.");	
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Thank you for playing!");
		}


} //End class