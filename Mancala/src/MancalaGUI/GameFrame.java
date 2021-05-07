package MancalaGUI;

import MancalaBackend.MancalaBoard;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    //ImageIO.read(getClass().getResource("Images/pit.png"));

    public GameFrame(MancalaBoard.PitSize size) {
        setTitle("Mancala");
        MancalaPanel layoutPanel = new MancalaPanel(new Dimension(1080, 1080 / 2));
        getContentPane().add(layoutPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
