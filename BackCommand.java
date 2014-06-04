/**
 * class BackCommand used to go back in a previous Room.
 * @author Rémi NICOLE
 */
public class BackCommand extends GoCommand {

	/**
	 * Constructor for BackCommand
	 */
	public BackCommand(){
		super();
	}

	/**
	 * Go back in a previous room of the player.
	 * @param player The player that typed the "back" command
	 * @throws UnauthorizedException When the user has no previous room
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws UnauthorizedException {
		try {
			super.goRoomCheck(player.getPreviousRoom(), player, true);
		} catch(IllegalArgumentException e)  {
			// Tried to teleport to null
			throw new UnauthorizedException("Awww, that's sweet. The little player want to go home.\n"
					+ "But we're just getting started!");
		}
		return false;
	}

}
