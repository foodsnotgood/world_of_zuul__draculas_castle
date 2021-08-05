public class FIGHTcommand extends Command {

    public FIGHTcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Nothing to fight here sire...");
        return false;
    }
}