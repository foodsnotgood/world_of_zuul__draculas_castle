import java.util.ArrayList;
import java.util.Scanner;

public class OPENcommand extends Command {
    private Scanner reader;

    public OPENcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
        reader = new Scanner(System.in);
    }


    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()){
            System.out.println("Open what?");
            return false;
        }

        Item item = player.getCurrentRoom().getItem(super.getSecondWord());

        if (item instanceof StaticConceilingObject){
            StaticConceilingObject sco = (StaticConceilingObject) item;

            if(!sco.isLocked()){
                return secretExit(sco, player);
            }
            System.out.println("It's locked... Try unlocking it!  ('unlock')");
            return false;
        }

        Item takenItem = player.getItem(super.getSecondWord());

        if (takenItem instanceof ConceilingObject){
            ConceilingObject co = (ConceilingObject) takenItem;
            if (co.hasHiddenObject()){
                co.setCanSeeObject(true);
                Item hiddenObject = co.getHiddenObject();

                System.out.println("You found a hidden " + hiddenObject.getName() + "! \n This could be very useful. Do you want to take it? (Type 'y' / 'n')");

                String answer = getAnswer();

                switch (answer) {
                    case "y":
                        player.addItem(hiddenObject);
                        co.removeHiddenObject();
                        System.out.println("\nGood thinking, you never know!");
                        break;
                    case "n":
                        if (co.removeHiddenObject()) {
                            player.getCurrentRoom().addItem(hiddenObject);
                            System.out.println("You dropped the " + hiddenObject.getName());
                        } else {
                            System.out.println("Something went wrong");
                        }
                        break;
                    default:
                        System.out.println("I don't know what you mean...!");
                }return false;
            }
                System.out.println("Nothing here...");
                return false;
        }
        System.out.println("You cannot open this...");
        return false;
    }

    private boolean secretExit(StaticConceilingObject staticConceilingObject, Player player){
        StaticConceilingObject sco = staticConceilingObject;
        if (sco.hasSecretExit()){
            SecretExit secretExit = sco.getSecretExit();
            System.out.println("You found a secret passage. Do you want to find out where is leads to? (Type 'y' / 'n')");
            String answer = reader.nextLine();
            switch (answer) {
                case "y":
                    addToHistory(player.getCurrentRoom());
                    player.setCurrentRoom(secretExit.getExitTo());
                    System.out.println(player.getCurrentRoom().getLongDescription());
                    break;
                case "n":
                    System.out.println("Maybe another time...");
                    break;
                default:
                    System.out.println("I don't know what you mean...!");
            }
            return false;
        }
        System.out.println("Nothing here sire!");
        return false;
    }

    private String getAnswer(){
        System.out.print("> ");
        String answer = reader.nextLine();
        return answer;
    }

    private void addToHistory(Room currentRoom){
        Game.addToHistory(currentRoom);
    }
}