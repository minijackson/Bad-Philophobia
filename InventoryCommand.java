/**
 * class InventoryCommand used to print the player's inventory
 * @author Rémi Nicole
 */
public class InventoryCommand extends Command {

	/**
	 * Constructor for InventoryCommand
	 */
	public InventoryCommand(){

	}

	/**
	 * Save the inventory into the message field.
	 * @param player The player that called this command
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) {
		setMessage(player.getInventory());
		return false;
	}

}
