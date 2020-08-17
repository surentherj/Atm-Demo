package oopsProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener
{
    JLabel l1, l2, title, tip;
    JTextField tf1;
    JButton btn1,btn2,btn3;
    JPasswordField p1;
    JLabel bg = new JLabel();
    public LoginPage()
    {
        JFrame frame = new JFrame("ATM");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        bg.setLayout(new FlowLayout());
        bg.setIcon(new ImageIcon("E:\\myFiles\\oopsProject\\images\\login.jpg"));

        title = new JLabel("WELCOME");
        title.setForeground(Color.blue);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        l1 = new JLabel("USERNAME ");
        l2 = new JLabel("PASSWORD ");
        tf1 = new JTextField("");
        p1 = new JPasswordField("");
        btn1 = new JButton("Sign In");
        btn2 = new JButton("Sign Up");
        btn3 = new JButton("Reset");
        tip = new JLabel("*(  Please  reset  before  you  leave  )*");

        l1.setForeground(Color.black);
        l2.setForeground(Color.black);
        tip.setForeground(Color.black);

        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));

        title.setBounds(600, 250, 400, 30);
        l1.setBounds(450, 300, 200, 30);
        l2.setBounds(450, 350, 200, 30);
        tf1.setBounds(600, 300, 200, 30);
        p1.setBounds(600, 350, 200, 30);
        btn1.setBounds(600, 400, 100, 30);
        btn2.setBounds(600, 450, 100, 30);
        btn3.setBounds(750, 400, 100, 30);
        tip.setBounds(500, 500, 600, 20);

        frame.add(l1);
        frame.add(l2);
        frame.add(tf1);
    //    frame.add(title);
        frame.add(p1);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(tip);
        frame.add(bg);

//        frame.setLayout(null);
        frame.setVisible(true);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btn1)
        {
            int count = 0;
            try
            {
                String name = tf1.getText();
                String pass = p1.getText();

                FileReader f1 = new FileReader("users.txt");
                FileReader f2 = new FileReader("passwords.txt");
                BufferedReader r1 = new BufferedReader(f1);
                BufferedReader r2 = new BufferedReader(f2);

                String s3,s4 = " ";
                int found = 0;
                while((s3 = r1.readLine()) != null)
                {
                    if(s3.equals(name))
                    {
                        found = 1;
                        break;
                    }
                    else
                    {
                        count++;
                    }
                }
                if(found == 1)
                {
                    int n = 0;
                    while (n <= count)
                    {
                        s4 = r2.readLine();
                        n++;
                    }
                    if (s4.equals(pass))
                    {
                        JOptionPane.showMessageDialog(this, "Logged In Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                        tf1.setText(null);
                        p1.setText(null);

                        oopsProject.AccountPage accountPage = new oopsProject.AccountPage(name);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            catch (Exception e)
            {

            }
        }
        else if(ae.getSource() == btn2)
        {
            SignUpPage signUpPage = new SignUpPage();
        }
        else if(ae.getSource() == btn3)
        {
            tf1.setText(null);
            p1.setText(null);
        }
    }
}
