import java.util.Set;
import java.util.HashMap;
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
	private HashMap < String, Room > exits;

	/**
	 * Image to display for the current room.
	 */
	private String imageName;

	/**
	 * Room class constructor.
	 * @param description Description for the room
	 * @param image Image path to display
	 */
	public Room(String description, String image) {
		this.description = description;
		exits = new HashMap < String, Room > ();
		imageName = image;
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
	 * Getter for the description field.
	 */
	public String getShortDescription() {
		return description;
	}

	/**
	 * Return the description of the room plus the available exits.
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n" + getExitString();
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

	/**
	 * Getter for the imageName field.
	 */
	public String getImageName() {
		return imageName;
	}
}
