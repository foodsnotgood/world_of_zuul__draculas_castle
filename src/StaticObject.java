/**
 * Maakt een Item aan op basis van Item dat niet kan worden meegenomen.
 */

public class StaticObject extends Item{
    public StaticObject(String name, String description, boolean visible) {
        super(name, description, 500, false, visible);
    }

    @Override
    public String getLongDescription() {
        return getName() + " (" + getDescription() + ")";
    }
}
