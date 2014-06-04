/**
 * class GoCommand used to make the player go to a given Room.
 * @author Rémi Nicole
 */
public class GoCommand extends Command {

	/**
	 * Constructor for GoCommand.
	 */
	public GoCommand(){

	}

	/**
	 * Make the player go to the Room through it's direction in the parameter field.
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a parameter other than an available exit direction
	 * @throws UnauthorizedException When the user isn't allowed to go to the given Room
	 * @return False because it is not the quit command
	 */
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
	 * Check if the wanted Room is connected to the current Room and go to this room.
	 * @param room Room where the user want to go
	 * @param player The player that called this command
	 * @param back true if it is called via the 'back' command
	 * @throws IllegalArgumentException When the user tries to go to null
	 * @throws UnauthorizedException When the user isn't allowed to go to the given Room
	 */
	protected void goRoomCheck(Room room, Player player, boolean back) throws IllegalArgumentException,UnauthorizedException {
		if(player.getCurrentRoom().isExit(room)) {
			goRoomNoCheck(room, player, back);
		} else {
			throw new UnauthorizedException("I'm sorry but you can't pass through walls.");
		}
	}

	/**
	 * Go to the Room without checking.
	 * @param room Room where the user want to go
	 * @param player The player that called this command
	 * @param back true if it is called via the 'back' command
	 * @throws IllegalArgumentException When the user tries to go to null
	 */
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
