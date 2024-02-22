import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public interface Panel {
}

class KeyboardPanel extends JPanel implements Panel {
    private final ArrayList<JButton> buttons;
    private final ArrayList<JPanel> panels;
    public KeyboardPanel() {
        this.buttons = new ArrayList<>();
        this.panels = new ArrayList<>();
        this.setLayout(new GridLayout(4, 10, 7, 7));

        for (char c = 'A'; c <= 'Z'; c++) {
            MyButton button = new MyButton(String.valueOf(c), 0, 0, 20, 30, new Font("Times New Roman",
                    Font.BOLD, 23),Color.GRAY, new Color(255,255,255));
            button.setOpaque(false);

            JPanel panel = new JPanel();
            panel.setBackground(Color.GRAY);
            //panel.setBounds(0, 0, 20, 30);
            panel.add(button);
            panel.setVisible(true);

            this.add(panel);
            this.buttons.add(button);
            this.panels.add(panel);
        }

        MyButton enterButton = new MyButton("Enter", 0, 0, 0, 0, new Font("Times New Roman",
                Font.BOLD, 20), Color.GRAY, new Color(255,255,255));
        enterButton.setOpaque(false);

        JPanel enterPanel = new JPanel();
        enterPanel.setVisible(true);
        enterPanel.setBackground(Color.GRAY);
        enterPanel.add(enterButton, BorderLayout.CENTER);

        this.add(enterPanel);
        this.buttons.add(enterButton);
        this.panels.add(enterPanel);

        MyButton deleteButton = new MyButton("Delete", 0, 0, 0, 0, new Font("Times New Roman",
                Font.BOLD, 20), Color.GRAY, new Color(255,255,255));
        deleteButton.setOpaque(false);

        JPanel deletePanel = new JPanel();
        deletePanel.setVisible(true);
        deletePanel.setBackground(Color.GRAY);
        deletePanel.add(deleteButton, BorderLayout.CENTER);

        this.add(deletePanel);
        this.buttons.add(deleteButton);
        this.panels.add(deletePanel);
    }
    public ArrayList<JButton> getButtons() {
        return this.buttons;
    }
    public ArrayList<JPanel> getPanels() {
        return this.panels;
    }
    public JPanel findPanel(ArrayList<JPanel> panels, String letter) {
        for (JPanel panel : panels) {
            Component[] components = panel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton button) {
                    if (button.getText().equals(letter)) {
                        return panel;
                    }
                }
            }
        }
        return null;
    }
}

class WordboardPanel extends JPanel implements Panel {
    private final JLabel[][] labels;
    private final JPanel[][] panels;
    public WordboardPanel(int wordLength) {
        labels = new JLabel[wordLength][wordLength];
        panels = new JPanel[wordLength][wordLength];
        this.setLayout(new GridLayout(wordLength, wordLength, 10, 10));

        Font font = null;
        switch (wordLength) {
            case 4 -> font = new Font("Times New Roman", Font.PLAIN, 50);
            case 5 -> font = new Font("Times New Roman", Font.PLAIN, 45);
            case 6 -> font = new Font("Times New Roman", Font.PLAIN, 40);
        }

        for (int i = 0; i < wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3);
                MyLabel label = new MyLabel(null, "", JLabel.CENTER, JLabel.CENTER, new Color(255, 255, 255),
                        font, 0, new Color(18, 18, 18, 255), JLabel.CENTER, JLabel.CENTER, 0, 0, 0, 0);

                JPanel panel = new JPanel();
                panel.setBackground(new Color(18,18,18,255));
                panel.add(label, BorderLayout.CENTER);
                panel.setBorder(border);
                panel.setVisible(true);

                this.add(panel);
                labels[i][j] = label;
                panels[i][j] = panel;
            }
        }
    }
    public JLabel[][] getLabels() {
        return this.labels;
    }
    public JPanel[][] getPanels() {
        return this.panels;
    }
}
