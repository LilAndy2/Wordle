import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static final Color CORRECT_COLOR = new Color(83,141,78,255);
    private static final Color INCORRECT_COLOR = new Color(58,58,60,255);
    private static final Color WRONG_PLACE_COLOR = new Color(181,159,59,255);
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

    public static void startGame(GamePage gamePage, String difficulty) throws IOException {
        KeyboardPanel keyboard = new KeyboardPanel();
        keyboard.setBackground(new Color(18,18,18,255));
        keyboard.setBounds(260, 700, 480, 210);
        keyboard.setVisible(true);

        MyButton settingsButton = gamePage.getSettingsButton();
        settingsButton.setVisible(true);
        MyButton instructionsButton = gamePage.getInstructionsButton();
        instructionsButton.setVisible(true);

        gamePage.getFrame().getContentPane().removeAll();
        gamePage.getFrame().add(keyboard);
        gamePage.getFrame().add(gamePage.getLine());
        gamePage.getFrame().add(gamePage.getTitleText());
        gamePage.getFrame().add(settingsButton);
        gamePage.getFrame().add(instructionsButton);
        gamePage.getFrame().revalidate();
        gamePage.getFrame().repaint();

        int wordLength = 0;

        switch (difficulty) {
            case "easy" -> wordLength = 4;
            case "medium" -> wordLength = 5;
            case "hard" -> wordLength = 6;
        }

        WordboardPanel wordboard = new WordboardPanel(wordLength);
        wordboard.setBackground(new Color(18,18,18,255));
        wordboard.setBounds(300, 200, 400, 400);
        wordboard.setVisible(true);

        gamePage.getFrame().add(wordboard);
        gamePage.getFrame().revalidate();
        gamePage.getFrame().repaint();

        Game game = new Game(difficulty);
        String referenceWord = game.chooseReferenceWord();
        System.out.println(referenceWord);
        ReferenceWord myWord = new ReferenceWord(referenceWord);
        ArrayList<String> referenceWordLetters = myWord.getLetters();

        instructionsButton.addActionListener(e -> {
            gamePage.getLine().setVisible(false);
            gamePage.getTitleText().setVisible(false);
            gamePage.getSettingsButton().setVisible(false);
            gamePage.getInstructionsButton().setVisible(false);
            wordboard.setVisible(false);
            keyboard.setVisible(false);
            ArrayList<Component> components = gamePage.displayInstructionsInGame();
            MyButton backButton = null;
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
                wordboard.setVisible(true);
                keyboard.setVisible(true);
                for (Component component : components) {
                    gamePage.getFrame().remove(component);
                }
                gamePage.getFrame().revalidate();
                gamePage.getFrame().repaint();
            });
        });

        settingsButton.addActionListener(e -> {
            gamePage.getLine().setVisible(false);
            gamePage.getTitleText().setVisible(false);
            gamePage.getSettingsButton().setVisible(false);
            gamePage.getInstructionsButton().setVisible(false);
            wordboard.setVisible(false);
            keyboard.setVisible(false);
            ArrayList<Component> components = gamePage.displaySettings();
        });

        final String[] character = new String[1];
        final int[] rowCount = {0};
        final int[] columnCount = {0};
        final int[] pressedEnterCount = {0};
        final boolean[] gameWon = {false};
        final JButton[] lastButtonClicked = {null};
        final boolean[] flag = {false};

        ArrayList<JButton> buttons = keyboard.getButtons();
        for (JButton button : buttons) {
            int finalWordLength = wordLength;

            button.addActionListener(e -> {
                if (button.getText().equals("Enter")) {
                    if (!(columnCount[0] == finalWordLength)) {
                        flag[0] = true;
                        JOptionPane.showMessageDialog(null, "Not enough letters!", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        pressedEnterCount[0]++;
                        int correctLetterCount = 0;

                        for (int i = 0; i < finalWordLength; i++) {
                            ArrayList<JPanel> keyboardPanels = keyboard.getPanels();
                            if (wordboard.getLabels()[rowCount[0]][i].getText().equals(referenceWordLetters.get(i))) {
                                wordboard.getLabels()[rowCount[0]][i].setBackground(CORRECT_COLOR);
                                wordboard.getPanels()[rowCount[0]][i].setBackground(CORRECT_COLOR);
                                Border border = BorderFactory.createLineBorder(CORRECT_COLOR, 2);
                                wordboard.getPanels()[rowCount[0]][i].setBorder(border);
                                correctLetterCount++;
                                JPanel panel = keyboard.findPanel(keyboardPanels, wordboard.getLabels()[rowCount[0]][i].getText());
                                panel.setBackground(CORRECT_COLOR);
                            } else if (referenceWordLetters.contains(wordboard.getLabels()[rowCount[0]][i].getText())) {
                                wordboard.getLabels()[rowCount[0]][i].setBackground(WRONG_PLACE_COLOR);
                                wordboard.getPanels()[rowCount[0]][i].setBackground(WRONG_PLACE_COLOR);
                                Border border = BorderFactory.createLineBorder(WRONG_PLACE_COLOR, 2);
                                wordboard.getPanels()[rowCount[0]][i].setBorder(border);
                                JPanel panel = keyboard.findPanel(keyboardPanels, wordboard.getLabels()[rowCount[0]][i].getText());
                                panel.setBackground(WRONG_PLACE_COLOR);
                            } else {
                                wordboard.getLabels()[rowCount[0]][i].setBackground(INCORRECT_COLOR);
                                wordboard.getPanels()[rowCount[0]][i].setBackground(INCORRECT_COLOR);
                                Border border = BorderFactory.createLineBorder(INCORRECT_COLOR, 2);
                                wordboard.getPanels()[rowCount[0]][i].setBorder(border);
                                JPanel panel = keyboard.findPanel(keyboardPanels, wordboard.getLabels()[rowCount[0]][i].getText());
                                panel.setBackground(INCORRECT_COLOR);
                            }
                        }

                        if (correctLetterCount == finalWordLength) {
                            gameWon[0] = true;
                        }

                        if (gameWon[0]) {
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

                        if (pressedEnterCount[0] == finalWordLength && !gameWon[0]) {
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
                } else if (button.getText().equals("Delete")) {
                    boolean deletedOnce = false;
                    if (lastButtonClicked[0] != null && !lastButtonClicked[0].getText().equals("Enter") && columnCount[0] != 0) {
                        wordboard.getLabels()[rowCount[0]][columnCount[0] - 1].setText("");
                        columnCount[0]--;
                        deletedOnce = true;
                        lastButtonClicked[0] = button;
                    }
                    if (flag[0] && !deletedOnce) {
                        wordboard.getLabels()[rowCount[0]][columnCount[0] - 1].setText("");
                        columnCount[0]--;
                        flag[0] = false;
                        lastButtonClicked[0] = button;
                    }
                } else {
                    boolean isFull = false;
                    if (columnCount[0] == finalWordLength) {
                        if (lastButtonClicked[0].getText().equals("Enter") || lastButtonClicked[0].getText().equals("Delete")) {
                            columnCount[0] = 0;
                            rowCount[0]++;
                        } else {
                            isFull = true;
                        }
                    }

                    if (!isFull) {
                        character[0] = button.getText();
                        wordboard.getLabels()[rowCount[0]][columnCount[0]].setText(String.valueOf(character[0]));
                        columnCount[0]++;
                        lastButtonClicked[0] = button;
                    }
                }
            });
        }
    }
}
