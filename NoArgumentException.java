/**
 * class NoArgumentException used to tell that a given command must have a parameter.
 * @author Rémi Nicole
 */
public class NoArgumentException extends Exception {

	/**
	  * Constructor for NoArgumentException.
	  * @param message Message to display in case of that error
	  */
	public NoArgumentException(String message){
		super(message);
	}

}
