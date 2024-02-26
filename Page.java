import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface Page {
    /*
     * Interface for the different pages of the game.
     * Contains general methods and attributes for the pages.
     */
    Color WHITE = new Color(255, 255, 255);
    Color BACKGROUND_COLOR = new Color(18,18,18,255);
    Color BLACK = new Color(0, 0, 0);
    Color CORRECT_COLOR = new Color(83,141,78,255);
    Color WRONG_PLACE_COLOR = new Color(181,159,59,255);
    Color CORRECT_COLOR_COLORBLIND = new Color(245,121,58,255);
    Color WRONG_PLACE_COLOR_COLORBLIND = new Color(133,192,249,255);
    String instructionsText = "<html><p>Guess the hidden word in 4, 5 or 6 tries, depending on the</p>" +
            "<p>difficulty you choose.</p>\n" +
            "<p></p>\n" +
            "<p>Each guess must be a 4, 5 or 6 letter word, depending on </p>" +
            "<p>the difficulty. Hit the enter button to submit the guess.</p>\n" +
            "<p></p>\n" +
            "<p>After your submission, the color of the tiles will change as </p>" +
            "<p>in the examples below.</p>\n" +
            "<p>_________________________________________________________________________________________</p>\n" +
            "<p></p>" +
            "<p><strong><u>Examples</u></strong></p></html>";
    static MyLabel getInstructionsLabel() {
        MyLabel instructions = new MyLabel(null, instructionsText, JLabel.CENTER, JLabel.TOP, WHITE,
                new Font(null, Font.PLAIN, 20), 0, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 0, 510, 700);
        instructions.setOpaque(false);
        return instructions;
    }
}

class LaunchPage implements Page {
    /*
     * Class for the launch page of the game.
     * Contains the frame for the launch page.
     * Also contains the interface for the user login.
     */
    private final LaunchFrame frame;
    private final MyLabel label;
    private JPanel logInPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JPanel signUpPanel;
    private JButton signUpFirstTimeButton;
    private JPanel notCorrectPanel;
    public LaunchPage() {
        this.label = new MyLabel(new ImageIcon("utils/images/wordle.jpg"), "Welcome to Wordle!",
                JLabel.CENTER, JLabel.TOP, WHITE, new Font("MV Boli", Font.PLAIN, 50), -75,
                BACKGROUND_COLOR, JLabel.CENTER, JLabel.CENTER, 250, 150, 500, 500);
        this.frame = new LaunchFrame();
        this.frame.add(this.label);
    }

    ////////////////////////////// GETTERS AND SETTERS //////////////////////////////
    LaunchFrame getFrame() {
        return this.frame;
    }
    MyLabel getLabel() {
        return this.label;
    }
    JPanel getLogInPanel() {
        return this.logInPanel;
    }
    JTextField getUsernameField() {
        return this.usernameField;
    }
    JPasswordField getPasswordField() {
        return this.passwordField;
    }
    JButton getLoginButton() {
        return this.loginButton;
    }
    JButton getSignUpButton() {
        return this.signUpButton;
    }
    JButton getSignUpFirstTimeButton() {return this.signUpFirstTimeButton;}
    JPanel getSignUpPanel() {return this.signUpPanel;}
    JPasswordField getConfirmPasswordField() {return this.confirmPasswordField;}
    JPanel getNotCorrectPanel() {return this.notCorrectPanel;}
    ////////////////////////////// GETTERS AND SETTERS //////////////////////////////

