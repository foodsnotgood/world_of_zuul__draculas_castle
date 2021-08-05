public class LockedRoom extends Room implements Locked{
    private boolean locked;
    private int keyCode;

    public LockedRoom(String description, boolean locked, int keyCode) {
        super(description);
        this.locked = locked;
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
