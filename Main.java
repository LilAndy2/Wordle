import java.awt.*;

public class Main {
    public static void main(String[] args) {
        LaunchPage launchPage = new LaunchPage();

        launchPage.getFrame().getStartButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            GamePage gamePage = new GamePage();
            gamePage.getFrame().getEasyButton().addActionListener(e1 -> startGame(gamePage, "easy"));
            gamePage.getFrame().getMediumButton().addActionListener(e1 -> startGame(gamePage, "medium"));
            gamePage.getFrame().getHardButton().addActionListener(e1 -> startGame(gamePage, "hard"));
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
    public static void startGame(GamePage gamePage, String difficulty) {
        KeyboardPanel keyboard = new KeyboardPanel();
        //keyboard.setLayout(new GridLayout(4, 10, 10, 10));
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
    }
}
