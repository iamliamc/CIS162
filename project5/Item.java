
/**
 * Write a description of class Item here.
 * Item class with 4 methods. Has some simple attributes 
 * 
 * Liam Considine
 * 7.14.2015
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private int weight;
    private boolean edible;
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight, boolean edible)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.edible = edible;
    }

    // Set methods
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setWeight(int weight)
    {
        this.weight = weight;
    }
    
    public void setEdible(boolean edible)
    {
        this.edible = edible;
    }
    
    //Get methods
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public boolean getEdible()
    {
        return edible;
    }
    
    public boolean isEdible()
    {
        if(edible == true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
