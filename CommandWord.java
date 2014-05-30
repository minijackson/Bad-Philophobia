/**
 * Representations for all the valid command words for the game.
 *
 * @author Rémi NICOLE
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
	GO("go"),
	BACK("back"),
	LOOK("look"),
	TAKE("take"),
	DROP("drop"),
	INVENTORY("inventory"),
	EAT("eat"),
	TEST("test"),
	QUIT("quit"),
	HELP("help"),
	CREDITS("credits"),
	UNKNOWN("?");

	private String commandString;


    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
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
