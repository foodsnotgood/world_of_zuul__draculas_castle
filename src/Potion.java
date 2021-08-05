/**
 * Potion is een subklasse van item
 * door het drinken krijgt health van Player een boost
 */

public class Potion extends Item{
    private double healthBoost;

    /**
     * Initialiseert een potion met dezeflde attributen als item. Weight wordt op 0 gezet zodat men oneindig veel potions zou kunnen meenemen
     * @param name
     * @param description
     * @param visible
     * @param healthBoost
     */
    public Potion(String name, String description,double healthBoost, boolean visible) {
        super(name, description, 0, true, visible);
        this.healthBoost = healthBoost;
    }

    /**
     * Retourneert double health boost
     * @return
     */
    public double getHealthBoost() {
        return this.healthBoost;
    }

    /**
     * override van superklasse methode getLongDescription
     * @return een String met beschrijving van de potion
     */
    @Override
    public String getLongDescription() {
        return getName() + " (" + getDescription() + "), drinking this will up your health by: " + getHealthBoost();
    }
}
