import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
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
    private final static Color WHITE = new Color(255,255,255);
    private final static Color BACKGROUND_COLOR = new Color(18,18,18,255);
    public static void setCorrectColor(Color correctColor) {
        CORRECT_COLOR = correctColor;
    }
    public static void setWrongPlaceColor(Color wrongPlaceColor) {
        WRONG_PLACE_COLOR = wrongPlaceColor;
    }
    private static void addUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("utils/credentials.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] info = line.split(" ");
            User user = new User(info[0], info[1]);
            users.add(user);
        }
    }
    private static User userLoggedIn;
    private static final ArrayList<User> users = new ArrayList<>();
    public static boolean checkIfUserExists(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public static User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public static boolean usernameAlreadyTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public static void removeDuplicates() {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getUsername().equals(users.get(j).getUsername())) {
                    users.remove(j);
                }
            }
        }
    }

    /*
     * The main method of the game.
     * It creates the launch page and sets the action listeners for the "Start" and "Instructions" buttons.
     * You can also log in to your account from this page.
     * Logging in will allow you to see your statistics and save your progress.
     */
    public static void main(String[] args) throws IOException {
        LaunchPage launchPage = new LaunchPage();
        Main.addUsers();
        Main.removeDuplicates();

        /* The buttons are set accordingly if the user is logged in. */
        if (userLoggedIn != null) {
            launchPage.getFrame().getStartButton().setBounds(200, 700, 125, 50);
            launchPage.getFrame().getInstructionsButton().setBounds(675, 700, 125, 50);
            launchPage.getFrame().getLogInButton().setVisible(false);
            launchPage.getLogOutButton().setVisible(true);
            launchPage.getStatsButton().setVisible(true);
        } else {
            launchPage.getFrame().getStartButton().setBounds(300, 700, 125, 50);
            launchPage.getFrame().getInstructionsButton().setBounds(575, 700, 125, 50);
            launchPage.getFrame().getLogInButton().setVisible(true);
            launchPage.getStatsButton().setVisible(false);
        }

        /* The action listeners for the "Start" button is set. */
        launchPage.getFrame().getStartButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            GamePage gamePage = new GamePage();
            chooseDifficulty(gamePage);
        });

        /* The action listeners for the "Instructions" button is set. */
        launchPage.getFrame().getInstructionsButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            InstructionsPage instructionsPage = new InstructionsPage();
            instructionsPage.getFrame().getBackButton().addActionListener(e1 -> {
                instructionsPage.getFrame().dispose();
                launchPage.getFrame().setVisible(true);
            });
        });

        /* The action listeners for the "Log out" button is set. */
        launchPage.getLogOutButton().addActionListener(e -> {
            userLoggedIn = null;
            launchPage.getLogOutButton().setVisible(false);
            launchPage.getFrame().getLogInButton().setVisible(true);
            launchPage.getStatsButton().setVisible(false);
            launchPage.getFrame().getStartButton().setBounds(300, 700, 125, 50);
            launchPage.getFrame().getInstructionsButton().setBounds(575, 700, 125, 50);
        });

        /* The action listeners for the "Log in" button is set. */
        launchPage.getFrame().getLogInButton().addActionListener(e -> {
            launchPage.getFrame().getStartButton().setVisible(false);
            launchPage.getFrame().getInstructionsButton().setVisible(false);
            launchPage.getFrame().getLogInButton().setVisible(false);
            launchPage.getLabel().setVisible(false);

            /* The log in panel is displayed and the action listeners for the buttons are set. */
            launchPage.displayLogIn();
            launchPage.getGoBackButton().addActionListener(e1 -> {
                launchPage.getLogInPanel().setVisible(false);
                launchPage.getFrame().getStartButton().setVisible(true);
                launchPage.getFrame().getInstructionsButton().setVisible(true);
                launchPage.getFrame().getLogInButton().setVisible(true);
                launchPage.getLabel().setVisible(true);
            });

            launchPage.getLoginButton().addActionListener(e1 -> {
                /*
                 * The username and password are retrieved from the text fields.
                 * A message is displayed if the user doesn't exist.
                 * If the authentication went well, the app returns to the launch page.
                 * The user logged in will be able to see his/her stats.
                 */
                String username = launchPage.getUsernameField().getText();
                String password = new String(launchPage.getPasswordField().getPassword());

                if (!Main.checkIfUserExists(username, password)) {
                    launchPage.getUserNotFound().setVisible(true);
                } else {
                    /* The user is logged in and the buttons are set accordingly. */
                    userLoggedIn = Main.getUser(username, password);
                    launchPage.getLogInPanel().setVisible(false);
                    launchPage.getFrame().getStartButton().setVisible(true);
                    launchPage.getFrame().getInstructionsButton().setVisible(true);
                    launchPage.getLogOutButton().setVisible(true);
                    launchPage.getLabel().setVisible(true);
                    launchPage.getFrame().getStartButton().setBounds(200, 700, 125, 50);
                    launchPage.getFrame().getInstructionsButton().setBounds(675, 700, 125, 50);
                    launchPage.getStatsButton().setVisible(true);
                }
            });

            launchPage.getSignUpButton().addActionListener(e1 -> {
                /*
                 * The sign-up panel is displayed and the action listeners for the buttons are set.
                 * The user can sign up and create an account.
                 */
                launchPage.getLogInPanel().setVisible(false);
                launchPage.displaySignUp();
                launchPage.getSignUpFirstTimeButton().addActionListener(e2 -> {
                    String username = launchPage.getUsernameField().getText();
                    String password = new String(launchPage.getPasswordField().getPassword());
                    String confirmedPassword = new String(launchPage.getConfirmPasswordField().getPassword());

                    if (!(password.equals(confirmedPassword))) {
                        launchPage.getNotCorrectPanel().setVisible(true);
                    } else if (Main.usernameAlreadyTaken(username)) {
                        launchPage.getUsernameAlreadyTaken().setVisible(true);
                    } else {
                        FileWriter fw;
                        try {
                            fw = new FileWriter("utils/credentials.txt", true);
                            fw.write(username + " " + password + "\n");
                            fw.close();

                            User user = new User(username, password);
                            users.add(user);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        launchPage.getSignUpPanel().setVisible(false);
                        launchPage.getLogInPanel().setVisible(true);
                    }
                });

                launchPage.getGoBackButton2().addActionListener(e2 -> {
                    launchPage.getSignUpPanel().setVisible(false);
                    launchPage.getLogInPanel().setVisible(true);
                });
            });
        });

        /* The action listeners for the "Stats" button is set. */
        launchPage.getStatsButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            StatsPage statsPage = new StatsPage(userLoggedIn);
            statsPage.getFrame().getBackButton().addActionListener(e1 -> {
                statsPage.getFrame().dispose();
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
        keyboard.setBackground(BACKGROUND_COLOR);
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
                                try {
                                    Main.main(null);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
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
                                try {
                                    Main.main(null);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
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
