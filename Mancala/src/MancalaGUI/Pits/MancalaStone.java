package MancalaGUI.Pits;

import java.awt.*;
import java.util.Random;

public class MancalaStone {

    private int x;
    private int y;
    private float radius;
    private Color color;

    public MancalaStone(int x, int y, float radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public static MancalaStone createRandomStone(int xmin, int xmax, int ymin, int ymax, float radius) {
        Random rand = new Random();
        float hue = rand.nextFloat();
        int col = Color.HSBtoRGB(hue, 1, 1);

        int x = (int)((rand.nextFloat() * (xmax - xmin)) + xmin);
        int y = (int)((rand.nextFloat() * (ymax - ymin)) + ymin);
        return new MancalaStone(x, y, radius, new Color(col));
    }
}
