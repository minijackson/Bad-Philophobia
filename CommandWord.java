/**
 * Representations for all the valid command words for the game.
 * @author Rémi NICOLE
 */
public enum CommandWord
{
	/**
	 * "go" command representation.
	 */
	GO("go"),
	/**
	 * "back" command representation.
	 */
	BACK("back"),
	/**
	 * "beamer" command representation.
	 */
	BEAMER("beamer"),
	/**
	 * "look" command representation.
	 */
	LOOK("look"),
	/**
	 * "take" command representation.
	 */
	TAKE("take"),
	/**
	 * "drop" command representation.
	 */
	DROP("drop"),
	/**
	 * "inventory" command representation.
	 */
	INVENTORY("inventory"),
	/**
	 * "eat" command representation.
	 */
	EAT("eat"),
	/**
	 * "test" command representation.
	 */
	TEST("test"),
	/**
	 * "quit" command representation.
	 */
	QUIT("quit"),
	/**
	 * "help" command representation.
	 */
	HELP("help"),
	/**
	 * "credits" command representation.
	 */
	CREDITS("credits"),
	/**
	 * Unknown command representation.
	 */
	UNKNOWN("?");

	/**
	 * The String corresponding the the first word of the command
	 */
	private String commandString;


    /**
     * Initialise with the corresponding command word.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
