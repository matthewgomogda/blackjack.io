//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTable;

import java.awt.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.io.File;

/**
 * 
 */

/**
 * @author 
 *
 */
public class BlackjackGUI extends JFrame{

	private JPanel mainPanel, welcomePanel, gamePanel;
	private BorderLayout borderLayout;
	private FlowLayout flowLayout;
	private GridLayout gridLayout;
	
	
    private JMenuBar menuBar;
    private JMenu mainMenu, loginMenu;
    private JMenuItem startGameMenuItem, statisticsMenuItem, helpMenuItem, existingMenuItem, newUserMenuItem;
    
    private JLabel card1Label, card2Label, card3Label, card4Label;
    
    private Player player;
    private Deck deck;
    private Dealer dealer;
    
    private boolean log = false;
    
    private Image gameBackground;
    
    
    
	
    private ImageIcon dealerCard1Icon, dealerCard2Icon, playerCard1Icon, playerCard2Icon;
    private Image dealerCard1Image, dealerCard2Image, playerCard1Image, playerCard2Image;
    private Image modDealerCard1Image, modDealerCard2Image, modPlayerCard1Image, modPlayerCard2Image;
    
    private HashMap <String,String> imageMap;
    
    private int WINDOW_WIDTH = 508 * 2;
	private int WINDOW_HEIGHT = 339 * 2;
    
	/**
	 * 
	 */
	public BlackjackGUI() {
		// Call super from JFrame and rename title
		super("CS 370 BlackJack");
		
		// Set size for window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
		welcomeScreen();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//setResizable(false);
	}
	
	public void welcomeScreen() {
		createMenuBar();
		welcomePanels();
		
		setJMenuBar(menuBar);
		add(mainPanel);
	}
	
	public void welcomePanels() {
		// Create Layouts
		borderLayout = new BorderLayout();
		
		// MainPanel 
		mainPanel = new JPanel();
		mainPanel.setLayout(borderLayout);
		mainPanel.setBackground(Color.WHITE);
		
		// Welcome Panel
		welcomePanel = new JPanel();
		welcomePanel.setBackground(Color.WHITE);
		
		// Add Panels to panel
		mainPanel.add(welcomePanel, BorderLayout.CENTER);
		//mainPanel.add(welcomePanel);
		
	}
	
