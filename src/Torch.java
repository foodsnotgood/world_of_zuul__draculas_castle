public class Torch extends Item{
    private boolean lit = false;

    public Torch(String name, String description, double weight){
        super(name, description,weight,true, true);
    }

    public boolean isLit() {
        return lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    public String getLongDescription() {
        return getName() + " (" + getDescription() + ") with weight of " + getWeight() + "kg";
    }
}
