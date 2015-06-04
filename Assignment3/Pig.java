
/**
 * Write a description of class Pig here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pig
{
    /** instance variables - make our two dies, our variables to store computerScore and playerScore, the current round score, 
     the winning score, and a boolean to determine whose turn it is **/
    private GVdie d1;
    private GVdie d2;
    private int computerScore;
    private int playerScore;
    private int currentRoundScore;
    final int WINNING_SCORE = 100;
    private boolean playerTurn;

    /**
     * Constructor for object of class Pig only one instantiates size of dice, and values of scores, and player starts first
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
    //Uses GVdie method .roll() to set values for our dies for this turn, 
    //prints out the dies values, checks if either are equal to 1 and if so sets the score to 0 else adds them together
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
    //Single player turn, rolls die - if their score is greater or equal to 100 player wins, if they roll two 1's their turn is over and their score is set to 0
    //If one die matches 1 than the current round score is set to 0
    public void playerRolls()
    {
        rollDice();
        if (playerScore >= 100)
        {
            System.out.println("Player wins!!!");
        }
        else if (d1.getValue() == 1 && d2.getValue() == 1){
            playerScore = 0;
            currentRoundScore = 0;
            playerTurn = false;
            System.out.println("Terrible roll! Player Score set to 0!");
        }
        else if (d1.getValue() == 1 || d2.getValue() == 1){
            System.out.println("Bad roll - turns over! Player points = " + playerScore);
            currentRoundScore = 0;
            playerTurn = false;
        }

    }
    //Player end's their turn, the currentRoundScore is added to their playerScore, the currentRound is reset, the turn is ended
    public void playerHolds()
    {
        playerScore += currentRoundScore;
        currentRoundScore = 0;
        System.out.println("Player's current score = " + playerScore);
        playerTurn  = false;
    }
    //Computers turn, while their score is less than 19 they keep playing, if they get two 1s turn over score and currentRoundScore reset if they pass 19 they hold
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
    // resets all the instance variables
    public void restart ()
    {
        computerScore = 0;
        playerScore = 0;
        currentRoundScore = 0;
        playerTurn = true;
    }
    // probably used by the GUI to return the die we instantiate in this class
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
    // if the playerScore surpases the WINNING_SCORE playerWon is true
    public boolean playerWon(){
        if (playerScore >= WINNING_SCORE){
            return true;
        }
        else {
            return false;
        }
    }
    // if the computerScore surpases the WINNING_SCORE computerWon is true
    public boolean computerWon(){
        if (computerScore >= WINNING_SCORE){
            return true;
        }
        else {
            return false;
        }
    }
    //the method we use to check whose turn it is
    public boolean isPlayerTurn()
    {
        if (playerTurn == true){
            return true;
        }
        else {
            return false;
        }
    }
    //part of our autoplay method, the player plays the same way as the computer!
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
    //player and computer (both playing by the same rules) whoever get's 100 first wins
    public void autoGame ()
    {
        while ((playerScore < WINNING_SCORE) && (computerScore < WINNING_SCORE))
        {
            playerTurn();
            computerTurn();
        }
    }

}
