import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
	 * Player for the game.
	 */
	private Player player;

	/**
	 * Parser for the game.
	 */
	private Parser parser;

	/**
	 * User interface for the game.
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
	 * The command number limit of the game.
	 * It is initially equals to 42
	 */
	private int commandCountDown;

	/**
	 * GameEngine class constructor.
	 */
	public GameEngine() {
		parser = new Parser();
		player = new Player((javax.swing.JOptionPane.showInputDialog("What is your name").toLowerCase().equals("retard"))? "moron" : "retard", createRooms());
		javax.swing.JOptionPane.showMessageDialog(null, "Whatever, I'll call you " + player.getName() + ".");
		helpCount = 0;
		commandCountDown = 42;
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
		gui.println(player.getCurrentRoom().getLongDescription());
		gui.showImage(player.getCurrentRoom().getImageName());
	}

	/**
	 * Create the rooms, link them and return the first Room.
	 * @return Room The first Room where the player should ba at start.
	 */
	private Room createRooms() {
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
		cave.addItem(new Item("magiccookie", 3, "a pretend magic cookie with mould on it, probably left there for many years. The use-by date has faded out. Why not eat it?"));

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

		// Trap Door
		// polarDesert.setExit("north", alpineTundra);
		polarDesert.setExit("west", cave);

		xericShrublands.setExit("north", steppe);
		xericShrublands.setExit("east", savanna);

		savanna.setExit("north", cave);
		savanna.setExit("west", xericShrublands);

		return temperateBroadleaf;
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

		if(commandCountDown > 1)
			gui.println("You now have " + --commandCountDown + " commands left. Death is coming");
		else {
			gui.println("You have no more commands left. Death is here...");
			--commandCountDown;
		}

		CommandWord commandWord = command.getCommandWord();
		if (commandWord == CommandWord.HELP)
			printHelp();
		else if (commandWord == CommandWord.CREDITS)
			printCredits();
		else if (commandWord == CommandWord.GO)
			goRoom(command);
		else if (commandWord == CommandWord.BACK)
			goBack();
		else if (commandWord == CommandWord.BEAMER)
			beamerAction(command);
		else if (commandWord == CommandWord.LOOK)
			gui.print(player.lookAround(command));
		else if (commandWord == CommandWord.TAKE)
			takeItem(command);
		else if (commandWord == CommandWord.DROP)
			dropItem(command);
		else if (commandWord == CommandWord.EAT)
			eat(command);
		else if (commandWord == CommandWord.INVENTORY)
			gui.println(player.getInventory());
		else if (commandWord == CommandWord.TEST)
			testCommands(command);
		else if (commandWord == CommandWord.QUIT) {
			if(command.hasParameter())
				gui.println("Quit what?");
			else
				endGame(false);
		}

		if(commandCountDown == 0) {
			endGame(false);
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
		Room nextRoom = player.getCurrentRoom().getExit(direction);
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
				player.pushForward();
			else
				player.popPreviousRooms();
			player.goRoom(room);
			gui.println(player.getCurrentRoom().getLongDescription());
			if(player.getCurrentRoom().getImageName() != null)
				gui.showImage(player.getCurrentRoom().getImageName());
		}
	}

	/**
	 * Make the Player go to the previous Room.
	 */
	private void goBack() {
		if(!player.noPreviousRooms()) {
			if(player.getCurrentRoom().isExit(player.getPreviousRoom()))
				goRoom(player.getPreviousRoom(), true);
			else
				gui.println("You cannot go there anymore");
		} else {
			gui.println("No previous room.");
		}
	}

	/**
	 * An action has been triggered on the beamer.
	 */
	public void beamerAction(Command command) {
		if(!command.hasParameter())
			gui.println("You can either charge or teleport with the beamer.\nBut that may be too much complicated for you, isn't it?");
		else if(command.getParameter().equals("charge"))
			beamerCharge();
		else if(command.getParameter().equals("teleport"))
			beamerTeleport();
		else
			gui.println("In order to do that, I may need to upgrade. But I don't want to.");
	}

	/**
	 * The user requested a charge on the beamer.
	 * @return String The message to be printed
	 */
	public void beamerCharge() { 
		if(player.getBeamerRoom() == null)
			gui.println("Useless room remembered.");
		else
			gui.println("Useful room overridden by a useless room.");

		player.setBeamerRoom(player.getCurrentRoom());
	}

	/**
	 * The user requested a teleport on the beamer.
	 * @return String The message to be printed
	 */
	public void beamerTeleport() {
		if(player.getBeamerRoom() == null)
			gui.println("I'm sorry but I can't teleport you nowhere.");
		else if(player.getBeamerRoom() == player.getCurrentRoom()) {
			player.setBeamerRoom(null);
			gui.println("Teleporting you right where you are...");
		} else {
			goRoom(player.getBeamerRoom());
			player.setBeamerRoom(null);
			gui.println("Teleporting you to useless room...");
		}
	}

	/**
	 * Make the Player take a given item.
	 * @param command Command used by the user
	 */
	private void takeItem(Command command) {
		if(command.hasParameter()) {
			if(player.getCurrentRoom().hasItem(command.getParameter())) {
				if(player.canCarry(player.getCurrentRoom().getItem(command.getParameter()))) {
					player.takeObject(player.getCurrentRoom().getItem(command.getParameter()));
					gui.println("Oh, dear. He took a" + (((new String("aeiouy")).contains(command.getParameter().substring(0,1)))? "n " : " ") + command.getParameter() + ".");
				} else
					gui.println("That's way too much items for you. I know, humans are weak.");
			} else
				gui.println("I'm not sure you want to take that.");
		} else
			gui.println("Wanna take a photo?");
	}

	/**
	 * Make the Player drop a given item.
	 * @param command Command used by the user
	 */
	private void dropItem(Command command) {
		if(command.hasParameter()) {
			if(player.hasItem(command.getParameter())) {
				player.dropObject(command.getParameter());
				gui.println("I don't think that was useful to drop a" + (((new String("aeiouy")).contains(command.getParameter().substring(0,1)))? "n " : " ") + command.getParameter() + ".");
			} else
				gui.println("If you want to drop that, you may have a mental disorder. As expected.");
		} else
			gui.println("I agree. We both want you to drop dead.");
	}

	/**
	 * Make the player eat an item.
	 * @param command Command used by the user
	 */
	private void eat(Command command) {
		if(command.hasParameter()) {
			if(player.hasItem(command.getParameter())) {
				if(command.getParameter().equals("magiccookie")) {
					player.eatObject(command.getParameter());
					player.setMaxWeight(player.getMaxWeight() + 100);
					gui.println("You found an out of date \"magic\" cookie inside a cave and you just ate it.\nNow you can carry more items. That's logic!");
				} else
					gui.println("If that's what you eat, I don't want to be invited to any of your meals.\nI'm afraid I can't allow you to do that.");
			} else
				gui.println("You don't have that. And I'm sure that if you did, it wouldn't be smart to eat that.");
		} else
			gui.println("Eat my shorts?");
	}

	/**
	 * Test a series of commands in a file.
	 * @param command Command used by the user
	 */
	private void testCommands(Command command) {
		if (!command.hasParameter()){
			gui.println("What do you want to test?");
			return;
		}
		try {
			Scanner s = new Scanner(new File(command.getParameter() + ".test"));
			while (s.hasNextLine()) {
				processCommand(s.nextLine());
			}
			s.close();
		} catch(FileNotFoundException e) {
			gui.println("No such test");
		}
	}

	/**
	 * Print goodbye message and disable gui.
	 * @param winning Equals to true if the player won
	 */
	private void endGame(boolean winning) {
		gui.println("Thank you for playing. Good bye. By the way, you "
				+ ((winning)? "won" : "lost") + ".");
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
