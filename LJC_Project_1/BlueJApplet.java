
//Import statements
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.net.*;
import javax.imageio.*;
import java.io.*;

/**
 * Java Applet Drawing CIS 162 Project 1
 * 
 * Liam Considine
 * v2
 */
public class BlueJApplet extends JApplet
{
    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {

    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     * 
     * Begin draw code here. 
     */
    public void paint(Graphics g)
    {
        //define background
        int backgroundX = 500;
        int backgroundY = 300;
        Color backgroundColor = new Color (230, 232, 211);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, backgroundX, backgroundY);

        //make skyline
        int skyX = 500;
        int skyY = 150;
        Color skyColor = new Color (211, 231, 232);
        g.setColor(skyColor);
        g.fillRect(0, 0, skyX, skyY);

        //make sun
        int sunX = 100;
        int sunY = 5;
        int sunDiam = 50;
        int ray1 = 5;
        int ray2 = 10;
        int ray3 = 20;
        int ray4 = 30;

        //set sun color & draw filled sun
        g.setColor(Color.yellow);
        g.fillOval(sunX, sunY, sunDiam, sunDiam);

        //add hazy rays (fulfill arithmatic requirement)
        g.drawOval(sunX - ray1/2, sunY - ray1/2, sunDiam + ray1, sunDiam + ray1);
        g.drawOval(sunX - ray2/2, sunY - ray2/2, sunDiam + ray1, sunDiam + ray1);
        g.drawOval(sunX - ray2/2, sunY - ray2/2, sunDiam + ray2, sunDiam + ray2);
        g.drawOval(sunX - ray3/2, sunY - ray3/2, sunDiam + ray2, sunDiam + ray2);
        g.drawOval(sunX - ray3/2, sunY - ray3/2, sunDiam + ray3, sunDiam + ray3);
        g.drawOval(sunX - ray4/2, sunY - ray4/2, sunDiam + ray3, sunDiam + ray3);
        g.drawOval(sunX - ray4/2, sunY - ray4/2, sunDiam + ray4, sunDiam + ray4);

        //set pyramid values & variables
        int pyX1 = 20;
        int pyX2 = 180;
        int pyX3 = 180;
        int pyX4 = 100;
        int pyY1 = 255;
        int pyY2 = 255;
        int pyY3 = 200;
        int pyY4 = 55;

        //set pyramid colors
        Color pyColor = new Color (247, 220, 64);
        g.setColor(pyColor);
        
        //create arrays (necessary arguments for fillPolygon method)
        int[] xPolyValues = {pyX1, pyX2, pyX3, pyX4};
        int[] yPolyValues = {pyY1, pyY2, pyY3, pyY4};
        g.fillPolygon(xPolyValues, yPolyValues, 4);

        //make pyramid look 3D
        Color pyShadeColor = new Color (227, 200, 50);
        g.setColor(pyShadeColor);
        g.drawLine(xPolyValues[0], yPolyValues[0], pyX2, pyY2);
        g.drawLine(pyX2, pyY2, pyX3, pyY3);
        g.drawLine(pyX1, pyY1, pyX4, pyY4);
        g.drawLine(pyX2, pyY2, pyX4, pyY4);
        g.drawLine(pyX3, pyY3, pyX4, pyY4);
        
        //set color and location-size of Necker cube
        g.setColor(Color.BLACK);
        int[] rectOne = {240, 200, 70, 70};
        int[] rectTwo = {220, 180, 70, 70};
        
        //add cube face 1 & 2
        g.drawRect(rectOne[0], rectOne[1], rectOne[2], rectOne[3]);
        g.drawRect(rectTwo[0], rectTwo[1], rectTwo[2], rectTwo[3]);
        
        //connect cube
        g.drawLine(rectOne[0], rectOne[1], rectTwo[0], rectTwo[1]);
        g.drawLine((rectOne[0] + 70), rectOne[1], (rectTwo[0] + 70), rectTwo[1]); 
        g.drawLine(rectOne[0], rectOne[1] + 70, rectTwo[0], rectTwo[1] + 70);
        g.drawLine(rectOne[0] + 70, rectOne[1] + 70, rectTwo[0] + 70, rectTwo[1] + 70);
        
        //add my name
        g.setColor(Color.BLACK);
        Font myChoiceOfFont = new Font ("serif", Font.BOLD, 20);
        g.setFont (myChoiceOfFont);
        g.drawString("Liam Considine", 350, 285);
        
        //label my shapes
        Font myChoiceOfFont2 = new Font ("Helvetica", Font.ITALIC, 20);
        g.setFont (myChoiceOfFont2);
        g.drawString("The Pyramid", 20, 40);
        g.drawString("Necker Cube", 200, 145);


        //Extra credit Add image
        BufferedImage photo = null;
        try {
            URL photoUrl = new URL (getCodeBase(), "MyPhoto.jpg");
            photo = ImageIO.read(photoUrl);
            /*render the image into a 150x200 rectangle */
            g.drawImage (photo, 350, 60, 150, 200, null);
        }
        catch (IOException e){
            g.drawString("Problem rendering photo", 50, 80);
        }

    }
    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }

}
