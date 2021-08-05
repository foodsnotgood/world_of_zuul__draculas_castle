public class Key extends Item{
    private int keyCode;

    public Key(String name, String description, double weight, boolean canBeTaken, boolean visible, int keyCode) {
        super(name, description, weight, canBeTaken, visible);
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
