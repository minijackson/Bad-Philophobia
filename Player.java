import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;

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
	 * The maximum weight of Items th player can carry.
	 */
	private int maxWeight = 100;

	/**
	 * Room where the player is currently in.
	 */
	private Room currentRoom;

	/**
	 * Room where the player was before now.
	 */
	private Stack<Room> previousRooms;

	/**
	 * Player class constructor.
	 * @param firstRoom Room where the player start.
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
	 */
	public String getName() {
		return name;
	}

	/**
	 * currentRoom field getter.
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
	 */
	public Room getPreviousRoom() {
		return previousRooms.peek();
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
	 */
	public boolean noPreviousRooms() {
		return previousRooms.empty();
	}

	/**
	 * Return the description of the room or a specific object.
	 * @param command Command used by the user
	 * @return String The description
	 */
	public String lookAround(Command command) {
		String description = "";
		if(!command.hasParameter())
			description +=  currentRoom.getLongDescription() + "\n";
		else if(currentRoom.hasItem(command.getParameter()))
			description += "This is " + currentRoom.getItem(command.getParameter()).getDescription() + ".\n";
		else
			description += "I'm not sure you want to look at that.\n";
		return description;
	}

	/**
	 * Check if the player has a given Item.
	 * @param item Item name to be checked
	 */
	public boolean hasItem(String item) {
		return backpack.containsKey(item);
	}

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
		return (returnString.equals(""))? "You have nothing. This may be a metaphor for your life.\nBut as always you didn't understand a word I said, don't you?"
										: "You have" + returnString + ".\nCan't you remember that?";
	}

}
