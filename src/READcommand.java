public class READcommand extends Command{
    public READcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!super.hasSecondWord()){
            System.out.println("What do you want to read?");
            return false;
        }
        if (player.hasItem(super.getSecondWord())){
            Item item = player.getItem(super.getSecondWord());
            if (item instanceof Note){
                Note note = (Note) item;
                note.readNote();
                return false;
            }
            System.out.println("Nothing to read here...");
            return false;
        }
        System.out.println("No such item in your bag. Try taking it first!");
        return false;
    }
}
