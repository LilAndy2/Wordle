import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MyLabel myLabel = new MyLabel(new ImageIcon("utils/wordle.jpg"), "Welcome to Wordle!", JLabel.CENTER, JLabel.TOP,
                                     new Color(255,255,255), new Font("MV Boli", Font.PLAIN, 50), -75,
                                     new Color(0,0,0), JLabel.CENTER, JLabel.CENTER, 150, 250, 500, 500);

        MyFrame myFrame = new MyFrame();
        myFrame.add(myLabel);
        //myFrame.pack();
    }
}
