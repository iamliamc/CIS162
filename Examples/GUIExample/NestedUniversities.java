import java.awt.*;
import javax.swing.*;

/*********************************************************************
*  NestedUniversities.java       Author: Ana Posada
*
*  Demonstrates a basic componenet hierarchy.
**********************************************************************/

public class NestedUniversities
{
    /*********************************************************************
    *  Presents three colored panels nested within a a forth.
    **********************************************************************/
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Nested Panels");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        // Set up first subpanel - GVSU
        JPanel subPanel1 = new JPanel();
        subPanel1.setPreferredSize (new Dimension(300, 200));
        subPanel1.setBackground (Color.black);
        ImageIcon gvsu = new ImageIcon("gvsuLogo.gif");
        JLabel label1 = new JLabel (gvsu);
        subPanel1.add (label1);

        // Set up second subpane2 _MSU
        JPanel subPanel2 = new JPanel();
        subPanel2.setPreferredSize (new Dimension(300, 200));
        subPanel2.setBackground (Color.green);
        ImageIcon msu = new ImageIcon("msuLogo.gif");
        JLabel label2 = new JLabel ("MSU",msu, SwingConstants.CENTER);
        //JLabel label2 = new JLabel ("MSU",msu);
        subPanel2.add (label2);

        // Set up third subpane3 - Michigan
        JPanel subPanel3 = new JPanel();
        subPanel3.setPreferredSize (new Dimension(300, 200));
        subPanel3.setBackground (Color.yellow);
        ImageIcon m = new ImageIcon("mLogo.gif");
        JLabel label3 = new JLabel (m);
        subPanel3.add (label3);

        // Set up primary panel
        JPanel primary = new JPanel();
        primary.setBackground (Color.blue);
        primary.add (subPanel2);
        primary.add (subPanel1);

        primary.add (subPanel3);

        frame.getContentPane().add(primary);

        frame.pack();
        frame.setVisible(true);
    }
}
