
/**********************************************
 * IpodDriver class - used to test the Ipod 
 * 
 * @author Ana Posada
 * @version fall 2013
 */
public class IpodDriver
{

    /*****************************************************************
     * main method 
     *****************************************************************/
    public static void main (String [] args)
    {
        // instantiating an object of the class Ipod
        Ipod ipod = new Ipod();
        
        // creating 3 songs
        Song song1 = new Song("La Camisa Negra","Juanes","Latin pop");
        Song song2 = new Song("Para Tu Amor","Juanes","Latin pop");
        Song song3 = new Song("Waka Waka","Shakira","Latin pop");
        
        System.out.println ("Was song " + song1 + " added ? " + ipod.addSong (song1));        
        System.out.println ("Was song " + song2 + " added ? " + ipod.addSong (song2));
        System.out.println ("Was song " + song3 + " added ? " + ipod.addSong (song3));
        System.out.println ("Was song " + song1 + " added ? " + ipod.addSong (song1));
        
        // printing the songs in the ipod
        ipod.display();
        
        System.out.println("Total number of songs: " + ipod.countSongs());
        
        System.out.println("Searching for Waka Waka: " + ipod.searchTitle ("Waka Waka"));
        
        System.out.println("Searching for Juanes: " + ipod.searchArtist("Juanes"));
    }

}
