package pkg_commands;

import pkg_world.Player;
import pkg_parsing.Parser;

/**
 * class HelpCommand used to print the help
 * @author Rémi Nicole
 */
public class HelpCommand extends Command {

	/**
	 * Help query counter.
	 * It is equal to 0 if the user did not ask for help,
	 * 1 if the user asked once the help and
	 * 2 if the user asked twice or more for the help
	 */
	private int helpCount;

	/**
	 * Constructor for HelpCommand
	 */
	public HelpCommand(){
		helpCount = 0;
	}

	/**
	 * Saves user's help in the parameter field.
	 * It really shows help if the user asked for more than once the help.
	 * @param player The player that called this command
	 * @return False because it is not the quit command
	 */
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
