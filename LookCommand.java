/**
 * class LookCommand
 */
public class LookCommand extends Command {

	/**
	 * Constructor for LookCommand
	 */
	public LookCommand(){

	}

	public boolean execute(Player player) throws IllegalArgumentException {
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
		setMessage(player.getCurrentRoom().getLongDescription());
		return false;
	}

	private String showItem(Item item) {
		return "This is " + item.getDescription() + ".";
	}

}
