import javax.swing.*;
import java.awt.*;

public interface Page {
}

class LaunchPage implements Page {
    private final LaunchFrame frame;
    public LaunchPage() {
        MyLabel label = new MyLabel(new ImageIcon("utils/wordle.jpg"), "Welcome to Wordle!", JLabel.CENTER, JLabel.TOP,
                new Color(255, 255, 255), new Font("MV Boli", Font.PLAIN, 50), -75,
                new Color(18, 18, 18, 255), JLabel.CENTER, JLabel.CENTER, 150, 250, 500, 500);
        this.frame = new LaunchFrame();
        this.frame.add(label);
    }
    LaunchFrame getFrame() {
        return this.frame;
    }
}

class InstructionsPage implements Page {
    private final InstructionsFrame frame;
    public InstructionsPage() {
        String instructions = "<html>The goal of the game is to guess the word in 6 tries. The word is 5 letters long. " +
                "You can only guess words that are 5 letters long. If a letter is in the word, it will be green. " +
                "If a letter is in the word but in the wrong place, it will be yellow. If a letter is not in the word, " +
                "it will be red. Good luck!</html>";
        MyLabel instructions1 = new MyLabel(null, instructions, JLabel.CENTER, JLabel.TOP, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), -75, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 150, 250, 500, 500);

        String title = "<html><b><u>Instructions</u></b></html>";
        MyLabel title1 = new MyLabel(null, title, JLabel.CENTER, JLabel.TOP, new Color(255, 255, 255), new Font(null,
                Font.PLAIN, 50), -75, new Color(18, 18, 18, 255), JLabel.CENTER, JLabel.CENTER, 0,
                0, 500, 500);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18,255));
        panel.setBounds(100,180,700,270);
        panel.add(title1);

        this.frame = new InstructionsFrame();
        this.frame.add(panel);
        this.frame.add(instructions1);
    }
    InstructionsFrame getFrame() {
        return this.frame;
    }
}

class GamePage implements Page {
    private final GameFrame frame;
    public GamePage() {

        //keyboard.setVisible(false);

        this.frame = new GameFrame();
        //this.frame.add(keyboard);
    }
    GameFrame getFrame() {
        return this.frame;
    }
}
