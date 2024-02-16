import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    JButton startButton;
    public MyFrame() {
        this.startButton = new MyButton("Start", 200, 100, 100, 50);
        this.startButton.addActionListener(e -> {
            System.out.println("Start button clicked");
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Wordle");
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("utils/logo.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0, 0, 0));

        this.add(startButton);
    }
}
