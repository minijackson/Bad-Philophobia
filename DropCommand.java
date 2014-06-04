/**
 * class DropCommand used to make the player drop an Item
 * @author Rémi Nicole
 */
public class DropCommand extends Command {

	/**
	 * Constructor for DropCommand
	 */
	public DropCommand(){

	}

	/**
	 * Drop the Item through it's name in the parameter field.
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a name other than any of the items in the Player's inventory
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException {
		if(hasParameter()) {
			if(player.hasItem(getParameter())){
				player.dropObject(getParameter());
				setMessage("Oh, dear. He took a" + (((new String("aeiouy")).contains(getParameter().substring(0,1)))? "n " : " ") + getParameter() + ".");
			} else
				throw new IllegalArgumentException("If you want to drop that, you may have a mental disorder. As expected.");
		} else
			throw new NoArgumentException("I agree. We both want you to drop dead.");
		return false;
	}

}
