import java.util.Scanner;

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
	  User interface for the game.
	 */
	private UserInterface gui;

	/**
	 * Help query counter.
	 * It is equal to 0 if the user did not asked for help,
	 * 1 if the user asked once the help and
	 * 2 if the user asked twice or more for the help
	 */
	private int helpCount;

	/**
	 * GameEngine class constructor.
	 */
	public GameEngine() {
		parser = new Parser();
		createRooms();
		helpCount = 0;
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
		Room temperateBroadleaf = new Room("in temperate forest", "temperatebroadleaf.jpg");
		Room taiga = new Room("in a boreal forest", "taiga.jpg");
		Room alpineTundra = new Room("on an alpine mountain", "alpinetundra.gif");
		Room steppe = new Room("on a vast grass plain", "steppe.gif");
		Room cave = new Room("inside a dark cave", "cave.gif");
		Room polarDesert = new Room("in a cold polar desert", "polardesert.gif");
		Room xericShrublands = new Room("in a sand desert", "xericShrublands.gif");
		Room savanna = new Room("in a savanna", "savanna.gif");

		// initialise room exits
		temperateBroadleaf.setExit("east", taiga);
		temperateBroadleaf.setExit("south", steppe);

		taiga.setExit("west", temperateBroadleaf);
		taiga.setExit("east", alpineTundra);
		taiga.setExit("south", cave);

		alpineTundra.setExit("west", taiga);
		alpineTundra.setExit("south", polarDesert);

		steppe.setExit("north", temperateBroadleaf);
		steppe.setExit("east", cave);
		steppe.setExit("south", xericShrublands);

		cave.setExit("north", taiga);
		cave.setExit("south", savanna);
		cave.setExit("east", polarDesert);
		cave.setExit("west", steppe);

		polarDesert.setExit("north", alpineTundra);
		polarDesert.setExit("west", cave);

		xericShrublands.setExit("north", steppe);
		xericShrublands.setExit("east", savanna);

		savanna.setExit("north", cave);
		savanna.setExit("west", xericShrublands);

		currentRoom = temperateBroadleaf;  // start game outside
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
		else if (commandWord.equals("credits"))
			printCredits();
		else if (commandWord.equals("go"))
			goRoom(command);
		else if (commandWord.equals("quit")) {
			if(command.hasParameter())
				gui.println("Quit what?");
			else
				endGame();
		}
	}

	/**
	 * Print user's help.
	 */
	private void printHelp() {
		if(helpCount == 0) {
			gui.println("Help ? Who needs help ? Only the weak ones.");
			helpCount++;
		} else {
			if(helpCount == 1) {
				gui.println("All right, all right! If you insist...");
				helpCount++;
			}
			gui.println("Your command words are: " + parser.showCommands());
			gui.println("That will be all.");
		}
	}

	/**
	 * Print the credits for the game.
	 */
	private void printCredits() {
		gui.println("Temperate rainforest photo (cc-by-nc-nd) : myheimu (http://www.fotopedia.com/wiki/Temperate_rainforest#!/items/flickr-7995237868)");
		gui.println("Taiga photo (public domain) : Becker0804 (https://commons.wikimedia.org/wiki/File:Talkessel_von_Werchojansk.JPG)");
	}

	/**
	 * Go to the given Room.
	 * If the user can't go to the given room,
	 * an error message is printed.
	 */
	private void goRoom(Command command) {
		if(!command.hasParameter()) {
			// If there is no second word, we don't know where to go...
			gui.println("Go where?");
			return;
		}

		String direction = command.getParameter();

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

	private void test(final Command command) {
		if(!command.hasParameter()) {
			gui.println("Please specify a file");
			return;
		} else {
			Scanner scan = new Scanner(command.getParameter());
			while(scan.hasNext()) {
				processCommand(scan.nextLine());
			}
		}
	}
}
