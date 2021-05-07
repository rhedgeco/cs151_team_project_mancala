package MancalaGUI;

import javax.swing.*;
import java.awt.*;

public class FreeLayoutPanel extends JPanel {

    private Insets insets;

    public FreeLayoutPanel(Dimension size) {
        setLayout(null);
        insets = getInsets();
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public Component add(Component c, int x, int y) {
        add(c);

        Dimension size = c.getPreferredSize();
        c.setBounds(x + insets.left, y + insets.top, size.width, size.height);

        return c;
    }
}
