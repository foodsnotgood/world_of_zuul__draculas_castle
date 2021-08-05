import java.util.ArrayList;
import java.util.Random;

/**
 * maakt items aan om in het spel te gebruiken
 */
public class ItemCreator {
    private ArrayList<Item> items;
    private Random random;

    public ItemCreator(){
        this.items = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Maakt nieuwe wapens aan at random en steekt deze in een ArrayList weapons om mee verder te werken.
     * @param numberOfWeapons Hoeveel wapens moeten er worden gemaakt?
     * @return ArrayList van die Wapens
     */
    private ArrayList<Weapon> generateWeapons(int numberOfWeapons){

        ArrayList<Weapon> weapons = new ArrayList<>();

        while(weapons.size()<numberOfWeapons){
            Weapon weapon;
            int randomNumber = random.nextInt(6);
            switch (randomNumber){
                case 0:
                    weapon = new Weapon("shortSword", "A one handed sword",4.6, 3.8, random.nextBoolean());
                    break;
                case 1:
                    weapon = new Weapon("longSword", "A two handed heavy sword",6.2,5.9, random.nextBoolean());
                    break;
                case 2:
                    weapon = new Weapon("halberd", "A two handed guarding weapon",5.4,6.2, random.nextBoolean());
                    break;
                case 3:
                    weapon = new Weapon("morningstar", "Heavy and deadly",3.6,5.8, random.nextBoolean());
                    break;
                case 4:
                    weapon = new Weapon("spear", "Long and quick to use",4.7,4.5, random.nextBoolean());
                    break;
                case 5:
                    weapon = new Weapon("axe", "Great for close battle",3.2,4.6, random.nextBoolean());
                    break;
                default: weapon = new Weapon("dagger", "about as useful as a butter knife",0.3, 1.6, random.nextBoolean());
            }
            weapons.add(weapon);
        }
        return weapons;
    }

    /**
     * Maakt nieuwe Food en Potions aan at random en voegt deze toe aan een ArrayList om mee verder te kunnen werken
     * @param numberOfStuff hoeveel spullen moeten er worden gemaakt
     * @return ArrayList van all deze items samen
     */
    private ArrayList<Item> generateFoodAndPotion (int numberOfStuff){
        ArrayList<Item> foodAndPotions = new ArrayList<>();

        while (foodAndPotions.size()<numberOfStuff){

            Item item;
            int randomNumber = random.nextInt(7);

            switch(randomNumber){
                case 0:
                    item = new Food("apple", "slightly molded but still aromatic", 1.3, random.nextBoolean());
                    break;
                case 1:
                    item = new Food("berry", "some strange berries you've never seen before", 5.2, random.nextBoolean());
                    break;
                case 2:
                    item = new Food("licorice", "sweet and aromatic it boosts your blood pressure", 4.5, random.nextBoolean());
                    break;
                case 3:
                    item = new Potion("potion", "almost black in color and it tastes of dirt and mud", 39, random.nextBoolean());
                    break;
                case 4:
                    item = new Potion("potion", "thick and red like blood from a fat pig", 28, random.nextBoolean());
                    break;
                case 5:
                    item = new Potion("potion", "bitter and sharp but easy enough to drink", 19, random.nextBoolean());
                    break;
                case 6:
                    item = new Potion("potion", "sweet and salty, almost too good to consume", 32, random.nextBoolean());
                    break;
                default: item = null;
            }
            foodAndPotions.add(item);
        }
        return foodAndPotions;
    }

    /**
     * Maakt een ArrayList aan met items waarbij verdeling weapons en Food/Potions zelf moet worden ingesteld in percent.
     * Door percentage weapons aan te geven wordt het resterend percentage automatisch berekend voor Food/Potions
     * @param numberOfItems hoeveel items moeten er worden geinitialiseert?
     * @param percentOfWeapons hoeveel percent daarvan Weapons?
     * @return ArrayList met alle items
     */
    public ArrayList<Item> generateItems(int numberOfItems, int percentOfWeapons){
        int percentOfFoodAndPotions = 100-percentOfWeapons;
        ArrayList<Weapon> weapons = generateWeapons(numberOfItems * percentOfWeapons / 100);
        ArrayList<Item> foodAndPotions = generateFoodAndPotion(numberOfItems * percentOfFoodAndPotions / 100);

        for (Weapon weapon : weapons)this.items.add(weapon);
        for (Item foodOrPotion : foodAndPotions)this.items.add(foodOrPotion);

        return this.items;
    }

    /**
     * Maakt een ArrayList aan met items waarbij verdeling Weapons en Food/Potions automatisch 50/50 word gezet.
     * @param numberOfItems hoeveel items moeten er worden gemaakt?
     * @return ArrayList van alle items;
     */
    public ArrayList<Item> generateItems(int numberOfItems){

        ArrayList<Weapon> weapons = generateWeapons(numberOfItems * 50 / 100);
        ArrayList<Item> foodAndPotions = generateFoodAndPotion(numberOfItems * 50 / 100);

        for (Weapon weapon : weapons)this.items.add(weapon);
        for (Item foodOrPotion : foodAndPotions)this.items.add(foodOrPotion);

        return this.items;
    }

    public Item generateRandomItem(){
        ArrayList<Item> itemList = generateItems(1, 0);
        return itemList.get(0);
    }
}
