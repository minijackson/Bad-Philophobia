package pkg_world;

import pkg_game.GameEngine;
import pkg_world.pkg_items.Item;
import pkg_world.pkg_items.ItemList;
import pkg_world.pkg_characters.Character;

import java.util.Set;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;

/**
 * Class used to handle a game's room.
 * @author Rémi NICOLE
 */

public class Room {

	/**
	 * Description of the room.
	 */
	private String description;

	/**
	 * Collection which match a direction
	 * with the room in that direction.
	 */
	private HashMap<String, Room> exits;

	/**
	 * Image to display for the current room.
	 */
	private String imageName;

	/**
	 * Item currently being in the room.
	 */
	private ItemList containedItems;

	/**
	 * Characters currently bing in the room.
	 */
	private HashMap<String, Character> containedCharacters;

	/**
	 * Room class constructor.
	 * @param description Description for the room
	 * @param image Image path to display
	 */
	public Room(String description, String image) {
		this(description, image, new ItemList(), new HashMap<String, Character>());
	}

	/**
	 * Room class constructor with the items.
	 * @param description Description for the room
	 * @param image Image path to display
	 * @param itemList The items currently in the room
	 */
	public Room(String description, String image, ItemList itemList, HashMap<String, Character> characters) {
		this.description = description;
		exits = new HashMap < String, Room > ();
		imageName = image;
		containedItems = itemList;
		containedCharacters = characters;
	}

	/**
	 * Define a room in a relative direction to the current room.
	 * @param direction Direction in which the room is.
	 * @param neighbor The room in that direction.
	 */
	public void setExit(String direction, Room neighbor) {
		exits.put(direction, neighbor);
	}

	/**
	 * containedItem field getter.
	 * @return The Room's inventory
	 */
	public ItemList getContainedItems() {
		return containedItems;
	}

	/**
	 * Return an Item through it's name.
	 * @return The wanted Item
	 */
	public Item getItem(String name) {
		return containedItems.get(name);
	}

	/**
	 * Return an Character through it's name.
	 * @return The wanted Character
	 */
	public Character getCharacter(String name) {
		return containedCharacters.get(name);
	}

	/**
	 * Check if the room has the given Item through it's name.
	 * @return True if this Room has the Item
	 */
	public boolean hasItem(String name) {
		return containedItems.containsKey(name);
	}

	/**
	 * Check if the room has the given Character through it's name.
	 * @return True if this Room has the Character
	 */
	public boolean hasCharacter(String name) {
		return containedCharacters.containsKey(name);
	}

	/**
	 * Add the given Item in the Room.
	 * @param item Item to be added
	 */
	public void addItem(Item item) {
		containedItems.put(item.getName(), item);
	}

	public void addCharacter(Character character) {
		containedCharacters.put(character.getName(), character);
	}

	/**
	 * Remove the given Item to the Room.
	 * @param itemName Item to be removed
	 */
	public void removeItem(String itemName) {
		containedItems.remove(itemName);
	}

	/**
	 * Remove the given Character to the Room.
	 * @param characterName Character to be removed
	 */
	public void removeCharacter(String characterName) {
		containedCharacters.remove(characterName);
	}

	/**
	 * Getter for the description field.
	 * @return The description field
	 */
	public String getShortDescription() {
		return description;
	}

	/**
	 * Return the description of the room, the available exits
	 * plus the items in the room if any.
	 * @return The description.
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n"
				+ ((!containedItems.isEmpty())? "You can see" + getHumanItemsList() + " near you.\n" : "")
				+ ((!containedCharacters.isEmpty())? "You can hear" + getHumanCharactersList() + " moving near you\n" : "")
				+ getExitString();
	}

	/**
	 * Return a human readable list of the items in the room.
	 * @return The list of the items
	 */
	public String getHumanItemsList() {
		return getHumanList(containedItems.keySet());
	}

	/**
	 * Return a human readable list of the characters in the room.
	 * @return The list of the items
	 */
	public String getHumanCharactersList() {
		return getHumanList(containedCharacters.keySet());
	}

	/**
	 * Return a human readable list of the given Set.
	 * @param names Set to display.
	 * @return The list of the items
	 */
	private String getHumanList(Set<String> names) {
		String returnString = "";
		Iterator<String> it = names.iterator();
		while(it.hasNext()) {

			String itemName = it.next();
			// If the item's name begins with a vowel, the prefix is ' an '
			// if not, the prefix is ' a '
			String prefix = " a" + (((new String("aeiouy")).contains(itemName.substring(0,1)))? "n" : "") + " ";

			if(returnString.equals(""))
				returnString += prefix + itemName;
			else {
				// Check if it is the last item
				if(it.hasNext())
					returnString += "," + prefix + itemName;
				else
					returnString += " and" + prefix + itemName;
			}
		}
		return returnString;
	}

	/**
	 * Return the available exits in a human readable format.
	 * @return The available exits in a human readable format
	 */
	private String getExitString() {
		StringBuilder returnString = new StringBuilder("Exits:");
		for(String vS:exits.keySet())
			returnString.append(" " + vS);
		return returnString.toString();
	}

	/**
	 * Return the room in a specific direction.
	 * @param direction The direction of the wanted room
	 * @return The wanted Room
	 */
	public Room getExit(String direction) {
		return exits.get(direction);
	}

	/**
	 * Return a random adjacent Room
	 * @return The wanted Room
	 */
	public Room getRandomAdjacentRoom() {
		Room nextRoom;
		do {
			// Convertit les valeurs en tableau primitif de Room
			// cf. http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#toArray-T:A-
			nextRoom = exits.values().toArray(new Room[0])[
				// Valeur aléatoire entre 0 et la taille du tableau (exclus)
				(new Random()).nextInt(exits.values().size())
				];

			// prevent the character from going to the random room
		} while (nextRoom == GameEngine.getRooms().get(0));
		return nextRoom;
	}

	/**
	 * Check if the given Room is an exit of this Room.
	 * @param exit The Room to check
	 * @return True if the given Room is an exit of this Room
	 */
	public boolean isExit(Room exit) {
		return exits.containsValue(exit);
	}

	/**
	 * Getter for the imageName field.
	 * @return The name of the Room's image representation
	 */
	public String getImageName() {
		return "Images/"  + imageName;
	}
}
