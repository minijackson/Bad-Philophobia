import java.util.HashMap;
import java.util.Iterator;

/**
 * Class used to verify the commands given by the user.
 * It contains all known commands and can verify
 * if a String is a known command.
 *
 * @author Rémi NICOLE
 */

public class CommandWords
{
	private HashMap<String, Command> commands;

	/**
	 * CommandWords class constructor.
	 */
	public CommandWords() {
		commands = new HashMap<String, Command>();
		commands.put("go", new GoCommand());
		commands.put("back", new BackCommand());
		commands.put("beamer", new BeamerCommand());
		commands.put("look", new LookCommand());
		commands.put("take", new TakeCommand());
		commands.put("drop", new DropCommand());
		commands.put("inventory", new InventoryCommand());
		commands.put("eat", new EatCommand());
		commands.put("test", new TestCommand());
		commands.put("quit", new QuitCommand());
		commands.put("help", new HelpCommand());
		commands.put("credits", new CreditsCommand());
	}

	/**
	 * Return true if and only if the command is known.
	 */
	public boolean isCommand(String aString) {
		return commands.containsKey(aString);
	}

	/**
	 * Getter for the knownCommands field.
	 */
	public String getCommandList() {
		String commandsString = "";
		Iterator<String> it = commands.keySet().iterator();
		while(it.hasNext()) {
			String command = it.next();
			commandsString += command + ((it.hasNext())? ", " : ".");
		}
		return commandsString;
	}

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Command getCommand(String commandWord)
    {
        return commands.get(commandWord);
    }
}

