import java.util.ArrayList;

public class Monsters {
    private String name;
    private double health;
    private double strength;
    private ArrayList<Item> items;
    private int maxItems;

    public Monsters(String name, double strength, int maxItems) {
        this.name = name;
        this.health = 100;
        this.strength = strength;
        this.items = new ArrayList<>();
        this.maxItems = maxItems;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getStrength() {
        return strength;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void hurt(double attack) {
        this.health = this.health - attack;
        if (health < 0){
            health = 0;
        }
    }

    public boolean hasMaxItems(){
        if (items.size()>= maxItems)return true;
        return false;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public boolean killed(){
        return health <= 0;
    }

}
