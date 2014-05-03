/**
 * Class used to handle a command of one or two words. 
 * If the command field is equal to <null>, that means
 * that the command is unknown. If the parameter field
 * is equal to <null>, that means that the command has
 *
 * no parameter or wasn't given any parameter.
 *
 * @author Rémi NICOLE 
 */

class Command {
	/**
	 * Command given by the user.
	 * It equals to <null> if there is
	 * no command or no known command.
	 */
	private String command;

	/**
	 * Parameter for the command.
	 * It equals to <null> if there is
	 * no parameter.
	 */
	private String parameter;

	/**
	 * Command class constructor.
	 * @param firstWord The command.
	 * @param parameter The parameter of the command.
	 */
	public Command(String firstWord, String parameter) {
		command = firstWord;
		this.parameter = parameter;
	}

	/**
	 * Command field getter.
	 * @see Command#command
	 */
	public String getCommandWord() {
		return command;
	}

	/**
	 * Parameter field getter.
	 * @see Command#parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * Return true if the command is <null>.
	 */
	public boolean isUnknown() {
		return (command == null);
	}

	/**
	 * Return true if the parameter is <null>.
	 */
	public boolean hasParameter() {
		return (parameter != null);
	}
}

