package oopsProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

public class AccountPage extends JFrame implements ActionListener
{
    JFrame frame;
    JButton btn1, btn2, btn3, btn4, btn5, btn6;
    JLabel title,bg =new JLabel();
    String user;
    oopsProject.UserDetails accountUser;
    public AccountPage(String name) throws Exception
    {
        user = name + ".txt";

        frame = new JFrame(name);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        bg.setLayout(new FlowLayout());
        bg.setIcon(new ImageIcon("E:\\myFiles\\oopsProject\\images\\acpage.jpg"));

        title = new JLabel("WELCOME "+name);
        title.setForeground(Color.blue);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        btn1 = new JButton("DEPOSIT");
        btn2 = new JButton("WITHDRAW");
        btn3 = new JButton("TRANSFER");
        btn4 = new JButton("CHECK BALANCE");
        btn5 = new JButton("LOGOUT");
        btn6 = new JButton("INFO");


        title.setBounds(550, 20, 400, 50);
        btn1.setBounds(110, 100, 400, 200);
        btn2.setBounds(700, 100, 400, 200);
        btn3.setBounds(110, 370, 400, 200);
        btn4.setBounds(700, 370, 400, 200);
        btn5.setBounds(550, 600, 100, 50);
        btn6.setBounds(520, 310, 170, 50);


        frame.add(title);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(btn4);
        frame.add(btn5);
        frame.add(btn6);
        frame.add(bg);

//        frame.setLayout(null);
        frame.setVisible(true);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);

        FileInputStream f1 = new FileInputStream(user);
        ObjectInputStream inp = new ObjectInputStream(f1);

        accountUser = (oopsProject.UserDetails)inp.readObject();

        inp.close();
        f1.close();

//        System.out.println(accountUser.name);
//        System.out.print(accountUser.password);


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btn1)
        {
            String amt = "0";
            amt = JOptionPane.showInputDialog("Enter the amount to deposit ");
            String s2 = "0";
            if(amt.equals(null) || amt.equals(""))
            {
                amt = "0";
            }

            int sum = Integer.parseInt(amt) + Integer.parseInt(accountUser.amount);
            accountUser.amount = new Integer(sum).toString();

            JOptionPane.showMessageDialog(this, "Amount Depositted "+sum, "Success", JOptionPane.INFORMATION_MESSAGE);

            try
            {
                FileOutputStream f2 = new FileOutputStream(user);
                ObjectOutputStream out = new ObjectOutputStream(f2);

                out.writeObject(accountUser);

                out.close();
                f2.close();
            }
            catch (Exception e)
            {

            }
        }
        else if(ae.getSource() == btn2)
        {
            String amt = JOptionPane.showInputDialog("Enter the amount to withdraw ");
            String s2 = "0";
            if(amt.equals(null) || amt.equals(""))
            {
                amt = "0";
            }

            if(Integer.parseInt(accountUser.amount) < Integer.parseInt(amt))
            {
                JOptionPane.showMessageDialog(this, "Insufficient Balance ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int balance = Integer.parseInt(accountUser.amount) - Integer.parseInt(amt);
                accountUser.amount = new Integer(balance).toString();

                JOptionPane.showMessageDialog(this, "Amount withdrawn "+amt, "Success", JOptionPane.INFORMATION_MESSAGE);

                try
                {
                    FileOutputStream f2 = new FileOutputStream(user);
                    ObjectOutputStream out = new ObjectOutputStream(f2);

                    out.writeObject(accountUser);

                    out.close();
                    f2.close();
                }
                catch (Exception e)
                {

                }
            }
        }
        else if(ae.getSource() == btn3)
        {
            oopsProject.AccountTransfer accountTransfer = new oopsProject.AccountTransfer(user);
        }
        else if(ae.getSource() == btn4)
        {
            String s2 = "0";
            try
            {
                FileInputStream f1 = new FileInputStream(user);
                ObjectInputStream inp = new ObjectInputStream(f1);

                accountUser = (oopsProject.UserDetails)inp.readObject();

                inp.close();
                f1.close();
            }
            catch (Exception e)
            {

            }
            s2 = accountUser.amount;
            JOptionPane.showMessageDialog(this, "The Balance is  "+s2, "Success", JOptionPane.INFORMATION_MESSAGE);

        }
        else if (ae.getSource() == btn6)
        {
            oopsProject.InfoWindow infoWindow = new oopsProject.InfoWindow(user);
        }
        else if (ae.getSource() == btn5)
        {
            if(JOptionPane.showConfirmDialog(this, "Are you sure ?") == 0)
            {
                frame.setVisible(false);
            }
        }

    }
}
