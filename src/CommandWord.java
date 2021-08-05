public enum CommandWord {
    GO("go"), BACK("back"), LOOK("look"), TAKE("take"),
    DROP("drop"),OPEN("open"), EAT("eat"), DRINK("drink"),
    LIGHT("light"), FIGHT("fight"), RUN("run"), UNLOCK("unlock"), READ("read"),
    QUIT("quit"),
    HELP("?"), UNKNOWN("?"), CHANGE("change");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
