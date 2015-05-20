
/**
 * Write a description of class CashRegister here. Assignment 2, implemenet a simple cash register program.
 * 
 * @author Liam Considine 
 * @version (a version number or a date)
 */

import java.text.NumberFormat;
import java.util.Scanner;

public class CashRegister
{
    // instance variables - replace the example below with your own
    double currentAmountDue;
    double totalDailySales;
    String storeName;
    final double salesTax = 0.06;
    NumberFormat fmt = NumberFormat.getCurrencyInstance();
    /**
     * Constructor for objects of class CashRegister
     */
    public CashRegister(String pName)
    {
        currentAmountDue = 0.0;
        totalDailySales = 0.0;
        storeName = pName;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        System.out.println("Welcome to " + storeName);
    }

    public CashRegister(double currentAmountDue, double totalDailySales, String storeName)
    {
        this.currentAmountDue = currentAmountDue;
        this.totalDailySales = totalDailySales;
        this.storeName = storeName;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        System.out.println("Welcome to " + storeName);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public double getTotalSales()
    {
        // Total Sales Getter
        return totalDailySales;
    }

    public double getAmountDue()
    {
        // Current Amount Due Getter
        return currentAmountDue;
    }
     
    public void scanPrice(double price)
    {
        // Scan Item if less than or equal to 0.0 scan error
        if (price <= 0.0) {
            System.out.println("There has been a scanner error Scan returned: " + price);
        }
        // If not less than or equal to 0.0 add to current amount and daily sales show current cart price
        else {
            currentAmountDue = currentAmountDue + price;
            totalDailySales = totalDailySales + currentAmountDue;
            System.out.println("The price of that item is: " + fmt.format(price) + " current cart cost: " + fmt.format(currentAmountDue));
        }
    }
    
    public void completeSale()
    {
        // Close out current order show sales tax and cat cost
        System.out.println("Order entered");
        double tax = salesTax * currentAmountDue;
        System.out.println("Sales Tax: " + fmt.format(tax));
        currentAmountDue = currentAmountDue + tax;
        System.out.println("Total amount due: " + fmt.format(currentAmountDue));
    }
    
    public void cancelSale()
    {
        // Cancel out current cart/order show operator amount due reset
        totalDailySales = totalDailySales - currentAmountDue;
        currentAmountDue = 0.0;
        System.out.println("Sale canceled, amount due set to: " + fmt.format(currentAmountDue));
    }    
    
    public void makePayment(double amount)
    {
        // If payment is less than 0 payment can't be negative
        if (amount < 0){
            System.out.println("Payment cannot be negative.");
            System.out.println("Payment due: " + currentAmountDue);
        }
        // If amount paid is less than current due show remaining charges
        else if (amount < currentAmountDue) {
            double due = currentAmountDue - amount;
            System.out.println("Payment: " + fmt.format(amount));
            System.out.println("Customer still owes: " + fmt.format(due));
        }
        // If amount paid is exact
        else if (amount == currentAmountDue){
            System.out.println("Payment: " + fmt.format(amount));
            System.out.println("Great work! Thank you. Have a nice day!");
        }
        // If the payment is greater than the cost
        else if (amount > currentAmountDue) {
            double change = amount - currentAmountDue;
            System.out.println("Payment: " + fmt.format(amount));
            System.out.println("Change due to customer: " + fmt.format(change));
        }
        // Ensures payment is 
        currentAmountDue = amount - currentAmountDue;
        currentAmountDue = 0.0;
    }

    public void showSalesReport()
    {
        // put your code here
        System.out.println(storeName + "'s Total Daily Sales = " + fmt.format(totalDailySales));
        currentAmountDue = 0.0;
    }
    
    public void clearAllSales()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to clear all sales? Y/N");
        String answer = sc.next();
        if (answer.equals("y")){
            totalDailySales = 0.0;
            System.out.println("All sales cleared");
        }
        else if (answer.equals("n")) {
            System.out.println("No changes to Daily Sales made");
        }
    }
    
    public static void main(String [] args)
    {
        CashRegister uMart = new CashRegister("uMart");
        uMart.scanPrice(1.50);
        uMart.scanPrice(3.00);
        uMart.completeSale();
        uMart.makePayment(5.00);
        uMart.showSalesReport();
        uMart.clearAllSales();
        uMart.scanPrice(1.50);
        uMart.scanPrice(3.00);
        uMart.completeSale();
        uMart.makePayment(1.50);
        uMart.scanPrice(0.0);
        uMart.scanPrice(-1.0);
        uMart.scanPrice(100.0);
        uMart.completeSale();
        //uMart.makePayment(106.0);
        uMart.makePayment(-106.0);

        
        
    }
}