public class LIGHTcommand extends Command{
    public LIGHTcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()){
            System.out.println("Light what?");
            return false;
        }
        if (player.light(getSecondWord())){
            player.getCurrentRoom().setVisible(true);
            System.out.println(player.getCurrentRoom().getLongDescription());
            return false;
        }else{
            System.out.println("You cannot light this...");
        }
        return false;
    }
}
