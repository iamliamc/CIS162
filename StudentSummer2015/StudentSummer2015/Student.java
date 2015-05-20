
/**********************************************************
 * A class that contains the information about a student.
 * 
 * @author CIS162 
 * @version Fall 2011
 * @Updated by Ana Posada. Fall 2013.
 * Includes an overloaded constructor with the String info
 * as the input parameter.
 **********************************************************/
public class Student
{
    // instance variables 
    /** Student first name */
    private String firstName;

    /** Student last name */
    private String lastName;

    /** Student id */
    private int gNumber;

    /** Cumulative gpa of the student */
    private double gpa;

    /**********************************************************
     ** Constructor with no parameters
     ***********************************************************/
    public Student ()
    {
        firstName = "Default";
        lastName = "Default";
        gNumber = 0;
        gpa = 0.0;
    }

    /**********************************************************
     ** Constructor for objects of class Student
     ** This constructor has one parameter for every attribute
     ***********************************************************/
    public Student(String pFirstName,String pLastName,int pGNumber,double pGpa)
    {

        firstName = pFirstName;    
        lastName = pLastName;
        gNumber = pGNumber;
        gpa = pGpa; 

    }

    /**********************************************************
     ** Constructor for objects of class Student
     ** This constructor has only 1 String as parameter
     ** This String contains the individual fields separated by 
     ** commas.  
     ** Example:
     ** "Smith,Jhoh,10,3.5"
     ** This constructor should parse the string parameter to 
     ** break it apart and assign the values of lastName, firstName,
     ** gNum and gpa to the instance variables. 
     ** You will need to use the String methods indexOf and substring,  
     ** the Integer.parseInt method and the Double.parseDouble method.
     ***********************************************************/
    public Student(String info)
    {
        int comma1 = info.indexOf(",");           // index of first comma
        int comma2 = info.indexOf(",", comma1+1); // index of second comma
        int comma3 = info.indexOf(",", comma2+1); // index of third comma

        // lastName is the subtring upto the first ","
        lastName = info.substring(0, comma1);

        // fistName is the subtring between the first "," and second ","
        firstName = info.substring(comma1 + 1, comma2);

        // gnum is between second "," and third ","
        // The String needs to be converted to an int
        gNumber = Integer.parseInt (info.substring(comma2 + 1, comma3));

        // gpa is after the third ","
        // The String needs to be converted to a double       
        gpa = Double.parseDouble(info.substring(comma3 + 1)); 

    }

    /**********************************************************
     ** The "getter" methods - Accesor methods
     ** Used to find the current value of a particular 
     ** attribute of the object
     **********************************************************/

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getGNumber()
    {
        return gNumber;
    }

    public double getGPA()
    {
        return gpa;
    }

    /**********************************************************
     ** Used to modify the value of a particular attribute
     ************************************************************/

    public void setFirstName(String pFirstName)
    {
        firstName = pFirstName;
    }

    public void setLastName(String pLastName)
    {
        lastName = pLastName;
    }

    public void setGNumber(int pGNumber)
    {
        gNumber = pGNumber;
    }

    public void setGPA(double pGpa)
    {
        gpa = pGpa;
    }

    /**********************************************************
     ** equals returns true if this student is equal to the 
     ** otherStudent entered as parameter
     ************************************************************/
    public boolean equals (Student otherStudent)
    {
        return( firstName.equals(otherStudent.firstName)&&
            lastName.equals(otherStudent.lastName)&&
            gpa == otherStudent.gpa &&
            gNumber == otherStudent.gNumber);
    }

    /**********************************************************
     ** toString returns a String with the values of the attributes 
     **  used to print
     ** the content of the objects.
     ************************************************************/
    public String toString()
    {
        return firstName + lastName + gNumber + gpa;
    } 

    public static void main (String[] args)
    {
        // Instantiating 3 student objects using the constructor that has
        // one parameter for every attribute      
        Student stud1 = new Student ("Jenny","Bernal",1, 4.0);
        Student stud2 = new Student ("Pepito","Perez",2, 4.0);
        Student stud3 = new Student ("Daniel","Kosten",3,4.0);

        // Instantiating  1 student object using the constructor with no pars  
        Student stud4 = new Student();   

        // Initializing the attributes for student4
        stud4.setFirstName("Sean");
        stud4.setLastName ("Riley");
        stud4.setGNumber (4);
        stud4.setGPA (4.0);

        //Printing the value of object attributes 

        // Notice that you have to invoke the methods from the
        // Student class to be able to access the instance 
        // variables.
        String name = stud1.getFirstName() + " " + stud1.getLastName();

        System.out.println ("The name of student1 is: "+ name);

        System.out.println ("The name of student1 is: " + 
            stud1.getFirstName() + " " + stud1.getLastName());

        // Printing all the instance variables of all the objects
        // The toString method of the Student class will run automatically 
        // when you enter the object name as a parameter in the 
        // System.out.println method.      
        System.out.println (stud1);
        System.out.println (stud2);
        System.out.println (stud3);
        System.out.println (stud4);
        System.out.println ( 1 / 2.0);

    } 
}
