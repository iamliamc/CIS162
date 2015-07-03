
/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private boolean gender;
    private String last;
    private String first;
    private int month;
    private int day;
    private int year;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String cardBrand;
    private double balance;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(boolean gender, String last, String first, int month, int day, int year, String address, String city, String state, int zip, String cardBrand, double balance)
    {
        this.gender = gender;
        this.last = last;
        this.first = first;
        this.month = month;
        this.day = day;
        this.year = year;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.cardBrand = cardBrand;
        this.balance = balance;
    }
    
    public Customer(String info){
        String [] tokens = info.split(",|/");
        if (tokens[0].trim().equals("male")){
            gender = false;
        }
        else {
            gender = true;
        }
        first = tokens[1].trim();
        last = tokens[2].trim();
        month = Integer.parseInt(tokens[3].trim());
        day = Integer.parseInt(tokens[4].trim());
        year = Integer.parseInt(tokens[5].trim());
        address = tokens[6].trim();
        city = tokens[7].trim();
        state = tokens[8].trim();
        zip = Integer.parseInt(tokens[9].trim());
        cardBrand = tokens[10].trim();
        balance = Double.parseDouble(tokens[11].trim());
    }

    public boolean isFemale()
    {
        if (gender == true){
            return true;
        }
        else {
            return false;
        }
    }
    // Get methods for Customer class
    public String getFirst()
    {
        return first;
    }

    public String getLast()
    {
        return last;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public int getZip()
    {
        return zip;
    }

    public String getCreditCard()
    {
        return cardBrand;
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public double getBalance()
    {
        return balance;
    }
    //Set Methods for customer class
    public void setFirst(String f)
    {
        first = f;
    }

    public void setLast(String l)
    {
        last = l;
    }

    public void setAddress(String a)
    {
        address = a;
    }

    public void setCity (String c)
    {
        city = c;
    }

    public void setState(String s)
    {
        state = s;
    }

    public void setZip(int z)
    {
        zip = z;
    }

    public void setCard(String c)
    {
        cardBrand = c;
    }

    public void setDay(int d)
    {
        day = d;
    }

    public void setMonth(int m)
    {
        month = m;
    }

    public void setYear(int y)
    {
        year = y;
    }

    public void setBalance(double b)
    {
        balance = b;
    }

    public String toString()
    {
        return "" + first + " " + last + "\n" + address + " " + "\n" + city + ", " + state + " " + zip;
    }

    public static void main(String args[]){
        Customer newCust = new Customer(false, "Man", "Fly", 5, 1, 1990, "123 Me Ave.", "Grand Rapids", "MI", 49546, "Visa", 20000.000);       
        System.out.println(newCust.getLast());
        newCust.setLast("Donkey");
        System.out.println(newCust.getLast());
        System.out.println(newCust.getFirst());
        System.out.println(newCust.getMonth());
        System.out.println(newCust.getDay());
        System.out.println(newCust.getYear());
        System.out.println(newCust.getAddress());
        System.out.println(newCust.getCity());
        System.out.println(newCust.getState());
        System.out.println(newCust.getZip());
        System.out.println(newCust.getCreditCard());
        System.out.println(newCust.getBalance());
        
        
    }

}
