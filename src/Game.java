import java.util.*;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Player player;
    private static Stack<Room> history;
    private MonsterCreator monsterCreator;
    private ItemCreator itemCreator;
    private List<Room> rooms;

    /**
     * Create the game and  initialise its internal map.
     */
    public Game() {
        this.monsterCreator = new MonsterCreator();
        this.itemCreator = new ItemCreator();
        createRooms();
        this.parser = new Parser();
        this.history = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        // create the rooms
        Room gate = new Room("in front of a forged iron GATE with stone gargoyles, washed grey by the rain, guarding the property on each side");
        Room driveWay = new Room("on a long curly uneven DRIVEWAY through the castles frontyard");
        Room cemetery = new Room("at the CEMETERY. Multiple families have buried their loved ones here. Some say, if you come here at midnight on a full moon you can here the widows cry in the wind...");
        Room roseGarden = new Room("in a once beautiful ROSE GARDEN. The gray curtain of death has laid a veil of rot on these withered thorns");
        Room catacombs = new Room("in the CATACOMBEs. Here only the past masters and their betrothed were buried. It smells of cold and slightly moist stone and the air here is uneasy");
        Room wineCellar = new Room("in the WINE CELLAR. Only a few spiderweb cobbed old bottles of wine remain in these shelves");
        Room servantEntrance = new Room("at the small unspectacular SERVANT ENTRANCE. Inside you find a untrustworthy stairway leading to a cellar");
        Room mainEntrance = new Room("at the MAIN ENTRANCE. Five big massive stone steps leading to a grand hand carved oaken double door of almost two stories " +
                "high with cast iron hinges");
        Room kitchen = new Room("in the KITCHEN. Once known for its fine meals cooked here, inspired by all corners of the worlds, only remnants of, what seems to be....blood, " +
                "show signs of recent use");
        Room servantQuarters = new Room("in the SLEEPING QUARTERS of the servants. Each having a little room without window and a bed with a hay filled \n" +
                "mattress that clearly have been used recently");
        Room mainHall = new Room("in the MAIN HALL. It's clearly built to impress anybody who entered here.");
        Room diningHall = new Room("in the DINING HALL with a floor chequered in alabaster and black moroccan marble presenting to a long table that " +
                "can offer seating to almost 30 people the a throne like chair" +
                "at the head end");
        Room ballRoom = new Room("in the BALL ROOM. Forgone glory and only the golden ornaments on all the walls and ceiling seem to tell stories of marvelous parties and gatherings of anybody " +
                "who was somebody");
        Room library = new Room("in the LIBRARY. Hundreds, maybe thousands of books, some maybe centuries old, waiting to be picked out the english mahogany carved shelves, " +
                "to reveal their wisdom and secrets");
        Room guestChambers = new Room("in a corridor leading to multiple guest and family chambers");
        Room hallway = new Room("in the HALLWAY, laid out with thick carpets on the wooden floor and centuries old wall \ncarpets depicting knights in various heroic situations, " +
                "making anybody who passes through here almost unnoticeable due to the sound muffling.");
        Room chessRoom = new Room("in the CHESS ROOM, used for a last match before night rest this room is a mess with clothing, drenched in blood, everywhere.\n" +
                "You notice a carpet witch a strange bulge but it's too dark to see clearly");
        Room masterChambers = new LockedRoom("in the MASTER CHAMBERS. Like a throne to his kingdom the master bedroom is overlooking the property", true, 123);

        // initialise room exits
        gate.setExit("north", driveWay);

        driveWay.setExit("west", cemetery);
        driveWay.setExit("east", roseGarden);
        driveWay.setExit("north", mainEntrance);
        driveWay.setExit("south", gate);

        cemetery.setExit("north", servantEntrance);
        cemetery.setExit("east", driveWay);

        roseGarden.setExit("west", driveWay);

        servantEntrance.setExit("down", wineCellar);
        servantEntrance.setExit("north", servantQuarters);
        servantEntrance.setExit("south", cemetery);

        mainEntrance.setExit("north", mainHall);
        mainEntrance.setExit("south", mainEntrance);

        wineCellar.setExit("east", catacombs);
        wineCellar.setExit("up", servantEntrance);

        catacombs.setExit("west", wineCellar);

        servantQuarters.setExit("south", servantEntrance);
        servantQuarters.setExit("west", kitchen);

        kitchen.setExit("tunnel", diningHall);
        kitchen.setExit("east", servantQuarters);

        mainHall.setExit("up", hallway);
        mainHall.setExit("south", mainEntrance);
        mainHall.setExit("east", diningHall);

        diningHall.setExit("west", mainHall);
        diningHall.setExit("tunnel", kitchen);
        diningHall.setExit("east", ballRoom);

        ballRoom.setExit("west", diningHall);
        ballRoom.setExit("east", library);

        library.setExit("west", ballRoom);

        guestChambers.setExit("east", hallway);

        hallway.setExit("up", masterChambers);
        hallway.setExit("down", mainHall);
        hallway.setExit("west", guestChambers);
        hallway.setExit("east", chessRoom);

        chessRoom.setExit("west", ballRoom);

        masterChambers.setExit("down", hallway);

        List<Room> roomList = Arrays.asList(new Room[]{cemetery,roseGarden,wineCellar,catacombs,servantEntrance,mainEntrance,kitchen,servantQuarters,mainHall,diningHall,ballRoom,library,guestChambers,hallway,chessRoom,masterChambers});


        //Monsters met items aanmaken
        ArrayList<Item> itemsForMonsters = itemCreator.generateItems(20,50);
        ArrayList<Monsters> monstersList = monsterCreator.generateMonstersWithItems(6, itemsForMonsters);

        Random r = new Random();
        // randomly place monsters
        for (Monsters monster : monstersList){
            boolean placed = false;
            while(!placed){
                int random = r.nextInt(roomList.size());
                Room randomRoom = roomList.get(random);
                if (!randomRoom.hasMonster()){
                    randomRoom.addMonster(monster);
                    placed = true;
                }
            }
        }

        ArrayList<Item> itemsForRooms = itemCreator.generateItems(40,40);
        for (Item item : itemsForRooms){
            int random = r.nextInt(roomList.size());
            Room randomRoom = roomList.get(random);
            randomRoom.addItem(item);
        }

        // create the items

        ConceilingObject urn = new ConceilingObject("urn", "A heavy urn made from sandalwood", 1.2, true);
        ConceilingObject basket = new ConceilingObject("basket", "An old broken reed basket", 0.8, true);

        Torch torch = new Torch("torch", "A torch drenched in spirit", 0.3);
        Torch torch2 = new Torch("torch", "A torch drenched in spirit", 0.3);

        StaticConceilingObject foodCupboard = new StaticConceilingObject("cupboard", "a cupboard used to store food", true);
        StaticConceilingObject bookShelf = new StaticConceilingObject("bookshelf", "which seems to be slightly movable", true, 789);
        StaticConceilingObject  trapdoor = new StaticConceilingObject("trapdoor", "under a carpet ", false);

        Key keyMasterChambre = new Key("key", "forged iron key on a blood-red braded ribbon", 0.1, true, true, 123);
        Key keyServantQuarters = new Key("book", "A heavy false book which seems to have a mechanical KEY structure hidden inside", 2.3, true, true, 789);

        Item rItem = itemCreator.generateRandomItem();
        Item rItem2 = itemCreator.generateRandomItem();

        Dracula dracula = new Dracula();

        //secret Exits
        SecretExit sMasterChamers = new SecretExit(masterChambers);
        SecretExit sServantQuarters = new SecretExit(servantQuarters);
        SecretExit sDiningHall = new SecretExit(diningHall);

        //hide items and exits
        urn.setHiddenObject(keyMasterChambre);
        basket.setHiddenObject(rItem);
        foodCupboard.setSecretExit(sMasterChamers);
        bookShelf.setSecretExit(sServantQuarters);
        trapdoor.setSecretExit(sDiningHall);

        //Note
        Note note = new Note("note", "A handwritten note", true, true);

        //Handmatig toevoegen van belangerijke en verstopte items
        gate.addItem(torch);

        driveWay.addItem(note);

        mainHall.addItem(torch2);

        kitchen.addItem(foodCupboard);
        kitchen.addItem(basket);

        library.addItem(bookShelf);

        catacombs.addItem(urn);

        chessRoom.addItem(trapdoor);

        diningHall.addItem(keyServantQuarters);

        //masterChambers.addMonster(dracula);


        Scanner reader = new Scanner(System.in);
        System.out.println("Type in your name if you dare:");
        System.out.print("> ");

        String name = reader.nextLine();
        this.player = new Player(name, gate);
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = command.execute(player);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        PrintReader.print("welcome");
        System.out.println("Type '" + CommandWord.HELP.toString() + "' if you need help.");
        System.out.println();
        printLocationInfo();
    }


    /**
     * Print info over huidige ruimte af plus info over de player.
     */
    private void printLocationInfo() {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println();
        System.out.println(player.getLongDescription());
        System.out.println();
    }

    /**
     * Voegt de huidige Room toe aan de Stack history
     * @param currentRoom
     */
    public static void addToHistory(Room currentRoom){
        history.push(currentRoom);
    }

    /**
     * Retourneert de laatste ruimte van de Stack history
     * @return de laatste Room uit de Stack history
     */
    public static Room goBack(){
        return history.pop();
    }

    /**
     * Retourneert de gehele Stack history
     * @return gehele Stack history
     */
    public static Stack<Room> showStack(){
        return history;
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
