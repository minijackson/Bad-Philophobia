package pkg_commands;

import pkg_world.Player;
import pkg_game.GameEngine;
import pkg_exceptions.NoArgumentException;
import pkg_exceptions.IllegalArgumentException;

/**
 * class ThrowCommand used to make the player throw an Item
 * @author Rémi Nicole
 */
public class ThrowCommand extends Command {

	/**
	 * Constructor for ThrowCommand
	 */
	public ThrowCommand(){

	}

	/**
	 * Throw the Item through it's name in the parameter field.
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a name other than any of the items in the Player's inventory
	 * @return False because it is not the quit command or true if the player won the game
	 */
	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException {
		if(hasParameter()) {
			if(player.hasItem(getParameter())){
				if(getParameter().equals("snowball")) {

					// Has thrown the snowball and is in the alpine tundra Room
					if(player.getCurrentRoom() == GameEngine.getRooms().get(3) && player.threwSnowball()) {
						setMessage("Snowball!!! A snowball!!! (The robot tries to fetch the snowball but falls into the water and \"dies\".)");
						// Won
						return true;
					} else {
						setMessage("Snowball!!! A snowball!!! (The robot fetches the snowball but melts it because of it's circuits)\n*Sob*, my snowball...");
						player.eatObject("snowball");
						player.throwSnowball();
					}

				} else {
					player.eatObject(getParameter());
					setMessage("Stop throwing everything away! I hope it's the last time.");
				}
			} else
				throw new IllegalArgumentException("If you want to throw that, you may have a mental disorder. As expected.");
		} else
			throw new NoArgumentException("Yes, I'm gonna throw a fit.");
		return false;
	}

}
