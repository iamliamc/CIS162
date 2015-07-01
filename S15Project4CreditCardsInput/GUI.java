import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

/***********************************************************************
 * GUI front end for a Credit Card database 
 * 
 * @author Scott Grissom
 * @version February 1, 2013.
 * Updated by Ana Posada - June 2015. Included one single class using 
 * extends and implements instead of using an inner class.
 **********************************************************************/
public class GUI extends JPanel implements ActionListener
{
    /** declare db as an object of the CustomerDatabase class */

    /** JButtons */
    
    /** text fields */
    private JTextField min;
    private JTextField max;
    private JTextField cityState;

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu testMenu;
    private JMenuItem quitItem;
    private JMenuItem openItem; 
    private JMenuItem countItem;
    private JMenuItem summaryItem;

    public static void main(String arg[]){

        new GUI();

    }

    /*********************************************************************
    Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public GUI(){ 
        db = new CustomerDatabase();
        db.readCustomerData("CustomerNames.txt");

        theGUI = new JFrame("Credit Card Database");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the Results Area for the Center area
        results = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(results);
        theGUI.add(BorderLayout.CENTER, scrollPane);

        // set up File menus
        setupMenus();

        // set up selection
        setupSelection();

        // set up buttons
        setupButtons ();
        theGUI.pack();

    }

    /*********************************************************************
    Set up the menu items
     *********************************************************************/
    private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        testMenu = new JMenu("Test");
        countItem = new JMenuItem("Counts");
        summaryItem = new JMenuItem("Summaries");

        // assign action listeners        
        quitItem.addActionListener(this);
        openItem.addActionListener(this);
        countItem.addActionListener(this);
        summaryItem.addActionListener(this);

        // display menu components
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        testMenu.add(countItem);
        testMenu.add(summaryItem);    
        menus = new JMenuBar();

        menus.add(fileMenu);
        menus.add(testMenu);
        theGUI.setJMenuBar(menus);
    }   

    /********************************************************
     *  Set up the text fields 
     **********************************************************/
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
    }

    /********************************************************
     *  Set up the Buttons 
     **********************************************************/
    private void setupButtons()
    {
        // complete this method based on project description

        
        
    }

    /*********************************************************************
     *This method displays the mailing address of each customer in the list
     *
     *@param ArrayList <Customer>
     * *********************************************************************/    
    public void displayCustomers (ArrayList <Customer> list) 
    {
        // complete this method based on project description

    }
    /*********************************************************************
     * This method clears the display area and then shows the summaries 
     * for each of the three credit cards: Discover, MasterCard and Visa
     *
     * *********************************************************************/  
    public void showSummaries () 
    {
        // complete this method based on project description

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

        // complete this method to handle the rest of the menu items and 
        // and the buttons.

    }
}

