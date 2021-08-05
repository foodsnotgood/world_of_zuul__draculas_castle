import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 * <p>
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Parser {
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input
    private Factory commandFactory;

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
        commandFactory = new Factory();
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if (commands.isCommand(word1)) {
            CommandWord commandWord = commands.getCommand(word1);
            return commandFactory.getCommand(commandWord, word2);
        } else {
            return new UNKNOWNcommand(CommandWord.UNKNOWN, word2);
        }
    }

    public String showCommands() {
        return commands.showAll();
    }
}