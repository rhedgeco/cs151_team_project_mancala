package MancalaGUI;

import MancalaBackend.MancalaBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    private MancalaBoard board;

    public GameFrame(MancalaBoard.PitSize size) {
        setTitle("Mancala");
        this.setLayout(new FlowLayout());
        board = new MancalaBoard(size);
        MancalaPanel layoutPanel = new MancalaPanel(new Dimension(1080, 1080 / 2), size, board);
        JPanel undoPanel = new JPanel();
        undoPanel.setLayout(new BoxLayout(undoPanel, BoxLayout.Y_AXIS));
        
        JTextArea undoCountText = new JTextArea("Undo Count: " + board.getUndoCount());
        undoCountText.setFocusable(false);
		JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(board.undo()) {
					undoCountText.setText("Undo Count: " + board.getUndoCount());
					layoutPanel.setClickable(true);
					layoutPanel.updateBoard();
				}
			}
        });
        JButton passButton = new JButton("Pass");
        passButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layoutPanel.passTurn();
				undoCountText.setText("Undo Count: " + board.getUndoCount());
				layoutPanel.updateBoard();
			}
        });
        undoPanel.add(undoCountText);
        undoPanel.add(undoButton);
        undoPanel.add(passButton);
        
        
        add(layoutPanel);
        add(undoPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
