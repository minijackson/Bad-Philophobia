/**
 * class DropCommand
 * @author Rémi Nicole
 */
public class DropCommand extends Command {

	/**
	  * Constructor for DropCommand
	  */
	public DropCommand(){

	}

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
