import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface Page {
    String instructionsText = "<html><p>Guess the hidden word in 4, 5 or 6 tries, depending on the</p>" +
            "<p>difficulty you choose.</p>\n" +
            "<p></p>\n" +
            "<p>Each guess must be a 4, 5 or 6 letter word, depending on </p>" +
            "<p>the difficulty. Hit the enter button to submit the guess.</p>\n" +
            "<p></p>\n" +
            "<p>After your submission, the color of the tiles will change as </p>" +
            "<p>in the examples below.</p>\n" +
            "<p>_________________________________________________________________________________________</p>\n" +
            "<p></p>" +
            "<p><strong><u>Examples</u></strong></p></html>";
    static MyLabel getInstructionsLabel() {
        MyLabel instructions = new MyLabel(null, instructionsText, JLabel.CENTER, JLabel.TOP, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 0, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 0, 510, 700);
        instructions.setOpaque(false);
        return instructions;
    }
}

class LaunchPage implements Page {
    private final LaunchFrame frame;
    public LaunchPage() {
        MyLabel label = new MyLabel(new ImageIcon("utils/images/wordle.jpg"), "Welcome to Wordle!", JLabel.CENTER, JLabel.TOP,
                new Color(255, 255, 255), new Font("MV Boli", Font.PLAIN, 50), -75,
                new Color(18, 18, 18, 255), JLabel.CENTER, JLabel.CENTER, 250, 250, 500, 500);
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
        MyLabel instructions = Page.getInstructionsLabel();

        String title = "<html><b><u>HOW TO PLAY</u></b></html>";
        MyLabel title1 = new MyLabel(null, title, JLabel.CENTER, JLabel.TOP, new Color(255, 255, 255), new Font(null,
                Font.PLAIN, 40), 0, new Color(18, 18, 18, 255), JLabel.CENTER, JLabel.CENTER, 0,
                0, 500, 500);

        ImageIcon correctExample = new ImageIcon("utils/images/correct.png");
        Image correctImage = correctExample.getImage();
        Image newCorrectImage = correctImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        correctExample = new ImageIcon(newCorrectImage);

        ImageIcon incorrectExample = new ImageIcon("utils/images/incorrect.png");
        Image incorrectImage = incorrectExample.getImage();
        Image newIncorrectImage = incorrectImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        incorrectExample = new ImageIcon(newIncorrectImage);

        ImageIcon wrongPlaceExample = new ImageIcon("utils/images/wrong place.png");
        Image wrongPlaceImage = wrongPlaceExample.getImage();
        Image newWrongPlaceImage = wrongPlaceImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        wrongPlaceExample = new ImageIcon(newWrongPlaceImage);

        String correctExampleText = "<html><p>The letter <strong>M</strong> is in the word and in the correct spot.</p></html>";
        MyLabel correct = new MyLabel(correctExample, correctExampleText, JLabel.CENTER, JLabel.BOTTOM, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 10, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 290, 500, 500);
        correct.setOpaque(false);

        String wrongPlaceExampleText = "<html><p>The letter <strong>M</strong> is in the word but in the wrong spot.</p></html>";
        MyLabel wrongPlace = new MyLabel(wrongPlaceExample, wrongPlaceExampleText, JLabel.CENTER, JLabel.BOTTOM, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 10, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 400, 500, 500);
        wrongPlace.setOpaque(false);

        String incorrectExampleText = "<html><p>The letters <strong>N</strong> and <strong>E</strong> are not in the word in any spot.</p></html>";
        MyLabel incorrect = new MyLabel(incorrectExample, incorrectExampleText, JLabel.CENTER, JLabel.BOTTOM, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 10, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 510, 500, 500);
        incorrect.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18,255));
        panel.setBounds(150,130,700,100);
        panel.setOpaque(false);
        panel.add(title1);

        this.frame = new InstructionsFrame();
        this.frame.add(panel);
        this.frame.add(instructions);
        this.frame.add(correct);
        this.frame.add(wrongPlace);
        this.frame.add(incorrect);
    }
    InstructionsFrame getFrame() {
        return this.frame;
    }
}

