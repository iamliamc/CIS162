/**
 * This is the "driver" to test the Student Class
 * 
 * It creates several students and uses different methods to set values and prints the 
 * content of the objects as strings.
 * 
 * @author CIS162 
 * @version Fall 2011
 */
public class University
{
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

    } 
}