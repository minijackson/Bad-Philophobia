import java.util.HashMap;

/**
 * Class used to verify the commands given by the user.
 * It contains all known commands and can verify
 * if a String is a known command.
 *
 * @author Rémi NICOLE
 */

public class CommandWords
{
	private HashMap<String, CommandWord> validCommands;

	/**
	 * CommandWords class constructor.
	 */
	public CommandWords() {
		validCommands = new HashMap<String, CommandWord>();
		validCommands.put("go", CommandWord.GO);
		validCommands.put("back", CommandWord.BACK);
		validCommands.put("look", CommandWord.LOOK);
		validCommands.put("take", CommandWord.TAKE);
		validCommands.put("drop", CommandWord.DROP);
		validCommands.put("inventory", CommandWord.INVENTORY);
		validCommands.put("eat", CommandWord.EAT);
		validCommands.put("test", CommandWord.TEST);
		validCommands.put("quit", CommandWord.QUIT);
		validCommands.put("help", CommandWord.HELP);
		validCommands.put("credits", CommandWord.CREDITS);
	}

	/**
	 * Return true if and only if the command is known.
	 */
	public boolean isCommand(String aString) {
		return validCommands.containsKey(aString);
	}

	/**
	 * Getter for the knownCommands field.
	 */
	public String getCommandList() {
		String commands = "";
		for(String command : validCommands.keySet()) {
			commands += command + " ";
		}
		return commands;
	}

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
}

