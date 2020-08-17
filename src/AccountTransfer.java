package oopsProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.lang.*;

public class AccountTransfer extends JFrame implements ActionListener
{
    JFrame frame;
    JLabel title,l1,l2,bg = new JLabel();
    JButton btn1;
    JTextField tf1,tf2;
    String source,dest;
    oopsProject.UserDetails from;
    oopsProject.UserDetails to;
    public AccountTransfer(String user)
    {
        source = user;

        frame = new JFrame("AccountTransfer");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        bg.setLayout(new FlowLayout());
        bg.setIcon(new ImageIcon("E:\\myFiles\\oopsProject\\images\\transfer.jpg"));

        title = new JLabel("Fund Transfer");
        title.setForeground(Color.blue);
        title.setFont(new Font("Serif", Font.BOLD, 20));


        l1 = new JLabel("To User   ");
        l2 = new JLabel("Amount    ");
        tf1 = new JTextField();
        tf2 = new JTextField();
        btn1 = new JButton("Transfer");

        title.setBounds(550, 20, 400, 50);
        l1.setBounds(400, 100, 150, 30);
        l2.setBounds(400, 150, 150, 30);
        tf1.setBounds(600, 100, 200, 30);
        tf2.setBounds(600, 150, 200, 30);
        btn1.setBounds(550, 200, 100, 20);

        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));

        frame.add(title);
        frame.add(l1);
        frame.add(l2);
        frame.add(tf1);
        frame.add(tf2);
        frame.add(btn1);
        frame.add(bg);

        btn1.addActionListener(this);

//        frame.setLayout(null);
        frame.setVisible(true);

        try
        {
            FileInputStream f1 = new FileInputStream(user);
            ObjectInputStream inp = new ObjectInputStream(f1);
            from = (oopsProject.UserDetails)inp.readObject();
            inp.close();
            f1.close();
        }
        catch (Exception e)
        {

        }
    }
    public void transfer()
    {
        try
        {
            String s1 = tf1.getText();
            String s2 = tf2.getText();

            FileReader f1 = new FileReader("users.txt");
            BufferedReader r1 = new BufferedReader(f1);

            String s3,s4 = "0";

            int found = 0;
            while((s3 = r1.readLine()) != null)
            {
                if(s3.equals(s1))
                {
                    found = 1;
                    break;
                }
            }
            if(found == 1)
            {
                dest = s1+".txt";

                FileInputStream f2 = new FileInputStream(dest);
                ObjectInputStream inp2 = new ObjectInputStream(f2);

                to = (oopsProject.UserDetails)inp2.readObject();

                inp2.close();
                f2.close();

                s4 = from.amount;

                if(Integer.parseInt(s2) < Integer.parseInt(s4))
                {

                    int sum = Integer.parseInt(s2) + Integer.parseInt(to.amount);
                    int diff = Integer.parseInt(s4) - Integer.parseInt(s2);

                    String toAmount = new Integer(sum).toString();
                    String fromAmount = new Integer(diff).toString();

                    FileOutputStream f3 = new FileOutputStream(source);
                    ObjectOutputStream out2 = new ObjectOutputStream(f3);

                    FileOutputStream f4 = new FileOutputStream(dest);
                    ObjectOutputStream out3 = new ObjectOutputStream(f4);

                    from.amount = fromAmount;
                    to.amount = toAmount;

                    out2.writeObject(from);
                    out3.writeObject(to);

                    out2.close();
                    f3.close();

                    out3.close();
                    f4.close();

                    JOptionPane.showMessageDialog(this, "SuccessFull Transaction", "Success", JOptionPane.INFORMATION_MESSAGE);

                    frame.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "To user not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e)
        {

        }

    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btn1)
        {
            transfer();
        }
    }

}
