package pkg_commands;

import pkg_world.Player;
import pkg_world.pkg_characters.Character;
import pkg_world.pkg_items.Item;
import pkg_exceptions.*;

/**
 * class GiveCommand used to make the Player give an Item
 * @author Rémi Nicole
 */
public class GiveCommand extends Command {

	/**
	  * Constructor for GiveCommand
	  */
	public GiveCommand(){

	}

	/**
	 * Make the player give an Item to a Character.
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter or with only one parameter
	 * @throws IllegalArgumentException When the user typed an Item or Character name other than any of the items or characters in the Room
	 * @throws UnauthorizedException When the Character doesn't want the Item or any Item
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws NoArgumentException,pkg_exceptions.IllegalArgumentException,UnauthorizedException {
		if(hasParameter()) {
			if(player.getCurrentRoom().hasCharacter(getParameter())) {
				Character character = player.getCurrentRoom().getCharacter(getParameter());

				if(hasSecondParameter()) {
					if(player.hasItem(getSecondParameter())) {
						Item item = player.getItem(getSecondParameter());

						if(!character.isSatisfied()) {
							if(character.wantsItem(item)) {
								setMessage(character.takeItem(item));
								player.eatObject(getSecondParameter());
							} else {
								String failureMessage = character.takeItem(item);
								throw new UnauthorizedException((failureMessage == null)? "He doesn't want anything from you. He's smart." : failureMessage);
							}

						} else
							throw new UnauthorizedException("He already used you, remember? No, of course not.");
					} else
						throw new pkg_exceptions.IllegalArgumentException("I'm not sure you want to give that.");
				} else
					throw new NoArgumentException("I'm afraid giving yourself to this one won't be enough. Again.");

			} else
				throw new pkg_exceptions.IllegalArgumentException("Still trying to give something to your imaginary friend?");
		} else
			throw new NoArgumentException("Would you give a try to instead give something to someone?");

		return false;
	}

}
