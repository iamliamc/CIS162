
/**
 * This is the main (example) for the Bank Account Program 
 * 
 * @author Brandon Shannon Liam Considine
 * @version 5/19/2015
 */
public class main
{
    public static void main(String args[])
    {
        BankAccount joe = new BankAccount("Joe Smith", 5643, 1000.0);
        joe.makeDeposit(247.35);
        System.out.println(joe);

        BankAccount heidi = new BankAccount("Heidi Lee", 1946, 2000.0);
        heidi.makeWithdrawal(247.35);
        System.out.println(heidi);
    }

}
