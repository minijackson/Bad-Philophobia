/**
 * class EatCommand
 * @author Rémi Nicole
 */
public class EatCommand extends Command {

	/**
	  * Constructor for EatCommand
	  */
	public EatCommand(){

	}

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
