import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;

/**
 * class Player used to handle the user's player.
 * @author Rémi Nicole
 */
public class Player {

	/**
	 * Name of the player.
	 * Either 'retard' or 'moron'
	 */
	private String name;

	/**
	 * Items carried by the player.
	 */
	private ItemList backpack;

	/**
	 * Weight of the carried items.
	 */
	private int backpackWeight;

	/**
	 * The maximum weight of Items the player can carry.
	 */
	private int maxWeight = 100;

	/**
	 * Room where the player is currently in.
	 */
	private Room currentRoom;

	/**
	 * Rooms where the player was before now.
	 */
	private Stack<Room> previousRooms;

	/**
	 * Room remembered by the beamer.
	 */
	private Room beamerRoom;

	/**
	 * Player class constructor.
	 * @param name The name of the player
	 * @param firstRoom Room where the player start
	 */
	public Player(String name, Room firstRoom) {
		this.name = name;
		backpack = new ItemList();
		backpackWeight = 0;
		currentRoom = firstRoom;
		previousRooms = new Stack<Room>();
	}

	/**
	 * name field getter.
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * maxWeight field getter.
	 * @return The maximum weight the player can carry
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * maxWeight field setter.
	 * @param maxWeight The value for the maxWeight field
	 */
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * currentRoom field getter.
	 * @return The Room where the player is currently in
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Go to the given room.
	 * @param room Room to go to
	 */
	public void goRoom(Room room) {
		currentRoom = room;
	}

	/**
	 * Return the last Room.
	 * @return The last Room were the Player was
	 */
	public Room getPreviousRoom() {
		return (previousRooms.empty())? null : previousRooms.peek();
	}

	/**
	 * Push a room in the previousRooms stack.
	 * @param room Room to be pushed
	 */
	public void pushPreviousRooms(Room room) {
		previousRooms.push(room);
	}

	/**
	 * Push the currentRoom in the previousRooms stack.
	 */
	public void pushForward() {
		previousRooms.push(currentRoom);
	}

	/**
	 * Pop the last room in the previousRooms stack.
	 */
	public void popPreviousRooms() {
		previousRooms.pop();
	}

	/**
	 * Return true if there is no previous rooms.
	 * @return True if there is no previous rooms
	 */
	public boolean noPreviousRooms() {
		return previousRooms.empty();
	}

	/**
	 * beamerRoom field setter.
	 * @param beamerRoom The beamer Room to be saved
	 */
	public void setBeamerRoom(Room beamerRoom) {
		this.beamerRoom = beamerRoom;
	}

	/**
	 * beamerRoom field getter.
	 * @return The saved Room by the beamer
	 */
	public Room getBeamerRoom() {
		return beamerRoom;
	}

	/**
	 * Return the Item in the player's inventory through it's name.
	 * @param item The name of the Item
	 * @return The asked Item
	 */
	public Item getItem(String item) {
		return backpack.get(item);
	}

	/**
	 * Check if the player has a given Item.
	 * @param item Item name to be checked
	 */
	public boolean hasItem(String item) {
		return backpack.containsKey(item);
	}

	/**
	 * Check if the player can carry the given Item.
	 * This methods simply checks the Item with it's weight
	 * @param item The Item to be checked
	 * @return True if the Item can be carried
	 */
	public boolean canCarry(Item item) {
		return backpackWeight + item.getWeight() < maxWeight;
	}

	/**
	 * Take the given object.
	 * @param item Item to be taken.
	 */
	public void takeObject(Item item) {
		currentRoom.getContainedItems().transfer(item.getName(), backpack);
		backpackWeight += item.getWeight();
	}

	/**
	 * Drop the given object.
	 * @param item Item to be dropped
	 */
	public void dropObject(String item) {
		backpackWeight -= backpack.get(item).getWeight();
		backpack.transfer(item, currentRoom.getContainedItems());
	}

	/**
	 * Destroy an object.
	 * @param itemName Item to be destroyed.
	 */
	public void eatObject(String itemName) {
		backpack.remove(itemName);
	}

	/**
	 * Return a String containing information
	 * about the items carried by the player.
	 * @return String The inventory
	 */
	public String getInventory() {
		String returnString = "";
		Set<String> itemsNames = backpack.keySet();
		Iterator<String> it = itemsNames.iterator();
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
		return (returnString.equals(""))?
			"You have nothing. This may be a metaphor for your life.\nBut as always you didn't understand a word I said, don't you?"
			: "You have" + returnString + ".\nCan't you remember that?";
	}

}
