/**
 * class GoCommand
 */
public class GoCommand extends Command {

	/**
	  * Constructor for GoCommand.
	  * @param trust True if there is no check for room connection needed
	  */
	public GoCommand(){

	}

	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException,UnauthorizedException {
		setMessage("");
		if(hasParameter()) {
			goRoomCheck(player.getCurrentRoom().getExit(getParameter()), player, false);
		} else {
			throw new NoArgumentException("If you were clever, I would have thought that it was a existential question.\nBut that is not the case and I cannot allow you to go nowhere");
		}
		return false;
	}

	/**
	 * Check if the wanted room is connected to the current Room and go to this room.
	 * @param room Room where the user want to go
	 * @param player The player that called this command
	 * @param back true if it is called via the 'back' command
	 */
	protected void goRoomCheck(Room room, Player player, boolean back) throws IllegalArgumentException,UnauthorizedException {
		if(player.getCurrentRoom().isExit(room)) {
			goRoomNoCheck(room, player, back);
		} else {
			throw new UnauthorizedException("I'm sorry but you can't pass through walls.");
		}
	}

	protected void goRoomNoCheck(Room room, Player player, boolean back) throws IllegalArgumentException {
		if(room != null) {
			if(!back)
				player.pushForward();
			else
				player.popPreviousRooms();
			player.goRoom(room);
			setMessage(((hasMessage()) ? getMessage() + "\n" : "") + room.getLongDescription());
		} else
			throw new IllegalArgumentException("This is a wall, not a door!");
	}

}
