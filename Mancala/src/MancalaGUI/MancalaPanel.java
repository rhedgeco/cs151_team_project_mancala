package MancalaGUI;

import MancalaBackend.MancalaBoard;
import MancalaGUI.Pits.MancalaPit;
import MancalaGUI.Pits.NormalPit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MancalaPanel extends FreeLayoutPanel {

    private Image background;
    private MancalaBoard board;

    private MancalaPit playerAMancala;
    private MancalaPit playerBMancala;
    private NormalPit[] playerAPits = new NormalPit[6];
    private NormalPit[] playerBPits = new NormalPit[6];
    private JLabel label;

    public MancalaPanel(Dimension size, MancalaBoard.PitSize pitSize) {
        super(size);
        try {
            background = ImageIO.read(getClass().getResource("Images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int psize = 3;
        if(pitSize == MancalaBoard.PitSize.Four) psize = 4;
        board = new MancalaBoard(pitSize);

        MancalaPit mancala = new MancalaPit();
        add(mancala, 0, 0);
        playerBMancala = mancala;

        MancalaPit mancala2 = new MancalaPit();
        add(mancala2, 1080-135, 0);
        playerAMancala = mancala2;

        for (int i = 0; i < 6; i++) {
            NormalPit pit = new NormalPit(5-i);
            pit.setStones(psize);
            pit.addActionListener( e -> PlayerBClick(pit.getValue()));
            add(pit, 135 + (i * 135), 12);
            playerBPits[i] = pit;
        }

        for (int i = 0; i < 6; i++) {
            NormalPit pit = new NormalPit(i);
            pit.setStones(psize);
            pit.addActionListener( e -> PlayerAClick(pit.getValue()));
            add(pit, 135 + (i * 135), 540 - 135 - 12);
            playerAPits[i] = pit;
        }

        label = new JLabel("Player A Turn!                      ");
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.PLAIN, 36));
        add(label, 400, 1080/4 - 25);
    }

    private void PlayerAClick(int index) {
        if(board.getTurn() != MancalaBoard.PlayerTypes.PlayerA) return;
        if(playerAPits[index].getStoneCount() == 0) return;
        PlayerClick(index);
    }

    private void PlayerBClick(int index) {
        if(board.getTurn() != MancalaBoard.PlayerTypes.PlayerB) return;
        if(playerBPits[5 - index].getStoneCount() == 0) return;
        PlayerClick(index);
    }

    private void PlayerClick(int index) {
        if(board.isGameOver()) return;
        board.pickUpStones(index);
        board.nextTurn();
        UpdateBoard();
    }

    private void UpdateBoard() {
        MancalaBackend.MancalaPit[] apits = board.getPlayerAPits();
        MancalaBackend.MancalaPit[] bpits = board.getPlayerBPits();

        playerAMancala.setStones(apits[6].getContainedCluster().getStoneCount());
        playerBMancala.setStones(bpits[6].getContainedCluster().getStoneCount());

        for (int i = 0; i < 6; i++) {
            playerBPits[5-i].setStones(bpits[i].getContainedCluster().getStoneCount());
            playerAPits[i].setStones(apits[i].getContainedCluster().getStoneCount());
        }

        String labelText = "Player A Turn!";
        if (board.getTurn() == MancalaBoard.PlayerTypes.PlayerB) labelText = "Player B Turn!";
        if (board.isGameOver()) labelText = "Game Over!";
        label.setText(labelText);
        label.updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}
