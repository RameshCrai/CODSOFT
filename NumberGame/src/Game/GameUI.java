package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GameUI extends JFrame {
	private static final int MAX_ATTEMPTS = 5;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 100;

	private JButton guessButton, playAgainButton;
	private JLabel leftText, bottomText, topText, attemptText, resultText, instruction1, instruction2, instruction3,
			welcomeText, project1;
	private JTextField guessNumber;
	private JPanel buttonPanel, mainPanel;

	private int attempts;
	private int numberToGuess;
	private GenerateNumberImpl generateNumber;
	
	
	int top = 5;
    int left = 5;
    int bottom = 5;
    int right = 5;

	public GameUI() {
		setTitle("Number Guessing Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 600, 450);
		setLayout(new BorderLayout());

		// Initialize components
		guessNumber = new JTextField(20);
		leftText = new JLabel("Enter a number:");
		guessButton = new JButton("Guess");
		playAgainButton = new JButton("Play Again");
		attemptText = new JLabel("Total Attempts: 0");
		resultText = new JLabel("Result:");
		welcomeText = new JLabel("Welcome To Number Guess Game!", JLabel.CENTER);
		topText = new JLabel("Instructions For Game:");
		instruction1 = new JLabel("1) Guess a number from 1 to 100");
		instruction2 = new JLabel("2) Enter the guessed number:");
		instruction3 = new JLabel("3) If not matched, try up to 5 times:");
		bottomText = new JLabel("Powered By: Ramesh Chamling Rai", JLabel.CENTER);
		project1 = new JLabel("Internship Project One - #CODESOFT");

		welcomeText.setBackground(Color.GRAY);
		welcomeText.setOpaque(true);
		welcomeText.setForeground(Color.BLUE);
		welcomeText.setBorder(new EmptyBorder(top, left, bottom, right));

		bottomText.setBackground(Color.GRAY);
		bottomText.setOpaque(true);
		bottomText.setForeground(Color.BLUE);
		bottomText.setBorder(new EmptyBorder(top, left, bottom, right));

		project1.setForeground(Color.BLUE);

		// Setup layout for the main panel using GridBagLayout
		mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(project1, gbc);

		gbc.gridx++;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		mainPanel.add(topText, gbc);

		gbc.gridy++;
		mainPanel.add(instruction1, gbc);

		gbc.gridy++;
		mainPanel.add(instruction2, gbc);

		gbc.gridy++;
		mainPanel.add(instruction3, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		mainPanel.add(leftText, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		mainPanel.add(guessNumber, gbc);

		// Button panel
		buttonPanel = new JPanel();
		buttonPanel.add(guessButton);
		buttonPanel.add(playAgainButton);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(buttonPanel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		mainPanel.add(attemptText, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		mainPanel.add(resultText, gbc);

		// Guess Game
		guessButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleGuess();
			}
		});

		// Play again
		playAgainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		});

		// Add panels to the frame
		add(welcomeText, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomText, BorderLayout.SOUTH);

		generateNumber = new GenerateNumberImpl(MIN_NUMBER, MAX_NUMBER);

		resetGame();
	}

	private void handleGuess() {
		try {
//			guess number string to int
			int userGuess = Integer.parseInt(guessNumber.getText());
//			count attempts
			attempts++;
			System.out.println("number to guess " + numberToGuess);
			if (userGuess < MIN_NUMBER || userGuess > MAX_NUMBER) {
				resultText.setText("Please enter a number between 1 and 100.");
			} else if (userGuess < numberToGuess) {
				resultText.setText("Too low! Try again.");
			} else if (userGuess > numberToGuess) {
				resultText.setText("Too high! Try again.");
			} else {
				resultText.setText("Congratulations! You've guessed the number.");
				guessButton.setEnabled(false);
			}

			attemptText.setText("Total Attempts: " + attempts);

			if (attempts >= MAX_ATTEMPTS && !resultText.getText().contains("Congratulations")) {
				resultText
						.setText("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
				guessButton.setEnabled(false);
			}

		} catch (NumberFormatException ex) {
			resultText.setText("Please enter a valid number.");
		}
	}

	private void resetGame() {
		numberToGuess = generateNumber.generateRandom();
		attempts = 0;
		guessNumber.setText("");
		attemptText.setText("Total Attempts: 0");
		resultText.setText("Result:");
		guessButton.setEnabled(true);
	}

}
