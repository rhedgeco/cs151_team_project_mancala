package MancalaGUI;

import MancalaBackend.MancalaBoard;

import javax.swing.*;
import java.awt.*;

public class StartingFrame extends JFrame {
    public StartingFrame() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = new JLabel("Select a Pit Size");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        container.add(label, c);

        JButton three = new JButton("Three");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        container.add(three, c);
        three.addActionListener( e -> StartGame(MancalaBoard.PitSize.Three));

        JButton four = new JButton("Four");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        container.add(four, c);
        four.addActionListener( e -> StartGame(MancalaBoard.PitSize.Four));

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void StartGame(MancalaBoard.PitSize size) {
        JFrame gameFrame = new GameFrame(size);
        setVisible(false);
        gameFrame.setVisible(true);
    }
}
