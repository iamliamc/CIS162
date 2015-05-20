
/**
 * This program creates user log ins
 * 
 * @author Brandon Shannon Liam Considine
 * @version 5/19/2015
 */

import java.util.Scanner;
import java.util.Random;

public class Login
{
    // instance variables - replace the example below with your own
    private String firstName;
    private String lastName;
    /**
     * Constructor for objects of class Login
     */
    public Login()
    {
        // initialise instance variables
        firstName = " ";
        lastName = " ";
        
        
    }
    public Login(String pfirstName, String plastName)
    {
        // initialise instance variables
        firstName = pfirstName;
        lastName = plastName;
        
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String args[])
    {
//         String firstName = " ";
//         String lastName = " ";
        Scanner scnr = new Scanner(System.in);

        Login user = new Login();
        System.out.println("Please enter first name");
        user.firstName = scnr.next();

        System.out.println("Please enter last name");
        user.lastName = scnr.next(); 

        Random gen = new Random ();

        int num = gen.nextInt(89) + 10;

        System.out.println("Your login name: " + user.lastName.substring(0,6) + user.firstName.charAt(0) + num);

    }
}
