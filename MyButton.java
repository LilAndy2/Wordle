import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String text, int x, int y, int width, int height, Font font, Color backgroundColor, Color foregroundColor) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFont(font);
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setForeground(foregroundColor);
    }
}
