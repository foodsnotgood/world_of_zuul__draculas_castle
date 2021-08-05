public class Note extends Item{
    public Note(String name, String description, boolean canBeTaken, boolean visible) {
        super(name, description, 0, canBeTaken, visible);
    }
    public void readNote(){
        PrintReader.print(this.getName());
    }
}
