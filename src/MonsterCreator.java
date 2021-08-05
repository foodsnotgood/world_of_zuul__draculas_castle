import java.util.*;

/**
 * Klasse om monsters aan te maken met random Items.
 * Door getMonsters() kan een ArrayList worden verkregen met alle Monsters klaar om verder te verdelen in het spel
 */
public class MonsterCreator {
    private ArrayList<Monsters> monsters;
    private Random random;

    /**
     * initialiseert de ArrayList monsters
     */
    public MonsterCreator(){
        random = new Random();
        monsters = new ArrayList<>();
    }


    /**
     * Maakt monsters aan en voegt deze toe aan ArrayList monsters
     */
    private ArrayList<Monsters> generateMonsters(int numberOfMonsters){

        ArrayList<Monsters> monstersWithoutItems = new ArrayList<>();
        while(monstersWithoutItems.size()<numberOfMonsters){
            Monsters monster;
            int randomNumber = random.nextInt(5);

            switch (randomNumber){
                case 0:
                    monster = new Monsters("Werewolf", 3.2, 3);
                    break;
                case 1:
                    monster = new Monsters("Werewolf", 2.8, 2);
                    break;
                case 2:
                    monster = new Monsters("Vampire", 4.6 , 4);
                    break;
                case 3:
                    monster = new Monsters("Vampire", 3.9, 3);
                    break;
                case 4:
                    monster = new Monsters("Halfdead", 6.3, 3);
                    break;
                default: monster = null;
            }
            monstersWithoutItems.add(monster);
        }
        return monstersWithoutItems;
    }


    /**
     * Verdeeld items at random aan monsters
     * @param monsters ArrayList van Monsters
     * @param items ArrayList van Items
     */
    private ArrayList<Monsters> distributeItems(ArrayList<Monsters> monsters, ArrayList<Item> items){
        Iterator it = items.iterator();
        while(it.hasNext()){
            for (Monsters monster : monsters) {
                    if (!monster.hasMaxItems()) {
                        Item item = (Item) it.next();
                        monster.addItem(item);
                        it.remove();
                        int allMonsters = monsters.size();
                        int teller = 0;
                        for (Monsters m : monsters){
                            if (m.hasMaxItems()) teller++;
                        }
                        if (teller >= allMonsters) return monsters;
                    }
            }
        }
        return monsters;
    }

    /**
     * Maakt een bepaald aantal Monsters aan met items uit een ArrayList
     * @param numberOfMonsters hoeveel Monsters
     * @param items ArrayList van items
     * @return ArrayList met alle Monsters met items
     */
    public ArrayList<Monsters> generateMonstersWithItems(int numberOfMonsters, ArrayList<Item> items){
        ArrayList<Monsters> monsters = generateMonsters(numberOfMonsters);
        distributeItems(monsters, items);

        return monsters;
    }
}
