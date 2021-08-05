public class TAKEcommand extends Command {
    public TAKEcommand(CommandWord firstWord, String secondWord)
    {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()) {
            System.out.println("Take what?");
            return false;
        }
        String itemName = super.getSecondWord();
        if (player.take(itemName)) {
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        return false;
    }
}
