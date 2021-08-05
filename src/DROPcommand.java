public class DROPcommand extends Command {
    public DROPcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return false;
        }
        String itemName = super.getSecondWord();
        if (player.drop(itemName)) {
            System.out.println(player.getCurrentRoom().getLongDescription());
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
        return false;
    }
}