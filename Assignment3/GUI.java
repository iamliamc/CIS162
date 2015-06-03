/***************************************************************
 * GUI front end to the game of Pig
 * 
 * @author Scott Grissom 
 * @version September 14, 2012
 ***************************************************************/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI implements ActionListener
{

    /** visual representation of the dice */
    GVdie d1, d2;

    /** buttons and labels */
    JButton roll, hold, compButton;
    JLabel message, round, player, computer;
    JFrame myGUI;
    Pig game;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem playItem;
    JMenuItem restartItem;      

    /****************************************************************
    Create all elements and display within the GUI
     ****************************************************************/    
    public static void main(String args[]){

        new GUI();
    }

    public GUI(){ 

        // create the game object as well as the GUI Frame   
        game = new Pig();
        myGUI = new JFrame();
        myGUI.setSize(400,400);
        
        //Change the JFrame title to include your name
        myGUI.setTitle("Ana's Game of Pig");    

        // place the die in the middle of the screen
        JPanel panel = new JPanel();
        d1 = game.getDie(1);
        d2 = game.getDie(2);
        panel.add(d1);
        panel.add(d2);
        myGUI.add(panel);

        // create the buttons and message field
        message = new JLabel();
        myGUI.add(BorderLayout.NORTH,message);
        panel = new JPanel();
        roll = new JButton("Roll");
        hold = new JButton("Hold");
        compButton = new JButton("Computer");
        
        // set the computerButton to false 
        compButton.setEnabled(false);
        
        // register the listeners
        roll.addActionListener(this);
        hold.addActionListener(this);
        compButton.addActionListener(this);

        // create the labels
        round = new JLabel ("Round: 0");
        player = new JLabel ("Player: 0");  
        computer = new JLabel ("Computer: 0");
        player.setForeground(Color.red);

        // position the GUI items on the screen
        JPanel top = new JPanel();
        top.add(player);
        top.add(new JLabel(" - "));
        top.add(round);
        top.add(new JLabel(" - "));    
        top.add(computer);
        panel.add(roll);
        panel.add(hold);
        panel.add(new JLabel(" --- "));      
        panel.add(compButton);
        myGUI.add(BorderLayout.NORTH, top);
        myGUI.add(BorderLayout.SOUTH, panel);    

        // set up File menu
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        playItem = new JMenuItem("Auto Play");    
        restartItem = new JMenuItem("Restart");
        quitItem.addActionListener(this);
        restartItem.addActionListener(this);
        playItem.addActionListener(this);
        fileMenu.add(restartItem);
        fileMenu.add(playItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        myGUI.setJMenuBar(menus);
        menus.add(fileMenu);    
        myGUI.setVisible(true);
    }

    /****************************************************************
    Respond to the user action

    @param e - the JComponent just selected
     ****************************************************************/
    public void actionPerformed(ActionEvent e){

        // what did the user just select?
        JComponent buttonPressed = (JComponent) e.getSource();

        // quit the game
        if (buttonPressed == quitItem){
            System.exit(1);
        }

        // start a new game    
        if (buttonPressed == restartItem){
            game.restart();
        }

        // start a new game    
        if (buttonPressed == playItem){
            playOneGame();        
        }

        // player rolls
        if (buttonPressed == roll)
            game.playerRolls();

        // player holds  
        if (buttonPressed == hold)
        {
            game.playerHolds();
        }

        // computer's turn 
        if (buttonPressed == compButton)
            game.computerTurn();

        // update text colors and disable buttons as needed
        if (game.isPlayerTurn()){
            compButton.setEnabled(false);
            roll.setEnabled(true);
            hold.setEnabled(true);
            player.setForeground(Color.red);
            computer.setForeground(Color.black);
        }else{
            compButton.setEnabled(true);
            roll.setEnabled(false);
            hold.setEnabled(false);
            player.setForeground(Color.black);
            computer.setForeground(Color.red);        
        }

        // update the three score labels
        round.setText("Round: " + game.getRoundScore());
        player.setText("Player: " + game.getPlayerScore());
        computer.setText("Computer: " + game.getComputerScore());
        // display winning message if either player or computer won

        if (game.playerWon())
            JOptionPane.showMessageDialog (null, "Player won");
        if (game.computerWon())
            JOptionPane.showMessageDialog (null, "Computer won");

    }

    /****************************************************************
     * Play one game
     ****************************************************************/
    private void playOneGame(){
        game.restart();
        game.autoGame();
    }
}