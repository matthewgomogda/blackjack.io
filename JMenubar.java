import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JMenubar extends JFrame implements ActionListener{
    static JMenuBar mb;
    static JMenu x,x1;
    static JMenuItem m1, s1, s2, s3, s4;
    static JFrame f;
    static JLabel l, ll, lll;

    static JTable t;

    static JPanel panel;
    static JPanel panel2;

    static Player user;


    static boolean log = false;
    public static void main(String[] args) {

        JMenubar m = new JMenubar();
        m.setBackground(Color.LIGHT_GRAY);
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.white);

        Player user = new Player();
        JButton ok = new JButton("ok");
        JButton cancel = new JButton("cancel");
        Font f1 = new Font(Font.SERIF, Font.ITALIC, 11);
        Font f2 = new Font("Consolas", Font.PLAIN, 18);
        Font f3 = new Font(Font.SERIF, Font.ITALIC, 14);
        f = new JFrame("blackjack.io");
        String name = user.getName();
        JTable list = new JTable();
        list.add(name, panel2);
        f.add(list);
       // t.add(list);
        l = new JLabel("blackjack.io");
        ll = new JLabel("blackjack.io");
        lll = new JLabel("blackjack.io");
        lll.setFont(f1);
        l.setFont(f2);
      //  llll = new JLabel(" -Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down. \n -Cards are equal to their value with face cards being 10 and an Ace being 1 or 11. \n -The players cards are added up for their total.\n -Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total. \n -Dealer “Hits” until they equal or exceed 17. \n -The goal is to have a higher card total than the dealer without going over 21. \n -If the player total equals the dealer total, it is a “Push” and the hand ends. \n -Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21. \n");
        mb = new JMenuBar();
        x = new JMenu("Main Menu");
        x1 = new JMenu("Login");
        m1 = new JMenuItem("Start Game");

        s1 = new JMenuItem("Existing User");
        s2 = new JMenuItem("New User");
        s3 = new JMenuItem("Help");
        s4 = new JMenuItem("View Statistics");

        m1.addActionListener(m);
        s3.addActionListener(m);
        s1.addActionListener(m);
        s2.addActionListener(m);
        s4.addActionListener(m);
        x.add(m1);

        x1.add(s1);
        x1.add(s2);
        x1.add(s3);
        x1.add(s4);
        x.add(x1);
        mb.add(x);
        panel2.add(l);
        //panel.add(ll);
        panel.add(lll);
        f.getContentPane().add(BorderLayout.CENTER, panel2);
        f.setJMenuBar(mb);

        f.setSize(1000,800);
        f.getContentPane().add(BorderLayout.SOUTH, panel);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
        String s = e.getActionCommand();

        lll.setText(s +"...");
        if( s == "Start Game") {
            lll.setText(s + " option is selected");
            if (e.getActionCommand() == "Start Game") {
                if (log == false) {
                    lll.setText("Starting Game...");
                }
                BlackjackGame newgame= new BlackjackGame();


            }
        }
        if( s == "Help") {
            l.setFont(new Font("Consolas", Font.BOLD, 12));
            l.setText("Cards are equal to their value with face cards being 10 and an Ace being 1 or 11, Players win their bet if they beat the dealer");

            lll.setText("Each player is dealt 2 cards." + "The dealer is dealt 2 cards with one face-up and one face-down.");
            lll.setText("Cards are equal to their value with face cards being 10 and an Ace being 1 or 11, Players win their bet if they beat the dealer");


        }

        }
    }


