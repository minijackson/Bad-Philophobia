/**
 * Class used to verify the commands given by the user.
 * It contains all known commands and can verify
 * if a String is a known command.
 *
 * @author Rémi NICOLE
 */

public class CommandWords
{
	/**
	 * A array containing all the known command for the game.
	 */
	private static final String knownCommands[] = {
		"go", "back", "look", "take", "drop", "inventory", "test", "quit", "help", "credits"
	};

	/**
	 * CommandWords class constructor.
	 */
	public CommandWords() {

	}

	/**
	 * Return true if and only if the command is known.
	 */
	public boolean isCommand(String aString) {
		for(int i = 0; i < knownCommands.length; i++) {
			if(knownCommands[i].equals(aString))
				return true;
		}
		return false;
	}

	/**
	 * Getter for the knownCommands field.
	 */
	public String getCommandList() {
		StringBuilder commands = new StringBuilder();
		for(int i = 0; i < knownCommands.length; i++) {
			commands.append( knownCommands[i] + "  " );
		}
		return commands.toString();
	}
}

