package pkg_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

import java.util.ArrayList;

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
	 * Container for the String of commands left.
	 */
	private JLabel commandsLeftLabel;

	/**
	 * The list of buttons in the user interface.
	 */
	private ArrayList<JButton> buttons;

	/**
	 * UserInterface class constructor.
	 * @param gameEngine  The gameplay GameEngine object.
	 */
	public UserInterface(GameEngine gameEngine) {
		engine = gameEngine;
		buttons = new ArrayList<JButton>();
		createGUI();
	}

	/**
	 * Change the text for the commands left JLabel
	 * @param commandsLeft The number of commands left
	 */
	public void setCommandsLeft(int commandsLeft) {
		commandsLeftLabel.setText(" Commands left: " + commandsLeft + "  ");
	}

	/**
	 * Print the given text in the text area.
	 * @param text Text to display
	 */
	public void print(String text) {
		log.append(text);
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * Print the given text plus a newline in the text area.
	 * @param text Text to display
	 */
	public void println(String text) {
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * Show the image corresponding to the path of the image.
	 * @param imageName The path of the image
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
	 * @param on True to activate, False to deactivate
	 */
	public void enable(boolean on) {
		entryField.setEditable(on);
		entryField.setEnabled(on);
		if(!on)
			entryField.getCaret().setBlinkRate(0);

		for (JButton button : buttons) {
			button.setEnabled(on);
		}

		commandsLeftLabel.setEnabled(on);
	}

	/**
	 * Create the user interface.
	 */
	private void createGUI() {
		myFrame = new JFrame("Bad Philophobia");
		entryField = new JTextField(34);

		log = new JTextArea();
		log.setEditable(false);
		JScrollPane listScroller = new JScrollPane(log);
		listScroller.setPreferredSize(new Dimension(200, 200));
		listScroller.setMinimumSize(new Dimension(100, 100));

		JPanel panel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(200,200));
		image = new JLabel();

		Font buttonsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);

		JButton buttonNorth = new JButton("↑");
		buttons.add(buttonNorth);

		JButton buttonSouth = new JButton("↓");
		buttons.add(buttonSouth);

		JButton buttonEast = new JButton("→");
		buttons.add(buttonEast);

		JButton buttonWest = new JButton("←");
		buttons.add(buttonWest);

		JButton buttonHelp = new JButton("?");
		buttons.add(buttonHelp);

		JButton buttonReturn = new JButton("⏎");
		buttons.add(buttonReturn);

		JButton buttonCharge = new JButton("⚛");
		buttons.add(buttonCharge);

		JButton buttonTeleport = new JButton("⇣");
		buttons.add(buttonTeleport);

		JButton buttonBack = new JButton("↺");
		buttons.add(buttonBack);

		for (JButton button : buttons) {
			button.addActionListener(this);
			button.setFont(buttonsFont);
		}

		buttonsPanel.setLayout(new GridLayout(3,3));
		buttonsPanel.add(buttonBack);
		buttonsPanel.add(buttonNorth);
		buttonsPanel.add(buttonCharge);
		buttonsPanel.add(buttonWest);
		buttonsPanel.add(buttonHelp);
		buttonsPanel.add(buttonEast);
		buttonsPanel.add(buttonReturn);
		buttonsPanel.add(buttonSouth);
		buttonsPanel.add(buttonTeleport);

		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.NORTH);
		panel.add(listScroller, BorderLayout.CENTER);
		panel.add(buttonsPanel, BorderLayout.EAST);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(entryField, BorderLayout.CENTER);
		commandsLeftLabel = new JLabel();
		bottomPanel.add(commandsLeftLabel, BorderLayout.EAST);

		panel.add(bottomPanel, BorderLayout.SOUTH);

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
	 * @param e Event
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("↑")) {
			engine.processCommand("go north");
		} else if(e.getActionCommand().equals("↓")) {
			engine.processCommand("go south");
		} else if(e.getActionCommand().equals("←")) {
			engine.processCommand("go west");
		} else if(e.getActionCommand().equals("→")) {
			engine.processCommand("go east");
		} else if(e.getActionCommand().equals("?")) {
			engine.processCommand("help");
		} else if(e.getActionCommand().equals("⚛")) {
			engine.processCommand("beamer charge");
		} else if(e.getActionCommand().equals("⇣")) {
			engine.processCommand("beamer teleport");
		} else if(e.getActionCommand().equals("↺")) {
			engine.processCommand("back");
		} else {
			processCommand();
		}
	}

	/**
	 * Process the command inside the text entry.
	 */
	private void processCommand() {
		boolean finished = false;
		String input = entryField.getText();
		entryField.setText("");

		engine.processCommand(input);
	}
}
