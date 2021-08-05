public class QUITcommand extends Command {

    public QUITcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (super.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}