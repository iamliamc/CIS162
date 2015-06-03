import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/******************************************
 * PigTest class to test the Pig class
 * @author - CIS-162 
 * @version - Fall 2012
 *******************************************/
public class PigTest {
    private static StringBuilder textLog;
    private static PrintStream stdout;
    private final static int WIN_SCORE = 100;
    private final static int COMPUTER_ROUND_LIMIT = 20;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        textLog = new StringBuilder();
        /* save the original System.out */
        stdout = System.out;
        System.setOut(new PrintStream(logger));

    }

    @AfterClass
    public static void tearDown() {
        /* restore System.out to its original stdout */
        System.setOut(stdout);
    }

    @Test
    public void testConstructor() {
        textLog.setLength(0);
        Pig p = new Pig();
        assertTrue("Missing a welcome message", textLog.length() != 0);
        assertEquals("player score should be initialiazed to zero", 0,
            p.getPlayerScore());
        assertEquals("computer score should be initialiazed to zero", 0,
            p.getComputerScore());
        assertEquals("round score should be initialiazed to zero", 0,
            p.getRoundScore());
    }

    private boolean isOne(Pig p) {
        return p.getDie(1).getValue() == 1 || p.getDie(2).getValue() == 1;
    }

    private boolean isOnes(Pig p) {
        return p.getDie(1).getValue() == 1 && p.getDie(2).getValue() == 1;
    }

    private int sumOfDices (Pig p)
    {
        return p.getDie(1).getValue() + p.getDie(2).getValue();
    }

    @Test
    public void testDieAccessor() {
        Pig p = new Pig();
        GVdie d1, d2;
        d1 = p.getDie(1);
        d2 = p.getDie(2);
        assertNotNull("getDie() should not return null", d1);
        assertNotSame("getDie(1) and getDie(2) should return different objects",
            d1, d2);
    }

    /* bring the game to an initial non-zero state */
    private boolean setNonZero (Pig p)
    {
        final int MAX_TURNS = 5000;
        GVdie d1, d2;
        d1 = p.getDie(1);
        d2 = p.getDie(2);
        int oldPlayer = p.getPlayerScore();
        /* do a few rolls to start with non-zero initial values */
        int turns = 0;
        do {
            //            textLog.setLength(0);
            p.playerRolls();
            //            System.err.println (textLog.toString());
            /*
             * keep a turn counter so when the user's implementation is
             * incomplete, we don't end up in an infinite loop
             */
            turns++;
        } while (p.getRoundScore() == 0 && turns < MAX_TURNS);
        assertEquals("playerRolls() should not update player score", 
            oldPlayer, p.getPlayerScore());
        return turns != MAX_TURNS;
    }

    @Test
    public void testPlayerRolls() {
        Pig p = new Pig();
        if (setNonZero (p)) {
            textLog.setLength(0);
            /* get the current state */
            int lastRound = p.getRoundScore();
            p.playerRolls();
            int currRound = p.getRoundScore();
            if (!isOne(p)) {
                assertEquals("playerRolls(): round score is not updated correctly ",
                    lastRound + sumOfDices(p), currRound);
            }
            else {
                assertEquals ("playerRolls(): round score should be reset to zero", 0, p.getRoundScore());
                assertTrue ("playerRolls(): missing message when the round is over?", textLog.length() > 0);
            }
        }
        else
            fail("Do you have a complete implementation of playerRolls()?");
    }

    @Test
    public void testPlayerRollsUntilPlayerLost() {
        Pig p = new Pig();
        if (setNonZero (p)) {
            /* get the current state */
            do {
                textLog.setLength(0);
                p.playerRolls();
            } while (!isOnes(p));
            assertEquals ("playerRolls(): player score should be reset to zero", 0, p.getPlayerScore());
            assertTrue ("playerRolls(): missing message when the round is over? ", textLog.length() > 0);
        }
        else
            fail("Do you have a complete implementation of playerRolls()?");
    }

    @Test
    public void testRollToWin()
    {
        Pig p = new Pig();
        if (setNonZero (p))
        {
            do {
                textLog.setLength(0);
                p.playerRolls();
            } while (p.getRoundScore() < WIN_SCORE);
            assertTrue ("playerRolls(): missing output when player won?", textLog.length() > 0);
            //            assertTrue ("incorrect implementation of playerWon()", p.playerWon());
        }
        else
            fail("Do you have a complete implementation of playerRolls()?");
    }

    @Test
    public void testHold()
    {
        Pig p = new Pig();
        if (setNonZero (p))
        {
            int oldPlayer = p.getPlayerScore();
            int round = p.getRoundScore();
            textLog.setLength(0);
            p.playerHolds();
            assertEquals("playerHolds() should update player's score", 
                oldPlayer + round, p.getPlayerScore());
            assertEquals("playerHolds() should reset round score", 
                0, p.getRoundScore());
            assertTrue ("playerHolds() should print players score", textLog.length() > 0);
        }
        else
            fail("Do you have a complete implementation of playerRolls()?");
    }

    @Test
    public void testRollandHoldToWin()
    {
        Pig p = new Pig();
        if (setNonZero (p))
        {
            do {
                textLog.setLength(0);
                p.playerRolls();
                p.playerHolds();
            } while (p.getPlayerScore() < WIN_SCORE);
            assertTrue ("incorrect implementation of playerWon()", p.playerWon());
        }
        else
            fail("Do you have a complete implementation of playerRolls()?");
    }

    @Test
    public void testComputerTurn()
    {
        Pig p = new Pig();
        int oldRound, newRound, oldScore, newScore;
        int turns = 0;
        while (turns < 500) {
            oldScore = p.getComputerScore();
            oldRound = p.getRoundScore();
            p.computerTurn();
            turns++;
            newScore = p.getComputerScore();
            newRound = p.getRoundScore();
            if (isOnes(p))
                assertEquals("After rolling (1,1), computer score should be reset to 0",
                    0, newScore);
            else if (isOne(p)) {
                assertEquals("After rolling (1,x), round score should be reset to 0",
                    0, newRound);
            }
            else if (newScore - oldScore < COMPUTER_ROUND_LIMIT) {
                assertTrue("Computer quit too early",
                    newScore - oldScore < COMPUTER_ROUND_LIMIT);
            }
            else if (newScore >= WIN_SCORE) {
                assertTrue("Computer won but but not recognized",
                    p.computerWon());
                break;        
            }

        }
        if (turns == 500)
            fail ("Incomplete implementation of coputerTurn()?");
    }

    @Test
    public void testRestart() {
        Pig p = new Pig();
        if (setNonZero(p)) {
            p.playerHolds();
            assertTrue(p.getPlayerScore() > 0);
            int turns = 0;
            do {
                p.computerTurn();
                turns++;
            } while (p.getComputerScore() == 0 && turns < 500);
            if (turns < 500) {
                p.restart();
                assertEquals("restart() should reset round score", 0, p.getRoundScore());
                assertEquals("restart() should reset player score", 0, p.getPlayerScore());
                assertEquals("restart() should reset computer score", 0, p.getComputerScore());
            }
            else
                fail ("Incomplete implementation of computerTurn()?");

        }
        else
            fail("Do you have a complete implementation of playerRolls()?");
    }

    @Test
    public void testGUIMethods() {
        Pig p = new Pig();
        assertTrue("player's true should be true", p.isPlayerTurn());
        p.playerHolds();
        assertFalse("playerHold() should turn over to computer", p.isPlayerTurn());
        p.computerTurn();
        assertTrue("computerTurn() should switch turn back to player", p.isPlayerTurn());
        p.playerRolls();
        p.playerHolds();
        assertFalse("playerHold() should turn over to computer", p.isPlayerTurn());
        p.restart();
        assertTrue("After restart, the player is ready to do his turn", p.isPlayerTurn());
    }

    /* Use the following class to capture all output from System.out */
    private static OutputStream logger = new OutputStream() {
            @Override
            public void write(int b) {
                textLog.append((char) b);
            }

        };

}
