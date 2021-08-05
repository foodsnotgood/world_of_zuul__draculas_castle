import java.util.Scanner;

public class GOcommand extends Command{
    private Fight fight;

    public GOcommand(CommandWord firstWord, String secondWord){
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()){
            System.out.println("Go where?");
            return false;
        }
        Room nextRoom = player.getCurrentRoom().getExit(super.getSecondWord());
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            if(nextRoom instanceof LockedRoom && ((LockedRoom) nextRoom).isLocked()){
                System.out.println("The door is locked. You will need to use a key to unlock the door");
                return false;
            }

            addToHistory(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);

            if (player.getCurrentRoom().hasMonster()){
                Monsters monster = player.getCurrentRoom().getMonster();
                fight = new Fight(monster, player);
                if (fight.isFinished() && monster.killed()){
                    if (monster instanceof Dracula){
                        PrintReader.print("victory");
                        System.out.println();
                        System.out.println("You have done the unthinkable. Dracula is dead. Actually dead. \nYour name will be known through songs for eons to come.");
                        return true;
                    }
                    return false;
                } else if (fight.isFinished() && player.killed()){
                    System.out.println("Do you want to try again?  type 'y' / 'n' ");
                    System.out.print(">  ");
                    String answer = getAnswer();
                    switch (answer){
                        case "y" : player.setCurrentRoom(Game.goBack());
                            player.setHealth(100);
                            break;
                        case "n" : return true;
                        default:
                            System.out.println("I don't know what you mean...");
                            break;
                    }
                }
            }
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        return false;
    }

    private String getAnswer(){
        Scanner reader = new Scanner(System.in);
        String answer = reader.nextLine();
        return answer;
    }

    private void addToHistory(Room currentRoom){
        Game.addToHistory(currentRoom);
    }
}
