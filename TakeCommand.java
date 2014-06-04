/**
 * class TakeCommand
 * @author Rémi Nicole
 */
public class TakeCommand extends Command {

	/**
	  * Constructor for TakeCommand
	  */
	public TakeCommand(){

	}

	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException,UnauthorizedException {
		if(hasParameter()) {
			if(player.getCurrentRoom().hasItem(getParameter())) {
				if(player.canCarry(player.getCurrentRoom().getItem(getParameter()))) {
					player.takeObject(player.getCurrentRoom().getItem(getParameter()));
					setMessage("Oh, dear. He took a" + (((new String("aeiouy")).contains(getParameter().substring(0,1)))? "n " : " ") + getParameter() + ".");
				} else 
					throw new UnauthorizedException("That's way too much items for you. I know, humans are weak.");
			} else 
				throw new IllegalArgumentException("I'm not sure you want to take that.");
		} else
			throw new NoArgumentException("Wanna take a photo?");

		return false;
	}

}
