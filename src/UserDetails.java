package oopsProject;

import java.io.*;

public class UserDetails implements Serializable
{
    public String name, userName, mailId, amount, password;

    public UserDetails(String a,String b, String c,String d,String e)
    {
        name = a;
        userName = b;
        mailId = c;
        amount = d;
        password = e;
    }
    public String retName()
    {
        return name;
    }
    public String retUserName()
    {
        return userName;
    }
    public String SretMailId()
    {
        return mailId;
    }
    public String retAmount()
    {
        return amount;
    }
    public String retPassword()
    {
        return password;
    }
}
