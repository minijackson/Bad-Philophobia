package pkg_commands;

import pkg_world.Player;
import pkg_exceptions.*;

/**
 * class BeamerCommand used to teleport into a remembered room
 * @author Rémi NICOLE
 */
public class BeamerCommand extends GoCommand {

	/**
	 * Constructor for BeamerCommand
	 */
	public BeamerCommand(){

	}

	/**
	 * Teleport to the remembered room or remember a room.
	 * It teleports to the remembered room if the command parameter
	 * was "teleport", and it remembers the room if the command parameter
	 * was "charge"
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a parameter other than "teleport" or "charge"
	 * @throws UnauthorizedException When the user tries to teleport to null
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws NoArgumentException,pkg_exceptions.IllegalArgumentException,UnauthorizedException {
		if(hasParameter()) {
			if(getParameter().equals("charge")) {
				charge(player);
				return false;
			} else if(getParameter().equals("teleport")) {
				teleport(player);
				return false;
			} else {
				throw new pkg_exceptions.IllegalArgumentException("In order to do that, I may need to upgrade. But I don't want to.");
			}
		} else 
			throw new NoArgumentException("You can either charge or teleport with the beamer.\nBut that may be too much complicated for you, isn't it?");
	}

	/**
	 * Teleport the player to the remembered room.
	 * @param player The player that called this command
	 * @throws UnauthorizedException When the user tries to teleport to null
	 */
	private void teleport(Player player) throws UnauthorizedException {
		try {
			if(player.getBeamerRoom() == player.getCurrentRoom())
				setMessage("Teleporting you right were you are.");
			else
				setMessage("Teleporting you to useless room...");
			goRoomNoCheck(player.getBeamerRoom(), player, false);
		} catch(pkg_exceptions.IllegalArgumentException e) {
			// Tried to teleport to null
			throw new UnauthorizedException("I'm sorry but I can't teleport you nowhere.");
		}
	}

	/**
	 * Remember the current room.
	 * @param player The player that called this command
	 */
	public void charge(Player player) {
		if(player.getBeamerRoom() == null)
			setMessage("Useless room remembered.");
		else
			setMessage("Useful room overridden by a useless room.");
		player.setBeamerRoom(player.getCurrentRoom());
	}

}
