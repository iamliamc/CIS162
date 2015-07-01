
/**********************************************************************
 * Song Class - contains the title, artist and genre of a song
 * 
 * @author Ana Posada 
 * @version Fall 2013
 **********************************************************************/
public class Song
{
    // instance variables - replace the example below with your own
    /** song title */
    private String  title;

    /** song artist */
    private String artist;

    /** song genre */
    private String genre;

    /**************************************************
     * Constructor for objects of class Song
     *************************************************/
    public Song(String title, String artist, String genre)
    {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    /*********************************************
     * getTitle
     * @return String 
     *********************************************/
    public String getTitle()
    {
        return title;
    }

    /*********************************************
     * getArtist
     * @return String 
     *********************************************/
    public String  getArtist()
    {
        return artist;
    }

    /*********************************************
     * getGenre
     * @return String 
     *********************************************/
    public String  getGenre()
    {
        return genre;
    }

    /*********************************************
     * getTitle
     * @return String 
     *********************************************/
    public String toString ()
    {
        return "Title: " + title + ", artist:" + artist + ", genre: " + genre;
    }

    /*********************************************
     * equals
     * @return boolean - true if this song is equal
     * to the song entered as an input parameter
     * @param - Song 
     *********************************************/
    public boolean equals (Song s)
    {
        return s.title.equals(title) && s.artist.equals(artist) && 
        s.genre.equals(genre);
    }

}
