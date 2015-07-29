import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Write a description of class Game here.
 * 
 * Liam Considine
 * 7.21.15
 */
public class Game
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> playerItems;
    private Room currentLocation;
    private Room lastLocation;
    private String currentMessage;
    private boolean winLose;
    //Game locations and items

    private Item metalLoop;
    private Item rocks;
    private Item boar;
    private Item bell;
    private Item scrapIron;
    private Item horseShoe;
    private Item boarAlter;
    private Item churchOrgan;
    private Item smallCart;
    private Item brokenCart;
    private Item woodenPale;

    private Room animalPen;
    private Room start;
    private Room quiteMarket;
    private Room openHill;
    private Room blackSmith;
    private Room secondSmith;
    private Room well;
    private Room church;
    private Room catacomb;
    private Room river;
    private Room ocean;

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        // initialise instance variables
        playerItems = new ArrayList<Item>();
        createRooms();

    }
    // helper method for constructor 
    public void createRooms()
    {
        // instantiates game items
        metalLoop = new Item("Iron loop", "a Dark and worn metal loop made out of Iron", 5, false);
        rocks = new Item("Various rocks", "The floor is made of gravel with various rocks", 1, false);
        boar = new Item("Small Boar", "Sleeping little boar by the water trough", 10, true);
        bell = new Item("Bronze Bell", "A shiny bronze bell", 1200, false);
        scrapIron = new Item("Scrap Iron", "Heavy iron scrap with sharp hooked end", 8, false);
        horseShoe = new Item("Horse shoe", "Scrapped old horseshoe", 4, false);
        boarAlter = new Item("Boar Altar", "Shrine with a wide stone basin and the features of a boar on the column", 12000, false);
        churchOrgan = new Item("Church Organ", "Massive pipe organ with winding tubes across the churches back walls", 12000, false);
        smallCart = new Item("Small Oxen Cart", "Small four wheeled cart resting at the top of the hill", 12000, false);
        brokenCart = new Item("Broken Cart", "The cart has shattered into pieces there are numerous usable boards", 12000, false);
        woodenPale = new Item("Wooden Pale", "Simple wooden pale damp and moldy", 12, false);    

        secondSmith = new Room ("A massive blacksmith turns his head grumbles and charges you with his hammer", null);
        start = new Room ("starts on a cot wearing shackles and is dizzy. Mud floor and water dripping from a crack in the ceiling. You are shackled to another person and a metal loop in the wall.", metalLoop, null);     
        quiteMarket = new Room ("Small market with empty stands. About 100 meters long, you can see a small plaza and a church, along with dark smoke pouring from a chimney. Along with a stone well.", bell);
        openHill = new Room ("Hillside leading down to a stream and the edge of a forest. ", smallCart);
        blackSmith = new Room ("Thick hot smoke pours out of a fire as you hear the ringing of a hammer on iron. There is a door slightly ajar. ", scrapIron, horseShoe);
        animalPen = new Room ("A roughly fenced animal pen with sheep, an ox and a few boars laying in the pen", boar);
        boar = new Item("Small Boar", "Sleeping little boar by the water trough", 10, true);
        well = new Room ("well with bucket clean fresh water", woodenPale);
        church = new Room ("Large dark cathedral with gothic details made of red granite.", churchOrgan, boarAlter);
        catacomb = new Room ("Dark musty cellar filled with bones", null);
        river = new Room ("12 meter wide river flowing briskly south", null);
        ocean = new Room ("endless water to the horizon", null);

        // add neighbors to all rooms
        start.addNeighbor("north", quiteMarket);
        start.addNeighbor("west", openHill);

        quiteMarket.addNeighbor("north", church);
        quiteMarket.addNeighbor("west", blackSmith);
        quiteMarket.addNeighbor("the middle", well);
        quiteMarket.addNeighbor("south", start);
        quiteMarket.addNeighbor("left", animalPen);

        blackSmith.addNeighbor("east", quiteMarket);
        blackSmith.addNeighbor("south", openHill);
        blackSmith.addNeighbor("north", secondSmith);
        secondSmith.addNeighbor("south", blackSmith);

        church.addNeighbor("south", quiteMarket);
        church.addNeighbor("north", catacomb);
        church.addNeighbor("the middle", well);
        // add boar win here

        openHill.addNeighbor("west", river);
        openHill.addNeighbor("east", start);
        openHill.addNeighbor("north", blackSmith);

        river.addNeighbor("east", openHill);
        river.addNeighbor("south", ocean);

        currentLocation = start;

    }

    public void move(String direction){
        Room nextRoom = currentLocation.getNeighbor(direction);
        if (nextRoom == null){
            currentMessage = "You can't go in that direction";
        }
        else{
            lastLocation = currentLocation;
            currentLocation = nextRoom;
            currentMessage = currentLocation.getLongDescription();
        }
    }

    public void setIntroMessage()
    {
        currentMessage = "Welcome to Mystery Text Based Adventure." + "\n" + "You control Cato, you can move through the world, collect items and performing actions." + "\n" + "Use your imagination, explore the world and do different things – find the purpose of life!";
    }

    public String getMessage()
    {
        return currentMessage;
    }

    public void help()
    {
        currentMessage = "You need to find a way out of this place!";
    }

    public void show()
    {
        currentMessage = currentLocation.getLongDescription();
    }

    public void inventory()
    {
        for(Item itm: playerItems){
            currentMessage = "";
            currentMessage += "" + itm.getName() + " ";
        }
    }

    public void eat (String item)
    {
        Item canEat = null;
        for(Item itm: playerItems){
            if(itm.getName().equals(item)){
                canEat = itm;
            }
        }
        if (canEat == null){
            currentMessage = "You don't have that item";
        }
        else{
            if(canEat.isEdible() == true){
                currentMessage = "Yum that was a tasty " + canEat.getName() + "!";
                playerItems.remove(canEat);
            }
            else if(canEat.isEdible() == false){
                currentMessage = canEat.getName() + " is not edible.";
            }
        }

    }

    public boolean gameOver()
    {
        if(winLose == true){
            currentMessage = "You've won!";
            return true;
        }
        else {
            return false;
        }     
    }

    public void pickup()
    {
        if(currentLocation.hasItem() == false){
            currentMessage = "There is no item in the room to take";
        }
        else if(currentLocation.getItem().getWeight() > 100){
            currentMessage = "That item is too heavy to pickup";
        }
        else{
            playerItems.add(currentLocation.getItem());
            currentLocation.removeItem();
        }

    }

    public void inspect()
    {

    }

    private Item searchInventory (String name)
    {
        Item sub = null;
        for(Item itm: playerItems){
            if(itm.getName().equals(name)){
                sub = itm;
            }
        }
        return sub;
    }

    public void leave(String item){
        boolean haveItem = false;
        Item holder = null;
        if (currentLocation.hasItem() == true){
            currentMessage = "This room already has an item you can't put one down";
        }
        else if(playerItems.size() == 0){
            currentMessage = "You are not holding any items";
        }
        else {
            for(Item i: playerItems){
                if(i.getName().equals(item)){
                    holder = i;
                    haveItem = true;
                    currentMessage = "You have dropped" + i.getName() + ".";
                }
            }
            playerItems.remove(holder);
            currentLocation.addItem(holder);
            if (haveItem == false){
                currentMessage = "You are not holding that item";
            }
        }

    }

    public void backup()
    {
        currentLocation = lastLocation;
        currentMessage = currentLocation.getDescription();
    }

    //Test out the Game class
    //
    public static void main (String args[]){
        Game testGame = new Game();
        testGame.setIntroMessage();
        System.out.println(testGame.getMessage());
        System.out.println(testGame.currentLocation.getDescription());
        //System.out.println(testGame.currentLocation.getMovements());
        System.out.println(testGame.currentLocation.hasItem());
        testGame.move("north");
        System.out.println(testGame.currentLocation.getDescription());
        System.out.println(testGame.currentLocation.getMovements());
        testGame.move("west");
        System.out.println(testGame.currentLocation.getDescription());
        System.out.println(testGame.currentLocation.hasItem());
        testGame.pickup();
        testGame.inventory();
        System.out.println(testGame.getMessage());
        System.out.println(testGame.currentLocation.hasItem());
        testGame.leave("Scrap Iron");
        System.out.println(testGame.currentLocation.hasItem());
        testGame.backup();
        testGame.currentLocation.getDescription();
        System.out.println(testGame.getMessage());
        testGame.move("left");
        testGame.pickup();
        testGame.eat("Ducky");
        System.out.println(testGame.getMessage());
        testGame.eat("Small Boar");
        System.out.println(testGame.getMessage());

    }
}
