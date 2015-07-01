//********************************************************************
//  QuoteOptionsPanel.java       Author: Lewis/Loftus
//
//  Demonstrates the use of radio buttons.
//********************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuoteOptionsPanel extends JPanel implements ActionListener
{
    private JLabel quote;
    private JRadioButton comedy, philosophy, carpentry;
    private String comedyQuote, philosophyQuote, carpentryQuote;

    //-----------------------------------------------------------------
    //  Sets up a panel with a label and a set of radio buttons
    //  that control its text.
    //-----------------------------------------------------------------
    public QuoteOptionsPanel()
    {
        comedyQuote = "This is really funny!!";
        philosophyQuote = "I think, therefore I am.";
        carpentryQuote = "Measure twice. Cut once.";

        quote = new JLabel (comedyQuote);
        quote.setFont (new Font ("Helvetica", Font.BOLD, 24));

        comedy = new JRadioButton ("Comedy");
        comedy.setBackground (Color.green);
        philosophy = new JRadioButton ("Philosophy");
        philosophy.setBackground (Color.green);
        carpentry = new JRadioButton ("Carpentry");
        carpentry.setBackground (Color.green);

        ButtonGroup group = new ButtonGroup();
        group.add (comedy);
        group.add (philosophy);
        group.add (carpentry);

        comedy.addActionListener (this);
        philosophy.addActionListener (this);
        carpentry.addActionListener (this);

        add (quote);
        add (comedy);
        add (philosophy);
        add (carpentry);

        setBackground (Color.green);
        setPreferredSize (new Dimension(300, 100));
    }

    //*****************************************************************
    //  Represents the listener for all radio buttons
    //*****************************************************************
    //--------------------------------------------------------------
    //  Sets the text of the label depending on which radio
    //  button was pressed.
    //--------------------------------------------------------------
    public void actionPerformed (ActionEvent event)
    {
        Object source = event.getSource();

        if (source == comedy)
            quote.setText (comedyQuote);
        else
        if (source == philosophy)
            quote.setText (philosophyQuote);
        else
            quote.setText (carpentryQuote);
    }
}

