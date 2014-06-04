import java.util.StringTokenizer;

/**
 * Class used to parse the commands with or without parameters
 * given by the user.
 * @author Rémi NICOLE
 */
public class Parser {

	/**
	 * Field used to get the list of known commands.
	 */
	private static CommandWords commands = new CommandWords();

	/**
	 * Return the Command corresponding to the given user's input.
	 * @param inputLine The user's input
	 * @return The corresponding Command
	 */
	public static Command getCommand(String inputLine) {

		String word1;
		String word2;

		StringTokenizer tokenizer = new StringTokenizer(inputLine);

		if(tokenizer.hasMoreTokens())
			word1 = tokenizer.nextToken();	// First word
		else
			word1 = null;
		if(tokenizer.hasMoreTokens())
			word2 = tokenizer.nextToken();	// Second word
		else
			word2 = null;

        Command command = commands.getCommand(word1);
        if(command != null) {
            command.setParameter(word2);
        }
        return command;
	}

	/**
	 * Getter for the knownCommands field of the commands field.
	 * @return The list of available commands
	 */
	public static String showCommands() {
		return commands.getCommandList();
	}
}
