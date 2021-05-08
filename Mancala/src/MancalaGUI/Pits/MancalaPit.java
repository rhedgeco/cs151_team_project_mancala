package MancalaGUI.Pits;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MancalaPit extends JButton {
    public MancalaPit() {
        try {
            Image image = ImageIO.read(getClass().getResource("Images/mancala.png"));
            setIcon(new ImageIcon(image));
            setBorder(BorderFactory.createEmptyBorder());
            setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
