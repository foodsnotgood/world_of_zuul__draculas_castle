/**
 * Klasse om wapens te maken gebaseerd op Klasse Item
 */
public class Weapon  extends Item{
    private double lethalForce;
    private double damaged;
    private boolean broken;

    /**
     * Constructor voor wapen.
     * @param name
     * @param description
     * @param lethalForce
     * @param visible
     */
    public Weapon(String name, String description, double weight, double lethalForce, boolean visible){
        super(name, description, weight, true, visible);
        this.lethalForce = lethalForce;
        this.broken = false;
    }

    /**
     * Retourneert als double hoe veel schade het wapen kan aanrichten
     * @return double lethalForce
     */
    public double getLethalForce() {
        return lethalForce;
    }


    /**
     * Maakt een String aan met beschrijving van het wapen
     * @return String
     */
    public String getLongDescription() {
        return getName() + " (" + getDescription() + ") with: Lethal force: " + this.lethalForce + ", weight: " + super.getWeight();
    }

    /**
     * Voegt schade toe aan het wapen.
     * Als de schade 100 bereikt wordt boolean broken op true gezet.
     * @param damage
     */
    public void damageWeapon(double damage){
        if (damage > 0 && !isBroken()){
            double damageToWeapon = damage/1.6;
            this.damaged += damageToWeapon;
            if(damaged >= 100) broken = true;
        }
    }

    /**
     * Retourneert boolen broken
     * @return boolean broken
     */
    public boolean isBroken(){
        return broken;
    }
}
