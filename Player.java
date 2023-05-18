import java.io.Serializable;
import java.sql.*;

public class Player implements Serializable
{
	
	private double bank;
	private int bet;
	private String name;
	private Hand hand;
	
	
	// Creates a player object
	public Player(String username) {
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con;
		Statement statement;
		hand = new Hand();
		name = username;
		try {
			con = DriverManager.getConnection(url,conuser,conpass);
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM blackjackusers.users WHERE username = '" + name + "'");
			rs.next();
			bank = rs.getDouble("Bank");
			if(bank <= 0) {
				System.out.println("No money in the bank. Adding 100");
				statement.executeUpdate("UPDATE blackjackusers.users SET Bank = 100 where username = '" + name + "'");
				bank = 100;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void restockBank() {
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con;
		Statement statement;
		try {
			con = DriverManager.getConnection(url,conuser,conpass);
			statement = con.createStatement();
			System.out.println("No money in the bank. Adding 100");
			statement.executeUpdate("UPDATE blackjackusers.users SET Bank = 100 where username = '" + name + "'");
			bank = 100;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Gets a player's bank amount
	public double getBank() {
		
		return bank;
	}
	
	// Removes a player's bet from their bank if they bust. Sets bet to zero afterwards.
//	public void bust() {
//		bank -= bet;
//		bet = 0;
//	}
	
	// Adds a player's bet from their bank if they win. Sets bet to zero afterwards.
	public void win() {
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con;
		Statement statement;
		try {
			con = DriverManager.getConnection(url,conuser,conpass);
			statement = con.createStatement();
			statement.executeUpdate("UPDATE blackjackusers.users SET Bank = Bank + " + bet + " where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET GamesWon = GamesWon + 1 where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET GamesPlayed = GamesPlayed + 1 where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET NetWin = NetWin + " + bet + " where username = '" + name + "'");
			ResultSet rs = statement.executeQuery("SELECT * FROM blackjackusers.users WHERE username = '" + name + "'");
			rs.next();
			bank = rs.getDouble("Bank");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bet = 0;
	}

	// Removes a player's bet from their bank if they lose. Sets bet to zero afterwards.
	public void loss() {
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con;
		Statement statement;
		try {
			con = DriverManager.getConnection(url,conuser,conpass);
			statement = con.createStatement();
			statement.executeUpdate("UPDATE blackjackusers.users SET Bank = Bank - " + bet + " where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET GamesLost = GamesLost + 1 where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET GamesPlayed = GamesPlayed + 1 where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET NetWin = NetWin - " + bet + " where username = '" + name + "'");
			ResultSet rs = statement.executeQuery("SELECT * FROM blackjackusers.users WHERE username = '" + name + "'");
			rs.next();
			bank = rs.getDouble("Bank");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bet = 0;
	}
	
	// This sets the player bank to -1. -1 is unreachable and they are removed from the game
	public void removeFromGame() {
		bank = -1;
	}
	
	// This resets the bank to 0. Currently used to reset a removed player's bank from -1 to 0.
	public void resetBank() {
		System.out.println("Out of money. Resetting bank to 100");
		bank = 100.0;
	}
	
	// This calculate the bet for a player who has a Blackjack
	public void blackjack() {
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con;
		Statement statement;
		try {
			con = DriverManager.getConnection(url,conuser,conpass);
			statement = con.createStatement();
			statement.executeUpdate("UPDATE blackjackusers.users SET Bank = Bank + " + bet*1.5 + " where username = '" + name + "'");
			statement.executeUpdate("UPDATE blackjackusers.users SET NetWin = NetWin + " + bet*1.5 + " where username = '" + name + "'");
			ResultSet rs = statement.executeQuery("SELECT * FROM blackjackusers.users WHERE username = '" + name + "'");
			rs.next();
			bank = rs.getDouble("Bank");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bet = 0;
	}
	
	// Sets a player's bet to 0 if the "push". Notice, no bet is added or removed.
	public void push() {
		bet = 0;
	}
	
	// Sets a player's bet
	public void setBet(int newBet) {
		bet = newBet;
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con;
		Statement statement;
		try {
			con = DriverManager.getConnection(url,conuser,conpass);
			statement = con.createStatement();
			statement.executeUpdate("UPDATE blackjackusers.users SET AmountWagered = AmountWagered + " + bet + " where username = '" + name + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Sets a player's name
	public void setName(String name1){
		name = name1;
	}
	
	// Gets a player's name
	public String getName() {
		return name;
	}
	
	// Gets a player's hand total
	public int getTotal() {
		return hand.calculateTotal();
	}
	
	// Gets a player's bet
	public int getBet(){
		return this.bet;
	}
		
	// Adds a card to a player's hand
	public void addCard(Card card) {
		hand.addCard(card);

	}
	
	// Gets the player's cards to print as a string
	public String getHandString() {
		String str = "Cards:" + hand.toString();

		return str;
	}
		
	// Clears a player's hand
	public void clearHand() {
		hand.clearHand();
	}
		
} //End class