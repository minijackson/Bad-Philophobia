import java.util.StringTokenizer;

/**
 * Class used to parse the commands with or without parameters
 * given by the user.
 *
 * @author Rémi NICOLE
 */
public class Parser {

	/**
	 * Field used to get the list of known commands.
	 */
	private CommandWords commands;

	/**
	 * Parser class constructor.
	 */
	public Parser() {
		commands = new CommandWords();
	}

	/**
	 * Get a new command from the user.
	 */
	public Command getCommand(String inputLine) {

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

		if(commands.isCommand(word1))
			return new Command(commands.getCommandWord(word1), word2);
		else
			return new Command(null, word2);
	}

	/**
	 * Getter for the knownCommands field of the commands field.
	 * @see CommandWords#knownCommands
	 */
	public String showCommands() {
		return commands.getCommandList();
	}
}
