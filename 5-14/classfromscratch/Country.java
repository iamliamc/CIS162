
/**
 * Write a description of class Country here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Country
{
    // instance variables - used to enter name of the country
    private String name;
    private int population;

    /**
     * Constructor for objects of class Country
     */
    public Country()
    {
    }

    public Country(String pName)
    {
        name = pName;
        population = 0;
    }

    public Country(String pName, int pPopulation)
    {
        name = pName;
        population = pPopulation;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getName()
    {
        // put your code here
        return name;
    }

    public int getPopulation()
    {
        // put your code here
        return population;
    }

    public void setName(String name)
    {
        // put your code here
        this.name = name;
    }

    public void setPopulation(int pPopulation)
    {
        // put your code here
        population = pPopulation;
    }
}
