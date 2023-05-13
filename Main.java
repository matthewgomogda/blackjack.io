import javax.swing.*;
import java.awt.event.*;
public class Main implements ActionListener {
    JTextField t1, t3;
    JPasswordField t2;
    JButton b, b2, b3, b4, b5;
    JLabel l1, l2;
    Main() {
        JFrame f = new JFrame();
// label
        l1 = new JLabel("Username");
        l1.setBounds(30, 80, 90, 30);
        l2 = new JLabel("Password");
        l2.setBounds(30, 130, 90, 30);
// textfield
        t3 = new JTextField();
        t3.setBounds(230, 105, 90, 30);
        t3.setEditable(false);
        t1 = new JTextField();
        t1.setBounds(130, 80, 90, 30);
        t2 = new JPasswordField();
        t2.setBounds(130, 130, 90, 30);
// button
        b = new JButton("login");
        b.setBounds(130, 180, 90, 30);
        b.addActionListener(this);
// frame
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(b);
        f.add(l1);
        f.add(l2);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
        String s1 = t1.getText();
        String s2 = t2.getText();
        String s3 = "";
        s1 = s1.toString();
        s2 = s2.toString();
        String u = "software";
        String p = "engineering";
// int a = Integer.parseInt(s1);
// int b = Integer.parseInt(s2);
// int c = 0;
// String s3 = "Success";
        boolean areEqual = s1.equalsIgnoreCase(u);
        boolean areEqual2 = s2.equals(p);
//System.out.println(s1);
//System.out.println(s2);
        if (e.getActionCommand() == "login") {
            if (areEqual && areEqual2) {
                s3 = "success";
                System.out.println("success");
// t3.setText("Successful");
            } else {
                System.out.println("unsuccessful");
                s3 = "unsuccessful";
            }
        }
        t3.setText(s3);
    }
    public static void main(String[] args) {
        new Main();
    }
}
