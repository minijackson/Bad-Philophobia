import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * class TestCommand
 * @author Rémi Nicole
 */
public class TestCommand extends Command {

	/**
	  * Constructor for TestCommand
	  */
	public TestCommand(){

	}

	public boolean execute(Player player) throws NoArgumentException,IllegalArgumentException {
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
					} catch (IllegalArgumentException e) {
						setMessage(getMessage() + "\n" + "Command: " + commandLine + "\n"
								+ e.getMessage());
					} catch(UnauthorizedException e)  {
						setMessage(getMessage() + "\n" + "Command: " + commandLine + "\n"
								+ e.getMessage());
					}
				}
				s.close();
			} catch(FileNotFoundException e) {
				throw new IllegalArgumentException("No such test");
			}
		} else
			throw new NoArgumentException("What do you want to test?");
		return false;
	}

}
