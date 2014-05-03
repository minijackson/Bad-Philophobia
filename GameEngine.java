import java.util.Scanner;
import java.util.Stack;

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
	 * Room where the player was before now.
	 */
	private Stack<Room> previousRooms;

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
		previousRooms = new Stack<Room>();
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
		gui.println("Who knows ? Maybe you can turn into something useful. One day. Maybe.");
		gui.println(currentRoom.getLongDescription());
		gui.showImage(currentRoom.getImageName());
	}

	/**
	 * Create the rooms and link them.
	 */
	private void createRooms() {
		// create the rooms
		Room temperateBroadleaf = new Room("in temperate forest", "temperatebroadleaf.jpg");
		temperateBroadleaf.addItem(new Item("wand", 3, "just an ordinary wand"));

		Room taiga = new Room("in a boreal forest", "taiga.jpg");
		taiga.addItem(new Item("snowball", 1, "some weirdly yellowy snowball"));
		taiga.addItem(new Item("bird", 6, "a frozen inert black bird"));

		Room alpineTundra = new Room("on an alpine mountain", "alpinetundra.jpg");
		alpineTundra.addItem(new Item("rock", 15, "a surprisingly solid magnificent rock"));
		alpineTundra.addItem(new Item("plank", 10, "a plank of wood, maybe from a chalet"));
		alpineTundra.addItem(new Item("snowball", 1, "a snowball. Yes, there is still snow in an alpine biome."));

		Room steppe = new Room("on a vast grass plain", "steppe.jpg");
		steppe.addItem(new Item("grass", 1, "a tuft of yellowish grass. Looking at the grass made you look like stupid"));

		Room cave = new Room("inside a dark cave", "cave.jpg");

		Room polarDesert = new Room("in a cold polar desert", "polardesert.jpg");
		polarDesert.addItem(new Item("ice", 5, "a little block of ice. But you don't have any drink"));

		Room xericShrublands = new Room("in a sand desert", "xericshrublands.jpg");
		xericShrublands.addItem(new Item("shrub", 10, "a spicky shrub. Useful if you want to make a shruberry"));

		Room savanna = new Room("in a savanna", "savanna.jpg");
		savanna.addItem(new Item("elephant", 1000, "a huge elephant looking at you, dazed. I bet he's smarter than you"));
		savanna.addItem(new Item("grass", 1, "a tuft of yellowish grass. You may have other things to do instead of looking at that"));

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
		else if (commandWord.equals("back"))
			goBack();
		else if (commandWord.equals("look"))
			lookAround(command);
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
		gui.println("Alpine tundra photo (public domain) : Zewu (https://en.wikipedia.org/wiki/File:Tarfala_Valley_-_Sweden.jpg)");
		gui.println("Steppe photo (cc-by-sa) : Matt Lavin (http://www.fotopedia.com/wiki/Steppe#!/items/flickr-7495949260)");
		gui.println("Lava tube photo : Tim Laman (http://science.nationalgeographic.com/science/photos/caves-gallery/#/lava-tube-cave_1036_600x450.jpg)");
		gui.println("Polar desert photo (cc-by) : Stephen Hudson (https://commons.wikimedia.org/wiki/File:AntarcticaDomeCSnow.jpg)");
		gui.println("Thar desert photo (cc-by-sa) : Gégard JANOT (https://commons.wikimedia.org/wiki/File:D%C3%A9sert_du_Rajasthan.jpg)");
		gui.println("Savanna photo (public domain) : United States Geological Survey (https://commons.wikimedia.org/wiki/File:Oldoinyolengai.jpg)");
	}

	/**
	 * Go to the given Room.
	 * @param command Command used by the user
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
		goRoom(nextRoom);
	}

	/**
	 * Go to the given Room.
	 * This function is equivalent to
	 * goRoom(room, false).
	 * @param room Room where the user want to go
	 */
	private void goRoom(Room room) {
		goRoom(room, false);
	}

	/**
	 * Go to the given Room.
	 * If the user can't go to the given room,
	 * an error message is printed.
	 * @param room Room where the user want to go
	 * @param back true if it is called via the 'back' command
	 */
	private void goRoom(Room room, boolean back) {
		if (room == null)
			gui.println("There is no door!");
		else {
			if(!back)
				previousRooms.push(currentRoom);
			else
				previousRooms.pop();
			currentRoom = room;
			gui.println(currentRoom.getLongDescription());
			if(currentRoom.getImageName() != null)
				gui.showImage(currentRoom.getImageName());
		}
	}

	private void goBack() {	
		if(!previousRooms.empty()) {
			goRoom(previousRooms.peek(), true);
		} else {
			gui.println("No previous room");
		}
	}

	/**
	 * Get the description of the room or a specific object.
	 * @param command Command used by the user
	 */
	private void lookAround(Command command) {
		if(!command.hasParameter())
			gui.println(currentRoom.getLongDescription());
		else if(currentRoom.hasItem(command.getParameter()))
			gui.println("This is " + currentRoom.getItem(command.getParameter()).getDescription() + ".");
		else
			gui.println("I'm not sure you want to look at that.");
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
