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
    
    private Player players;
    private Deck deck;
    
    private boolean log = false;
    
    private Image gameBackground;
    
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
		
		// Game Panel
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
		
		// Screen Panel
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(3,3));
		//gamePanel.setLayout(gridBagLayout);
		gamePanel.setBackground(new Color(50, 163, 39));
		
		// Bank Panel
		JPanel bankPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
				
		JLabel bankLabel = new JLabel("Bank:\t");
		JLabel bankMoneyLabel = new JLabel("$$$");
		
		bankPanel.add(bankLabel,gbc);
		bankPanel.add(bankMoneyLabel,gbc);
		
		// Dealer card layout
		JPanel dealerPanel = new JPanel(new BorderLayout());
		JLabel dealerLabel = new JLabel("Dealer");
		
		JLabel card1Label = new JLabel("Card1");
		JLabel card2Label = new JLabel("Card2");
		
		JPanel dealerCardPanel = new JPanel(new GridLayout(1,2));
		dealerCardPanel.setBackground(Color.RED);
		
		gamePanel.add(new JLabel("Top Left"));
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
			}
			
		});
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		dealPanel.add(amountLabel,gbc);
		dealPanel.add(amountInput,gbc);
		dealPanel.add(amountEnteredButton);
		
		// Player card layout
		JPanel playerPanel = new JPanel(new BorderLayout());
		JLabel playerLabel = new JLabel("Player");
				
		JLabel card3Label = new JLabel("Card3");
		JLabel card4Label = new JLabel("Card4");
		
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
		
		
		gamePanel.add(new JLabel("Center Left"));
		gamePanel.add(new JLabel("True Center"));
		gamePanel.add(new JLabel("Center Right"));
		
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
