import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /*
     * The colors of the tiles in the game.
     * CORRECT_COLOR: The color of the tiles that are in the correct position and in the word.
     * INCORRECT_COLOR: The color of the tiles that are not in the word.
     * WRONG_PLACE_COLOR: The color of the tiles that are in the word but not in the correct position.
     */
    private static Color CORRECT_COLOR = new Color(83,141,78,255);
    private final static Color INCORRECT_COLOR = new Color(58,58,60,255);
    private static Color WRONG_PLACE_COLOR = new Color(181,159,59,255);
    public static void setCorrectColor(Color correctColor) {
        CORRECT_COLOR = correctColor;
    }
    public static void setWrongPlaceColor(Color wrongPlaceColor) {
        WRONG_PLACE_COLOR = wrongPlaceColor;
    }

    /*
     * The main method of the game.
     * It creates the launch page and sets the action listeners for the "Start" and "Instructions" buttons.
     */
    public static void main(String[] args) {
        LaunchPage launchPage = new LaunchPage();

        launchPage.getFrame().getStartButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            GamePage gamePage = new GamePage();
            chooseDifficulty(gamePage);

        });
        launchPage.getFrame().getInstructionsButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            InstructionsPage instructionsPage = new InstructionsPage();
            instructionsPage.getFrame().getBackButton().addActionListener(e1 -> {
                instructionsPage.getFrame().dispose();
                launchPage.getFrame().setVisible(true);
            });
        });
    }

    /*
     * The method that chooses the difficulty of the game.
     * It starts the game with the corresponding difficulty parameter.
     */
    public static void chooseDifficulty(GamePage gamePage) {
        gamePage.getFrame().getEasyButton().addActionListener(e1 -> {
            try {
                startGame(gamePage, "easy");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        gamePage.getFrame().getMediumButton().addActionListener(e1 -> {
            try {
                startGame(gamePage, "medium");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        gamePage.getFrame().getHardButton().addActionListener(e1 -> {
            try {
                startGame(gamePage, "hard");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /*
     * The method that starts the game.
     * It sets the keyboard, word board, settings button, and instructions button.
     * It also sets the action listeners for the buttons in the keyboard.
     */
    public static void startGame(GamePage gamePage, String difficulty) throws IOException {
        /* The keyboard panel is created and added to the game page. */
        KeyboardPanel keyboard = new KeyboardPanel();
        keyboard.setBackground(new Color(18,18,18,255));
        keyboard.setBounds(260, 700, 480, 210);
        keyboard.setVisible(true);
        gamePage.setKeyboard(keyboard);

        /* The word board panel is created and added to the game page. */
        int wordLength = 0;
        switch (difficulty) {
            case "easy" -> wordLength = 4;
            case "medium" -> wordLength = 5;
            case "hard" -> wordLength = 6;
        }
        WordboardPanel wordBoard = new WordboardPanel(wordLength);
        wordBoard.setBackground(new Color(18,18,18,255));
        wordBoard.setBounds(300, 200, 400, 400);
        wordBoard.setVisible(true);
        gamePage.setWordBoard(wordBoard);

        /* The settings button is created and added to the game page. */
        MyButton settingsButton = gamePage.getSettingsButton();
        gamePage.setButton(gamePage, settingsButton, wordBoard, keyboard);

        /* The instructions button is created and added to the game page. */
        MyButton instructionsButton = gamePage.getInstructionsButton();
        gamePage.setButton(gamePage, instructionsButton, wordBoard, keyboard);

        /* The game page is updated with the new panels and buttons. */
        gamePage.getFrame().getContentPane().removeAll();
        gamePage.getFrame().add(keyboard);
        gamePage.getFrame().add(wordBoard);
        gamePage.getFrame().add(gamePage.getLine());
        gamePage.getFrame().add(gamePage.getTitleText());
        gamePage.getFrame().add(settingsButton);
        gamePage.getFrame().add(instructionsButton);
        gamePage.getFrame().revalidate();
        gamePage.getFrame().repaint();

        /*
         * The game is started with the chosen difficulty.
         * The reference word is chosen and an array with its letters is generated.
         */
        Game game = new Game(difficulty);
        gamePage.setGame(game);
        String referenceWord = game.chooseReferenceWord();
        System.out.println(referenceWord);
        ReferenceWord myWord = new ReferenceWord(referenceWord);
        ArrayList<String> referenceWordLetters = myWord.getLetters();

        /* Parameters for the action listeners of the buttons in the keyboard. */
        final String[] character = new String[1];
        final int[] rowCount = {0};
        final int[] columnCount = {0};
        final int[] pressedEnterCount = {0};
        final boolean[] gameWon = {false};
        final JButton[] lastButtonClicked = {null};
        final boolean[] flag = {false};
        int finalWordLength = wordLength;

        /* The action listeners for the buttons in the keyboard are set. */
        ArrayList<JButton> buttons = keyboard.getButtons();
        for (JButton button : buttons) {
            button.addActionListener(e -> {
                /* The action listener for the "Enter" button. */
                if (button.getText().equals("Enter")) {
                    /* If the row is not full, a warning message is shown. */
                    if (!(columnCount[0] == finalWordLength)) {
                        flag[0] = true;
                        JOptionPane.showMessageDialog(null, "Not enough letters!", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        pressedEnterCount[0]++;
                        int correctLetterCount = 0;

                        /* The letters in the row are checked and the tiles are colored accordingly. */
                        for (int i = 0; i < finalWordLength; i++) {
                            ArrayList<JPanel> keyboardPanels = keyboard.getPanels();
                            if (wordBoard.getLabels()[rowCount[0]][i].getText().equals(referenceWordLetters.get(i))) {
                                /* The tile and the keyboard letter are colored green if the letter is in the correct position and in the word. */
                                wordBoard.getLabels()[rowCount[0]][i].setBackground(CORRECT_COLOR);
                                wordBoard.getPanels()[rowCount[0]][i].setBackground(CORRECT_COLOR);
                                Border border = BorderFactory.createLineBorder(CORRECT_COLOR, 2);
                                wordBoard.getPanels()[rowCount[0]][i].setBorder(border);
                                /* The correct letter count is incremented. */
                                correctLetterCount++;
                                JPanel panel = keyboard.findPanel(keyboardPanels, wordBoard.getLabels()[rowCount[0]][i].getText());
                                panel.setBackground(CORRECT_COLOR);
                            } else if (referenceWordLetters.contains(wordBoard.getLabels()[rowCount[0]][i].getText())) {
                                /* The tile and the keyboard letter are colored yellow if the letter is in the word but not in the correct position. */
                                wordBoard.getLabels()[rowCount[0]][i].setBackground(WRONG_PLACE_COLOR);
                                wordBoard.getPanels()[rowCount[0]][i].setBackground(WRONG_PLACE_COLOR);
                                Border border = BorderFactory.createLineBorder(WRONG_PLACE_COLOR, 2);
                                wordBoard.getPanels()[rowCount[0]][i].setBorder(border);
                                JPanel panel = keyboard.findPanel(keyboardPanels, wordBoard.getLabels()[rowCount[0]][i].getText());
                                panel.setBackground(WRONG_PLACE_COLOR);
                            } else {
                                /* The tile and the keyboard letter are colored dark gray if the letter is not in the word. */
                                wordBoard.getLabels()[rowCount[0]][i].setBackground(INCORRECT_COLOR);
                                wordBoard.getPanels()[rowCount[0]][i].setBackground(INCORRECT_COLOR);
                                Border border = BorderFactory.createLineBorder(INCORRECT_COLOR, 2);
                                wordBoard.getPanels()[rowCount[0]][i].setBorder(border);
                                JPanel panel = keyboard.findPanel(keyboardPanels, wordBoard.getLabels()[rowCount[0]][i].getText());
                                panel.setBackground(INCORRECT_COLOR);
                            }
                        }

                        /* The game is won if the correct letter count is equal to the length of the word. */
                        if (correctLetterCount == finalWordLength) {
                            gameWon[0] = true;
                        }

                        if (gameWon[0]) {
                            /* If the game is won, a message is displayed and the player can either start again or go to the main menu. */
                            String[] options = {"Play again", "Exit"};
                            ImageIcon icon = new ImageIcon("utils/images/happy.png");
                            Image image = icon.getImage();
                            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icon = new ImageIcon(newImage);
                            int answer = JOptionPane.showOptionDialog(null, "You won!", "Congratulations",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, 0);
                            gamePage.getFrame().dispose();
                            if (answer == 0) {
                                GamePage newGamePage = new GamePage();
                                chooseDifficulty(newGamePage);
                            } else {
                                Main.main(null);
                            }
                        }

                        /* The game is lost when the player runs out of guesses. */
                        if (pressedEnterCount[0] == finalWordLength && !gameWon[0]) {
                            /* When the game is lost, a message is displayed and the player can either start again or go to the main menu. */
                            String[] options = {"Play again", "Exit"};
                            ImageIcon icon = new ImageIcon("utils/images/sad.png");
                            Image image = icon.getImage();
                            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icon = new ImageIcon(newImage);
                            int answer = JOptionPane.showOptionDialog(null, "You ran out of guesses! You lost!\n" +
                                            "The correct word was " + referenceWord, "Sorry",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, 0);
                            gamePage.getFrame().dispose();
                            if (answer == 0) {
                                GamePage newGamePage = new GamePage();
                                chooseDifficulty(newGamePage);
                            } else {
                                Main.main(null);
                            }
                        }
                    }
                    lastButtonClicked[0] = button;

                /* The action listener for the "Delete" button. */
                } else if (button.getText().equals("Delete")) {
                    boolean deletedOnce = false;
                    /*
                     * You can only delete if the row wasn't checked with the "Enter" button.
                     * The row needs to have letters in it.
                     * The column count is decremented and the tile's text is deleted.
                     */
                    if (lastButtonClicked[0] != null && !lastButtonClicked[0].getText().equals("Enter") && columnCount[0] != 0) {
                        wordBoard.getLabels()[rowCount[0]][columnCount[0] - 1].setText("");
                        columnCount[0]--;
                        deletedOnce = true;
                        lastButtonClicked[0] = button;
                    }

                    /*
                     * If the last button clicked was "Enter" and the deletion in the previous if statement hasn't happened
                     * (because pressing "Enter" usually means moving on a new row, so there would be no letters to delete),
                     * the letter is now deleted from the word board.
                     */
                    if (flag[0] && !deletedOnce) {
                        wordBoard.getLabels()[rowCount[0]][columnCount[0] - 1].setText("");
                        columnCount[0]--;
                        flag[0] = false;
                        lastButtonClicked[0] = button;
                    }

                /* The action listener for the other buttons in the keyboard. */
                } else {
                    boolean isFull = false;
                    /* If the row is full, the last button pressed needs to be checked. */
                    if (columnCount[0] == finalWordLength) {
                        /* If the last button pressed was "Enter" or "Delete", the column count is reset and the row count is incremented. */
                        if (lastButtonClicked[0].getText().equals("Enter") || lastButtonClicked[0].getText().equals("Delete")) {
                            columnCount[0] = 0;
                            rowCount[0]++;
                        /* If the last button pressed is another letter, this means that the row is full and no other letters can be added. */
                        } else {
                            isFull = true;
                        }
                    }

                    /* The letter of the button is added to the word board. */
                    if (!isFull) {
                        character[0] = button.getText();
                        wordBoard.getLabels()[rowCount[0]][columnCount[0]].setText(String.valueOf(character[0]));
                        columnCount[0]++;
                        lastButtonClicked[0] = button;
                    }
                }
            });
        }
    }
}
