
import java.util.Scanner; // Enables user input

public class Salary {
   public static void main(String [] args) {
      int hourlyWage   = 0;
      int annualSalary = 0;

      Scanner scnr = new Scanner(System.in); // Setup to scan chars from System.in

      System.out.println("Enter hourly wage: ");
      hourlyWage = scnr.nextInt();  // Read next integer from scanner

      annualSalary = hourlyWage * 40 * 50;
      System.out.print("Salary is ");
      System.out.println(annualSalary);
	  
	  //Casting operation
	  int x; 
	  //Do the division then 'cast' to int
	  x = (int) (10.0/3);
	  //Cast the double to int then divide
	  x = (int) 10.0 / 3;

      return;
   }
}