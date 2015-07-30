import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.text.DefaultCaret;
import javax.swing.JApplet;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/***********************************************************************
 * GUI front end for a Credit Card database 
 * 
 * @author Scott Grissom
 * @version February 1, 2013.
 * Updated by Ana Posada - June 2015. Included one single class using 
 * extends and implements instead of using an inner class.
 * Updated by Liam Considine - to fit final project requirements
 **********************************************************************/
public class GUI extends JPanel implements ActionListener
{
    /** declare game object to be used by GUI */
    private Game g;

    /** JButtons */
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton northeast;
    private JButton northwest;

    private JButton inspect;
    private JButton help;
    private JButton pickup;
    private JButton leave;
    private JButton backup;
    private JButton eat;
    private JButton show;
    private JButton inv;

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem quitItem;
    private JMenuItem newGame; 


    public static void main(String arg[]){

        new GUI();

    }

    /*********************************************************************
    Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public GUI(){ 
        g = new Game();
        g.setIntroMessage();
        theGUI = new JFrame("Cato the Explorer");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the Results Area for the Center area
        results = new JTextArea(30,60);
        JScrollPane scrollPane = new JScrollPane(results);
        results.setLineWrap(true);
        results.setWrapStyleWord(true);

        DefaultCaret caret = (DefaultCaret) results.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        theGUI.add(BorderLayout.CENTER, scrollPane);

        // set up File menus
        setupMenus();

        // set up selection
        //setupSelection();

        // set up buttons
        setupButtons ();
        theGUI.pack();
        
        //configure beginning of game messages
        results.setText(g.getMessage());
        g.show();
        results.append(g.getMessage());

    }

    /*********************************************************************
    Set up the menu items
     *********************************************************************/
    private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        newGame = new JMenuItem("New");

        // assign action listeners        
        quitItem.addActionListener(this);
        newGame.addActionListener(this);

        // display menu components
        fileMenu.add(newGame);
        fileMenu.add(quitItem);   
        menus = new JMenuBar();

        menus.add(fileMenu);
        theGUI.setJMenuBar(menus);
    }   

    /********************************************************
     *  Set up the Buttons declare buttons, add them to the different panels, add panels to layouts add buttons to action listener
     **********************************************************/
    private void setupButtons()
    {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        inspect = new JButton ("Inspect");
        help = new JButton ("Help");
        pickup = new JButton ("Pickup");
        leave = new JButton ("Leave");
        backup = new JButton ("Backup");
        show = new JButton ("Show");
        eat = new JButton ("Eat");
        inv = new JButton ("Inventory");
        
        Border thickBorder = new LineBorder(Color.pink, 4);
        inspect.setBorder(thickBorder);
        
        actionPanel.add(new JLabel("Actions"));
        actionPanel.add(inspect);
        actionPanel.add(help);
        actionPanel.add(pickup);
        actionPanel.add(leave);
        actionPanel.add(backup);
        actionPanel.add(show);
        actionPanel.add(eat);
        actionPanel.add(inv);

        inspect.addActionListener(this);
        help.addActionListener(this);
        pickup.addActionListener(this);
        leave.addActionListener(this);
        backup.addActionListener(this);
        show.addActionListener(this);
        eat.addActionListener(this);
        inv.addActionListener(this);

        theGUI.add(BorderLayout.SOUTH, actionPanel);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        north = new JButton ("North");
        south = new JButton ("South");
        east = new JButton ("East");
        west = new JButton ("West");
        
        northwest = new JButton ("Northwest");
        northeast = new JButton ("Northeast");
        
        north.setBackground(Color.blue);
        east.setBackground(Color.red);
        west.setBackground(Color.green);
        south.setBackground(Color.orange);

        eastPanel.add(new JLabel("Directions"));
        eastPanel.add(north);
        eastPanel.add(south);
        eastPanel.add(west);
        eastPanel.add(east);
        eastPanel.add(northeast);
        eastPanel.add(northwest);

        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);
        northeast.addActionListener(this);
        northwest.addActionListener(this);

        theGUI.add(BorderLayout.EAST, eastPanel);
    }

    //helper method used at each movement action to indicate if the game is complete
    private void gameOver(){
        if(g.gameOver() == true){
            inspect.setEnabled(false);
            help.setEnabled(false);
            pickup.setEnabled(false);
            leave.setEnabled(false);
            backup.setEnabled(false);
            show.setEnabled(false);
            inv.setEnabled(false);
            eat.setEnabled(false);
            north.setEnabled(false);
            south.setEnabled(false);
            east.setEnabled(false);
            west.setEnabled(false);
            northwest.setEnabled(false);
            northeast.setEnabled(false);
        }        
    }

    //method used by the new button in the menu to restart the game
    private void newGame(){
        g = new Game();
        g.setIntroMessage();
        results.setText(g.getMessage());
        inspect.setEnabled(true);
        help.setEnabled(true);
        pickup.setEnabled(true);
        leave.setEnabled(true);
        backup.setEnabled(true);
        show.setEnabled(true);
        inv.setEnabled(true);
        eat.setEnabled(true);
        north.setEnabled(true);
        south.setEnabled(true);
        east.setEnabled(true);
        west.setEnabled(true);
        northwest.setEnabled(true);
        northeast.setEnabled(true);
    }

    /*********************************************************************
     *Responds to menu selections and button clicks
     *
     *@param e the button or menu item that was selected
     * *********************************************************************/

    public void actionPerformed(ActionEvent e)
    {
        // menu item - quit
        if (e.getSource() == quitItem)
        {
            System.exit(1);   
        }

        if (e.getSource() == eat){
            String message = "What do you want to eat?";
            String toEat = JOptionPane.showInputDialog(null, message);
            g.eat(toEat);
            results.append(g.getMessage());
        }

        if (e.getSource() == help){
            g.help();
            results.append(g.getMessage());
        }

        if (e.getSource() == north){
            g.move("north");
            gameOver();
            results.append(g.getMessage());
        }

        if (e.getSource() == south){
            g.move("south");
            gameOver();    
           results.append(g.getMessage());
        }

        if (e.getSource() == east){
            g.move("east");
            gameOver();
            results.append(g.getMessage());            
        }

        if (e.getSource() == west){
            g.move("west");
            gameOver();            
            results.append(g.getMessage());
        }
        
        if (e.getSource() == northwest){
            g.move("northwest");
            gameOver();
            results.append(g.getMessage());
        }
        
        if (e.getSource() == northeast){
            g.move("northeast");
            gameOver();
            results.append(g.getMessage());
        }

        if (e.getSource() == newGame){
            newGame();
        }

        if (e.getSource() == backup){
            g.backup();
            results.append(g.getMessage());
        }

        if (e.getSource() == show){
            g.show();
            results.append(g.getMessage());
        }

        if (e.getSource() == inv){
            g.inventory();
            results.append(g.getMessage());
        }

        if (e.getSource() == inspect){
            g.inspect();
            results.append(g.getMessage());
        }

        if (e.getSource() == pickup){
            g.pickup();
            results.append(g.getMessage());
        }

        if (e.getSource() == leave) {
            String message = "What do you want to leave?";
            String toLeave = JOptionPane.showInputDialog(null, message);
            g.leave(toLeave);
            results.append(g.getMessage());
        }

    }
}

