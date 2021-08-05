public class HELPcommand extends Command {
    private String help;
    private CommandWords validCommands;

    public HELPcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
        validCommands = new CommandWords();
        help = validCommands.showAll();
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Player " + player.getName() + " is seeking to kill count Dracula");
        System.out.println("try to find him and beware the monsters. Do not forget to use a torch to look around because the shadows here");
        System.out.println("are somewhat even darker than elsewhere");
        System.out.println();
        System.out.println("Possible command words are:   " + help);
        System.out.println();
        return false;
    }
}