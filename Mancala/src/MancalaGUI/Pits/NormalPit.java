package MancalaGUI.Pits;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class NormalPit extends JButton {

    private int value;
    private ArrayList<MancalaStone> stones = new ArrayList<>();

    public NormalPit(int value) {
        try {
            Image image = ImageIO.read(getClass().getResource("Images/pit.png"));
            setIcon(new ImageIcon(image));
            setBorder(BorderFactory.createEmptyBorder());
            setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getStoneCount() {
        return stones.size();
    }

    public void setStones(int count) {
        if (count == stones.size()) return;

        stones.clear();
        int mid = 135 / 2;
        for (int i = 0; i < count; i++)
            stones.add(MancalaStone.createRandomStone(mid - 20, mid + 20, mid - 20, mid + 20, 20));
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
