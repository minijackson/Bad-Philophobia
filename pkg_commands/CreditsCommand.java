package pkg_commands;

import pkg_world.Player;

/**
 * class CreditsCommand used to print the credits of the game
 * @author Rémi Nicole
 */
public class CreditsCommand extends Command {

	/**
	 * Constructor for CreditsCommand
	 */
	public CreditsCommand(){

	}

	/**
	 * Save the credits into the message field.
	 * @param player The player that called this command
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) {
		setMessage("Temperate rainforest photo (cc-by-nc-nd) : myheimu (http://www.fotopedia.com/wiki/Temperate_rainforest#!/items/flickr-7995237868)\n"
				+ "Taiga photo (public domain) : Becker0804 (https://commons.wikimedia.org/wiki/File:Talkessel_von_Werchojansk.JPG)\n"
				+ "Alpine tundra photo (public domain) : Zewu (https://en.wikipedia.org/wiki/File:Tarfala_Valley_-_Sweden.jpg)\n"
				+ "Steppe photo (cc-by-sa) : Matt Lavin (http://www.fotopedia.com/wiki/Steppe#!/items/flickr-7495949260)\n"
				+ "Lava tube photo : Tim Laman (http://science.nationalgeographic.com/science/photos/caves-gallery/#/lava-tube-cave_1036_600x450.jpg)\n"
				+ "Polar desert photo (cc-by) : Stephen Hudson (https://commons.wikimedia.org/wiki/File:AntarcticaDomeCSnow.jpg)\n"
				+ "Thar desert photo (cc-by-sa) : Gégard JANOT (https://commons.wikimedia.org/wiki/File:D%C3%A9sert_du_Rajasthan.jpg)\n"
				+ "Savanna photo (public domain) : United States Geological Survey (https://commons.wikimedia.org/wiki/File:Oldoinyolengai.jpg)");
		return false;
	}

}
