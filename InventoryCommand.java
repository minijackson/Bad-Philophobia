/**
 * class InventoryCommand
 * @author Rémi Nicole
 */
public class InventoryCommand extends Command {

	/**
	  * Constructor for InventoryCommand
	  */
	public InventoryCommand(){

	}

	public boolean execute(Player player) {
		setMessage(player.getInventory());
		return false;
	}

}
