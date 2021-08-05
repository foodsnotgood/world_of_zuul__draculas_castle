/**
 * Items die doorheen het spel worden verdeeld en die kunnen worden meegenomen door Player
 */
public class Item {
    private String name;
    private String description;
    private double weight;
    private final boolean CANBETAKEN;
    private boolean visible;


    /**
     * Standaard Item geinitialiseerd met
     * @param name naam van het item
     * @param description korte beschrijving
     * @param weight het gewicht
     * @param canBeTaken boolean of het kan worden meegenomen
     * @param visible boolean of het zichtbaar is
     */
    public Item(String name, String description, double weight, boolean canBeTaken, boolean visible) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.CANBETAKEN = canBeTaken;
        this.visible = visible;
    }

    /**
     * Retourneert String met alle info over het item
     * @return
     */
    public String getLongDescription(){
        return getName() + " (" + getDescription() + ") with weight of " + getWeight() + "kg";
    };

    /**
     * Retourneert allen de naam als String van het item
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Retourneert de korte beschrijving als String
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourneert gewicht als double
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * retourneert boolean of het kan worden meegenomen
     * @return
     */
    public boolean isCanBeTaken() {
        return CANBETAKEN;
    }

    /**
     * Retourneert boolean of het zichtbaar is
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * verandert boolean visible in zichtbaar of niet zichtbaar
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
