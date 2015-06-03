import java.util.Scanner;
/***********************************************
 * Write a description of the Game class here.
 * 
 * @author Brandon Shannon and Liam Considine
 * @version 6/02/15
 ***********************************************/
public class Game
{
    /** Objects of the GVdie class  */
    private GVdie d1;
    private GVdie d2;
    private GVdie d3;

    /** credit balance  */
    private int credits;

    /**message string**/
    private String message;

    /********************************************
     * Constructor for objects of class Chuck
     *******************************************/
    public Game(String message)
    {
        // instantiate the 3 GVdie 
        d1 = new GVdie();
        d2 = new GVdie();
        d3 = new GVdie();
        // initialize credits to 0
        credits = 0;
        // initialize message 
        this.message = message;
    }

    /************************************************
     * getMessage - returns the message
     ***********************************************/
    public String getMessage ( ) 
    {
        return message;
    }

    /************************************************
     * setMessage - sets the message to the input
     * parameter
     ***********************************************/
    public void setMessage (String message) 
    {
        this.message = message;
    }

    /************************************************
     * getCredits - returns the credits
     ***********************************************/    
    public int getCredits ( )
    {
        return credits;
    }

    /************************************************
     * addCredits - add  amount to the credits.  
     ***********************************************/    
    public void addCredits (int amount) 
    {
        credits = credits + amount;
    }

    /***********************************************************
     * rollDice - Roll the three dice invoking the roll method of the 
     * GVdie class
     **********************************************************/        
    public void rollDice ( ) 
    {
        d1.roll();
        d2.roll();
        d3.roll();
        // complete to roll d2 and d3
    }

    /***********************************************************
     * return true if two of the dice match num, 
     * return false otherwise.
     **********************************************************/       
    public boolean isDoubles (int num) 
    {
        int v1 = d1.getValue();
        int v2 = d2.getValue();
        int v3 = d3.getValue();
        // complete  to get the value of d2 and d3
        if ((num == v1 && num == v2) || (num == v2 && num == v3) || (num == v1 && num == v3)){
            return true;
        }
        else {
            return false;
        }
        // write the required logic 
    }

    /***********************************************************
     *return true if all three dice are identical to the number
     *passed as parameter. return false otherwise.
     **********************************************************/
    public boolean isTriplets (int num ) 
    {
        int v1 = d1.getValue();
        int v2 = d2.getValue();
        int v3 = d3.getValue();
        if (num == v1 && num == v2 && num == v3){
            return true;
        }
        else {
            return false;
        }

        // write the required logic 
    }

    /***********************************************************
     *return true if the num passed as parameter was rolled
     **********************************************************/
    public boolean wasNumberRolled(int num ) 
    {
        int v1 = d1.getValue();
        int v2 = d2.getValue();
        int v3 = d3.getValue();
        // complete  to get the value of d2 and d3
        if ((v1 == num && num != v2 && num != v3) || (v2 == num && num != v1 && num != v3) || (v3 == num && num != v1 && num != v2)){
            return true;
        }
        else { 
            return false;
        }
        // write the required logic 
    }

    /***********************************************************
     *return the value of the die passed as parameter
     **********************************************************/
    public int getDieValue (int num)
    {
        int die = 0;
        
        if (num == 1){
            die = d1.getValue();
        }
        else if (num == 2){
            die = d2.getValue();
        }
        else if (num == 3){
            die = d3.getValue();
        }
        return die;
        // continue with the logic to return value of d2 and value of d3
    }

    /***********************************************************
     *Check how many of the dice match num.  
     *If all three dice, increase the credits by 30.  
     *If only two dice, increase the credits by 20  
     *If only one die, increase credits by 10  
     **********************************************************/     
    public static void main (String [] args ) 
    {
        Scanner scan = new Scanner (System.in);
        int guess;

        // instantiate an object of the Game class named myGame.  Pass 
        // an interesting message as input parameter!
        Game myGame = new Game ("Welcome");
        // Printing the initial message from the game
        System.out.println(myGame.getMessage());

        //Prompts the user to guess an integer number between 1 - 6   
        System.out.println("Please guess an integer between 1 and 6");
        while (myGame.credits < 100) {
        guess = scan.nextInt();
        
        while (guess <= 0 || guess > 6){
            System.out.println("Please guess an integer between 1 and 6");
            guess = scan.nextInt();
        }

        System.out.println("your guess was: " + guess);

        // invoke the rollDice method of the game class

        myGame.rollDice();

        // Printing the values of the rolled dice
        System.out.println("Die1 is: " + myGame.getDieValue(1));
        // complete for Die2 and Die3

        System.out.println("Die2 is: " + myGame.getDieValue(2));
        
        System.out.println("Die3 is: " + myGame.getDieValue(3));
        if (myGame.isTriplets(guess)) 
        {
            // add 30 to the credits

            myGame.addCredits(30);
            
            // set the message to "Tripples!"
            myGame.setMessage("Tripples!");

        }
        else if (myGame.isDoubles(guess)){
            myGame.addCredits(20);
            
            myGame.setMessage("Doubles!");
        }
        else if (myGame.wasNumberRolled(guess)){
            myGame.addCredits(10);
            
            myGame.setMessage("Your guess was matched once");
        }
        else {
            myGame.setMessage("No luck!");
        }

        // Complete the logic for doubles and wasNumberRolled

        // printing the results of the game
            System.out.println ("Results: " + myGame.getMessage() + " Credits: " + myGame.getCredits());
    }
}
}

