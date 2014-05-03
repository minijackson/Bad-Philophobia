
/**
 * Class used to handle an item contained in a Room.
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
	 * @param description Description of the item
	 * @param weight Weight of the item
	 */
	public Item(String name, int weight, String description) {
		this.name = name;
		this.weight = weight;
		this.description = description;
	}

	/**
	 * name field getter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * weight field getter.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * description field getter.
	 */
	public String getDescription() {
		return description;
	}

}
