import java.util.Random;

/**
 * Klasse om een gevecht aan te maken tussen Player  en een monster
 */
public class Fight {
    private Monsters monster;
    private Player player;
    private boolean finished;
    private Parser parser;
    private Factory factory;
    private Random r;

    /**
     * Constructor om gevecht te initialiseren. Commandos zoals Fight en Run worden hier anders geinterpreteerd als normaal en gaan op basis van Random resultaten bepalen
     * @param monster een monster met wie het gevecht wordt aangegaan
     * @param player de Player
     */
    public Fight(Monsters monster, Player player){
        this.monster = monster;
        this.player = player;
        this.finished = false;
        this.parser = new Parser();
        this.r = new Random();
        this.factory = new Factory();


        PrintReader.print(monster.getName());

        System.out.println("A " + monster.getName() + " was lurking in the shadows waiting for you!");
        System.out.println("Draw your weapon or run like a fool!");

        while(!finished){

            System.out.println();
            System.out.println("Type 'fight', 'run', 'change' or 'drink'.");

            Command command = parser.getCommand();
            CommandWord commando = command.getCommandWord();
            String secondWord = command.getSecondWord();


            switch (commando){
                case FIGHT:
                        if (player.getWeapon() == null){
                            player.nextWeapon();
                        }
                        try{
                            Thread.sleep(500);
                            playerAttack();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        if(!monster.killed()) {
                            try{
                                Thread.sleep(1000);
                                monsterAttack();
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                        break;

                case RUN:
                        try{
                            Thread.sleep(1000);
                            if(playerRun()){
                                break;
                            }
                            System.out.println("No luck, the beast is faster and hurt you...");
                            monsterAttack();
                            break;
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        break;

                case CHANGE:
                        CHANGEcommand change = new CHANGEcommand(CommandWord.CHANGE, secondWord);
                        change.execute(player);
                        break;

                case DRINK:
                        DRINKcommand drink = new DRINKcommand(CommandWord.DRINK, secondWord);
                        drink.execute(player);
                        break;

                default:
                    System.out.println("What to you mean?");
                    break;
            }
            if (monster.killed()){
                finished = true;
                System.out.println("Great job sire! The beast is slain.");
                for (Item item : monster.getItems()){
                    player.getCurrentRoom().addItem(item);
                }
                player.getCurrentRoom().removeMonster();
                System.out.println("Look around to see if it had anything useful for you to take!");
            } else if (player.killed()){
                finished = true;
                PrintReader.print("death");
                System.out.println("You died having fought the " + monster.getName() + ". You will be remembered....");
                System.out.println();
            }
        }
    }

    /**
     * Geeft aan of boolean finished op true of false staat.
     * @return boolean finished
     */
    public boolean isFinished(){
        return finished;
    }

    /**
     * private methode om de player te laten attackeren.
     * Een combinatie van een random getal en de sterkte van het gebruikte wapen wordt schade toegevoegd aan het monster.
     * Ook het wapen wordt beschadigd en kan kapot gaan.
     */
    private void playerAttack(){
        int luck = r.nextInt(10);
        double attack = player.getWeapon().getLethalForce()* (double) luck;

        if (attack > 0){
            monster.hurt(attack);
            System.out.println();
            if (!monster.killed())System.out.println("Great! The creature is at " + Math.round(monster.getHealth()) + " health");
            Weapon currentWeapon = player.getWeapon();
            currentWeapon.damageWeapon(attack);

            if (currentWeapon.isBroken()){
                player.setCurrentWeapon(null);
                player.removeItem(currentWeapon);
                System.out.println("Watch out! The " + currentWeapon.getName() + " broke! Change quickly to a different weapon! 'change'");
            }

        }else if (attack == 0) System.out.println("You missed...");
    }

    /**
     * Een Random getal en de sterkte van het Monster bepalen hoeveel schade wordt toegevoegd aan Player
     */
    private void monsterAttack(){
        int mLuck = r.nextInt(5);
        double mAttack = monster.getStrength() * (double) mLuck;
        if (mAttack > 0){
            player.hurt(mAttack);
            System.out.println();
            long playerHealth = Math.round(player.getHealth());
            if (playerHealth > 0){
                System.out.println("The creature lashed out and hurt you. Your health is now " + playerHealth + "...");
            } else {
                System.out.println("The creature lashed out and...");
            }

        }else if (mAttack == 0) System.out.println("\nThe creature missed...");
    }

    /**
     * Op basis van een Random lukt het al dan niet voor de Player om weg te lopen.
     * @return boolean
     */
    private boolean playerRun(){
        int rluck = r.nextInt(24)+1;
        if (rluck % 2 == 0){
            System.out.println("You run and you found a perfect hiding spot. The creature eventually gives up and leaves");
            finished = true;
            return true;
        }return false;
    }
}
