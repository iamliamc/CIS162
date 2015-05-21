
/**
 * Assignment 2, implemenet a simple cash register program.
 * 
 * @author Liam Considine 
 * 5/20/2015 
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
    
    /**
     * Constructor for objects of class CashRegister with all parameters defined
     */
    
    public CashRegister(double currentAmountDue, double totalDailySales, String storeName)
    {
        this.currentAmountDue = currentAmountDue;
        this.totalDailySales = totalDailySales;
        this.storeName = storeName;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        System.out.println("Welcome to " + storeName);
    }
    
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
            System.out.println("There has been a scanner error... Scan returned: " + price);
        }
        // If not less than or equal to 0.0 add to current amount and daily sales show current cart price
        else {
            currentAmountDue = currentAmountDue + price;
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
        totalDailySales = totalDailySales + currentAmountDue;
        System.out.println("Total amount due: " + fmt.format(currentAmountDue));
    }
    
    public void cancelSale()
    {
        // Cancel out current cart/order show operator amount due reset
        currentAmountDue = 0.0;
        System.out.println("Sale canceled, amount due set to: " + fmt.format(currentAmountDue));
    }    
    
    public void makePayment(double amount)
    {
        // If payment is less than 0 payment can't be negative
        if (amount <= 0){
            System.out.println("Payment cannot be Zero or negative.");
            System.out.println("Payment due: " + currentAmountDue);
        }
        // If amount paid is less than current due show remaining charges
        else if (amount < currentAmountDue) {
            currentAmountDue = currentAmountDue - amount;
            System.out.println("Payment: " + fmt.format(amount));
            System.out.println("Customer still owes: " + fmt.format(currentAmountDue));
        }
        // If amount paid is exact
        else if (amount == currentAmountDue){
            System.out.println("Payment: " + fmt.format(amount));
            System.out.println("Great work! Thank you. Have a nice day!");
            currentAmountDue = 0.0;
        }
        // If the payment is greater than the cost
        else if (amount > currentAmountDue) {
            double change = amount - currentAmountDue;
            currentAmountDue = 0.0;
            System.out.println("Payment: " + fmt.format(amount));
            System.out.println("Change due to customer: " + fmt.format(change));
        }
    }

    public void showSalesReport()
    {
        // put your code here
        System.out.println(storeName + "'s Total Daily Sales = " + fmt.format(totalDailySales));
    }
    
    public void clearAllSales()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to clear all sales? Y/N");
        String answer = sc.next();
        if (answer.equals("y")){
            totalDailySales = 0.0;
            currentAmountDue = 0.0;
            System.out.println("All sales cleared");
        }
        else{
            System.out.println("No changes to Daily Sales made");
        }
    }
    
    public static void main(String [] args)
    {
        CashRegister uMart = new CashRegister("uMart");
        //Test scanPrice, completeSale, getAmountDue, getTotalSales, makePayment(under), makePayment(exact case), clearAllSales(y), showSalesReport
        uMart.scanPrice(1.50);
        uMart.scanPrice(3.00);
        uMart.scanPrice(4.50);
        uMart.completeSale();
        System.out.println("Test getAmountDue: " + uMart.getAmountDue());
        uMart.makePayment(9.00);
        uMart.makePayment(0.54);
        System.out.println("Test getTotalSales: " + uMart.getTotalSales());
        uMart.clearAllSales();
        uMart.showSalesReport();
        
        //Test scanPrice, completeSale, makePayment(negative), makePayment(zero), makePayment(overpay), clearAllSales(n)
        uMart.scanPrice(10.50);
        uMart.scanPrice(3.00);
        uMart.scanPrice(4.50);
        uMart.completeSale();
        uMart.makePayment(0.00);
        uMart.makePayment(-1.00);
        uMart.makePayment(9.54);
        uMart.makePayment(10.00);
        uMart.clearAllSales();
        
        //Test second instance of CashRegister class, scanPrice(zero), scanPrice(negative)
        CashRegister quickieMart = new CashRegister("quickieMart");
        quickieMart.scanPrice(1.50);
        quickieMart.scanPrice(36.00);
        quickieMart.cancelSale();
        quickieMart.scanPrice(50.00);
        quickieMart.scanPrice(-1.00);
        quickieMart.scanPrice(0.00);
        quickieMart.completeSale();
        quickieMart.makePayment(100.0);
        
        
        
        
        


        
      
        
    }
}