	public void createMenuBar() {
		// Create components
		menuBar = new JMenuBar();
		
		mainMenu = new JMenu("Main Menu");
		startGameMenuItem = new JMenuItem("Start Game");
		startGameMenuItem.addActionListener(new StartGameListener());
		
		statisticsMenuItem = new JMenuItem("Statistics");
		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, 
						"Numbered cards are equal to their repective value.\n"
						+ "Face cards value is 10.\n"
						+ "Ace could be 1 or 11 depending on player choice.\n"
						+ "Players win their bet if they beat the dealer");
			}
			
		});
		
		loginMenu = new JMenu("Login");
		newUserMenuItem = new JMenuItem("New User");
		existingMenuItem = new JMenuItem("Existing User");
		
		loginMenu.add(newUserMenuItem);
		loginMenu.add(existingMenuItem);
		
		mainMenu.add(startGameMenuItem);
		mainMenu.add(loginMenu);
		mainMenu.add(statisticsMenuItem);
		mainMenu.add(helpMenuItem);
		
		menuBar.add(mainMenu);
	}
	
	public void startGame() {
		//GridBagLayout gridBagLayout = new GridBagLayout();
		
		// start game 
		deck = new Deck();
		player = new Player();
		dealer = new Dealer();
		
		imageMap = new HashMap<>();
		
		imageMap.put("AC", "image/ace_of_clubs.png");
		imageMap.put("AD", "image/ace_of_diamonds.png");
		imageMap.put("AH", "image/ace_of_hearts.png");
		imageMap.put("AS", "image/ace_of_spades.png");
		
		for (int r = 2;  r < 11; r++) {
			imageMap.put(r+"C", "image/"+r+"_of_clubs.png");
			imageMap.put(r+"D", "image/"+r+"_of_diamonds.png");
			imageMap.put(r+"H", "image/"+r+"_of_hearts.png");
			imageMap.put(r+"S", "image/"+r+"_of_spades.png");
		}

		imageMap.put("JC", "image/jack_of_clubs.png");
		imageMap.put("JD", "image/jack_of_diamonds.png");
		imageMap.put("JH", "image/jack_of_hearts.png");
		imageMap.put("JS", "image/jack_of_spades.png");

		imageMap.put("QC", "image/queen_of_clubs.png");
		imageMap.put("QD", "image/queen_of_diamonds.png");
		imageMap.put("QH", "image/queen_of_hearts.png");
		imageMap.put("QS", "image/queen_of_spades.png");

		imageMap.put("KC", "image/king_of_clubs.png");
		imageMap.put("KD", "image/king_of_diamonds.png");
		imageMap.put("KH", "image/king_of_hearts.png");
		imageMap.put("KS", "image/king_of_spades.png");
		
		dealerCard1Icon = new ImageIcon(this.getClass().getResource("image/Bicycle_Playing_Cards_red.jpg"));
		dealerCard1Image = dealerCard1Icon.getImage();
		modDealerCard1Image= dealerCard1Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
		dealerCard1Icon = new ImageIcon(modDealerCard1Image);
		
		dealerCard2Icon = new ImageIcon(this.getClass().getResource("image/Bicycle_Playing_Cards_red.jpg"));
		dealerCard2Image = dealerCard2Icon.getImage();
		modDealerCard2Image= dealerCard2Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
		dealerCard2Icon = new ImageIcon(modDealerCard2Image);
		
		playerCard1Icon = new ImageIcon(this.getClass().getResource("image/Bicycle_Playing_Cards_red.jpg"));
		playerCard1Image = playerCard1Icon.getImage();
		modPlayerCard1Image= playerCard1Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
		playerCard1Icon = new ImageIcon(modPlayerCard1Image);
		
		playerCard2Icon = new ImageIcon(this.getClass().getResource("image/Bicycle_Playing_Cards_red.jpg"));
		playerCard2Image = playerCard2Icon.getImage();
		modPlayerCard2Image= playerCard2Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
		playerCard2Icon = new ImageIcon(modPlayerCard2Image);
		
				
		try {
			deck.shuffle();
		} catch (InvalidDeckPositionException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		// Screen Panel
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(3,3));
		//gamePanel.setLayout(gridBagLayout);
		gamePanel.setBackground(new Color(50, 163, 39));
		
		//   Message Panel
		JPanel messagePanel = new JPanel(new FlowLayout());
		JLabel messageLabel = new JLabel("Enter amount to start game");
		messagePanel.add(messageLabel);
		
		// Dealer card layout
		JPanel dealerPanel = new JPanel(new BorderLayout());
		JLabel dealerLabel = new JLabel("Dealer");
				
		card1Label = new JLabel(dealerCard1Icon);
		card2Label = new JLabel(dealerCard2Icon);
				
		JPanel dealerCardPanel = new JPanel(new GridLayout(1,2));
		dealerCardPanel.setBackground(Color.RED);
		
		
		// Bank Panel
		JPanel bankPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0;
				
		JLabel bankLabel = new JLabel("Bank: " + String.valueOf(player.getBank()));
		JLabel betLabel = new JLabel("Bet: 0");
		
		bankPanel.add(bankLabel,gbc);
		bankPanel.add(betLabel);
		
		
		// Add top panels to gamePanel
		gamePanel.add(messagePanel);
		gamePanel.add(dealerPanel);
		gamePanel.add(bankPanel);
		
		// Amount to deal 
		JPanel dealPanel = new JPanel(new GridBagLayout());
		JLabel amountLabel = new JLabel("Amount to deal");
		JTextField amountInput = new JTextField();
		
		JButton amountEnteredButton = new JButton("Place Bet");
		amountEnteredButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num =  Integer.parseInt(amountInput.getText());
				//play hand or something
				
				amountInput.setText("");
				amountInput.setEnabled(false);
				
				if(player.getBank() > 0)
				{
					
					player.setBet(num);
					player.addCard(deck.nextCard());
					dealer.addCard(deck.nextCard());
					player.addCard(deck.nextCard());
					dealer.addCard(deck.nextCard());
					
					bankLabel.setText("Bank: " + (player.getBank() - num));
					betLabel.setText("Bet: " + num);
					
					String[] dealerValues = dealer.getHandString(true, false).split(" ");
					System.out.println(dealer.getHandString(true, false));
					
					String[] playerValues = player.getHandString().split(" ");
					System.out.println(player.getHandString());
					
					dealerCard1Icon = new ImageIcon(this.getClass().getResource(imageMap.get(dealerValues[1])));
					dealerCard1Image = dealerCard1Icon.getImage();
					modDealerCard1Image= dealerCard1Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
					dealerCard1Icon = new ImageIcon(modDealerCard1Image);
					
					card1Label.setIcon(dealerCard1Icon);
					
					dealerCard2Icon = new ImageIcon(this.getClass().getResource(imageMap.get(dealerValues[2])));
					dealerCard2Image = dealerCard2Icon.getImage();
					modDealerCard2Image= dealerCard2Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
					dealerCard2Icon = new ImageIcon(modDealerCard2Image);
					
					///card2Label.setIcon(dealerCard2Icon);
					
					playerCard1Icon = new ImageIcon(this.getClass().getResource(imageMap.get(playerValues[1])));
					playerCard1Image = playerCard1Icon.getImage();
					modPlayerCard1Image= playerCard1Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
					playerCard1Icon = new ImageIcon(modPlayerCard1Image);
					
					card3Label.setIcon(playerCard1Icon);
					
					playerCard2Icon = new ImageIcon(this.getClass().getResource(imageMap.get(playerValues[2])));
					playerCard2Image = playerCard2Icon.getImage();
					modPlayerCard2Image= playerCard2Image.getScaledInstance(100, 170, java.awt.Image.SCALE_SMOOTH);
					playerCard2Icon = new ImageIcon(modPlayerCard2Image);
					
					card4Label.setIcon(playerCard2Icon);
					
				}
				else {
					
				}
			}
			
		});
		
		dealPanel.add(amountLabel,gbc);
		dealPanel.add(amountInput,gbc);
		dealPanel.add(amountEnteredButton);
		
		// Player card layout
		JPanel playerPanel = new JPanel(new BorderLayout());
		JLabel playerLabel = new JLabel("Player");
				
		card3Label = new JLabel(playerCard1Icon);
		card4Label = new JLabel(playerCard2Icon);
		
		JPanel playerCardPanel = new JPanel(new GridLayout(1,2));
		playerCardPanel.setBackground(Color.RED);
		
		// Hit or stand buttons
		JPanel actionPanel = new JPanel(new GridBagLayout());
		
		JButton hitBtn = new JButton("Hit");
		JButton standBtn = new JButton("Stand");
		
		// Adding components onto one another
		
		actionPanel.add(hitBtn,gbc);
		actionPanel.add(standBtn, gbc);
		
		playerCardPanel.add(card3Label);
		playerCardPanel.add(card4Label);
		
		playerPanel.add(playerCardPanel);
		playerPanel.add(playerLabel, BorderLayout.SOUTH);
		
		
		dealerCardPanel.add(card1Label);
		dealerCardPanel.add(card2Label);
		
		dealerPanel.add(dealerLabel, BorderLayout.NORTH);
		dealerPanel.add(dealerCardPanel, BorderLayout.CENTER);
		
		
		gamePanel.add(new JLabel(""));
		gamePanel.add(new JLabel(""));
		gamePanel.add(new JLabel(""));
		
		gamePanel.add(dealPanel);
		gamePanel.add(playerPanel);
		gamePanel.add(actionPanel);
		
		
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		//setSize(508,339);
		revalidate();
		
		
		
		
		
//		ImageIcon backIcon = new ImageIcon(this.getClass().getResource("/gameBackground.jpg"));
//		Image backImage = backIcon.getImage();
//		Image modBackImage = backImage.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
//		backIcon = new ImageIcon(modBackImage);
//		
//		JLabel backgroundImageLabel = new JLabel(backIcon);
//		//backgroundImageLabel.add(imgIcon);
//		//backgroundImageLabel.setSize(508,339);
//		
//		gamePanel.add(backgroundImageLabel);
		
		//pack();
		//setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
	}
	
	private class StartGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainPanel.remove(welcomePanel);
			startGame();
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BlackjackGUI();

	}
}
