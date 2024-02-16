import javax.swing.*;

public class MyButton extends JButton {
    public MyButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
    }
}
