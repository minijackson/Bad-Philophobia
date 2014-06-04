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

abstract class Command {
	/**
	 * Parameter for the command.
	 * It equals to <null> if there is
	 * no parameter.
	 */
	private String parameter;

	/**
	 * Message to be printed.
	 * This message is printed if and only if the command
	 * was successfully processed
	 */
	private String message;

	/**
	 * Parameter field getter.
	 * @see Command#parameter
	 */
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * Return true if the parameter is <null>.
	 */
	public boolean hasParameter() {
		return (parameter != null);
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return (message == null)? "" : message;
	}

	public boolean hasMessage() {
		return (message == null) ? false : !message.equals("");
	}

	public abstract boolean execute(Player player) throws NoArgumentException,IllegalArgumentException,UnauthorizedException;
}

