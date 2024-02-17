import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KeyboardPanel extends JPanel {
    private final ArrayList<JButton> buttons;
    public KeyboardPanel() {
        this.buttons = new ArrayList<>();
        setLayout(new GridLayout(4, 10));

        for (char c = 'A'; c <= 'Z'; c++) {
            MyButton button = new MyButton(String.valueOf(c), 0, 0, 0, 0, new Font("Times New Roman",
                    Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));
            JPanel panel = new JPanel();
            panel.add(button);
            this.add(panel);
            this.buttons.add(button);
        }

        MyButton enterButton = new MyButton("Enter", 0, 0, 0, 0, new Font("Times New Roman",
                Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));
        this.add(enterButton);
        this.buttons.add(enterButton);
        MyButton deleteButton = new MyButton("Delete", 0, 0, 0, 0, new Font("Times New Roman",
                Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));
        this.add(deleteButton);
        this.buttons.add(deleteButton);
    }
    public ArrayList<JButton> getButtons() {
        return this.buttons;
    }
}
