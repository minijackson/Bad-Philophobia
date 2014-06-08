package pkg_world.pkg_characters;

import pkg_world.pkg_items.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * class Character
 * @author Rémi Nicole
 */
public class Character {

	/**
	 * Name of the creature/character.
	 */
	private final String name;

	/**
	 * Dialog displayed when typing "talk &lt;name&gt;".
	 * When multiple elements, a random one is displayed
	 */
	private ArrayList<String> dialogs;

	/**
	 * Item wanted by the creature/character.
	 * When the creature/character doesn't want
	 * any Item, this field is equal to null.
	 */
	private Item wantedItem;

	/**
	 * Action to do in case of the player giving
	 * the wanted Item.
	 */
	private String successAction;

	/**
	 * Action to do in case of the player giving
	 * an other Item that the wanted one.
	 */
	private String failureAction;

	/**
	 * True if the creature/character already had
	 * his wanted Item.
	 */
	private boolean satisfied;

	/**
	 * Constructor for Character with name only.
	 * When using this constructor only, the
	 * creature/character says nothing and
	 * wants nothing.
	 * @param name The name of the creature/character
	 */
	public Character(final String name){
		this.name = name;
		satisfied = false;
	}

	/**
	 * Constructor for Character with name and dialog only.
	 * When using this constructor only, the
	 * creature/character wants nothing.
	 * @param name The name of the creature/character
	 * @param dialog The dialog displayed when talking to it/him/her
	 */
	public Character(final String name, final String dialog) {
		this(name);
		dialogs = new ArrayList<String>();
		dialogs.add(dialog);
	}

	/**
	 * Constructor for Character.
	 * @param name The name of the creature/character
	 * @param dialog The dialog displayed when talking to it/him/her
	 * @param wantedItem Item wanted by the creature/character
	 */
	public Character(final String name, final String dialog, Item wantedItem, final String successAction, final String failureAction) {
		this(name, dialog);
		this.wantedItem = wantedItem;
		this.successAction = successAction;
		this.failureAction = failureAction;
	}

	/**
	 * Add a possible dialog
	 * @param dialog The dialog to add
	 */
	public void addDialog(final String dialog) {
		dialogs.add(dialog);
	}

	/**
	 * A dialog to be displayed.
	 * @return A random dialog from the dialogs field
	 */
	public String talk() {
		return dialogs.get((new Random()).nextInt(dialogs.size()));
	}

	/**
	 * Try to give him an Item.
	 * @return An action to be displayed in case of success or failure
	 */
	public String takeItem(Item item) {
		return (wantedItem == item) ? successAction : failureAction;
	}

	/**
	 * Name field getter
	 * @return The name of the creature/character
	 */
	public String getName() {
		return name;
	}

	public boolean isSatisfied() {
		return satisfied;
	}

}
