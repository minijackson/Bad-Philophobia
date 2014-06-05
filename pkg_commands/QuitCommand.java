package pkg_commands;

import pkg_world.Player;

/**
 * class QuitCommand used to quit the game
 * @author Rémi Nicole
 */
public class QuitCommand extends Command {

	/**
	 * Constructor for QuitCommand
	 */
	public QuitCommand(){

	}

	/**
	 * Return true as it is the quit command.
	 * @return True because it the quit command
	 */
	public boolean execute(Player player) {
		return true;
	}

}
