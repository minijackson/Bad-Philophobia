/**
 * class IllegalArgumentException used to tell that a command parameter is invalid.
 * @author Rémi Nicole
 */
public class IllegalArgumentException extends Exception {

	/**
	  * Constructor for IllegalArgumentException.
	  * @param message Message to display in case of that error
	  */
	public IllegalArgumentException(String message){
		super(message);
	}

}
