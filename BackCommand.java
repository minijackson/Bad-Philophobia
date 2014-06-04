/**
 * class BackCommand
 */
public class BackCommand extends GoCommand {

	/**
	  * Constructor for BackCommand
	  */
	public BackCommand(){
		super();
	}

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
