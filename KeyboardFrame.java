import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardFrame extends JFrame {
    public KeyboardFrame() {
        setTitle("Keyboard Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 10)); // Adjust grid size as needed

        // Create buttons for each letter of the alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            addButton(String.valueOf(c));
        }

        addButton("Enter");
        addButton("Delete");

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private void addButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ButtonClickListener());
        add(button);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String command = button.getText();

            // Handle button click, e.g., append the clicked letter to a text field
            if (command.equals("Enter")) {
                // Perform Enter action
                System.out.println("Enter pressed");
            } else if (command.equals("Delete")) {
                // Perform Delete action
                System.out.println("Delete pressed");
            } else {
                // Handle alphabet button
                System.out.println("You clicked: " + command);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyboardFrame frame = new KeyboardFrame();
            frame.setVisible(true);
        });
    }
}