    void displayLogIn() {
        /*
         * Method to display the log in panel in the launch page.
         * Contains the title, username and password fields, log in button and sign up button.
         */
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Login");
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        titleLabel.setForeground(BLACK);
        titleLabel.setBackground(WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(200, 5, 120, 65);
        titlePanel.setBackground(WHITE);

        JLabel line = new JLabel();
        line.setText("---------------------------------------------");
        line.setBackground(BLACK);

        JPanel linePanel = new JPanel();
        linePanel.add(line);
        linePanel.setBounds(0,70,500,2);
        linePanel.setBackground(BLACK);

        JLabel username = new JLabel();
        username.setText("Username:");
        username.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(username);
        usernamePanel.setBackground(WHITE);
        usernamePanel.setBounds(20,100,150,50);

        this.usernameField = new JTextField();
        this.usernameField.setBounds(200,100,200,40);
        this.usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel password = new JLabel();
        password.setText("Password:");
        password.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(password);
        passwordPanel.setBackground(WHITE);
        passwordPanel.setBounds(20,180,150,50);

        this.passwordField = new JPasswordField();
        this.passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.passwordField.setBounds(200,180,200,40);

        this.loginButton = new JButton();
        this.loginButton.setText("Login");
        this.loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.loginButton.setBackground(WHITE);
        this.loginButton.setBounds(50, 280, 400, 40);

        JLabel moreText = new JLabel();
        moreText.setText("Not a member?");
        moreText.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JPanel moreTextPanel = new JPanel();
        moreTextPanel.add(moreText);
        moreTextPanel.setBackground(WHITE);
        moreTextPanel.setBounds(125, 350, 150, 25);

        this.signUpButton = new JButton();
        this.signUpButton.setText("Sign up");
        this.signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.signUpButton.setBackground(WHITE);
        this.signUpButton.setBounds(275,350,100,25);

        this.logInPanel = new JPanel();
        this.logInPanel.setBackground(WHITE);
        this.logInPanel.setBounds(250,250,500,500);
        this.logInPanel.setVisible(true);
        this.logInPanel.setLayout(null);
        this.logInPanel.add(titlePanel);
        this.logInPanel.add(linePanel);
        this.logInPanel.add(usernamePanel);
        this.logInPanel.add(this.usernameField);
        this.logInPanel.add(passwordPanel);
        this.logInPanel.add(this.passwordField);
        this.logInPanel.add(this.loginButton);
        this.logInPanel.add(moreTextPanel);
        this.logInPanel.add(this.signUpButton);

        this.frame.add(this.logInPanel);
    }
    void displaySignUp() {
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Sign up");
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        titleLabel.setForeground(BLACK);
        titleLabel.setBackground(WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBounds(175, 5, 150, 65);
        titlePanel.setBackground(WHITE);

        JLabel line = new JLabel();
        line.setText("---------------------------------------------");
        line.setBackground(BLACK);

        JPanel linePanel = new JPanel();
        linePanel.add(line);
        linePanel.setBounds(0,70,500,2);
        linePanel.setBackground(BLACK);

        JLabel username = new JLabel();
        username.setText("Username:");
        username.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(username);
        usernamePanel.setBackground(WHITE);
        usernamePanel.setBounds(20,100,150,50);

        this.usernameField = new JTextField();
        this.usernameField.setBounds(200,100,200,40);
        this.usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel password = new JLabel();
        password.setText("Password:");
        password.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(password);
        passwordPanel.setBackground(WHITE);
        passwordPanel.setBounds(20,180,150,50);

        this.passwordField = new JPasswordField();
        this.passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.passwordField.setBounds(200,180,200,40);

        JLabel confirmPassword = new JLabel();
        confirmPassword.setText("Confirm Password:");
        confirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JPanel confirmPasswordPanel = new JPanel();
        confirmPasswordPanel.add(confirmPassword);
        confirmPasswordPanel.setBackground(WHITE);
        confirmPasswordPanel.setBounds(20,260,250,80);

        this.confirmPasswordField = new JPasswordField();
        this.confirmPasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.confirmPasswordField.setBounds(30,300,200,40);

        this.signUpFirstTimeButton = new JButton();
        this.signUpFirstTimeButton.setText("Sign Up");
        this.signUpFirstTimeButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.signUpFirstTimeButton.setBackground(WHITE);
        this.signUpFirstTimeButton.setBounds(50, 420, 400, 40);

        JLabel notCorrect = new JLabel();
        notCorrect.setText("Password confirmation failed!");
        notCorrect.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        notCorrect.setBackground(WHITE);
        notCorrect.setForeground(Color.RED);

        this.notCorrectPanel = new JPanel();
        this.notCorrectPanel.add(notCorrect);
        this.notCorrectPanel.setBounds(125,370,250,40);
        this.notCorrectPanel.setBackground(WHITE);
        this.notCorrectPanel.setVisible(false);

        this.signUpPanel = new JPanel();
        this.signUpPanel.setBackground(WHITE);
        this.signUpPanel.setBounds(250,250,500,500);
        this.signUpPanel.setVisible(true);
        this.signUpPanel.setLayout(null);
        this.signUpPanel.add(titlePanel);
        this.signUpPanel.add(linePanel);
        this.signUpPanel.add(usernamePanel);
        this.signUpPanel.add(this.usernameField);
        this.signUpPanel.add(passwordPanel);
        this.signUpPanel.add(this.passwordField);
        this.signUpPanel.add(confirmPasswordPanel);
        this.signUpPanel.add(confirmPasswordField);
        this.signUpPanel.add(this.signUpFirstTimeButton);
        this.signUpPanel.add(notCorrectPanel);

        this.frame.add(this.signUpPanel);
    }
}

class InstructionsPage implements Page {
    /*
     * Class for the instructions page of the game.
     * Contains the frame for the instructions page.
     * Also adds the instructions text, title and examples to the frame.
     */
    private final InstructionsFrame frame;
    public InstructionsPage() {
        MyLabel instructions = Page.getInstructionsLabel();

        String title = "<html><b><u>HOW TO PLAY</u></b></html>";
        MyLabel title1 = new MyLabel(null, title, JLabel.CENTER, JLabel.TOP, WHITE, new Font(null,
                Font.PLAIN, 40), 0, BACKGROUND_COLOR, JLabel.CENTER, JLabel.CENTER, 0,
                0, 500, 500);

        ImageIcon correctExample = new ImageIcon("utils/images/correct.png");
        Image correctImage = correctExample.getImage();
        Image newCorrectImage = correctImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        correctExample = new ImageIcon(newCorrectImage);

        ImageIcon incorrectExample = new ImageIcon("utils/images/incorrect.png");
        Image incorrectImage = incorrectExample.getImage();
        Image newIncorrectImage = incorrectImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        incorrectExample = new ImageIcon(newIncorrectImage);

        ImageIcon wrongPlaceExample = new ImageIcon("utils/images/wrong place.png");
        Image wrongPlaceImage = wrongPlaceExample.getImage();
        Image newWrongPlaceImage = wrongPlaceImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        wrongPlaceExample = new ImageIcon(newWrongPlaceImage);

        String correctExampleText = "<html><p>The letter <strong>M</strong> is in the word and in the correct spot.</p></html>";
        MyLabel correct = new MyLabel(correctExample, correctExampleText, JLabel.CENTER, JLabel.BOTTOM, WHITE,
                new Font(null, Font.PLAIN, 20), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 290, 500, 500);
        correct.setOpaque(false);

        String wrongPlaceExampleText = "<html><p>The letter <strong>M</strong> is in the word but in the wrong spot.</p></html>";
        MyLabel wrongPlace = new MyLabel(wrongPlaceExample, wrongPlaceExampleText, JLabel.CENTER, JLabel.BOTTOM,
                WHITE, new Font(null, Font.PLAIN, 20), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 400, 500, 500);
        wrongPlace.setOpaque(false);

        String incorrectExampleText = "<html><p>The letters <strong>N</strong> and <strong>E</strong> are not in the word in any spot.</p></html>";
        MyLabel incorrect = new MyLabel(incorrectExample, incorrectExampleText, JLabel.CENTER, JLabel.BOTTOM, WHITE,
                new Font(null, Font.PLAIN, 20), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 510, 500, 500);
        incorrect.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBounds(150,130,700,100);
        panel.setOpaque(false);
        panel.add(title1);

        this.frame = new InstructionsFrame();
        this.frame.add(panel);
        this.frame.add(instructions);
        this.frame.add(correct);
        this.frame.add(wrongPlace);
        this.frame.add(incorrect);
    }
    InstructionsFrame getFrame() {
        return this.frame;
    }
}

class GamePage implements Page, ActionListener {
    /*
     * Class for the game page of the game.
     * Contains the frame for the game page.
     * Also contains the settings and instructions buttons, the keyboard and word board.
     */
    private final GameFrame frame;
    private final JPanel line;
    private final JLabel titleText;
    private final MyButton settingsButton;
    private final MyButton instructionsButton;
    private KeyboardPanel keyboard;
    private WordboardPanel wordBoard;
    private Game game;
    private final JRadioButton colorBlindMode;
    private boolean colorBlindModeEnabled;

    ////////////////////////////// GETTERS AND SETTERS //////////////////////////////
    public void setKeyboard(KeyboardPanel keyboard) {
        this.keyboard = keyboard;
    }
    public void setWordBoard(WordboardPanel wordBoard) {
        this.wordBoard = wordBoard;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    GameFrame getFrame() {
        return this.frame;
    }
    JPanel getLine() {
        return this.line;
    }
    JLabel getTitleText() {
        return this.titleText;
    }
    MyButton getSettingsButton() {
        return this.settingsButton;
    }
    MyButton getInstructionsButton() {
        return this.instructionsButton;
    }
    public void setColorBlindModeEnabled(boolean colorBlindModeEnabled) {
        this.colorBlindModeEnabled = colorBlindModeEnabled;
    }
    ////////////////////////////// GETTERS AND SETTERS //////////////////////////////

    public GamePage() {
        /*
         * Constructor for the game page.
         * Initializes the frame, line, title text, settings button, instructions button, keyboard and word board.
         * Also adds all the components to the frame.
         */
        this.colorBlindMode = new JRadioButton("Enable Color Blind Mode");

        this.colorBlindModeEnabled = false;

        line = new JPanel();
        line.setVisible(true);
        line.setBounds(0,125,1000,2);
        line.setBackground(WHITE);

        titleText = new JLabel();
        titleText.setText("Wordle");
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        titleText.setForeground(WHITE);
        titleText.setBounds(350, 20, 300, 100);
        titleText.setBackground(BACKGROUND_COLOR);
        titleText.setOpaque(true);

        ImageIcon settingsIcon = new ImageIcon("utils/images/settings.png");
        settingsButton = new MyButton("", 900, 80, 30, 30,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);
        settingsButton.setName("Settings");
        settingsButton.setIcon(settingsIcon);
        settingsButton.setOpaque(false);
        settingsButton.setVisible(false);

        ImageIcon instructionsIcon = new ImageIcon("utils/images/instructions.png");
        instructionsButton = new MyButton("", 940, 80, 30, 30,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);
        instructionsButton.setName("Instructions");
        instructionsButton.setIcon(instructionsIcon);
        instructionsButton.setOpaque(false);
        instructionsButton.setVisible(false);

        this.frame = new GameFrame();
        this.frame.add(titleText);
        this.frame.add(line);
        this.frame.add(settingsButton);
        this.frame.add(instructionsButton);

    }

    public ArrayList<Component> displayInstructionsInGame() {
        /*
         * Method to display the instructions in the game page.
         * Contains the instructions text, title, examples and a back button.
         * Returns an ArrayList of the components added to the frame.
         */
        MyLabel instructions = Page.getInstructionsLabel();

        String title = "<html><b><u>HOW TO PLAY</u></b></html>";
        MyLabel title1 = new MyLabel(null, title, JLabel.CENTER, JLabel.TOP, WHITE, new Font(null,
                Font.PLAIN, 40), 0, BACKGROUND_COLOR, JLabel.CENTER, JLabel.CENTER, 0,
                0, 500, 500);

        ImageIcon correctExample = new ImageIcon("utils/images/correct.png");
        Image correctImage = correctExample.getImage();
        Image newCorrectImage = correctImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        correctExample = new ImageIcon(newCorrectImage);

        ImageIcon incorrectExample = new ImageIcon("utils/images/incorrect.png");
        Image incorrectImage = incorrectExample.getImage();
        Image newIncorrectImage = incorrectImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        incorrectExample = new ImageIcon(newIncorrectImage);

        ImageIcon wrongPlaceExample = new ImageIcon("utils/images/wrong place.png");
        Image wrongPlaceImage = wrongPlaceExample.getImage();
        Image newWrongPlaceImage = wrongPlaceImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        wrongPlaceExample = new ImageIcon(newWrongPlaceImage);

        String correctExampleText = "<html><p>The letter <strong>M</strong> is in the word and in the correct spot.</p></html>";
        MyLabel correct = new MyLabel(correctExample, correctExampleText, JLabel.CENTER, JLabel.BOTTOM, WHITE,
                new Font(null, Font.PLAIN, 20), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 290, 500, 500);
        correct.setOpaque(false);

        String wrongPlaceExampleText = "<html><p>The letter <strong>M</strong> is in the word but in the wrong spot.</p></html>";
        MyLabel wrongPlace = new MyLabel(wrongPlaceExample, wrongPlaceExampleText, JLabel.CENTER, JLabel.BOTTOM, WHITE,
                new Font(null, Font.PLAIN, 20), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 400, 500, 500);
        wrongPlace.setOpaque(false);

        String incorrectExampleText = "<html><p>The letters <strong>N</strong> and <strong>E</strong> are not in the word in any spot.</p></html>";
        MyLabel incorrect = new MyLabel(incorrectExample, incorrectExampleText, JLabel.CENTER, JLabel.BOTTOM, WHITE,
                new Font(null, Font.PLAIN, 20), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 245, 510, 500, 500);
        incorrect.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBounds(150,130,700,100);
        panel.setOpaque(false);
        panel.add(title1);

        MyButton backButton = new MyButton("Go back", 0, 0, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);

        this.frame.getContentPane().setBackground(BACKGROUND_COLOR);

        ArrayList<Component> components = new ArrayList<>();
        components.add(backButton);
        components.add(panel);
        components.add(instructions);
        components.add(correct);
        components.add(wrongPlace);
        components.add(incorrect);

        for (Component component : components) {
            this.frame.add(component);
        }

        return components;
    }

    public ArrayList<Component> displaySettings() {
        /*
         * Method to display the settings in the game page.
         * Contains the dark mode and color-blind mode buttons and a back button.
         * Returns an ArrayList of the components added to the frame.
         */
        JPanel line = new JPanel();
        line.setVisible(true);
        line.setBounds(0,125,1000,2);
        line.setBackground(Color.WHITE);

        JLabel titleText = new JLabel();
        titleText.setText("Settings");
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        titleText.setForeground(Color.WHITE);
        titleText.setBounds(337, 20, 325, 100);
        titleText.setBackground(BACKGROUND_COLOR);
        titleText.setOpaque(true);

        MyButton backButton = new MyButton("Go back", 0, 70, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);

        this.colorBlindMode.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.colorBlindMode.setBackground(BACKGROUND_COLOR);
        this.colorBlindMode.setForeground(WHITE);
        this.colorBlindMode.setBounds(300, 150, 350, 20);
        this.colorBlindMode.addActionListener(this);

        ArrayList<Component> components = new ArrayList<>();
        components.add(backButton);
        components.add(line);
        components.add(titleText);
        components.add(this.colorBlindMode);

        for (Component component : components) {
            this.frame.add(component);
        }

        return components;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * Method to handle the action event for the color-blind mode button.
         * Changes the background color of the frame and the colors of the components based on the selected mode.
         */
        if (e.getSource() == this.colorBlindMode) {
            /* Color-blind mode will change the colors of the components to be more color-blind friendly. */
            setColorBlindModeEnabled(this.colorBlindMode.isSelected());
            int wordLength = this.game.getWordLength();
            if (this.colorBlindModeEnabled) {
                /*
                 * When color-blind mode is enabled, all green tiles and key panels will turn orange
                 * and all yellow tiles and key panels will turn light blue.
                 */
                Main.setCorrectColor(CORRECT_COLOR_COLORBLIND);
                Main.setWrongPlaceColor(WRONG_PLACE_COLOR_COLORBLIND);
                for (int i = 0; i < wordLength; i++) {
                    for (int j = 0; j < wordLength; j++) {
                        if (wordBoard.getPanels()[i][j].getBackground().equals(CORRECT_COLOR)) {
                            wordBoard.getPanels()[i][j].setBackground(CORRECT_COLOR_COLORBLIND);
                            wordBoard.getLabels()[i][j].setBackground(CORRECT_COLOR_COLORBLIND);
                            Border border = BorderFactory.createLineBorder(CORRECT_COLOR_COLORBLIND, 2);
                            wordBoard.getPanels()[i][j].setBorder(border);
                        } else if (wordBoard.getPanels()[i][j].getBackground().equals(WRONG_PLACE_COLOR)) {
                            wordBoard.getPanels()[i][j].setBackground(WRONG_PLACE_COLOR_COLORBLIND);
                            wordBoard.getLabels()[i][j].setBackground(WRONG_PLACE_COLOR_COLORBLIND);
                            Border border = BorderFactory.createLineBorder(WRONG_PLACE_COLOR_COLORBLIND, 2);
                            wordBoard.getPanels()[i][j].setBorder(border);
                        }
                    }
                }
                for (JPanel panel : keyboard.getPanels()) {
                    if (panel.getBackground().equals(CORRECT_COLOR)) {
                        panel.setBackground(CORRECT_COLOR_COLORBLIND);
                    } else if (panel.getBackground().equals(WRONG_PLACE_COLOR)) {
                        panel.setBackground(WRONG_PLACE_COLOR_COLORBLIND);
                    }
                }
            } else {
                /*
                 * When color-blind mode is disabled, all orange tiles and key panels will turn green
                 * and all light blue tiles and key panels will turn yellow.
                 */
                Main.setCorrectColor(CORRECT_COLOR);
                Main.setWrongPlaceColor(WRONG_PLACE_COLOR);
                for (int i = 0; i < wordLength; i++) {
                    for (int j = 0; j < wordLength; j++) {
                        if (wordBoard.getPanels()[i][j].getBackground().equals(CORRECT_COLOR_COLORBLIND)) {
                            wordBoard.getPanels()[i][j].setBackground(CORRECT_COLOR);
                            wordBoard.getLabels()[i][j].setBackground(CORRECT_COLOR);
                            Border border = BorderFactory.createLineBorder(CORRECT_COLOR, 2);
                            wordBoard.getPanels()[i][j].setBorder(border);
                        } else if (wordBoard.getPanels()[i][j].getBackground().equals(WRONG_PLACE_COLOR_COLORBLIND)) {
                            wordBoard.getPanels()[i][j].setBackground(WRONG_PLACE_COLOR);
                            wordBoard.getLabels()[i][j].setBackground(WRONG_PLACE_COLOR);
                            Border border = BorderFactory.createLineBorder(WRONG_PLACE_COLOR, 2);
                            wordBoard.getPanels()[i][j].setBorder(border);
                        }
                    }
                }
                for (JPanel panel : keyboard.getPanels()) {
                    if (panel.getBackground().equals(CORRECT_COLOR_COLORBLIND)) {
                        panel.setBackground(CORRECT_COLOR);
                    } else if (panel.getBackground().equals(WRONG_PLACE_COLOR_COLORBLIND)) {
                        panel.setBackground(WRONG_PLACE_COLOR);
                    }
                }
            }
        }
    }

    public void setButton(GamePage gamePage, MyButton button, WordboardPanel wordBoard, KeyboardPanel keyboard) {
        /*
         * Method to set the action listener for the settings and instructions buttons.
         * When the buttons are clicked, the game page will be hidden and the settings or instructions will be displayed.
         * Also adds a back button to the settings and instructions pages to go back to the game page.
         */
        button.setVisible(true);
        button.addActionListener(e -> {
            gamePage.getLine().setVisible(false);
            gamePage.getTitleText().setVisible(false);
            gamePage.getSettingsButton().setVisible(false);
            gamePage.getInstructionsButton().setVisible(false);
            wordBoard.setVisible(false);
            keyboard.setVisible(false);

            ArrayList<Component> components;
            if (button.getName().equals("Settings")) {
                components = gamePage.displaySettings();
            } else if (button.getName().equals("Instructions")) {
                components = gamePage.displayInstructionsInGame();
            } else {
                components = null;
            }

            MyButton backButton = null;
            assert components != null;
            for (Component component : components) {
                if (component instanceof MyButton) {
                    backButton = (MyButton) component;
                }
            }

            assert backButton != null;
            backButton.addActionListener(e1 -> {
                gamePage.getLine().setVisible(true);
                gamePage.getTitleText().setVisible(true);
                gamePage.getSettingsButton().setVisible(true);
                gamePage.getInstructionsButton().setVisible(true);
                wordBoard.setVisible(true);
                keyboard.setVisible(true);
                for (Component component : components) {
                    gamePage.getFrame().remove(component);
                }
                gamePage.getFrame().revalidate();
                gamePage.getFrame().repaint();
            });
        });
    }
}
