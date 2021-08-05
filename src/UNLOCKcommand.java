import java.util.ArrayList;

public class UNLOCKcommand extends Command{
    public UNLOCKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (player.hasKey()){
            if (!this.hasSecondWord()){
                System.out.println("What do you want to unlock? Type 'Unlock' + the direction / name of the item.");
                return false;
            }

            Locked locked = getLocked(player);

            if (locked == null){
                System.out.println("No lock to be opened...");
                return false;
            }
            if (locked.isLocked()){
                boolean foundTheRightKey = unlock(player, locked);

                if (!foundTheRightKey) {
                    System.out.println("None of the keys you have seem to fit this lock");
                    return false;
                }
                if (foundTheRightKey && locked instanceof LockedRoom){
                    System.out.println("Great, you unlocked the door");
                    return false;
                }
                if (foundTheRightKey && locked instanceof StaticConceilingObject){
                    System.out.println("Great, you unlocked " + ((StaticConceilingObject) locked).getName());
                    System.out.println("Try opening it now.");
                    return false;
                }
            }
            System.out.println("The door seems to be open");
            return false;
        }
            System.out.println("You haven't got a key");
            return false;
    }

    private Locked getLocked(Player player){
        Locked locked = null;
        Room room = player.getCurrentRoom().getExit(this.getSecondWord());
        if (room != null && room instanceof Locked) locked = (Locked) room;
        Item item = player.getCurrentRoom().getItem(this.getSecondWord());
        if (item != null && item instanceof Locked) locked = (Locked) item;

        return locked;
    }

    private boolean unlock(Player player, Locked locked){
        ArrayList<Key> keys = player.getKeys();
        for (Key key : keys){
            if (key.getKeyCode() == locked.getKeyCode()){
                locked.setLocked(false);
                return true;
            }
        } return false;
    }
}
