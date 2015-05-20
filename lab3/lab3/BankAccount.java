
/**
 * Lab 3 Bank Account Class
 * This program keeps track of name, balance, and ID number
 * Liam Considine Brandon Shannon
 * @version 5/19/2015
 */

import java.text.NumberFormat;

public class BankAccount
{
    // instance variables - replace the example below with your own
    private String name;
    private int acct;
    private double bal;
    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * Constructor for objects of class BankAccount
     */
    public BankAccount()
    {
        // initialise instance variables
        name = "Fresh account";
        acct = 00000000;
        bal = 0;
    }

    public BankAccount(String input_name, int input_acct, double input_bal)
    {
        // initialise instance variables
        name = input_name;
        acct = input_acct;
        bal = input_bal;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getName()
    {
        // put your code here
        return name;
    }

    public int getAcc()
    {
        // put your code here
        return acct;
    }

    public double getbalance()
    {
        // put your code here
        return bal;
    }

    public void setName(String pname)
    {
        // put your code here
        name = pname;
    }

    public void setAcc(int pacct)
    {
        // put your code here
        acct = pacct;
    }

    public void setBal(double pbal)
    {
        // put your code here
        bal = pbal;
    }

    public void makeDeposit(double amount)
    {
        bal = bal + amount;
    }

    public void makeWithdrawal(double amount)
    {
        // put your code here
        bal = bal - amount;
    }

    public String toString()
    {
        // put your code here
        return "Name: "+ name + " Account: " + acct + " Balance: " + fmt.format(bal); 
    }
}
