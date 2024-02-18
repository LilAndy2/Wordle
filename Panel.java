import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public interface Panel {
}

class KeyboardPanel extends JPanel implements Panel {
    private final ArrayList<JButton> buttons;
    public KeyboardPanel() {
        this.buttons = new ArrayList<>();
        this.setLayout(new GridLayout(4, 10));

        for (char c = 'A'; c <= 'Z'; c++) {
            MyButton button = new MyButton(String.valueOf(c), 0, 0, 0, 0, new Font("Times New Roman",
                    Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));
            button.setSize(100,100);

            JPanel panel = new JPanel();
            panel.setBackground(new Color(18,18,18,255));
            panel.add(button, BorderLayout.CENTER);
            panel.setVisible(true);

            this.add(panel);
            this.buttons.add(button);
        }

        MyButton enterButton = new MyButton("Enter", 0, 0, 0, 0, new Font("Times New Roman",
                Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));

        JPanel enterPanel = new JPanel();
        enterPanel.setVisible(true);
        enterPanel.setBackground(new Color(18,18,18,255));
        enterPanel.add(enterButton, BorderLayout.CENTER);

        this.add(enterPanel);
        this.buttons.add(enterButton);

        MyButton deleteButton = new MyButton("Delete", 0, 0, 0, 0, new Font("Times New Roman",
                Font.BOLD, 20), new Color(255,255,255), new Color(0,0,0));

        JPanel deletePanel = new JPanel();
        deletePanel.setVisible(true);
        deletePanel.setBackground(new Color(18,18,18,255));
        deletePanel.add(deleteButton, BorderLayout.CENTER);

        this.add(deletePanel);
        this.buttons.add(deleteButton);
    }
    public ArrayList<JButton> getButtons() {
        return this.buttons;
    }
}

class WordboardPanel extends JPanel implements Panel {
    private final JLabel[][] labels;
    public WordboardPanel(int wordLength) {
        labels = new JLabel[wordLength][wordLength];
        this.setLayout(new GridLayout(wordLength, wordLength, 10, 10));
        for (int i = 0; i < wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3);
                MyLabel label = new MyLabel(null, "", JLabel.CENTER, JLabel.CENTER, new Color(255, 255, 255),
                        new Font("Times New Roman", Font.PLAIN, 20), 0, new Color(18, 18, 18, 255), JLabel.CENTER,
                        JLabel.CENTER, 0, 0, 0, 0);

                JPanel panel = new JPanel();
                panel.setBackground(new Color(18,18,18,255));
                panel.add(label, BorderLayout.CENTER);
                panel.setBorder(border);
                panel.setVisible(true);

                this.add(panel);
                labels[i][j] = label;
            }
        }
    }
    public JLabel[][] getLabels() {
        return this.labels;
    }
}
