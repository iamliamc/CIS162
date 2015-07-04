import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;
/**
 * Write a description of class CustomerDatabase here.
 * 
 * 7/2/2015 
 * @Liam Considine
 */
public class CustomerDatabase
{
    // instance variables - replace the example below with your own
    private ArrayList<Customer> customers;
    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * Constructor for objects of class CustomerDatabase
     */
    public CustomerDatabase()
    {
        customers = new ArrayList<Customer>();
    }

    public void readCustomerData(String filename)
    {
        Customer c;
        // Attempt to read the complete set of data from file.
        try{ 
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            String logline;

            // read header information first
            logline = sc.nextLine();
            while(sc.hasNext()) {
                logline = sc.nextLine();

                // remove this print statement after method works
                System.out.println(logline);

                // add two lines of code to instantiate a new Customer 
                c = new Customer(logline);
                // and add to database	
                customers.add(c);

            }
            sc.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " +
                filename);
        }

    }

    public void addCustomer(Customer c)
    {
        customers.add(c);
    }

    public int countCustomers()
    {
        return customers.size();
    }

    public int debtFree()
    {
        int debtFree = 0;
        for (Customer cst: customers){
            if (cst.getBalance() == 0.0){
                debtFree++;
            }

        }
        return debtFree;
    }

    public int countCustomers(int zip)
    {
        int count = 0;
        for(Customer cst : customers){
            if (cst.getZip() == zip){
                count++;
            }
        }
        return count;
    }

    public int countDebtFree()
    {
        int count = 0;
        for(Customer cst : customers){
            if (cst.getBalance() == 0.0){
                count++;
            }
        }
        return count;
    }

    public Customer getHighestDebt()
    {
        Customer one = customers.get(0);
        for(Customer cst: customers){
            if (cst.getBalance() > one.getBalance()){
                one = cst;
            }
        }
        return one;
    }

    public Customer getYoungestCardholder()
    {
        Customer one = customers.get(0);
        for(Customer cst: customers) {
            if (cst.getYear() > one.getYear()){
                one = cst;
            }
            if (cst.getYear() == one.getYear() && cst.getMonth() > one.getMonth()){
                one = cst;
            }
            if (cst.getYear() == one.getYear() && cst.getMonth() == one.getMonth() && cst.getDay() > one.getDay()){
                one = cst;
            }
        }
        return one;
    }

    public String getCardSummary(String card)
    {
        double sumBal = 0.0;
        int countCards = 0;
        double avgDebt = 0.0;
        if (card.equals("Visa") || card.equals("MasterCard") || card.equals("Discover")){
            for (Customer cst: customers){
                if(cst.getCreditCard().equals(card)){
                    countCards++;
                    sumBal += cst.getBalance();               
                }
            }
        }
        else {
            return "Input Card not valid!";
        }
        if (countCards != 0){
            return "" + card + ": " + countCards + " cards with average balance of " + fmt.format(sumBal/countCards); 
        }
        else {
            return "Customers were not found!";
        }
    }

    public ArrayList<Customer> getMailingList(double low, double high)
    {
        ArrayList<Customer> mailingList = new ArrayList<Customer>();
        for (Customer cst: customers){
            if(cst.getBalance() > low && cst.getBalance() < high){
                mailingList.add(cst);
            }
        }
        return mailingList;
    }

    public ArrayList<Customer> getMailingList(String keyword){
        ArrayList<Customer> mailingList = new ArrayList<Customer>();
        for (Customer cst: customers){
            if (cst.getCity().toLowerCase().contains(keyword.toLowerCase()) || cst.getState().toLowerCase().contains(keyword.toLowerCase())){
                mailingList.add(cst);
            }
        }
        return mailingList;
    }

    public static void main(String args[]){
        String info = "male, Joe, Smith, 4/20/1963, 123 St., San Francisco, CA,49401, Discover, 12345.67";
        Customer cust1 = new Customer(info);
        info = "female, Jo Anne, Henderson, 2/19/1999, 345 Ave., Detroit, MI, 49401, Mastercard, 12000";
        Customer cust2 = new Customer(info);
        info = "female, Happy, Pants, 2/19/1988, 654 Ct., Mayday, AK, 99999, Mastercard, 908703.0";
        Customer cust3 = new Customer(info);
        info = "male, Dante, Cope, 1/5/1922, 353 St., Grand Rapids, MI, 49546, Visa, 1000000.0";
        Customer cust4 = new Customer(info);
        info = "male, George, Bradley, 2/5/1924, 4246 Knapp Rd., Grand Rapids, MI, 49444, Visa, 0.0";
        Customer cust5 = new Customer(info);

        CustomerDatabase db = new CustomerDatabase();
        db.addCustomer(cust1);
        db.addCustomer(cust2);
        db.addCustomer(cust3);
        db.addCustomer(cust4);
        db.addCustomer(cust5);
        System.out.println("Youngest Card Holder: " + db.getYoungestCardholder());
        System.out.println("Highest debt: " + db.getHighestDebt());
        System.out.println("Customer Mailing List in State or City with MI: " + "\n" + db.getMailingList("MI"));
        System.out.println("Customers Mailling List with debt 900000.0 to 1000001.0: " + "\n"  + db.getMailingList(900000.0, 1000001.0));
        System.out.println("Count customers: " + db.countCustomers());
        System.out.println("Count customers zip 99999: " + db.countCustomers(99999));
        System.out.println("Count debt free customers: " + db.countDebtFree());
        System.out.println("Get card Summary Visa: " + db.getCardSummary("Visa"));
    }

}
