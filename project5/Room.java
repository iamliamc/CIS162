import java.util.HashMap;
/**
 * Write a description of class Room here.
 * 
 * Liam Considine
 * 7.16.2015
 */
public class Room
{
    // instance variables - replace the example below with your own
    private String description;
    private Item roomItem;
    private Item roomItem2;
    private HashMap <String, Room> movements;

    /**
     * Constructor for objects of class Room
     */
    public Room(String d, Item i)
    {
        this.description = d;
        this.roomItem = i;
        movements = new HashMap<String, Room>();
    }
    
    //overload constructor not ended up being used as it would complicate things too much
    public Room(String d, Item i, Item i2)
    {
        this.description = d;
        this.roomItem = i;
        this.roomItem2 = i2;
        movements = new HashMap<String, Room>();
    }

    // get & set methods
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String d)
    {
        this.description = d;
    }

    public Item getItem()
    {
        return roomItem;
    }

    public HashMap getMovements()
    {
        return movements;
    }

    public void addItem(Item i)
    {
        this.roomItem = i;
    }

    // simple check to see if a room has an item
    public boolean hasItem()
    {
        if (roomItem != null){
            return true;
        }
        else {
            return false;
        }
    }

    //update hashmap with neighbouring room object
    public void addNeighbor(String dir, Room r)
    {
        this.movements.put(dir, r);
    }

    public Room getNeighbor(String dir)
    {
        Room neighbor = this.movements.get(dir);
        return neighbor;
    }

    public Item removeItem()
    {
        this.roomItem = null;
        return roomItem;
    }

    //shows a rooms description was getting nullpointers so I gave it a branch depending on whether the room has an item
    public String getLongDescription()
    {
        if (hasItem()){
            String longDescription = "You are in" + description + ". You see " + roomItem.getDescription() + ".";
            return longDescription + "\n";
        }
        else {
            String longDescription = "You are in" + description + ".";
            return longDescription + "\n";
        }
    }

    //test room methods
    public static void main(String [ ] args)
    {
        Item boar = new Item("Boar", "Cute little boar", 10, true);
        Room quiteMarket = new Room("See the world little man", boar);
        if(quiteMarket.hasItem()== true){
            System.out.println("quiteMarket has item");
        }
        Room well = new Room("Stone hand dug well", null);
        quiteMarket.addNeighbor("east", well);
        System.out.println(quiteMarket.getNeighbor("east").getDescription());
    }

    
    
}
