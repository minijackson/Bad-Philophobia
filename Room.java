import java.util.Set;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Iterator;

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
	 * Room class constructor.
	 * @param description Description for the room
	 * @param image Image path to display
	 */
	public Room(String description, String image) {
		this(description, image, new ItemList());
	}

	/**
	 * Room class constructor with the items.
	 * @param description Description for the room
	 * @param image Image path to display
	 * @param itemList The items currently in the room
	 */
	public Room(String description, String image, ItemList itemList) {
		this.description = description;
		exits = new HashMap < String, Room > ();
		imageName = image;
		containedItems = itemList;
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
	 */
	public ItemList getContainedItems() {
		return containedItems;
	}

	/**
	 * Return the given Item.
	 */
	public Item getItem(String name) {
		return containedItems.get(name);
	}

	/**
	 * Check if the room has the given Item.
	 */
	public boolean hasItem(String name) {
		return containedItems.get(name) != null;
	}

	/**
	 * Add the given Item in the Room.
	 * @param item Item to be added
	 */
	public void addItem(Item item) {
		containedItems.put(item.getName(), item);
	}

	/**
	 * Remove the given Item to the Room.
	 * @param item Item to be removed
	 */
	public void removeItem(String itemName) {
		containedItems.remove(itemName);
	}

	/**
	 * Getter for the description field.
	 * @return String The description field
	 */
	public String getShortDescription() {
		return description;
	}

	/**
	 * Return the description of the room, the available exits
	 * plus the items in the room if any.
	 * @return String The description.
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n"
				+ ((!containedItems.isEmpty())? "You can see" + getHumanItemsList() + " near you.\n" : "")
				+ getExitString();
	}

	/**
	 * Return a human readable list of the items in the room.
	 * @return String The list of the items
	 */
	public String getHumanItemsList() {
		String itemsList = "";
		Set<String> names = containedItems.keySet();
		Iterator<String> it = names.iterator();
		while(it.hasNext()) {

			String itemName = it.next();
			// If the item's name begins with a vowel, the prefix is ' an '
			// if not, the prefix is ' a '
			String prefix = " a" + (((new String("aeiouy")).contains(itemName.substring(0,1)))? "n" : "") + " ";

			if(itemsList.equals(""))
				itemsList += prefix + itemName;
			else {
				// Check if it is the last item
				if(it.hasNext())
					itemsList += "," + prefix + itemName;
				else
					itemsList += " and" + prefix + itemName;
			}
		}
		return itemsList;
	}

	/**
	 * Return the available exits in a human readable format.
	 */
	private String getExitString() {
		StringBuilder returnString = new StringBuilder("Exits:");
		for(String vS:exits.keySet())
			returnString.append(" " + vS);
		return returnString.toString();
	}

	/**
	 * Return the room in a specific direction.
	 * @param direction The direction of the wanted room.
	 */
	public Room getExit(String direction) {
		return exits.get(direction);
	}

	public boolean isExit(Room exit) {
		return exits.containsValue(exit);
	}

	/**
	 * Getter for the imageName field.
	 */
	public String getImageName() {
		return "Images/"  + imageName;
	}
}
