import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public interface Panel {
    /*
     * Interface for the panels.
     * Contains the constants for the colors and the background color.
     */
    Color WHITE = new Color(255, 255, 255);
    Color BACKGROUND_COLOR = new Color(18,18,18,255);
}

class KeyboardPanel extends JPanel implements Panel {
    /*
     * Class for the keyboard panel.
     * Contains and initialises the buttons and the panels.
     * The buttons are the letters from A to Z, the "Enter" button and the "Delete" button.
     */
    private final ArrayList<JButton> buttons;
    private final ArrayList<JPanel> panels;
    public KeyboardPanel() {
        this.buttons = new ArrayList<>();
        this.panels = new ArrayList<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            MyButton button = new MyButton(String.valueOf(c), 0, 0, 20, 30,
                    new Font("Times New Roman", Font.BOLD, 23), Color.GRAY, WHITE);
            button.setOpaque(false);

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.GRAY);
            panel.add(button);
            panel.setVisible(true);
            panel.setPreferredSize(new Dimension(40, 50));

            this.buttons.add(button);
            this.panels.add(panel);
        }

        MyButton enterButton = new MyButton("Enter", 0, 0, 0, 0,
                new Font("Times New Roman", Font.BOLD, 20), Color.GRAY, WHITE);
        enterButton.setOpaque(false);

        JPanel enterPanel = new JPanel(new BorderLayout());
        enterPanel.setVisible(true);
        enterPanel.setBackground(Color.GRAY);
        enterPanel.add(enterButton, BorderLayout.CENTER);

        this.buttons.add(enterButton);
        this.panels.add(enterPanel);

        MyButton deleteButton = new MyButton("Delete", 0, 0, 0, 0,
                new Font("Times New Roman", Font.BOLD, 20), Color.GRAY, WHITE);
        deleteButton.setOpaque(false);

        JPanel deletePanel = new JPanel(new BorderLayout());
        deletePanel.setVisible(true);
        deletePanel.setBackground(Color.GRAY);
        deletePanel.add(deleteButton, BorderLayout.CENTER);

        this.buttons.add(deleteButton);
        this.panels.add(deletePanel);

        this.displayPanels();

    }
    public ArrayList<JButton> getButtons() {
        return this.buttons;
    }
    public ArrayList<JPanel> getPanels() {
        return this.panels;
    }
    public JPanel findPanel(ArrayList<JPanel> panels, String letter) {
        /*
         * Method to find the panel with the given letter.
         * Return null if the letter is not found on any panel.
         */
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
    private void displayPanels() {
        /*
         * Method to display the panels on the keyboard panel.
         * The panels are displayed similar to a real keyboard.
         */
        ArrayList<JPanel> topRowPanels = new ArrayList<>();
        ArrayList<JPanel> middleRowPanels = new ArrayList<>();
        ArrayList<JPanel> bottomRowPanels = new ArrayList<>();
        char[] topRowLetters = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'};
        char[] middleRowLetters = {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'};
        char[] bottomRowLetters = {'Z', 'X', 'C', 'V', 'B', 'N', 'M'};

        JPanel enterPanel = findPanel(this.panels, "Enter");
        enterPanel.setBounds(114, 0, 64, 50);
        enterPanel.setPreferredSize(new Dimension(64, 60));

        for (char c : topRowLetters) {
            JPanel panel = findPanel(this.panels, String.valueOf(c));
            topRowPanels.add(panel);
        }

        for (char c : middleRowLetters) {
            JPanel panel = findPanel(this.panels, String.valueOf(c));
            middleRowPanels.add(panel);
        }

        for (char c : bottomRowLetters) {
            JPanel panel = findPanel(this.panels, String.valueOf(c));
            bottomRowPanels.add(panel);
        }

        JPanel deletePanel = findPanel(this.panels, "Delete");
        deletePanel.setBounds(114, 399, 64, 50);
        deletePanel.setPreferredSize(new Dimension(64, 60));

        JPanel topRow = new JPanel(new GridLayout(1, 10, 7, 7));
        topRow.setVisible(true);
        topRow.setBackground(BACKGROUND_COLOR);
        topRow.setPreferredSize(new Dimension(463, 60));
        for (JPanel panel : topRowPanels) {
            topRow.add(panel);
        }

        JPanel middleRow = new JPanel(new GridLayout(1, 9, 7, 7));
        middleRow.setVisible(true);
        middleRow.setBackground(BACKGROUND_COLOR);
        middleRow.setPreferredSize(new Dimension(416, 60));
        for (JPanel panel : middleRowPanels) {
            middleRow.add(panel);
        }

        JPanel bottomRow = new JPanel(new GridLayout(1, 9, 7, 7));
        bottomRow.setVisible(true);
        bottomRow.setBackground(BACKGROUND_COLOR);
        bottomRow.setPreferredSize(new Dimension(322, 60));
        for (JPanel panel : bottomRowPanels) {
            bottomRow.add(panel);
        }

        this.add(topRow);
        this.add(middleRow);
        this.add(enterPanel);
        this.add(bottomRow);
        this.add(deletePanel);
    }
}

class WordboardPanel extends JPanel implements Panel {
    /*
     * Class for the word board panel.
     * Contains and initialises the labels and the panels.
     * The labels are the letters of the word to be guessed.
     */
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
                MyLabel label = new MyLabel(null, "", JLabel.CENTER, JLabel.CENTER, WHITE, font,
                        0,BACKGROUND_COLOR, JLabel.CENTER, JLabel.CENTER, 0, 0, 0, 0);

                JPanel panel = new JPanel();
                panel.setBackground(BACKGROUND_COLOR);
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
