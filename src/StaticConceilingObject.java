/**
 * Statisch object op basis van StaticObject dat niet kan worden meegenomen en een geheime uitgang bevat.
 */

public class StaticConceilingObject extends StaticObject implements Locked{

    private SecretExit secretExit;
    private boolean locked;
    private int keyCode;

    /**
     * Constructor die het object initialiseert
     * @param name korte naam van het object
     * @param description beschrijving
     * @param visible boolean
     */
    public StaticConceilingObject(String name, String description, boolean visible) {
        super(name, description, visible);
        this.secretExit = null;
        this.locked = false;
        this.keyCode = 0;
    }

    /**
     * Initialiseert het object als locked door een keyCode mee te geven
     * @param name
     * @param description
     * @param visible
     * @param keyCode
     */
    public StaticConceilingObject(String name, String description, boolean visible, int keyCode) {
        super(name, description, visible);
        this.secretExit = null;
        this.locked = true;
        this.keyCode = keyCode;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean hasSecretExit(){
        return secretExit != null;
    }

    public void setSecretExit(SecretExit exit){
        this.secretExit=exit;
    }

    public SecretExit getSecretExit(){
        if (secretExit!=null)return secretExit;
        return null;
    }
}
