import javax.swing.*;
import java.awt.*;

public interface Frame {
}

class LaunchFrame extends JFrame implements Frame {
    private final MyButton startButton;
    private final MyButton instructionsButton;
    public LaunchFrame() {
        this.startButton = new MyButton("Start", 100, 800, 125, 50, new Font("Times New Roman",
                Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));
        this.instructionsButton = new MyButton("Instructions", 775, 800, 125, 50, new Font("Times New Roman",
                Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Wordle");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(18,18,18,255));

        this.add(this.startButton);
        this.add(this.instructionsButton);
    }
    public MyButton getStartButton() {
        return this.startButton;
    }
    public MyButton getInstructionsButton() {
        return this.instructionsButton;
    }
}

class InstructionsFrame extends JFrame implements Frame {
    private final MyButton backButton;
    public InstructionsFrame() {
        this.backButton = new MyButton("Go back", 0, 0, 125, 50, new Font("Times New Roman",
                Font.BOLD, 20), new Color(18,18,18,255), new Color(255,255,255));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Instructions");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(18,18,18,255));

        this.add(this.backButton);
    }
    public MyButton getBackButton() {
        return this.backButton;
    }
}
