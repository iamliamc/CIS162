
/**
 * Write a description of class Pig here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pig
{
    // instance variables - replace the example below with your own
    private GVdie d1;
    private GVdie d2;
    private int computerScore;
    private int playerScore;
    private int currentRoundScore;
    final int WINNING_SCORE = 100;
    private boolean playerTurn;

    /**
     * Constructor for objects of class Pig
     */
    public Pig()
    {
        System.out.println("Ready to play Pig?!?");
        d1 = new GVdie(100);
        d2 = new GVdie(100);
        computerScore = 0;
        playerScore = 0;
        currentRoundScore = 0;
        playerTurn = true;
    }
    //Returns currentRoundScore
    public int getRoundScore()
    {
        return currentRoundScore;
    }
    //Returns playerScore
    public int getPlayerScore ()
    {
        return playerScore;
    }
    //Returns computerScore
    public int getComputerScore()
    {
        return computerScore;
    }

    public void rollDice()
    {
        d1.roll();
        d2.roll();
        System.out.println("die1: " + d1.getValue() + " die2: " + d2.getValue());
        if (d1.getValue() == 1 || d2.getValue() == 1){
            currentRoundScore = 0;
        }
        else {
            currentRoundScore += (d1.getValue() + d2.getValue());
        }
        System.out.println("Current Round Score = " + currentRoundScore);

    }

    public void playerRolls()
    {
        rollDice();
        if (playerScore >= 100)
        {
            System.out.println("Player wins!!!");
        }
        else if (d1.getValue() == 0 && d2.getValue() == 0){
            playerScore = 0;
            playerTurn = false;
            System.out.println("Terrible roll! Player Score set to 0!");
        }
        else if (d1.getValue() == 0 || d2.getValue() == 0){
            System.out.println("Bad roll - turns over! Player points = " + playerScore);
            currentRoundScore = 0;
            playerTurn = false;
        }

    }

    public void playerHolds()
    {
        playerScore += currentRoundScore;
        currentRoundScore = 0;
        System.out.println("Player's current score = " + playerScore);
        playerTurn  = false;
    }

    public void computerTurn()
    {
        while (currentRoundScore < 19)
        {
            rollDice();
            if (d1.getValue() == 1 && d2.getValue() == 1){
                computerScore = 0;
                currentRoundScore = 0;
                playerTurn = true;
                break;
            }
            else if (d1.getValue() == 1 || d2.getValue() == 1){
                currentRoundScore = 0;
                playerTurn = true;
                break;
            }
        }
        computerScore += currentRoundScore;
        currentRoundScore = 0;
        System.out.println("Computer's current score = " + computerScore);
    }

    public void restart ()
    {
        computerScore = 0;
        playerScore = 0;
        currentRoundScore = 0;
        playerTurn = true;
    }

    public GVdie getDie(int num)
    {
        if (num == 1){
            return d1;
        }
        else if (num == 2){
            return d2;
        }
        else {
            System.out.println("Not a legal value!");
        }      
        return getDie(num);
    }

    public boolean playerWon(){
        if (playerScore >= 100){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean computerWon(){
        if (computerScore >= 100){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isPlayerTurn()
    {
        if (playerTurn == true){
            return true;
        }
        else {
            return false;
        }
    }

    public void playerTurn ()
    {
        while (currentRoundScore < 19)
        {
            rollDice();
            if (d1.getValue() == 1 && d2.getValue() == 1){
                playerScore = 0;
                currentRoundScore = 0;
                playerTurn = false;
                break;
            }
            else if (d1.getValue() == 1 || d2.getValue() == 1){
                currentRoundScore = 0;
                playerTurn = false;
                break;
            }
        }
        playerScore += currentRoundScore;
        currentRoundScore = 0;
        System.out.println("Player's current score = " + playerScore);
    }

    public void autoGame ()
    {
        while ((playerScore < 100) && (computerScore < 100))
        {
            playerTurn();
            computerTurn();
        }
    }

}
