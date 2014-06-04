/**
 * class BeamerCommand
 */
public class BeamerCommand extends GoCommand {

	/**
	 * Constructor for BeamerCommand
	 */
	public BeamerCommand(){

	}

	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException,UnauthorizedException {
		if(hasParameter()) {
			if(getParameter().equals("charge")) {
				charge(player);
				return false;
			} else if(getParameter().equals("teleport")) {
				teleport(player);
				return false;
			} else {
				throw new IllegalArgumentException("In order to do that, I may need to upgrade. But I don't want to.");
			}
		} else 
			throw new NoArgumentException("You can either charge or teleport with the beamer.\nBut that may be too much complicated for you, isn't it?");
	}

	private void teleport(Player player) throws UnauthorizedException {
		try {
			if(player.getBeamerRoom() == player.getCurrentRoom())
				setMessage("Teleporting you right were you are.");
			else
				setMessage("Teleporting you to useless room...");
			goRoomNoCheck(player.getBeamerRoom(), player, false);
		} catch(IllegalArgumentException e) {
			// Tried to teleport to null
			throw new UnauthorizedException("I'm sorry but I can't teleport you nowhere.");
		}
	}

	public void charge(Player player) {
		if(player.getBeamerRoom() == null)
			setMessage("Useless room remembered.");
		else
			setMessage("Useful room overridden by a useless room.");
		player.setBeamerRoom(player.getCurrentRoom());
	}

}
