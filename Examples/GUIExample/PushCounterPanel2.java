import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*********************************************************************
*  PushCounterPanel.java       Authors: Lewis/Loftus
*
*  Demonstrates a graphical user interface and an event listener.
*  Initial code changed by Ana Posada to demostrate the use of 
*  extends and implements at the same time on the class definition.
**********************************************************************/

public class PushCounterPanel2 extends JPanel implements ActionListener
{
   private int count;
   private JButton push;
   private JLabel label;

   /********************************************************************
   *  Constructor: Sets up the GUI.
   ********************************************************************/
   public PushCounterPanel2 ()
   {
      count = 0;
      push = new JButton ("Push Me!");
      
      //------------ Relationship between the JButton and the listener  
//       push.addActionListener (this); 
      
      label = new JLabel ("Pushes: " + count);
      add (push);
      add (label);
      setPreferredSize (new Dimension(300, 40));
      setBackground (Color.cyan);
   }

      /********************************************************************
      *  Updates the counter and label when the button is pushed.
      ********************************************************************/
      public void actionPerformed (ActionEvent event)
      {
         count++;
         label.setText("Pushes: " + count);
      }
   }







