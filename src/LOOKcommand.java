public class LOOKcommand extends Command {
    public LOOKcommand(CommandWord firstWord, String secondWord)
    {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player)
    {
        if (super.hasSecondWord()){
            if (super.getSecondWord().equals("player")){
                System.out.println(player.getLongDescription());
                return false;
            }
            System.out.println("Look where?  --type 'look player' to see what items you have available--");
            return false;
        }
        System.out.println(player.getCurrentRoom().getLongDescription());
        return false;
    }
}