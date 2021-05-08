package MancalaGUI;

import MancalaBackend.MancalaBoard;
import MancalaGUI.Pits.MancalaPit;
import MancalaGUI.Pits.NormalPit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MancalaPanel extends FreeLayoutPanel {

    private Image background;
    private MancalaBoard board;

    private MancalaPit playerAMancala;
    private MancalaPit playerBMancala;
    private NormalPit[] playerAPits = new NormalPit[6];
    private NormalPit[] playerBPits = new NormalPit[6];
    private JTextArea label;
    private boolean isClickable;

    public MancalaPanel(Dimension size, MancalaBoard.PitSize pitSize, MancalaBoard board) {
        super(size);
        try {
            background = ImageIO.read(getClass().getResource("Images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int psize = 3;
        if(pitSize == MancalaBoard.PitSize.Four) psize = 4;
        this.board = board;

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
        
        isClickable = true;
        
        label = new JTextArea("Player A Turn! ");
        label.setFocusable(false);
        label.setBackground(new Color(0,0,0,0));
        label.setOpaque(false);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.PLAIN, 36));
        
        add(label, 400, 1080/4 - 25);
    }
    
    public boolean passTurn() {
    	if(!isClickable) {
    		board.nextTurn();
    		isClickable = true;
    		return true;
    	} else {
    		return false;
    	}
    }

    private void PlayerAClick(int index) {
        if(board.getTurn() != MancalaBoard.PlayerTypes.PlayerA) return;
        if(playerAPits[index].getStoneCount() == 0) return;
        if(!isClickable) return;
        PlayerClick(index);
    }

    private void PlayerBClick(int index) {
        if(board.getTurn() != MancalaBoard.PlayerTypes.PlayerB) return;
        if(playerBPits[5 - index].getStoneCount() == 0) return;
        if(!isClickable) return;
        PlayerClick(index);
    }

    private void PlayerClick(int index) {
        if(board.isGameOver()) return;
        if(board.pickUpStones(index)) { 	// If the player couldn't gain free turn, pass the turn.
			isClickable = true;
        } else {
        	isClickable = false;
        }
        updateBoard();
    }

    public void updateBoard() {
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
        repaint();
    }
    
    public void setClickable(boolean change) {
    	isClickable = change;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}
