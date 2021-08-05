
public class RUNcommand extends Command {

    public RUNcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("No need to rush");
        return false;
    }
}
