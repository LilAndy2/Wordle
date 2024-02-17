import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        KeyboardPanel keyboard = new KeyboardPanel();
        keyboard.setLayout(new GridLayout(4, 10, 10, 10));
        keyboard.setBackground(new Color(18,18,18,255));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Wordle");
        frame.setVisible(true);
        frame.add(keyboard);
    }
}
