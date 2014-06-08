package pkg_commands;

import pkg_world.Player;
import pkg_exceptions.NoArgumentException;
import pkg_exceptions.IllegalArgumentException;

/**
 * class TalkCommand used to talk to a Character
 * @author Rémi Nicole
 */
public class TalkCommand extends Command {

	/**
	  * Constructor for TalkCommand
	  */
	public TalkCommand(){

	}

	/**
	 * Make the player talk to a Character.
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a name other than any of the characters in the Room
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException {
		if(hasParameter()) {
			if(player.getCurrentRoom().hasCharacter(getParameter())){
				setMessage(player.getCurrentRoom().getCharacter(getParameter()).talk());
			} else if(player.getCurrentRoom().hasItem(getParameter()))
				throw new IllegalArgumentException("It's a" + (((new String("aeiouy")).contains(getParameter().substring(0,1)))? "n " : " ") + getParameter() + "! Why would you want to talk to that!");
			else
				throw new IllegalArgumentException("Your imaginary friend is not here. He might have left you.");
		} else
			throw new NoArgumentException("Talking alone won't make you more clever.");

		return false;
	}

}
