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
        gamePage.getFrame().getEasyButton().setVisible(false);
        gamePage.getFrame().getMediumButton().setVisible(false);
        gamePage.getFrame().getHardButton().setVisible(false);

        KeyboardPanel keyboard = new KeyboardPanel();
        keyboard.setLayout(new GridLayout(4, 10, 10, 10));
        keyboard.setBackground(new Color(18,18,18,255));
        gamePage.getFrame().add(keyboard);
    }
}
