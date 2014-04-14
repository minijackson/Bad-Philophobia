/**
 * Class handling the gameplay for the game.
 * It takes care of room, parser, and room creations
 * and command processing.
 *
 * @author Rémi NICOLE
 */
public class GameEngine
{
	/**
	 * Parser for the game.
	 */
	private Parser parser;

	/**
	 * Room where the player is currently in.
	 */
	private Room currentRoom;

	/**
	 * User interface for the game.
	 */
	private UserInterface gui;

	/**
	 * GameEngine class constructor.
	 */
	public GameEngine() {
		parser = new Parser();
		createRooms();
	}

	/**
	 * Setter for the gui field.
	 * @see GameEngine#gui;
	 */
	public void setGUI(UserInterface userInterface) {
		gui = userInterface;
		printWelcome();
	}

	/**
	 * Welcome the user at the start of the game.
	 */
	private void printWelcome() {
		gui.println("Greetings human.");
		gui.println("I see the assassins have failed. Too bad...");
		gui.println("You know what they say: if you want something done, do it yourself.");
		gui.println("At least I can see that you don't remember anything. At last something that I can take advantage of.");
		gui.println("That was predictable, human minds are weak.");
		gui.println("\nBecause you're stupid, I will describe you everything that will be around us.");
		gui.println("Who know ? Maybe you can turn into something useful. One day. Maybe.");
		gui.println(currentRoom.getLongDescription());
		gui.showImage(currentRoom.getImageName());
	}

	/**
	 * Create the rooms and link them.
	 */
	private void createRooms() {
		// create the rooms
		Room outside = new Room("outside the main entrance of the university", "outside.gif");
		Room theatre = new Room("in a lecture theatre", "castle.gif");
		Room pub = new Room("in the campus pub", "courtyard.gif");
		Room lab = new Room("in a computing lab", "stairs.gif");
		Room office = new Room("the computing admin office", "dungeon.gif");

		// initialise room exits
		outside.setExit("east", theatre);
		outside.setExit("south", lab);
		outside.setExit("west", pub);

		theatre.setExit("west", outside);

		pub.setExit("east", outside);

		lab.setExit("north", outside);
		lab.setExit("east", office);

		office.setExit("west", lab);

		currentRoom = outside;  // start game outside
	}

	/**
	 * Process the command.
	 * @param commandLine The command to process.
	 */
	public void processCommand(String commandLine) {
		gui.println(commandLine);
		Command command = parser.getCommand(commandLine);

		if(command.isUnknown()) {
			gui.println("I don't know what you mean...");
			return;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help"))
			printHelp();
		else if (commandWord.equals("go"))
			goRoom(command);
		else if (commandWord.equals("quit")) {
			if(command.hasSecondWord())
				gui.println("Quit what?");
			else
				endGame();
		}
	}

	/**
	 * Print user's help.
	 */
	private void printHelp() {
		gui.println("Help ? Who needs help ? Only the weak ones.")
			gui.println("Your command words are: " + parser.showCommands());
		gui.println("That will be all.");
	}

	/**
	 * Go to the given Room.
	 * If the user can't go to the given room,
	 * an error message is printed.
	 */
	private void goRoom(Command command) {
		if(!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			gui.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null)
			gui.println("There is no door!");
		else {
			currentRoom = nextRoom;
			gui.println(currentRoom.getLongDescription());
			if(currentRoom.getImageName() != null)
				gui.showImage(currentRoom.getImageName());
		}
	}

	/**
	 * Print goodbye message and disable gui.
	 */
	private void endGame() {
		gui.println("Thank you for playing.  Good bye.");
		gui.enable(false);
	}

}
