package MancalaBackend.StateSystem;

import MancalaBackend.MancalaBoard;
import MancalaBackend.MancalaPit;

/**
 * MancalaState is an object that holds data representing the current state of a mancala board
 */
public class MancalaState {

    private static final int PLAYER_PIT_LENGTH = 7;

    private MancalaBoard.PlayerTypes currentTurn;
    private int[] playerAPits;
    private int[] playerBPits;

    public MancalaState(MancalaBoard.PlayerTypes currentTurn,
                        MancalaPit[] playerAPits,
                        MancalaPit[] playerBPits) throws InvalidPlayerPitException {
        if (playerAPits.length != PLAYER_PIT_LENGTH && playerBPits.length != PLAYER_PIT_LENGTH)
            throw new InvalidPlayerPitException();

        this.currentTurn = currentTurn;

        this.playerAPits = new int[PLAYER_PIT_LENGTH];
        this.playerBPits = new int[PLAYER_PIT_LENGTH];
        for(int i = 0; i < PLAYER_PIT_LENGTH; i++) {
            this.playerAPits[i] = playerAPits[i].getStoneCount();
            this.playerBPits[i] = playerBPits[i].getStoneCount();
        }
    }

    public MancalaBoard.PlayerTypes getCurrentTurn() {
        return currentTurn;
    }

    public int[] getPlayerAPits() {
        return playerAPits;
    }

    public int[] getPlayerBPits() {
        return playerBPits;
    }
}
