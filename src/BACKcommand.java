public class BACKcommand extends Command {
    public BACKcommand(CommandWord firstWord, String secondWord)
    {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (super.hasSecondWord()){
            System.out.println("Back to where?");
        }else if(Game.showStack().empty()){
            System.out.println("You are at the very beginning");
        }else{
            player.setCurrentRoom(Game.goBack());
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        return false;
    }
}