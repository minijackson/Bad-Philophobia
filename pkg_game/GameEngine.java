package pkg_game;

import pkg_world.Player;
import pkg_world.Room;
import pkg_world.pkg_items.Item;

import pkg_world.pkg_characters.Character;
import pkg_world.pkg_characters.MovingCharacter;

import pkg_commands.Command;
import pkg_commands.GoCommand;
import pkg_commands.TestCommand;
import pkg_commands.ThrowCommand;

import pkg_parsing.Parser;

import pkg_exceptions.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class handling the gameplay for the game.
 * It takes care of rooms, parser, and room creations
 * and command processing.
 * @author Rémi NICOLE
 */
public class GameEngine
{
	/**
	 * Player for the game.
	 */
	private Player player;

	/**
	 * A list of all the rooms in the game.
	 */
	private static ArrayList<Room> gameRooms;

	/**
	 * Set of all the moving characters in the game.
	 */
	private HashSet<MovingCharacter> movingCharacters;

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
		gameRooms = new ArrayList<Room>();
		movingCharacters = new HashSet<MovingCharacter>();
		String response = javax.swing.JOptionPane.showInputDialog("What is your name");
		if(response != null) {
			player = new Player((response.equalsIgnoreCase("retard"))? "moron" : "retard", createRooms());
			javax.swing.JOptionPane.showMessageDialog(null, "Whatever, I'll call you " + player.getName() + ".");
		} else {
			player = new Player("dumbass", createRooms());
			javax.swing.JOptionPane.showMessageDialog(null, "Alright. if you take it like this, I'll call you dumbass.");
		}
		helpCount = 0;
		commandCountDown = 42;
	}

	/**
	 * Setter for the gui field.
	 * @param userInterface The user interface to set
	 */
	public void setGUI(UserInterface userInterface) {
		gui = userInterface;
		gui.setCommandsLeft(commandCountDown);
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
		gui.println("\nBeware: Death is coming!\n");
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
		Item nut = new Item("nut", 2, "a black and brown oak nut. Too hard for you");
		temperateBroadleaf.addItem(nut);

		Room taiga = new Room("in a boreal forest", "taiga.jpg");
		taiga.addItem(new Item("snowball", 1, "some weirdly yellowy snowball"));
		taiga.addItem(new Item("bird", 6, "a frozen inert black bird"));
		taiga.addCharacter(new Character("squirrel", "Squeekee squeeki squiki kwiki kwik.",
				nut, "The squirrel says \"Squeekeeee!\". He looks like happy and run away from you.",
				"He looks disapointed and refuses your offer."));

		Room alpineTundra = new Room("on an alpine mountain", "alpinetundra.jpg");
		alpineTundra.addItem(new Item("rock", 15, "a surprisingly solid magnificent rock"));
		alpineTundra.addItem(new Item("plank", 10, "a plank of wood, maybe from a chalet"));
		alpineTundra.addItem(new Item("snowball", 1, "a snowball. Yes, there is still snow in an alpine biome."));

		Room steppe = new Room("on a vast grass plain", "steppe.jpg");
		steppe.addItem(new Item("grass", 1, "a tuft of yellowish grass. Looking at the grass made you look like stupid"));
		Character fox = new Character("fox", "Ring-ding-ding-ding-dingeringeding!");
		fox.addDialog("Gering-ding-ding-ding-dingeringeding!");
		fox.addDialog("Gering-ding-ding-ding-dingeringeding!");
		fox.addDialog("Wa-pa-pa-pa-pa-pa-pow!");
		fox.addDialog("Wa-pa-pa-pa-pa-pa-pow!");
		fox.addDialog("Wa-pa-pa-pa-pa-pa-pow!");
		fox.addDialog("Hatee-hatee-hatee-ho!");
		fox.addDialog("Hatee-hatee-hatee-ho!");
		fox.addDialog("Hatee-hatee-hatee-ho!");
		fox.addDialog("Joff-tchoff-tchoffo-tchoffo-tchoff!");
		fox.addDialog("Tchoff-tchoff-tchoffo-tchoffo-tchoff!");
		fox.addDialog("Joff-tchoff-tchoffo-tchoffo-tchoff!");
		fox.addDialog("Jacha-chacha-chacha-chow!");
		fox.addDialog("Cacha-chacha-chacha-chow!");
		fox.addDialog("Cacha-chacha-chacha-chow!");
		fox.addDialog("Fraka-kaka-kaka-kaka-kow!");
		fox.addDialog("Fraka-kaka-kaka-kaka-kow!");
		fox.addDialog("Fraka-kaka-kaka-kaka-kow!");
		fox.addDialog("A-hee-ahee ha-hee!");
		fox.addDialog("A-hee-ahee ha-hee!");
		fox.addDialog("A-hee-ahee ha-hee!");
		fox.addDialog("A-oo-oo-oo-ooo!");
		fox.addDialog("Woo-oo-oo-ooo!");
		fox.addDialog("Wa-wa-way-do Wub-wid-bid-dum-way-do Wa-wa-way-do");
		fox.addDialog("Bay-budabud-dum-bam");
		fox.addDialog("Mama-dum-day-do");
		steppe.addCharacter(fox);

		Room cave = new Room("inside a dark cave", "cave.jpg");
		cave.addItem(new Item("magiccookie", 3, "a pretend magic cookie with mould on it, probably left there for many years. The use-by date has faded out. Why not eat it?"));

		Room polarDesert = new Room("in a cold polar desert", "polardesert.jpg");
		polarDesert.addItem(new Item("ice", 5, "a little block of ice. But you don't have any drink"));

		Room xericShrublands = new Room("in a sand desert", "xericshrublands.jpg");
		xericShrublands.addItem(new Item("shrub", 10, "a spicky shrub. Useful if you want to make a shruberry"));

		Room savanna = new Room("in a savanna", "savanna.jpg");
		savanna.addCharacter(new Character("elephant"));
		savanna.addItem(new Item("grass", 1, "a tuft of yellowish grass. You may have other things to do instead of looking at that"));

		// initialise room exits
		temperateBroadleaf.setExit("east", taiga);
		temperateBroadleaf.setExit("south", steppe);
		MovingCharacter mouse = new MovingCharacter(temperateBroadleaf, "mouse", "*very annoying high pitched noise (like a baby)*");
		temperateBroadleaf.addCharacter(mouse);
		movingCharacters.add(mouse);

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

		Room randomRoom = new Room("in a randomly random room. If you try to escape it, you will be teleported.\nAs you don't understand, try it.", "random.png");

		polarDesert.setExit("south", randomRoom);
		savanna.setExit("east", randomRoom);

		gameRooms.add(randomRoom);
		gameRooms.add(temperateBroadleaf);
		gameRooms.add(taiga);
		gameRooms.add(alpineTundra);
		gameRooms.add(steppe);
		gameRooms.add(cave);
		gameRooms.add(polarDesert);
		gameRooms.add(xericShrublands);
		gameRooms.add(savanna);

		return temperateBroadleaf;
	}

	/**
	 * Process the command.
	 * @param commandLine The command to process.
	 */
	public void processCommand(String commandLine) {
		gui.println("\n" + commandLine + "\n");
		Command command = Parser.getCommand(commandLine);

		if(command == null) {
			gui.println("I don't know what you mean...");
			return;
		}

		gui.setCommandsLeft(--commandCountDown);

		// If we can cast the command into a GoCommand
		// Or if it is the test command
		if(GoCommand.class.isInstance(command) || command.getClass().equals(TestCommand.class)) {
			// The MovingCharacters move
			// Needed before the execute method
			for(MovingCharacter character : movingCharacters) {
				character.move();
			}
		}

		try {
			boolean quit = command.execute(player);

			// If we can cast the command into a GoCommand
			// Or if it is the test command
			if(GoCommand.class.isInstance(command) || command.getClass().equals(TestCommand.class)) {
				// The game image is reloaded
				if(player.getCurrentRoom().getImageName() != null) {
					gui.showImage(player.getCurrentRoom().getImageName());
				}
			}

			if(command.hasMessage())
				gui.println(command.getMessage());

			if(quit) {
				// If the player quitted through the "throw" command, it's a win
				if(command.getClass().equals(ThrowCommand.class)) {
					endGame(true);
				} else {
					endGame(false);
				}
			}
		} catch(NoArgumentException e) {
			gui.println(e.getMessage());
		} catch(pkg_exceptions.IllegalArgumentException e) {
			gui.println(e.getMessage());
		} catch(UnauthorizedException e) {
			gui.println(e.getMessage());
		}

		if(commandCountDown == 0) {
			endGame(false);
		}

	}

	/**
	 * Print goodbye message and disable gui.
	 * @param winning Equals to true if the player won
	 */
	private void endGame(boolean winning) {
		gui.println("Thank you for playing. Good bye. By the way, you "
				+ ((winning)? "won by luck" : "lost") + ".");
		gui.enable(false);
	}

	public static ArrayList<Room> getRooms() {
		return gameRooms;
	}

}
