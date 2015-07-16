import java.util.HashMap;
/***********************************************
 * Lab to practice HashMaps
 * 
 * @author Brandon Shannon and Liam Considine
 * @version - July 2015
 ***********************************************/
public class House
{
    /** streetNumber */
    private  int streetNumber;
    /** streetName */
    private String streetName;
    /** lastName of owners */
    private String lastName;
    /** HashMap of neighbors - the name of the HashMap should be: myNeighbors  */
    private HashMap <String, House> myNeighbors; 
    
    /********************************************************
     ** Constructor.
     ** @param - int streetNumber , String streetName and 
     ** String lastName
     ** initializes the instance variables, including the 
     ** HashMap - see documentation to instantiate a HashMap
     ********************************************************/
    public House (int streetNumber, String streetName, String lastName)
    {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.lastName = lastName;
        
        myNeighbors = new HashMap<String, House>();
        
    }
    /*******************************************************
     **  getter methods
     *******************************************************/
    public int getStreetNumber()
    {
      return streetNumber;
    }

    public String getStreetName()
    {
      return streetName;
    } 

    public String getLastName()
    {
      return lastName;
    }

    /*******************************************************
     **  setter methods
     *******************************************************/
    public void setStreetNumber(int streetNumberP)
    {
        streetNumber = streetNumberP;
    }

    public void setStreetName(String streetNameP)
    {
        streetName = streetNameP;
    } 

    public void setLastName(String lastNameP)
    {
        lastName = lastNameP;
    }

    /*******************************************************
     ** adds the provided house to the corresponding direction
     ** to the HashMap of neighbors
     ** @param - String direction 
     ** @param - House h
     ** ONE line of code - see documentation for the put method
     *******************************************************/
    public void addNeighbor (String direction, House h) 
    {
        this.myNeighbors.put(direction, h);

    }

    /*******************************************************
     ** returns the adjacent house in the requested direction.  
     ** Return null if there is no house in that direction.
     ** ONE line of code - see documentation for the get method
     *******************************************************/
    public House getNeighbor (String direction) 
    { 
        House neighbor = this.myNeighbors.get(direction);
        return neighbor;
    }

    /**********************************************************************
     ** Navigates the HashMap and prints each key and its associated value
     ***********************************************************************/   
    public void printAllNeighbors()
    {
        for (String key : myNeighbors.keySet())
        {
            System.out.println( "\nkey: " + key + " value: " + myNeighbors.get(key));
        }
    }

    public String toString()
    {   
       return "Address: " + streetNumber + " " + streetName + "\nOwner: " + lastName;  
    }

    public static void main (String [] args)
    {
        House whiteHouse = new House (1600, "Pennsylvania Ave", "Obama");
        House jerrysHouse = new House (1602, "Pennsylvania Ave", "Mabrito");
        House guentersHouse = new House (1604, "Pennsylvania Ave", "Tusch");
        House anasHouse = new House (1609, "Pennsylvania Ave", "Posada");

        whiteHouse.addNeighbor("east", jerrysHouse);
        jerrysHouse.addNeighbor("west", whiteHouse);
        whiteHouse.addNeighbor("south", guentersHouse);
        guentersHouse.addNeighbor("north", whiteHouse);
        whiteHouse.addNeighbor("north west", anasHouse);
        anasHouse.addNeighbor("south east", whiteHouse);

        // Have fun creating Houses for the members of your teams and creating 
        // neighbors for each of the houses - see the whiteHouse examples

        House liamHouse = new House (1601, "Fairlane Ave", "Considine");
        House brandonHouse = new House (1000, "8th Ave", "Shannon");
        
        liamHouse.addNeighbor("east", guentersHouse);
        guentersHouse.addNeighbor("east", liamHouse);
        brandonHouse.addNeighbor("south", liamHouse);
        liamHouse.addNeighbor("north", brandonHouse);
        anasHouse.addNeighbor("south", brandonHouse);
        brandonHouse.addNeighbor("north", anasHouse);
        
        
        // printing details about the whiteHouse
        System.out.println("========== White House info ==============");
        System.out.println(whiteHouse);
        
        // printing the neighbors of the whiteHouse
        System.out.println("================ Print all whitehouse neighbors ==============");
        whiteHouse.printAllNeighbors();
        
        // print the houses that you created, including the neighbors
        System.out.println("================ Print all liamHouse neighbors ==============");        
        System.out.println(liamHouse);
        liamHouse.printAllNeighbors();

        System.out.println("================ Print all brandonHouse neighbors ==============");
        System.out.println(brandonHouse);
        brandonHouse.printAllNeighbors();
        System.out.println();
        
        // invoke the getNeighbor method and print 
        // the information of the house that is to the east of the whiteHouse
        System.out.println("East of the Whitehouse is: " + whiteHouse.getNeighbor("east"));
        System.out.println();
        
        // invoke the getNeighbor method with some of the houses that you created 
        // and print the information of the house returned by this method.
        System.out.println("South of Brandon's House is: " + brandonHouse.getNeighbor("south"));
        
        
    }  
}

