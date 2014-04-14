import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * Class handling the game's user interface.
 * @author Rémi NICOLE
 */
public class UserInterface implements ActionListener {

	/**
	 * Gameplay handler
	 */
	private GameEngine engine;

	/**
	 * JFrame containing the graphics.
	 */
	private JFrame myFrame;

	/**
	 * Field where the user will put its commands.
	 */
	private JTextField entryField;

	/**
	 * Container for the displayed text.
	 */
	private JTextArea log;

	/**
	 * Container for the image of the room.
	 */
	private JLabel image;

	/**
	 * UserInterface class constructor.
	 * @param gameEngine  The gameplay GameEngine object.
	 */
	public UserInterface(GameEngine gameEngine) {
		engine = gameEngine;
		createGUI();
	}

	/**
	 * Print the given text in the text area.
	 * @param text Text to display.
	 */
	public void print(String text) {
		log.append(text);
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * Print the given text plus a newline in the text area.
	 * @param text Text to display.
	 */
	public void println(String text) {
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * Show the image corresponding to the path of the image.
	 * @param imageName The path of the image.
	 */
	public void showImage(String imageName) {
		URL imageURL = this.getClass().getClassLoader().getResource(imageName);
		if(imageURL == null)
			System.out.println("image not found");
		else {
			ImageIcon icon = new ImageIcon(imageURL);
			image.setIcon(icon);
			myFrame.pack();
		}
	}

	/**
	 * Enable or disable the input field.
	 */
	public void enable(boolean on) {
		entryField.setEditable(on);
		if(!on)
			entryField.getCaret().setBlinkRate(0);
	}

	/**
	 * Create the user interface.
	 */
	private void createGUI() {
		myFrame = new JFrame("Zork");
		entryField = new JTextField(34);

		log = new JTextArea();
		log.setEditable(false);
		JScrollPane listScroller = new JScrollPane(log);
		listScroller.setPreferredSize(new Dimension(200, 200));
		listScroller.setMinimumSize(new Dimension(100, 100));

		JPanel panel = new JPanel();
		image = new JLabel();

		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.NORTH);
		panel.add(listScroller, BorderLayout.CENTER);
		panel.add(entryField, BorderLayout.SOUTH);

		myFrame.getContentPane().add(panel, BorderLayout.CENTER);

		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		entryField.addActionListener(this);

		myFrame.pack();
		myFrame.setVisible(true);
		entryField.requestFocus();
	}

	/**
	 * Actionlistener for the textfield.
	 * @param e Event.
	 */
	public void actionPerformed(ActionEvent e) {
		processCommand();
	}

	/**
	 * Process the command inside the text entry.
	 */
	private void processCommand() {
		boolean finished = false;
		String input = entryField.getText();
		entryField.setText("");

		engine.interpretCommand(input);
	}
}
