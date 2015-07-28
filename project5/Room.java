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
    
    public Room(String d, Item i, Item i2)
    {
        this.description = d;
        this.roomItem = i;
        this.roomItem2 = i2;
        movements = new HashMap<String, Room>();
    }
    
    public String getDescription()
    {
        return description;
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
    
    public boolean hasItem()
    {
        if (roomItem != null){
            return true;
        }
        else {
            return false;
        }
    }
    
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
    
    public String getLongDescription()
    {
        String longDescription = "You are in" + description + ". You see " + roomItem.getDescription() + ".";
        return longDescription;
    }
    
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
