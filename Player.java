import java.util.Stack;

public class Player {

	/**
	 * Name of the player.
	 * Either 'retard' or 'moron'
	 */
	private String name;

	/**
	 * Item held by the player.
	 */
	private Item backpack;

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
	 * Take the given object.
	 * @param item Item to be taken.
	 */
	public void takeObject(Item item) {
		backpack = item;
	}

}
