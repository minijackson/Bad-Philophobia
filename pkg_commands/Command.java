/**
 * \package pkg_commands
 * Package containing all the classes executing a command.
 */
package pkg_commands;

import pkg_world.Player;
import pkg_exceptions.*;

/**
 * Class used to handle a command of one or two words.
 * @author Rémi NICOLE
 */
public abstract class Command {

	/**
	 * Parameter for the command.
	 * It equals to null if there is
	 * no parameter.
	 */
	private String parameter;

	/**
	 * Second parameter for the command.
	 * Equals to null if there is no
	 * second parameter
	 */
	private String secondParameter;

	/**
	 * Message to be printed.
	 * This message is printed if and only if the command
	 * was successfully processed
	 */
	private String message;

	/**
	 * Parameter field getter.
	 * @return The parameter given by the user
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * Second Parameter field getter.
	 * @return The second parameter given by the user
	 */
	public String getSecondParameter() {
		return secondParameter;
	}

	/**
	 * Parameter field setter.
	 * @param parameter The parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * Second Parameter field setter.
	 * @param parameter The second parameter to set
	 */
	public void setSecondParameter(String parameter) {
		secondParameter = parameter;
	}

	/**
	 * Return true if the parameter is null.
	 * @return True if the parameter is null
	 */
	public boolean hasParameter() {
		return (parameter != null);
	}

	/**
	 * Return true if the second parameter is null.
	 * @return True if the second parameter is null
	 */
	public boolean hasSecondParameter() {
		return (secondParameter != null);
	}

	/**
	 * Message field setter.
	 * @param message The message to set
	 */
	protected void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Message field getter.
	 * @return The message to print to the user
	 */
	public String getMessage() {
		return (message == null)? "" : message;
	}

	/**
	 * Return true if the message is equal to null or ""
	 * @return True if the message is equal to null or ""
	 */
	public boolean hasMessage() {
		return (message == null) ? false : !message.equals("");
	}

	/**
	 * Process the command.
	 * @param player The player that called this command
	 * @throws NoArgumentException When the command needed a parameter but the user didn't specify one
	 * @throws IllegalArgumentException When the user typed a parameter other than the correct ones
	 * @throws UnauthorizedException When the user isn't allowed to do this action
	 */
	public abstract boolean execute(Player player) throws NoArgumentException,pkg_exceptions.IllegalArgumentException,UnauthorizedException;
}

