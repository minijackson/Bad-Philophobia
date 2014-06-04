/**
 * class LookCommand used to show information about the current Room or a given Item
 * @author Rémi Nicole
 */
public class LookCommand extends Command {

	/**
	 * Constructor for LookCommand
	 */
	public LookCommand(){

	}

	/**
	 * Saves the information about the current Room or a given Item in the message field.
	 * @param player The player that called this command
	 * @throws IllegalArgumentException When the user typed a name other than any of the items in the Player's or the Room's inventory
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws IllegalArgumentException {
		if(hasParameter()) {
			if(player.getCurrentRoom().hasItem(getParameter())) {
				setMessage(
						showItem(player.getCurrentRoom().getItem(getParameter()))
						);
			} else if (player.hasItem(getParameter()) ) {
				setMessage(
						showItem(player.getItem(getParameter()))
						);
			} else
				throw new IllegalArgumentException( "I'm not sure you want to look at that.");
		} else
			setMessage(player.getCurrentRoom().getLongDescription());
		return false;
	}

	/**
	 * Return a given Item's description.
	 * @param item The item for which we want the description
	 * @return The item's description
	 */
	private String showItem(Item item) {
		return "This is " + item.getDescription() + ".";
	}

}
