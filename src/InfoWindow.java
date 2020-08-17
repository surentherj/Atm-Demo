package oopsProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InfoWindow extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,bg = new JLabel();
    JLabel r1,r2,r3,r4;
    JFrame frame;
    JButton btn1;
    oopsProject.UserDetails info;

    public InfoWindow(String user)
    {
        try
        {
            FileInputStream f1 = new FileInputStream(user);
            ObjectInputStream inp1 = new ObjectInputStream(f1);

            info = (oopsProject.UserDetails) inp1.readObject();

            inp1.close();
            f1.close();
        }
        catch (Exception e)
        {

        }


        frame = new JFrame("INFO");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        bg.setLayout(new FlowLayout());
        bg.setIcon(new ImageIcon("E:\\myFiles\\oopsProject\\images\\home22.jpg"));

        l1 = new JLabel("NAME ");
        l2 = new JLabel("USERNAME");
        l3 = new JLabel("MAILID");
        l4 = new JLabel("BALANCE");

        r1 = new JLabel(info.name);
        r2 = new JLabel(info.userName);
        r3 = new JLabel(info.mailId);
        r4 = new JLabel(info.amount);

        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l4.setFont(new Font("Serif", Font.BOLD, 20));
        r1.setFont(new Font("Serif", Font.BOLD, 20));
        r2.setFont(new Font("Serif", Font.BOLD, 20));
        r3.setFont(new Font("Serif", Font.BOLD, 20));
        r4.setFont(new Font("Serif", Font.BOLD, 20));

        btn1 = new JButton("Close");


        l1.setBounds(450, 100, 200, 50);
        l2.setBounds(450, 150, 200, 50);
        l3.setBounds(450, 200, 200, 50);
        l4.setBounds(450, 250, 200, 50);

        r1.setBounds(650, 100, 200, 50);
        r2.setBounds(650, 150, 200, 50);
        r3.setBounds(650, 200, 200, 50);
        r4.setBounds(650, 250, 200, 50);

        btn1.setBounds(550, 300, 100, 30);


        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);

        frame.add(r1);
        frame.add(r2);
        frame.add(r3);
        frame.add(r4);
        frame.add(btn1);

        frame.add(bg);

        btn1.addActionListener(this);

//        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btn1)
        {
            frame.setVisible(false);
        }
    }
}
