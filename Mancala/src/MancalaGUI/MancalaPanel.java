package MancalaGUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MancalaPanel extends FreeLayoutPanel {

    private Image background;

    public MancalaPanel(Dimension size) {
        super(size);

        try {
            background = ImageIO.read(getClass().getResource("Images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this);
    }
}
