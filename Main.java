import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LaunchPage launchPage = new LaunchPage();

        launchPage.getFrame().getStartButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            GamePage gamePage = new GamePage();
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
    public static void startGame(GamePage gamePage, String difficulty) throws IOException {
        KeyboardPanel keyboard = new KeyboardPanel();
        keyboard.setBackground(new Color(18,18,18,255));
        keyboard.setBounds(250, 700, 500, 200);
        keyboard.setVisible(true);

        gamePage.getFrame().getContentPane().removeAll();
        gamePage.getFrame().add(keyboard);
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
        wordboard.setBounds(250, 100, 500, 500);
        wordboard.setVisible(true);

        gamePage.getFrame().add(wordboard);
        gamePage.getFrame().revalidate();
        gamePage.getFrame().repaint();

        Game game = new Game(difficulty);
        String referenceWord = game.chooseReferenceWord();
        System.out.println(referenceWord);
        ReferenceWord myWord = new ReferenceWord(referenceWord);
        ArrayList<String> referenceWordLetters = myWord.getLetters();

        final String[] character = new String[1];
        final int[] rowCount = {0};
        final int[] columnCount = {0};
        final int[] pressedEnterCount = {0};
        final boolean[] gameWon = {false};
        final JButton[] lastButtonClicked = {null};

        ArrayList<JButton> buttons = keyboard.getButtons();
        for (JButton button : buttons) {
            int finalWordLength = wordLength;

            button.addActionListener(e -> {
                if (button.getText().equals("Enter")) {
                    if (!(columnCount[0] == finalWordLength)) {
                        // Display a message "Not enough letters!"
                    } else {
                        pressedEnterCount[0]++;
                        int correctLetterCount = 0;

                        for (int i = 0; i < finalWordLength; i++) {
                            if (wordboard.getLabels()[rowCount[0]][i].getText().equals(referenceWordLetters.get(i))) {
                                wordboard.getLabels()[rowCount[0]][i].setForeground(Color.GREEN);
                                correctLetterCount++;
                            } else if (referenceWordLetters.contains(wordboard.getLabels()[rowCount[0]][i].getText())) {
                                wordboard.getLabels()[rowCount[0]][i].setForeground(Color.YELLOW);
                            } else {
                                wordboard.getLabels()[rowCount[0]][i].setForeground(Color.RED);
                            }
                        }

                        if (correctLetterCount == finalWordLength) {
                            gameWon[0] = true;
                        }

                        if (gameWon[0]) {
                            // Display a message "You won!"
                        }

                        if (pressedEnterCount[0] == 6 && !gameWon[0]) {
                            // Display a message "You lost! The word was x"
                        }
                    }
                    lastButtonClicked[0] = button;
                } else if (button.getText().equals("Delete")) {
                    if (lastButtonClicked[0] != null && !lastButtonClicked[0].getText().equals("Enter") && columnCount[0] != 0) {
                        wordboard.getLabels()[rowCount[0]][columnCount[0] - 1].setText("");
                        columnCount[0]--;
                    }
                } else {
                    if (columnCount[0] == finalWordLength) {
                        columnCount[0] = 0;
                        rowCount[0]++;
                    }
                    character[0] = button.getText();
                    System.out.println(character[0]);
                    wordboard.getLabels()[rowCount[0]][columnCount[0]].setText(String.valueOf(character[0]));
                    columnCount[0]++;
                    lastButtonClicked[0] = button;
                }
            });
        }
    }
}
