import javax.swing.*;
import java.awt.*;

public interface Frame {
    /*
     * Interface for the frames.
     * Contains the constants for the colors and the background color.
     */
    Color WHITE = new Color(255, 255, 255);
    Color BLACK = new Color(0, 0, 0);
    Color BACKGROUND_COLOR = new Color(18,18,18,255);
}

class LaunchFrame extends JFrame implements Frame {
    /*
     * Class for the launching frame.
     * Contains and initialises the "Start" and "Instructions" buttons.
     */
    private final MyButton startButton;
    private final MyButton instructionsButton;
    private final MyButton logInButton;
    public LaunchFrame() {
        this.startButton = new MyButton("Start", 300, 700, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), WHITE, BLACK);
        this.instructionsButton = new MyButton("Instructions", 575, 700, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), WHITE, BLACK);
        this.logInButton = new MyButton("Log in", 850, 10, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Wordle");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/images/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        this.add(this.startButton);
        this.add(this.instructionsButton);
        this.add(this.logInButton);
    }
    public MyButton getStartButton() {
        return this.startButton;
    }
    public MyButton getInstructionsButton() {
        return this.instructionsButton;
    }
    public MyButton getLogInButton() {
        return this.logInButton;
    }
}

class InstructionsFrame extends JFrame implements Frame {
    /*
     * Class for the instructions frame.
     * Contains and initialises the "Go back" button.
     */
    private final MyButton backButton;
    public InstructionsFrame() {
        this.backButton = new MyButton("Go back", 0, 0, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Instructions");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/images/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        this.add(this.backButton);
    }
    public MyButton getBackButton() {
        return this.backButton;
    }
}

class GameFrame extends JFrame implements Frame {
    /*
     * Class for the game frame.
     * Contains and initialises the "Easy", "Medium" and "Hard" buttons.
     */
    private final MyButton easyButton;
    private final MyButton mediumButton;
    private final MyButton hardButton;

    public GameFrame() {
        this.easyButton = new MyButton("Easy", 100, 550, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), WHITE, BLACK);
        this.mediumButton = new MyButton("Medium", 437, 550, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), WHITE, BLACK);
        this.hardButton = new MyButton("Hard", 775, 550, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), WHITE, BLACK);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Wordle");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/images/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        MyLabel title = new MyLabel(null, "Choose a difficulty", JLabel.CENTER, JLabel.BOTTOM, WHITE,
                new Font("Times New Roman", Font.PLAIN, 100), 10, BACKGROUND_COLOR, JLabel.CENTER,
                JLabel.CENTER, 250, 100, 500, 100);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(BACKGROUND_COLOR);
        textPanel.setBounds(100, 350, 800, 150);
        textPanel.add(title);

        this.add(this.easyButton);
        this.add(this.mediumButton);
        this.add(this.hardButton);
        this.add(textPanel);
    }

    public MyButton getEasyButton() {
        return this.easyButton;
    }
    public MyButton getMediumButton() {
        return this.mediumButton;
    }
    public MyButton getHardButton() {
        return this.hardButton;
    }
}

class StatsFrame extends JFrame implements Frame {
    /*
     * Class for the stats frame.
     * Contains and initialises the "Go back" button.
     */
    private final MyButton backButton;
    public StatsFrame() {
        this.backButton = new MyButton("Go back", 0, 0, 125, 50,
                new Font("Times New Roman", Font.BOLD, 20), BACKGROUND_COLOR, WHITE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Stats");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/images/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        this.add(this.backButton);
    }
    public MyButton getBackButton() {
        return this.backButton;
    }
}
