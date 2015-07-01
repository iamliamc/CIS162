import javax.swing.JFrame;

/********************************************************************
*  PushCounter.java       Authors: Lewis/Loftus
*
*  Demonstrates a graphical user interface and an event listener.
********************************************************************/
public class PushCounter2
{
  /********************************************************************
  *  Creates the main program frame.
  ********************************************************************/
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Push Counter");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(new PushCounterPanel2());

      frame.pack();
      frame.setVisible(true);
   }
}
