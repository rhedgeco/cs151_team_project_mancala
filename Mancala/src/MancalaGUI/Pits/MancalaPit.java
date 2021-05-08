package MancalaGUI.Pits;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MancalaPit extends JButton {

    private ArrayList<MancalaStone> stones = new ArrayList<>();

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

    public void setStones(int count) {
        if (count == stones.size()) return;

        stones.clear();
        int mid1 = 135 / 2;
        int mid2 = 1080 / 4;
        for (int i = 0; i < count; i++)
            stones.add(MancalaStone.createRandomStone(mid1 - 20, mid1 + 20, mid2 - 200, mid2 + 200, 20));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (MancalaStone stone : stones) {
            g.setColor(stone.getColor());
            g.fillOval(
                    (int)(stone.getX() - stone.getRadius()),
                    (int)(stone.getY() - stone.getRadius()),
                    (int)(stone.getRadius() * 2),
                    (int)(stone.getRadius() * 2)
            );
            g.setColor(Color.black);
            g.drawOval(
                    (int)(stone.getX() - stone.getRadius()),
                    (int)(stone.getY() - stone.getRadius()),
                    (int)(stone.getRadius() * 2),
                    (int)(stone.getRadius() * 2)
            );
        }
    }
}
