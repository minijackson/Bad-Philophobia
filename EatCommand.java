/**
 * class EatCommand used to make the player eat an object.
 * @author Rémi Nicole
 */
public class EatCommand extends Command {

	/**
	 * Constructor for EatCommand
	 */
	public EatCommand(){

	}

	/**
	 * Make the player eat an object.
	 * Only the "magiccookie" Item is eatable
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a name other than any of the items in the Player's inventory
	 * @throws UnauthorizedException When the user tries to eat a non-eatable object
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException,UnauthorizedException {
		if(hasParameter()) {
			if(player.hasItem(getParameter())){
				if(getParameter().equals("magiccookie")) {
					player.eatObject(getParameter());
					player.setMaxWeight(player.getMaxWeight() + 100);
					setMessage("You found an out of date \"magic\" cookie inside a cave and you just ate it.\nNow you can carry more items. That's logic!");
				} else
					throw new UnauthorizedException("If that's what you eat, I don't want to be invited to any of your meals.\nI'm afraid I can't allow you to do that.");
			} else 
				throw new IllegalArgumentException("You don't have that. And I'm sure that if you did, it wouldn't be smart to eat that.");
		} else 
			throw new NoArgumentException("Eat my shorts?");
		return false;
	}

}
