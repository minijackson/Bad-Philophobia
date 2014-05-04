import java.util.HashMap;

public class ItemList extends HashMap<String, Item> {

	/**
	 * Transfer a given item to another ItemList or HashMap
	 * @param item Item to be transferred
	 * @param itemList Map of items in which the item is transferred
	 */
	public void transfer(String item, HashMap<String, Item> itemMap) {
		itemMap.put(item, this.remove(item));
	}

}
