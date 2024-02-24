import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    /*
     * Constructor for MyLabel with personalized settings of a JLabel.
     */
    public MyLabel(ImageIcon icon, String text, int horizontalTextPosition, int verticalTextPosition,
                   Color foregroundColor, Font font, int iconTextGap, Color backgroundColor, int verticalAlignment,
                   int horizontalAlignment, int x, int y, int width, int height) {
        this.setText(text);
        this.setIcon(icon);
        this.setHorizontalTextPosition(horizontalTextPosition);
        this.setVerticalTextPosition(verticalTextPosition);
        this.setForeground(foregroundColor);
        this.setFont(font);
        this.setIconTextGap(iconTextGap);
        this.setBackground(backgroundColor);
        this.setOpaque(true);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setBounds(x, y, width, height);
    }
}