class GamePage implements Page, ActionListener {
    private final GameFrame frame;
    private final JPanel line;
    private final JLabel titleText;
    private final MyButton settingsButton;
    private final MyButton instructionsButton;
    private final JRadioButton darkMode;
    private final JRadioButton colorBlindMode;
    private boolean darkModeEnabled;
    private boolean colorBlindModeEnabled;
    public GamePage() {
        this.darkMode = new JRadioButton("Enable Dark Mode");
        this.colorBlindMode = new JRadioButton("Enable Color Blind Mode");

        this.darkModeEnabled = false;
        this.colorBlindModeEnabled = false;

        line = new JPanel();
        line.setVisible(true);
        line.setBounds(0,125,1000,2);
        line.setBackground(Color.WHITE);

        titleText = new JLabel();
        titleText.setText("Wordle");
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        titleText.setForeground(Color.WHITE);
        titleText.setBounds(350, 20, 300, 100);
        titleText.setBackground(new Color(18,18,18,255));
        titleText.setOpaque(true);

        ImageIcon settingsIcon = new ImageIcon("utils/images/settings.png");
        settingsButton = new MyButton("", 900, 80, 30, 30, new Font("Times New Roman",
                Font.BOLD, 20), new Color(18,18,18,255), new Color(255,255,255));
        settingsButton.setName("Settings");
        settingsButton.setIcon(settingsIcon);
        settingsButton.setOpaque(false);
        settingsButton.setVisible(false);

        ImageIcon instructionsIcon = new ImageIcon("utils/images/instructions.png");
        instructionsButton = new MyButton("", 940, 80, 30, 30, new Font("Times New Roman",
                Font.BOLD, 20), new Color(18,18,18,255), new Color(255,255,255));
        instructionsButton.setName("Instructions");
        instructionsButton.setIcon(instructionsIcon);
        instructionsButton.setOpaque(false);
        instructionsButton.setVisible(false);

        this.frame = new GameFrame();
        this.frame.add(titleText);
        this.frame.add(line);
        this.frame.add(settingsButton);
        this.frame.add(instructionsButton);

    }
    GameFrame getFrame() {
        return this.frame;
    }
    JPanel getLine() {
        return this.line;
    }
    JLabel getTitleText() {
        return this.titleText;
    }
    MyButton getSettingsButton() {
        return this.settingsButton;
    }
    MyButton getInstructionsButton() {
        return this.instructionsButton;
    }
    public ArrayList<Component> displayInstructionsInGame() {
        MyLabel instructions = Page.getInstructionsLabel();

        String title = "<html><b><u>HOW TO PLAY</u></b></html>";
        MyLabel title1 = new MyLabel(null, title, JLabel.CENTER, JLabel.TOP, new Color(255, 255, 255), new Font(null,
                Font.PLAIN, 40), 0, new Color(18, 18, 18, 255), JLabel.CENTER, JLabel.CENTER, 0,
                0, 500, 500);

        ImageIcon correctExample = new ImageIcon("utils/images/correct.png");
        Image correctImage = correctExample.getImage();
        Image newCorrectImage = correctImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        correctExample = new ImageIcon(newCorrectImage);

        ImageIcon incorrectExample = new ImageIcon("utils/images/incorrect.png");
        Image incorrectImage = incorrectExample.getImage();
        Image newIncorrectImage = incorrectImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        incorrectExample = new ImageIcon(newIncorrectImage);

        ImageIcon wrongPlaceExample = new ImageIcon("utils/images/wrong place.png");
        Image wrongPlaceImage = wrongPlaceExample.getImage();
        Image newWrongPlaceImage = wrongPlaceImage.getScaledInstance(220, 50, Image.SCALE_SMOOTH);
        wrongPlaceExample = new ImageIcon(newWrongPlaceImage);

        String correctExampleText = "<html><p>The letter <strong>M</strong> is in the word and in the correct spot.</p></html>";
        MyLabel correct = new MyLabel(correctExample, correctExampleText, JLabel.CENTER, JLabel.BOTTOM, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 10, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 290, 500, 500);
        correct.setOpaque(false);

        String wrongPlaceExampleText = "<html><p>The letter <strong>M</strong> is in the word but in the wrong spot.</p></html>";
        MyLabel wrongPlace = new MyLabel(wrongPlaceExample, wrongPlaceExampleText, JLabel.CENTER, JLabel.BOTTOM, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 10, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 400, 500, 500);
        wrongPlace.setOpaque(false);

        String incorrectExampleText = "<html><p>The letters <strong>N</strong> and <strong>E</strong> are not in the word in any spot.</p></html>";
        MyLabel incorrect = new MyLabel(incorrectExample, incorrectExampleText, JLabel.CENTER, JLabel.BOTTOM, new Color(255, 255, 255),
                new Font(null, Font.PLAIN, 20), 10, new Color(18, 18, 18, 255), JLabel.CENTER,
                JLabel.CENTER, 245, 510, 500, 500);
        incorrect.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18,255));
        panel.setBounds(150,130,700,100);
        panel.setOpaque(false);
        panel.add(title1);

        MyButton backButton = new MyButton("Go back", 0, 0, 125, 50, new Font("Times New Roman",
                Font.BOLD, 20), new Color(18,18,18,255), new Color(255,255,255));

        this.frame.getContentPane().setBackground(new Color(18,18,18,255));

        ArrayList<Component> components = new ArrayList<>();
        components.add(backButton);
        components.add(panel);
        components.add(instructions);
        components.add(correct);
        components.add(wrongPlace);
        components.add(incorrect);

        for (Component component : components) {
            this.frame.add(component);
        }

        return components;
    }
    public ArrayList<Component> displaySettings() {
        JPanel line = new JPanel();
        line.setVisible(true);
        line.setBounds(0,125,1000,2);
        line.setBackground(Color.WHITE);

        JLabel titleText = new JLabel();
        titleText.setText("Settings");
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        titleText.setForeground(Color.WHITE);
        titleText.setBounds(337, 20, 325, 100);
        titleText.setBackground(new Color(18,18,18,255));
        titleText.setOpaque(true);

        MyButton backButton = new MyButton("Go back", 0, 70, 125, 50, new Font("Times New Roman",
                Font.BOLD, 20), new Color(18,18,18,255), new Color(255,255,255));

        this.darkMode.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.darkMode.setBackground(new Color(18,18,18,255));
        this.darkMode.setForeground(Color.WHITE);
        this.darkMode.setBounds(300, 140, 300, 20);

        this.colorBlindMode.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.colorBlindMode.setBackground(new Color(18,18,18,255));
        this.colorBlindMode.setForeground(Color.WHITE);
        this.colorBlindMode.setBounds(300, 180, 350, 20);

        ArrayList<Component> components = new ArrayList<>();
        components.add(backButton);
        components.add(line);
        components.add(titleText);
        components.add(this.darkMode);
        components.add(this.colorBlindMode);

        for (Component component : components) {
            this.frame.add(component);
        }

        return components;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.darkMode) {
            setDarkModeEnabled(this.darkMode.isSelected());
//            if (this.darkModeEnabled) {
//                this.frame.getContentPane().setBackground(new Color(18, 18, 18, 255));
//            } else {
//                this.frame.getContentPane().setBackground(Color.WHITE);
//            }
        } else if (e.getSource() == this.colorBlindMode) {
            setColorBlindModeEnabled(this.colorBlindMode.isSelected());
            if (this.colorBlindModeEnabled) {
                Main.setCorrectColor(new Color(245,121,58,255));
                Main.setWrongPlaceColor(new Color(133,192,249,255));
            } else {
                Main.setCorrectColor(new Color(83,141,78,255));
                Main.setWrongPlaceColor(new Color(181,159,59,255));
            }
        }
    }
    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }
    public void setColorBlindModeEnabled(boolean colorBlindModeEnabled) {
        this.colorBlindModeEnabled = colorBlindModeEnabled;
    }
    public void setButton(GamePage gamePage, MyButton button, WordboardPanel wordboard, KeyboardPanel keyboard) {
        button.setVisible(true);
        button.addActionListener(e -> {
            gamePage.getLine().setVisible(false);
            gamePage.getTitleText().setVisible(false);
            gamePage.getSettingsButton().setVisible(false);
            gamePage.getInstructionsButton().setVisible(false);
            wordboard.setVisible(false);
            keyboard.setVisible(false);
            ArrayList<Component> components;
            if (button.getName().equals("Settings")) {
                components = gamePage.displaySettings();
            } else if (button.getName().equals("Instructions")) {
                components = gamePage.displayInstructionsInGame();
            } else {
                components = null;
            }
            MyButton backButton = null;
            assert components != null;
            for (Component component : components) {
                if (component instanceof MyButton) {
                    backButton = (MyButton) component;
                }
            }
            assert backButton != null;
            backButton.addActionListener(e1 -> {
                gamePage.getLine().setVisible(true);
                gamePage.getTitleText().setVisible(true);
                gamePage.getSettingsButton().setVisible(true);
                gamePage.getInstructionsButton().setVisible(true);
                wordboard.setVisible(true);
                keyboard.setVisible(true);
                for (Component component : components) {
                    gamePage.getFrame().remove(component);
                }
                gamePage.getFrame().revalidate();
                gamePage.getFrame().repaint();
            });
        });
    }
}
