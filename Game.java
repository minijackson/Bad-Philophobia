import pkg_game.GameEngine;
import pkg_game.UserInterface;

/**
 * Main class used to instantiate other objects.
 * @author Rémi NICOLE
 */

public class Game
{
	/**
	 * Main function.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		Game game = new Game();
	}

	/**
	 * The game's user interface.
	 */
	private UserInterface gui;

	/**
	 * The gameplay manager.
	 */
	private GameEngine engine;

	/**
	 * Game class constructor
	 */
	public Game () {
		engine = new GameEngine();
		gui = new UserInterface(engine);
		engine.setGUI(gui);
	}
}
