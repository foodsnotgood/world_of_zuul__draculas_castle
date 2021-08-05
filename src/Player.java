import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Player Klasse met String Naam, Room currentRoom, een List van Items die Player draagt, double mawWeight met maximum gewicht dat kan worden meegenomen,
 * health van de Player en plaats voor een weapon om te vechten.
 */
public class Player {
    private final String NAME;
    private Room currentRoom;
    private ArrayList<Item> items;
    private double maxWeight = 20;
    private double health = 100;
    private Weapon currentWeapon;

    /**
     * Initialiseert Player met een Naam en een currentRoom.
     * Initialisatie van ArrayList items en weapon met begin wapen een 'Dagger'
     * @param name Naam van de Player
     * @param currentRoom De Room waar Player zich op dit moment bevindt.
     */
    public Player(String name, Room currentRoom) {
        this.NAME = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
        Weapon dagger = new Weapon("dagger", "small dagger", 0.3,3.6, true);
        items.add(dagger);
        this.currentWeapon = dagger;
    }

    /**
     * Retourneert String Naam van de Player
     * @return
     */
    public String getName() {
        return NAME;
    }

    /**
     * verandert het double maximum gewicht dat Player kan meenemen
     * @param maxWeight
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Retourneert de huidige Room waar Player zich in bevindt
     * @return Room currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Verandert Room currentRoom om Player te verplaatsen naar andere locatie
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Retourneert double health van de Player
     * @return
     */
    public double getHealth() {
        return health;
    }

    /**
     * Veranderd health van Player
     * @param health
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Retourneert het maximum gewicht als double dat de Player kan dragen.
     * @return maxWeight
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    /**
     * In een Fight als Player wordt geraakt verander health van player min de waarde double van een attack
     * @param attack
     */
    public void hurt(double attack){
        this.health = this.health -attack;
        Math.round(this.health);
    }

    /**
     * Retourneert boolean of Player health bij nul is aangekomen.
     * @return boolean wel of niet gedoodt
     */
    public boolean killed(){
        return health <= 0;
    }

    /**
     * Kijkt of Player een bepaald Item in List heeft op basis van String naam van de Item
     * @param name
     * @return boolean
     */
    public boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    /**
     * Retourneert een Item op basis van een String als deze zich in List items bevindt
     * @param name
     * @return een Item of null
     */
    public Item getItem(String name){
        if (this.hasItem(name)){
            for (Item item : items){
                if (item.getName().equalsIgnoreCase(name)) return item;
            }
        }return null;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    /**
     * Retourneert boolean om te kijken of Player dit Item kan mee nemen.
     * @param item
     * @return
     */
    private boolean canITakeThisItem(Item item){
        if (item instanceof Food && item instanceof Potion) return true;
        if (item.isCanBeTaken()){
            double itemWeight = item.getWeight();
            double totalWeight = 0;
            for(Item i : items){
                totalWeight += i.getWeight();
            }
            return totalWeight + itemWeight <= maxWeight;
        }
        return false;
    }

    private double getTotalWeight(){
        double totalWeight = 0;
        for (Item item : items){
            totalWeight += item.getWeight();
        }
        Math.round(totalWeight);
        return totalWeight;
    }


    /**
     * nagaan of player een Key heeft in zijn ArrayList items
     */
    public boolean hasKey(){
        for (Item item : items){
            if (item instanceof Key) return true;
        }return false;
    }

    /**
     * retourneert een ArrayList met alle sleutels die player heeft
     */
    public ArrayList<Key> getKeys(){
        ArrayList<Key> keys = new ArrayList<>();
        for (Item item : items){
            if (item instanceof Key){
                keys.add((Key)item);
            }
        }
        return keys;
    }

    /**
     * Retourneert het huide wapen van Player
     * @return weapon
     */
    public Weapon getWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon newWeapon){
        this.currentWeapon = newWeapon;
    }

    /**
     * print een lijst van alle wapens uit ArrayList items
     */
    public ArrayList<Weapon> showWeapons(){
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (Item item : items){
            if (item instanceof Weapon){
                weapons.add((Weapon) item);
            }
        }
        return weapons;
    }


