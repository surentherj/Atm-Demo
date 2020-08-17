package oopsProject;

import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignUpPage extends JFrame implements ActionListener
{
    /*
    * Name
    * UserName
    * MailId
    * Password
    * ConfirmPassword
    * */
    JFrame frame;
    JLabel title,l1,l2,l3,l4,l5,l6,bg = new JLabel() ;
    JTextField tf1,tf2,tf3,tf4;
    JPasswordField p1,p2;
    JButton btn1;

    public SignUpPage()
    {
        frame = new JFrame("SignUpPage");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        bg.setLayout(new FlowLayout());
        bg.setIcon(new ImageIcon("E:\\myFiles\\oopsProject\\images\\login.jpg"));

        title = new JLabel("SIGN UP");
        title.setForeground(Color.blue);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        l1 = new JLabel("NAME               ");
        l2 = new JLabel("USERNAME           ");
        l3 = new JLabel("MAIL ID            ");
        l4 = new JLabel("PASSWORD           ");
        l5 = new JLabel("CONFIRM PASSWORD   ");
        l6 = new JLabel("BASE DEPOSIT       ");

        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l4.setFont(new Font("Serif", Font.BOLD, 20));
        l5.setFont(new Font("Serif", Font.BOLD, 20));
        l6.setFont(new Font("Serif", Font.BOLD, 20));

        p1 = new JPasswordField();
        p2 = new JPasswordField();
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        btn1 = new JButton("Sign Up");

        title.setBounds(600,200,400,30);
        l1.setBounds(350, 250, 200, 30);
        l2.setBounds(350, 300, 200, 30);
        l3.setBounds(350, 350, 200, 30);
        l6.setBounds(350, 400, 200, 30);
        l4.setBounds(350, 450, 200, 30);
        l5.setBounds(350, 500, 250, 30);

        tf1.setBounds(650, 250, 200, 30);
        tf2.setBounds(650, 300, 200, 30);
        tf3.setBounds(650, 350, 200, 30);
        tf4.setBounds(650, 400, 200, 30);
        p1.setBounds(650, 450, 200, 30);
        p2.setBounds(650, 500, 200, 30);

        btn1.setBounds(600, 550, 100, 30);

        frame.add(title);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(l6);

        frame.add(tf4);
        frame.add(tf1);
        frame.add(tf2);
        frame.add(tf3);
        frame.add(p1);
        frame.add(p2);

        frame.add(btn1);

        frame.add(bg);

//        frame.setLayout(null);
        frame.setVisible(true);

        btn1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btn1) {
            String ps1 = p1.getText();
            String ps2 = p2.getText();
            String name = tf1.getText();
            String user = tf2.getText();
            String mail = tf3.getText();
            String amount = tf4.getText();
            try
            {
                if(tf1.getText().equals("") || tf2.getText().equals("")  || tf3.getText().equals("") || tf4.getText().equals("") || p1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this,"Fileld must not be empty" , "Error" , JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (!oopsProject.MailCheck.isValidMail(mail))
                    {
                        JOptionPane.showMessageDialog(this,"Enter a valid mail id" , "Error" , JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(Integer.parseInt(amount) < 500)
                        {
                            JOptionPane.showMessageDialog(this,"Deposit must be atleast 500" , "Error" , JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            if (ps1.equals(ps2) && ps1 != null)
                            {
                                FileWriter file1 = new FileWriter("users.txt", true);
                                FileWriter file2 = new FileWriter("passwords.txt", true);
                                FileReader f1 = new FileReader("users.txt");
                                BufferedReader r1 = new BufferedReader(f1);
                                String s3;
                                int found = 0;
                                while ((s3 = r1.readLine()) != null)
                                {
                                    if(s3.equals(user))
                                        found = 1;
                                }
                                if(found != 1)
                                {
                                    oopsProject.UserDetails userDetails = new oopsProject.UserDetails(name,user,mail,amount,ps1);

                                    String str = user+".txt";
                                    FileOutputStream file = new FileOutputStream(str);
                                    ObjectOutputStream out = new ObjectOutputStream(file);

                                    out.writeObject(userDetails);

                                    out.close();
                                    file.close();

                                    FileInputStream f5 = new FileInputStream(str);
                                    ObjectInputStream inp = new ObjectInputStream(f5);

                                    oopsProject.UserDetails login = (oopsProject.UserDetails)inp.readObject();

                                    inp.close();
                                    f5.close();


                                    BufferedWriter b1 = new BufferedWriter(file1);
                                    BufferedWriter b2 = new BufferedWriter(file2);
                                    b1.write(user);
                                    b1.write("\n");
                                    b2.write(ps1);
                                    b2.write("\n");
                                    b1.close();
                                    b2.close();

                                    JOptionPane.showMessageDialog(this, "Account Created", "Success", JOptionPane.INFORMATION_MESSAGE);

                                    frame.setVisible(false);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this, "Username Already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this, "Passwords did not match", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
            catch(Exception e)
            {
                System.out.print("bad");
            }
        }
    }
}
