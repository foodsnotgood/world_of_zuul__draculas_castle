public class DRINKcommand extends Command{
    public DRINKcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()){
            System.out.println("What do you want to drink?");
            return false;
        }
        if (player.drink(super.getSecondWord())){
            System.out.println("Great choice!");
            System.out.println("Your health is back to: " + player.getHealth() + "!");
        }else{
            System.out.println("you cannot drink this ");
        }
        return false;
    }
}
