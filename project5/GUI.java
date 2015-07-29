import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.text.DefaultCaret;

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

    private JButton inspect;
    private JButton help;
    private JButton pickup;
    private JButton leave;
    private JButton backup;
    private JButton eat;
    private JButton show;

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem quitItem;
    private JMenuItem newGame; 
    private JMenuItem countItem;
    private JMenuItem summaryItem;

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
        System.out.println(g.getMessage());
        results.setText(g.getMessage());

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
     *  Set up the text fields 
     **********************************************************
    private void setupSelection()
    {
        JPanel selectPane = new JPanel();

        // create menu components
        min = new JTextField(10);
        max = new JTextField(10);
        cityState = new JTextField(15);

        //add components to the JPanel
        selectPane.add (new JLabel ("Min $ "));
        selectPane.add  (min);
        selectPane.add (new JLabel ("Max $ "));
        selectPane.add (max);
        selectPane.add (new JLabel ("City/ST"));
        selectPane.add (cityState);

        theGUI.add(BorderLayout.SOUTH, selectPane);
    }/

    /********************************************************
     *  Set up the Buttons 
     **********************************************************/
    private void setupButtons()
    {
        // complete this method based on project description
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        inspect = new JButton ("Inspect");
        help = new JButton ("Help");
        pickup = new JButton ("Pickup");
        leave = new JButton ("Leave");
        backup = new JButton ("Backup");
        show = new JButton ("Show");
        eat = new JButton ("Eat");
      
        actionPanel.add(new JLabel("Actions"));
        actionPanel.add(inspect);
        actionPanel.add(help);
        actionPanel.add(pickup);
        actionPanel.add(leave);
        actionPanel.add(backup);
        actionPanel.add(show);
        actionPanel.add(eat);
        
        inspect.addActionListener(this);
        help.addActionListener(this);
        pickup.addActionListener(this);
        leave.addActionListener(this);
        backup.addActionListener(this);
        show.addActionListener(this);
        eat.addActionListener(this);
        
        theGUI.add(BorderLayout.SOUTH, actionPanel);
        
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        
        north = new JButton ("North");
        south = new JButton ("South");
        east = new JButton ("East");
        west = new JButton ("West");

        eastPanel.add(new JLabel("Directions"));
        eastPanel.add(north);
        eastPanel.add(south);
        eastPanel.add(west);
        eastPanel.add(east);

        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);

        theGUI.add(BorderLayout.EAST, eastPanel);
    }
    
    private void gameOver(){
        
    }
    
    private void newGame(){
        
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
            results.append(g.getMessage());
        }
        
        if (e.getSource() == south){
            g.move("south");
            results.append(g.getMessage());
        }
        
        if (e.getSource() == east){
            g.move("east");
            results.append(g.getMessage());
        }
        
        if (e.getSource() == west){
            g.move("west");
            results.append(g.getMessage());
        }
        
        if (e.getSource() == newGame){
            g = new Game();
            results.setText(g.getMessage());
        }
        
        if (e.getSource() == backup){
            g.backup();
            results.setText(g.getMessage());
        }
    }
}

