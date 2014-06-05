package pkg_world;

import java.util.HashMap;

/**
 * class ItemList used to handle a list of Item (inventory).
 * @author Rémi NICOLE
 */
public class ItemList extends HashMap<String, Item> {

	/**
	 * Transfer a given item to another ItemList or HashMap
	 * @param item Item to be transferred
	 * @param itemMap Map of items in which the item is transferred
	 */
	public void transfer(String item, HashMap<String, Item> itemMap) {
		itemMap.put(item, this.remove(item));
	}

}
