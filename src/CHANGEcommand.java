import java.util.ArrayList;
import java.util.Scanner;

public class CHANGEcommand extends Command{

    private Scanner reader;

    public CHANGEcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
        this.reader = new Scanner(System.in);
    }

    @Override
    public boolean execute(Player player) {
            ArrayList<Weapon> weapons = player.showWeapons();

            if (weapons.isEmpty()) System.out.println("You have no more weapons...");

            System.out.println("These are the weapons in your arsenal. Type the name of the weapon you want to use!");

            for (Weapon weapon : weapons) System.out.println(weapon.getLongDescription());
            System.out.println();

            boolean found = false;

            while (!found){
                System.out.print("> ");
                String answer = getAnswer();

                if (player.changeWeapon(answer)){
                    found = true;
                    return false;
                }
                System.out.println("Try another one");
            }
        return false;
    }

    private String getAnswer(){
        Scanner reader = new Scanner(System.in);
        String answer = reader.nextLine();
        return answer;
    }
}
