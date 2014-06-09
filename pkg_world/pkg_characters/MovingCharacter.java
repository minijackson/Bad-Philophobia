package pkg_world.pkg_characters;

import pkg_world.pkg_characters.Character;
import pkg_world.Room;
import pkg_world.pkg_items.Item;

/**
 * class MovingCharacter
 * @author Rémi Nicole
 */
public class MovingCharacter extends Character {

	/**
	 * Room where the MovingCharacter is currently in.
	 */
	private Room currentRoom;

	/**
	 * Constructor for MovingCharacter with name only.
	 * When using this constructor only, the
	 * creature/character says nothing and
	 * wants nothing.
	 * @param startRoom Room where the creature/character start
	 * @param name The name of the creature/character
	 */
	public MovingCharacter(Room startRoom, final String name){
		super(name);
		currentRoom = startRoom;
	}

	/**
	 * Constructor for MovingCharacter with name and dialog only.
	 * When using this constructor only, the
	 * creature/character wants nothing.
	 * @param startRoom Room where the creature/character start
	 * @param name The name of the creature/character
	 * @param dialog The dialog displayed when talking to it/him/her
	 */
	public MovingCharacter(Room startRoom, final String name, final String dialog) {
		super(name, dialog);
		currentRoom = startRoom;
	}

	/**
	 * Constructor for MovingCharacter.
	 * @param startRoom Room where the creature/character start
	 * @param name The name of the creature/character
	 * @param dialog The dialog displayed when talking to it/him/her
	 * @param wantedItem Item wanted by the creature/character
	 */
	public MovingCharacter(Room startRoom, final String name, final String dialog, Item wantedItem, final String successAction, final String failureAction) {
		super(name, dialog, wantedItem, successAction, failureAction);
		currentRoom = startRoom;
	}

	/**
	 * Move to a random adjacent Room to the current one.
	 */
	public void move() {
		currentRoom.removeCharacter(getName());
		currentRoom = currentRoom.getRandomAdjacentRoom();
		currentRoom.addCharacter(this);
	}
}
