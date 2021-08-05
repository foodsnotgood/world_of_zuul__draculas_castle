/**
 * Klasse om objecten aan te maken die andere items kan verbergen
 */
public class ConceilingObject extends Item{

    private Item hiddenObject;
    private boolean canSeeHiddenObject;

    /**
     * Constructor om object te initialiseren. Een hidden object moet steeds apart toegevoegd worden.
     * @param name
     * @param description
     * @param weight
     * @param visible
     */
    public ConceilingObject(String name, String description, double weight, boolean visible){
        super(name, description, weight, true, visible);
        this.canSeeHiddenObject = false;
        this.hiddenObject = null;
    }

    public boolean hasHiddenObject(){
        return hiddenObject != null;
    }

    public void setCanSeeObject(boolean canSeeHiddenObject){
        this.canSeeHiddenObject = canSeeHiddenObject;
    }

    private boolean isCanSeeHiddenObject(){
        return this.canSeeHiddenObject;
    }

    public void setHiddenObject(Item object){
        this.hiddenObject = object;
    }

    public Item getHiddenObject(){
        if (isCanSeeHiddenObject())return this.hiddenObject;
        return null;
    }


    public boolean removeHiddenObject(){
        if (this.isCanSeeHiddenObject()){
            this.hiddenObject = null;
            return true;
        }
        return false;
    }

    public String getLongDescription() {
        return getName() + " (" + getDescription() + ") with weight of " + getWeight() + "kg";
    }
}
