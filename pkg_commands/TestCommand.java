package pkg_commands;

import pkg_world.Player;
import pkg_parsing.Parser;
import pkg_exceptions.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * class TestCommand used to test series of commands
 * @author Rémi Nicole
 */
public class TestCommand extends Command {

	/**
	  * Constructor for TestCommand
	  */
	public TestCommand(){

	}

	/**
	 * Test series of Commands stored in a .test file and store the messages in the message field.
	 * Each command in the test file must be separated by an end-of-line character
	 * @param player The player that called this command
	 * @throws NoArgumentException When the user typed the command without parameter
	 * @throws IllegalArgumentException When the user typed a name other than any of the .test files in the directory
	 * @return False because it is not the quit command
	 */
	public boolean execute(Player player) throws NoArgumentException,pkg_exceptions.IllegalArgumentException {
		setMessage("");
		if(hasParameter()) {
			try {
				Scanner s = new Scanner(new File(getParameter() + ".test"));
				while (s.hasNextLine()) {

					String commandLine = s.nextLine();
					Command testedCommand = Parser.getCommand(commandLine);

					try {

						testedCommand.execute(player);
						if(testedCommand.hasMessage())
							setMessage(getMessage() + "\n" + "Command: " + commandLine + "\n"
									+ testedCommand.getMessage());

					} catch (NoArgumentException e) {
						setMessage(getMessage() + "\n" + "Command: " + commandLine + "\n"
								+ e.getMessage());
					} catch (pkg_exceptions.IllegalArgumentException e) {
						setMessage(getMessage() + "\n" + "Command: " + commandLine + "\n"
								+ e.getMessage());
					} catch(UnauthorizedException e)  {
						setMessage(getMessage() + "\n" + "Command: " + commandLine + "\n"
								+ e.getMessage());
					}
				}
				s.close();
			} catch(FileNotFoundException e) {
				throw new pkg_exceptions.IllegalArgumentException("No such test");
			}
		} else
			throw new NoArgumentException("What do you want to test?");
		return false;
	}

}
