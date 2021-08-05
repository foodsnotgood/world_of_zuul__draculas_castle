public class EATcommand extends Command {

    public EATcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()){
            System.out.println("What do you crave?");
            return false;
        }
        if (player.eat(super.getSecondWord())){
            System.out.println("Great choice!");
            System.out.println("You can now carry: " + player.getMaxWeight() + "!");
        }else{
            System.out.println("you cannot eat this ");
        }
        return false;
    }
}