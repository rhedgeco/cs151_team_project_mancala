package MancalaGUI;

import MancalaGUI.Pits.MancalaPit;
import MancalaGUI.Pits.NormalPit;

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

        // layout testing
        // TODO: send info to backend
        MancalaPit mancala = new MancalaPit();
        mancala.addActionListener( e -> System.out.println("click lol"));
        add(mancala, 0, 0);

        MancalaPit mancala2 = new MancalaPit();
        mancala2.addActionListener( e -> System.out.println("click lol"));
        add(mancala2, 1080-135, 0);

        for (int i = 0; i < 6; i++) {
            NormalPit pit = new NormalPit();
            pit.addActionListener( e -> System.out.println("click lol"));
            add(pit, 135 + (i * 135), 12);
        }

        for (int i = 0; i < 6; i++) {
            NormalPit pit = new NormalPit();
            pit.addActionListener( e -> System.out.println("click lol"));
            add(pit, 135 + (i * 135), 540 - 135 - 12);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this);
    }
}
