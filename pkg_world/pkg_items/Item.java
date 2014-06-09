/**
 * \package pkg_world.pkg_items
 * Package containing all the classes in relation with the items
 */

package pkg_world.pkg_items;

/**
 * Class used to handle an item contained in a Room.
 * @author Rémi Nicole
 */
public class Item {

	/**
	 * Name of the item.
	 */
	private String name;
	
	/**
	 * Weight of the item.
	 */
	private int weight;

	/**
	 * Description of the item.
	 */
	private String description;

	/**
	 * Item class constructor.
	 * @param name Name of the item
	 * @param weight Weight of the item
	 * @param description Description of the item
	 */
	public Item(String name, int weight, String description) {
		this.name = name;
		this.weight = weight;
		this.description = description;
	}

	/**
	 * name field getter.
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * weight field getter.
	 * @return The weight of the item
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * description field getter.
	 * @return The description of the item
	 */
	public String getDescription() {
		return description;
	}

}
