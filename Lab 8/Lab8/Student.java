
/**
 * Write a description of class Student here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Student
{
    // instance variables - replace the example below with your own
    private String first;
    private String last;
    private int id;
    private double gpa;

    /**
     * Constructor for objects of class Student
     */
    public Student(String first, String last, int id, double gpa)
    {
        this.first = first;
        this.last = last;
        this.id =  id;
        this.gpa = gpa;     
    }

    public String getFirst()
    {
        return first;
    }
    
    public String getLast()
    {
        return last;
    }
    
    public int getId()
    {
        return id;
    }
    
    public double getGpa()
    {
        return gpa;
    }
    
    public String toString()
    {
        return "" + first + " " + last + " " + "(" + id + ")" + " " + gpa;
    }
    
}
