public class Food extends Item{

    private double carryBoost;


    /**
     * Constructor voor Food, gewicht wordt automatisch op 0 gezet zodat oneindig hoeveelheden Food kan worden opgenomen on ArrayList items bij Player.
     * Het eten van Food door player geeft hem een kracht boost zodat deze meer kan dragen.
     * @param name naam van het voedsel
     * @param description korte beschrijving
     * @param carryBoost zoveel kracht geeft het aan Player door dit te eten
     */
    public Food(String name, String description, double carryBoost, boolean visible){
        super(name, description, 0, true, visible);
        this.carryBoost = carryBoost;
    }

    /**
     * Retourneert de kracht boost als double
     * @return carryBoost
     */
    public double getCarryBoost() {
        return carryBoost;
    }

    /**
     * Override van superklassen methode getLongDescription()
     * @return een beschrijving als String
     */
    @Override
    public String getLongDescription() {
        return getName() + " (" + getDescription() + ") eating this will up your strength by: " + this.getCarryBoost();
    }
}
