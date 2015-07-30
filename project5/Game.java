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
    private boolean win;
    private boolean lose;
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
        lastLocation = null;
        win = false;
        lose = false;

    }
    // helper method for constructor 
    public void createRooms()
    {
        // instantiates game items
        metalLoop = new Item("Iron loop", "a dark and worn metal loop made out of iron", 5, false);
        rocks = new Item("Various rocks", "The floor is made of gravel with various rocks", 1, false);
        boar = new Item("Small Boar", "Sleeping little boar by the water trough", 10, true);
        bell = new Item("Bronze Bell", "A shiny bronze bell", 1200, false);
        scrapIron = new Item("Scrap Iron", "Heavy iron scrap with sharp hooked end", 8, false);
        horseShoe = new Item("Horse shoe", "Scrapped old horseshoe", 4, false);
        boarAlter = new Item("Boar Altar", "Shrine with a wide stone basin and the features of a boar on the column", 12000, false);
        churchOrgan = new Item("Church Organ", "Massive pipe organ with winding tubes across the churches back walls", 12000, false);
        smallCart = new Item("Small Oxen Cart", "Small four wheeled cart resting at the top of the hill", 12000, false);
        woodenPale = new Item("Wooden Pale", "Simple wooden pale half filled with clean water", 12, false);    
        boar = new Item("Small Boar", "Sleeping little boar by the water trough", 10, true);

        secondSmith = new Room (" a dark room massive blacksmith turns his head grumbles and charges you with his hammer", null);
        start = new Room (" a room on a cot wearing shackles and is dizzy. Mud floor and water dripping from a crack in the ceiling. You are shackled to another person and a metal loop in the wall...", metalLoop, null);     
        quiteMarket = new Room (" a small market with empty stands. About 100 meters long, you can see a small plaza and a church, along with dark smoke pouring from a chimney. Along with a stone well.", bell);
        openHill = new Room (" a ditch on a large hillside leading down to a stream and the edge of a forest. ", smallCart);
        blackSmith = new Room (" the forge room thick hot smoke pours out of a fire as you hear the ringing of a hammer on iron. There is a door slightly ajar. ", scrapIron, horseShoe);
        animalPen = new Room (" a roughly fenced animal pen with sheep, an ox and a few boars laying in the pen", boar);
        well = new Room (" the square by the well with bucket clean fresh water", woodenPale);
        church = new Room (" the large dark cathedral, it has gruesome gothic details made of red granite.", churchOrgan, boarAlter);
        catacomb = new Room (" a dark musty cellar filled with bones", null);
        river = new Room (" a small grove next to a 12 meter wide river flowing briskly south", null);
        ocean = new Room (" a marsh where the river meets endless water to the horizon", null);

        // add neighbors to all rooms
        start.addNeighbor("north", quiteMarket);
        start.addNeighbor("west", openHill);

        quiteMarket.addNeighbor("north", church);
        quiteMarket.addNeighbor("west", blackSmith);
        quiteMarket.addNeighbor("northeast", well);
        quiteMarket.addNeighbor("south", start);
        quiteMarket.addNeighbor("northwest", animalPen);

        well.addNeighbor("south", quiteMarket);
        well.addNeighbor("north", church);
        animalPen.addNeighbor("east", quiteMarket);

        blackSmith.addNeighbor("east", quiteMarket);
        blackSmith.addNeighbor("south", openHill);
        blackSmith.addNeighbor("north", secondSmith);
        secondSmith.addNeighbor("south", blackSmith);

        church.addNeighbor("south", quiteMarket);
        church.addNeighbor("east", well);

        openHill.addNeighbor("west", river);
        openHill.addNeighbor("east", start);
        openHill.addNeighbor("north", blackSmith);

        river.addNeighbor("east", openHill);

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
        currentMessage = "Welcome to Mystery Text Based Adventure." + "\n" + "You control Cato, you can move through the world, collect items and performing actions." + "\n" + "Use your imagination, explore the world and do different things – find the purpose of life!" + "\n";
    }

    public String getMessage()
    {
        return currentMessage;
    }

    public void help()
    {
        currentMessage = "You need to find a way out of this place!" + "\n";
    }

    public void show()
    {
        currentMessage = currentLocation.getLongDescription() + "\n";
    }

    public void inventory()
    {
        currentMessage = "";
        for(Item itm: playerItems){
            currentMessage = currentMessage + itm.getName() + " " + "\n";
        }
        System.out.println(currentMessage);
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
            currentMessage = "You don't have that item"+ "\n";
        }
        else{
            if(canEat.isEdible() == true){
                currentMessage = "Yum that was a tasty " + canEat.getName() + "!" + "\n";
                playerItems.remove(canEat);
            }
            else if(canEat.isEdible() == false){
                currentMessage = canEat.getName() + " is not edible." + "\n";
            }
        }

    }

    public boolean gameOver()
    {
        if(currentLocation == ocean){
            win = true;
            currentMessage = "You see a ship in the distance and quickly build an enormous bonfire, before night falls a small off-board ship has come and you join the crew";
        }

        if(currentLocation == catacomb){
            lose = true;
            currentMessage = "A phantom appears and kills you.";
        }

        if(win == true){
            currentMessage = "You've won!" + "\n";
            return true;
        }

        else if(lose == false){
            currentMessage = "You've lost!" + "\n";
            return true;
        }

        else {
            return false;
        }     
    }

    public void inspect()
    {
        if (currentLocation == church){
            currentMessage = "You approach the church organ and accidentally press a key, the organ bellows a massive low tone. The roof shakes and falls onto you crushing your body" + "\n";
            lose = true;
        }

        else if (currentLocation == openHill){
            currentMessage = "You walk up to the cart and bend to check the axels, the small stones under your feet move and the cart shifts down the hill towards the river rolling uncontrollably" + "\n";
            brokenCart = new Item("Broken Cart", "The cart has shattered into pieces there are numerous usable boards", 12000, false);
            river = new Room (" a small grove next to a 12 meter wide river flowing briskly south", brokenCart);
            river.addNeighbor("south", ocean);
            river.addNeighbor("east", openHill);
        }

        else if (currentLocation == church && searchInventory("Wooden Pale") == woodenPale && searchInventory("Small Boar") == boar){
            currentMessage = "You approach the boar alter and out of your inventory hops the boar, it lands in the basin and knocks the water out of your wooden pale - PIGGY BATH!!! The squeaking boar wakes the old bishop who shows you out of the abandoned town.";
            playerItems.remove(boar);
            win = true;
        }

        else if(currentLocation == church && searchInventory("Small boar") == boar){
            catacomb = new Room (" a dark musty cellar filled with bones", null);
            catacomb.addNeighbor("south", church);
            church.addNeighbor("north", catacomb);

        }

        else if (currentLocation == church && searchInventory("Wooden Pale") == woodenPale){
            currentMessage = "You approach the boar alter and look at its basin, there are human bones next to the alter, but also animal bones, you pour water into the basin, a massive portal opens beneath your feet and you fall into paradise";
            win = true;
        }

        else {
            currentMessage = "There is nothing to inspect here!" + "\n" + "\n";
        }
    }

    public void pickup()
    {
        if(currentLocation.hasItem() == false){
            currentMessage = "There is no item in the room to take" + "\n";
        }
        else if(currentLocation.getItem().getWeight() > 100){
            currentMessage = "That item is too heavy to pickup" + "\n";
        }
        else{
            playerItems.add(currentLocation.getItem());
            currentMessage = "You have picked up " + currentLocation.getItem().getName() + "\n";
            currentLocation.removeItem();
        }

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
            currentMessage = "This room already has an item you can't put one down" + "\n";
        }
        else if(playerItems.size() == 0){
            currentMessage = "You are not holding any items" + "\n";
        }
        else {
            for(Item i: playerItems){
                if(i.getName().equals(item)){
                    holder = i;
                    haveItem = true;
                    currentMessage = "You have dropped " + i.getName() + "." + "\n";
                }
            }
            playerItems.remove(holder);
            currentLocation.addItem(holder);
            if (haveItem == false){
                currentMessage = "You are not holding that item" + "\n";
            }
        }

    }

    public void backup()
    {
        if(lastLocation == null){
            currentMessage = "You can't go back from here" + "\n";
        }
        else{
            currentLocation = lastLocation;
            currentMessage = "You are back in " + currentLocation.getDescription() + "\n";
        }
    }

    //Test out the Game class
    public static void main (String args[]){
        Game testGame = new Game();
        testGame.setIntroMessage();
        System.out.println(testGame.getMessage());
        System.out.println(testGame.currentLocation.getDescription());
        //System.out.println(testGame.currentLocation.getMovements());
        System.out.println(testGame.currentLocation.hasItem());
        testGame.pickup();
        testGame.inventory();
        testGame.leave("Iron loop");
        System.out.println(testGame.getMessage());
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