    /**
     * Verander van wapen
     * @param weapon
     * @return
     */
    public boolean changeWeapon(String weapon){
        for (Item item : items){
            if (item instanceof Weapon){
                Weapon w = (Weapon) item;
                if (w.getName().equalsIgnoreCase(weapon)){
                    this.currentWeapon = w;
                    System.out.println("Current weapon: " + currentWeapon.getName());
                    return true;
                }
            }
        }
        System.out.println("No such weapon in your arsenal...");
        return false;
    }

    public void nextWeapon(){
        if (currentWeapon == null){
            setCurrentWeapon(showWeapons().get(0));
        }
    }

    /**
     * Kijkt of Item voorkomt in ruimte en of het niet te zwaar is. Zo ja, wordt Item toegevoegd aan ArrayList items of weapons.
     * Als Player nog geen wapen in gebruik heeft wordt het nieuwe wapen als currentWeapon gebruikt.
     * @param itemName
     * @return boolean
     */
    public boolean take(String itemName) {
        if (currentRoom.hasItem(itemName)) {
            Item i = currentRoom.getItem(itemName);
            if (i.isVisible() && canITakeThisItem(i)){
                currentRoom.takeItem(i);
                this.addItem(i);
                return true;
                }
            else if ((i.getWeight()+this.getTotalWeight()) > maxWeight){
                System.out.println("This one is to heavy sir!");
                return false;
            }
        }else if(!currentRoom.hasItem(itemName)){
            System.out.println("There is no item with that name here");
            return false;
        }return false;
    }

    /**
     * Voeg een item toe aan List items
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Kijkt of het item voorkomt in List items.
     * Haalt Item uit de List items van Player en voegt deze toe aan de huidige Room
     * @param itemName
     * @return boolean op basis van of het gelukt is om dit item te droppen
     */
    public boolean drop(String itemName) {
        Item item = this.getItem(itemName);
        if (items.remove(item)) {
            currentRoom.addItem(item);
            return true;
        }
        return false;
    }

    /**
     * Zoekt de juist Food item op basis van een String. De item wordt verwijderd en Player krijgt een krachtboost
     * @param itemName String naam van het op te eten Food item
     * @return boolean
     */
    public boolean eat(String itemName){
        Item item = this.getItem(itemName);
        if (item instanceof Food){
            Food food = (Food) item;
            double hBoost = food.getCarryBoost();
            if (items.remove(food)){
                this.maxWeight += food.getCarryBoost();
            }
            return true;
        }return false;
    }

    /**
     * Zoekt de juist Potion item op basis van een String. De item wordt verwijderd en Player krijgt een health boost.
     * @param itemName String naam van het te drinken Potion item
     * @return boolean
     */
    public boolean drink(String itemName) {
        Item item = this.getItem(itemName);
        if (item instanceof Potion) {
            Potion potion = (Potion) item;
            double hBoost = potion.getHealthBoost();
            if (items.remove(item)) {
                if (this.health < 100) {
                    this.health += hBoost;
                    Math.round(health);
                    if (this.health > 100) {
                        this.health = 100;
                    }
                }
                return true;
            }
        }return false;
    }

    /**
     * Retourneert true als het lukt om een Torch item aan te steken.
     * @param itemName
     * @return
     */
    public boolean light(String itemName){
        Item item = this.getItem(itemName);
        if (item instanceof Torch){
            Torch torch = (Torch) item;
            torch.setLit(true);
            return true;
        }return false;
    }


    /**
     * Retourneert een String met beschrijving van de Player.
     * @return String
     */
    public String getLongDescription() {
        String desc = "Master " + NAME + ", you have ";
        if (items.isEmpty()) {
            desc += "nothing in your bag";
        } else {
            desc += "the following things in your bag: ";
            for (Item item : items) {
                desc += "\n   " + item.getLongDescription();
            }
            desc += "\n Total weight: " + getTotalWeight() + "kg of maximum " + this.maxWeight + "kg";
        }
        desc += "\n Current weapon: " + this.currentWeapon.getName();
        return desc;
    }
}
