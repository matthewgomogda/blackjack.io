import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.sql.*;
public class Blackjack {

	static String menu(Connection con, Statement statement) {
		Scanner inp = new Scanner(System.in);
		System.out.println("Welcome to Blackjack.io!\nPlease enter (S) to signup for an account, (L) to login, or (G) to play as a guest: ");
		String signin = inp.next();
		signin.toLowerCase();
		String tablepass = "";
		if(signin.equals("l")) {
			System.out.println("Enter username: ");
			String username = inp.next();
			System.out.println("Enter password:" );
			String password = inp.next();
			ResultSet rs;
			try {
				rs = statement.executeQuery("SELECT * FROM blackjackusers.users WHERE username = '" + username + "'");
				rs.next();
				tablepass = rs.getString("password");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(password.equals(tablepass)) {
				System.out.println("Welcome " + username+ "!");
				return username;
			}else {
				System.out.println("Sorry, the password does not match. Returning to main menu");
				menu(con,statement);
				
			}
		}else if(signin.equals("s")) {
			System.out.println("Welcome to Blackjack.io! Please enter a username to signup: ");
			String username = inp.next();
			boolean passwordconf = true;
			while(passwordconf) {
				System.out.println("Please enter a password: ");
				String password = inp.next();
				System.out.println("Please reenter password: ");
				if(inp.next().equals(password)) {
					System.out.println("Welcome " + username + "!");
					try {
						statement.executeUpdate("INSERT INTO blackjackusers.users " + "VALUES ('"+username+"', '"+password+"', 0, 0, 0, 0, 0,0)");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return username;
				}else {
					System.out.println("Passwords do not match");
				}
			}
			}else if (signin.equals("g")){
				return "guest";
			}else {
		System.out.println("Input not recognized");
		menu(con,statement);
			}
		return "guestuser";
	}
		
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String url = "jdbc:mysql:///blackjackusers";
		final String conuser = "root";
		final String conpass = "SSJGoku1!";
		Connection con = DriverManager.getConnection(url,conuser,conpass);
		Statement statement = con.createStatement();
		String username = menu(con,statement);

        BlackjackGame mygame = new BlackjackGame();
        mygame.initializeGame(username);
        do {
            mygame.shuffle();
            mygame.getBets();
            mygame.dealCards();
            mygame.printStatus();
            mygame.checkBlackjack();
            mygame.hitOrStand();
            mygame.dealerPlays();
            mygame.settleBets();
            mygame.printMoney();
            mygame.clearHands();
        } while (mygame.playAgain());
        //mygame.endGame();

        try
        {
            FileOutputStream outObjectFile = new FileOutputStream("objOut", false);

            ObjectOutputStream outObjectStream = new ObjectOutputStream(outObjectFile);

            outObjectStream.writeObject(mygame);

            outObjectStream.flush();
            outObjectStream.close();
        }
        catch(FileNotFoundException fnfException)
        {
            System.out.println("No file");
        }
        catch(IOException ioException)
        {
          //  System.out.println("bad IO");
        }

        try
        {
            FileInputStream inObjectFile = new FileInputStream("objOut");

            ObjectInputStream inObjectStream = new ObjectInputStream(inObjectFile);

            Card myNewCard = (Card)inObjectStream.readObject();

            System.out.println(myNewCard);

        }
        catch(FileNotFoundException fnfException)
        {
            System.out.println("No File");
        }
        catch(IOException ioException)
        {
          //  System.out.println("IO no good");
        }

        catch(ClassNotFoundException cnfException)
        {
            System.out.println("This is not a Card.");
        }

    }

}