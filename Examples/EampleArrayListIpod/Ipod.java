import java.util.ArrayList;
/*****************************************************
 * Ipod class - contains an ArrayList of the Song class
 * 
 * @author Ana Posada
 * @version Fall 2013
 *******************************************************/

public class Ipod
{
    /** collection of songs */
    private ArrayList <Song> ipod;

    /*************************************************
     * Constructor for objects of class Ipod
     *************************************************/
    public Ipod()
    {
        ipod = new ArrayList <Song> ();
    }

    /******************************************************
     * countSongs
     * @return the number of elements in the ArrayList
     ******************************************************/
    public int countSongs()
    {
        return ipod.size();
    }

    /******************************************************
     * addSong
     * @param - Song
     * This method returns a boolean variable indicating:
     * true if the Song was added to the ArrayList
     * false if the Song was not added to the ArrayList.
     ******************************************************/
    public boolean addSong (Song newS)
    {
        boolean found = false;
        if (!ipod.contains (newS))
        {
            found = true;
            ipod.add(newS);
        }

        return found;
    }

    /******************************************************
     * searchTitle
     * @param - String pTitle
     * @return Song that contains the title entered as 
     * parameter
     ******************************************************/
    public Song searchTitle (String pTitle)
    {
        for (Song s : ipod)
            if (s.getTitle().equalsIgnoreCase(pTitle))
                return s;
        return null;
    }
    
    /******************************************************
     * searchArtist
     * @param - String pArtist
     * @return ArrayList <Song> that contains the songs 
     * for the artist entered as parameter
     ******************************************************/
    public ArrayList <Song> searchArtist (String pArtist)
    {
        ArrayList <Song> result = new ArrayList <Song> ();
        for (Song s : ipod)
            if (s.getArtist().equalsIgnoreCase(pArtist))
                result.add(s);
        return result;
    }
    
    /******************************************************
     * display
     * prints the songs in the ipod one song per line
     ******************************************************/
    public void display()
    {
        System.out.println ("The ipod contains \n");
        for (Song s : ipod)
            System.out.println (s  + "\n");
    }
    
    /******************************************************
     * toString
     * @return String - Returns the Strings of the songs
     * a line per song.  
     ******************************************************/
    public String toString ()
    {
        String result = null;
        for (Song s : ipod)
            result += s.toString() + "\n";

        return result;

    } 
}
