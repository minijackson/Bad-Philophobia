/**
 * class HelpCommand
 * @author Rémi Nicole
 */
public class HelpCommand extends Command {

	private int helpCount;

	/**
	 * Constructor for HelpCommand
	 */
	public HelpCommand(){
		helpCount = 0;
	}

	public boolean execute(Player player) {
		setMessage("");
		if(helpCount == 0) {
			setMessage("Help ? Who needs help ? Only the weak ones.");
			++helpCount;
		} else {
			if(helpCount == 1){
				setMessage("All right, all right! If you insist...\n");
				++helpCount;
			}
			setMessage(getMessage() + "What you can do is: " + Parser.showCommands() + "\n"
					+ "That will be all.");
		}
		return false;
	}

}
