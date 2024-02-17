import javax.swing.*;
import java.awt.*;

public interface Page {
}

class LaunchPage implements Page {
    private final LaunchFrame frame;
    private final MyLabel label;
    public LaunchPage() {
        this.label = new MyLabel(new ImageIcon("utils/wordle.jpg"), "Welcome to Wordle!", JLabel.CENTER, JLabel.TOP,
                new Color(255,255,255), new Font("MV Boli", Font.PLAIN, 50), -75,
                new Color(18,18,18,255), JLabel.CENTER, JLabel.CENTER, 150, 250, 500, 500);
        this.frame = new LaunchFrame();
        this.frame.add(this.label);
    }
    LaunchFrame getFrame() {
        return this.frame;
    }
}

class InstructionsPage implements Page {
    private final InstructionsFrame frame;
    private final MyLabel label;
    public InstructionsPage() {
        this.label = new MyLabel(new ImageIcon("utils/instructions.jpg"), "Instructions", JLabel.CENTER, JLabel.TOP,
                new Color(255,255,255), new Font("MV Boli", Font.PLAIN, 50), -75,
                new Color(18,18,18,255), JLabel.CENTER, JLabel.CENTER, 150, 250, 500, 500);
        this.frame = new InstructionsFrame();
        this.frame.add(this.label);
    }
    InstructionsFrame getFrame() {
        return this.frame;
    }
}
