package pkg_exceptions;

/**
 * class UnauthorizedException used to tell that the user cannot execute a given command.
 * @author Rémi Nicole
 */
public class UnauthorizedException extends Exception {

	/**
	  * Constructor for UnauthorizedException.
	  * @param message Message to display in case of that error
	  */
	public UnauthorizedException(String message){
		super(message);
	}

}
