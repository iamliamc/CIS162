import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test cases for CashRegister
 * 
 * @author "Hans Dulimarta <dulimarh@cis.gvsu.edu>"
 * @version Fall 2012
 */
public class CashTest {

    private static StringBuilder textLog;
    private static Random gen;
    private static NumberFormat fmt;
    private static YesNoResponder yesNo;
    private static PrintStream stdout;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        textLog = new StringBuilder();
        /* save the original System.out */
        stdout = System.out;
        System.setOut(new PrintStream(logger));
        gen = new Random();
        fmt = NumberFormat.getCurrencyInstance();
        
        /* Create an output pipe for supplying "y"-"n" response
         * to the tested program */
        yesNo = new YesNoResponder();
        PipedInputStream fakeKbd = new PipedInputStream();
        yesNo.connect(fakeKbd);

        /* The tested program reads its input from the keyboard (System.in)
         * and we need to create our own fake keyboard.
         */
        System.setIn(fakeKbd);
    }

    @AfterClass
    public static void tearDown() {
        /* restore System.out to its original stdout */
        System.setOut(stdout);
    }
    
    @Test
    public void testConstructor() {
        textLog.setLength(0);
        for (String s : STORE_NAMES) {
            new CashRegister(s);
            Assert.assertTrue("Constructor should print store name \"" + s + "\"", 
                    textLog.indexOf(s) >= 0);
        }
    }
    
    @Test
    public void testZeroSales() {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        Assert.assertEquals("Constructor: total sales should be initialized to ZERO", 0.0, 
                c.getTotalSales(), TOLERANCE);
    }
    
    @Test
    public void testZeroAmountDue() {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        Assert.assertEquals("Constructor: amount due should be initialized to ZERO", 0.0, 
                c.getAmountDue(), TOLERANCE);
    }
    
    @Test 
    public void testScanPrice()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        testScanPrices (c);
    }
    
    private void testScanPrices (CashRegister c)
    {
        final int NUM_SALES_ITEM = 20;
        double total = 0.0;
        for (int k = 0; k < NUM_SALES_ITEM; k++) {
            textLog.setLength(0);
            /* Add 1 cent to ensure non-zero price */
            double p = 0.01 + gen.nextDouble() * 50.0; 
            String pStr = fmt.format(p);
            total += p;
            c.scanPrice(p);
            Assert.assertEquals("scanPrice(): incorrect calculation of amount due", total, 
                    c.getAmountDue(), TOLERANCE);
            Assert.assertTrue("scanPrice() : incorrect currency format, expected " + 
                    pStr, textLog.toString().indexOf(pStr) >= 0);
        }
    }
    
    @Test
    public void testCompleteSale()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        testScanPrices (c);
        double due = c.getAmountDue();
        double tax = due * SALES_TAX;
        double total = due + tax;
        textLog.setLength(0);
        c.completeSale();
        double dueAfterTax = c.getAmountDue();
        Assert.assertEquals("completSale(): incorrect total due", 
                total, dueAfterTax, TOLERANCE);
        String taxStr = fmt.format(tax);
        String totalStr = fmt.format(total);
        int pos;
        pos = textLog.toString().indexOf(taxStr);
        Assert.assertTrue("compleSale(): incorrect sales tax, expected " + taxStr, 
                pos >= 0);
        pos = textLog.toString().indexOf(totalStr);
        Assert.assertTrue("compleSale(): incorrect total amount due, expected " + totalStr, 
                pos >= 0);
    }
    
    @Test
    public void testCancelSale()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        testScanPrices (c);
        textLog.setLength(0);
        c.cancelSale();
        Assert.assertEquals("cancelSale(): amount due should be reset to ZERO", 0.0, 
                c.getAmountDue(), TOLERANCE);
    }

    @Test
    public void testMakeExactPayment ()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        testScanPrices (c);
        double due = c.getAmountDue();
        c.makePayment(due);
        Assert.assertEquals("makePayment(): amount due should be reset to zero", 
                0.0, c.getAmountDue(), TOLERANCE);
    }

    @Test
    public void testMakeHighPayment ()
    {
        final double EXTRA_PAY = 2.50;
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        testScanPrices (c);
        double due = c.getAmountDue();
        textLog.setLength(0);
        c.makePayment(due + EXTRA_PAY);
        String change = fmt.format(EXTRA_PAY);
        int pos = textLog.toString().indexOf(change);
        Assert.assertTrue("makePayment(): incorrect change, expected value " + change, 
                pos >= 0);
    }
    
    @Test
    public void testSalesReport()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        doTransactions (c);
    }
    
    private void doTransactions(CashRegister c)
    {
        final int NUM_TRANSACTIONS = 200;
        final double CANCEL_PERCENTAGE = 0.10;
        double totalSales = 0.0;
        for (int k = 0; k < NUM_TRANSACTIONS; k++)
        {
            testScanPrices(c);
            if (gen.nextDouble() < CANCEL_PERCENTAGE) {
                c.cancelSale();
            }
            else {
                c.completeSale();
                double due = c.getAmountDue();
                totalSales += due;
                c.makePayment(due + 1.0); /* pay extra */
            }
        }
        textLog.setLength(0);
        c.showSalesReport();
        int pos;
        pos = textLog.toString().indexOf(STORE_NAMES[0]);
        Assert.assertTrue("showSalesReport(): missing store name",
                pos >= 0);
        String totalStr = fmt.format(totalSales);
        pos = textLog.toString().indexOf(totalStr);
        Assert.assertTrue("showSalesReport(): incorrect total sales. Expected " 
                + totalStr, pos >= 0);
    }

    @Test
    public void testInvalidScanPrice()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        textLog.setLength(0);
        c.scanPrice(0.0);
        Assert.assertTrue("scanPrint(): missing message for zero price", 
                textLog.length() > 0);
        textLog.setLength(0);
        c.scanPrice(-1.0);
        Assert.assertTrue("scanPrint(): missing message for negative price", 
                textLog.length() > 0);
    }
    
    
    @Test
    public void testClearSales()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        doTransactions(c);
        double total = c.getTotalSales();
        
        yesNo.response(false); /* place a "n" response on the "keyboard" */
        c.clearAllSales();
        Assert.assertEquals("clearAllSales(): Total sales should not be reset to zero", 
                total, c.getTotalSales(), TOLERANCE); 

        yesNo.response(true); /* place a "y" response on the "keyboard" */
        c.clearAllSales();
        total = c.getTotalSales();
        Assert.assertEquals("clearAllSales(): Total sales should not be reset to zero", 
                0, total, TOLERANCE); 
    }
    
    @Test
    public void testPaymentMessage()
    {
        CashRegister c = new CashRegister(STORE_NAMES[0]);
        testScanPrices(c);
        c.completeSale();
        double due = c.getAmountDue();
        
        double[] pays = {-1.0, due /2, due, due * 2};
        String[] payStr = {"negative", "insufficient", "exact", "extra"};
        String[] msgs = new String[pays.length];
        for (int k = 0; k < pays.length; k++) {
            msgs[k] = pay (c, pays[k]);
            Assert.assertTrue("makePayment(): missing message for " + 
                    payStr[k] + " payment ", msgs[k].length() > 0);
        }
        for (int k = 0; k < msgs.length; k++)
            for (int m = k + 1; m < msgs.length; m++)
            {
                if (msgs[k].equals(msgs[m]))
                    Assert.fail("makePayment(): message for paying " +
                            fmt.format(pays[k]) + " and " + fmt.format(pays[m]) + 
                            " should be different");
            }
    }
    
    private String pay (CashRegister c, double amount)
    {
        textLog.setLength(0);
        c.makePayment(amount);
        return textLog.toString();
    }
    
    /* Use the following class to capture all output from System.out */
    private static OutputStream logger = new OutputStream() {
        @Override
        public void write(int b) {
            textLog.append((char)b);
        }

    };
    
    private static class YesNoResponder extends PipedOutputStream {
        
        public void response (boolean yesNo)
        {
            try {
                write(yesNo ? 'y' : 'n');
                write('\n');
            } catch (Exception e) {
                System.err.println ("YesNoResponder: exception: " + e.getMessage());
            }
        }
    }
    
    final static double TOLERANCE = 1E-3;
    final static double SALES_TAX = 0.06;
    final String[] STORE_NAMES = {"My Store", "uMart", "Dulimarta's Store"};
    
    public static void main(String[] args) {
        try {
            Result res = org.junit.runner.JUnitCore.runClasses(CashTest.class);
            if (res.getFailureCount() == 0) {
                System.out.println ("Congratulations. All tests passed successfully");
            }
            else {
                System.out.printf ("**** The following %d tests failed ****\n", res.getFailureCount());
                int count = 1;
                for (Failure f : res.getFailures()) {
                    System.out.printf ("[%2d] %-45s: %s\n", count, f.getDescription(), f.getMessage());
                    count++;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
        
    }
}
