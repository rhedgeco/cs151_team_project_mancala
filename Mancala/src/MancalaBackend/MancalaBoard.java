package MancalaBackend;

/***
 * MancalaBoard represents a mancala board and controls the flow of the game
 */
public class MancalaBoard {

	private static final int BOARD_SIZE = 7;
    private PlayerTypes turn = PlayerTypes.PlayerA;
    private MancalaPit[] playerAPits = new MancalaPit[BOARD_SIZE];
    private MancalaPit[] playerBPits = new MancalaPit[BOARD_SIZE];
    
    private int undoCount;
    private boolean isUndoable;
    private HistoryBoard prevBoard;

    /***
     * Creates a new mancala board
     * @param size the starting number of stones to go in each players pits
     */
    public MancalaBoard(PitSize size) {
        startNewGame(size);
    }

    /***
     * starts a new mancala game
     * @param size the starting number of stones to go in each player pits
     */
    public void startNewGame(PitSize size) {
        int pitSize = 3;
        if (size == PitSize.Four) pitSize = 4;

        for (int i = 0; i < BOARD_SIZE-1; i++) {
            playerAPits[i] = new MancalaPit(pitSize, false);
            playerBPits[i] = new MancalaPit(pitSize, false);
        }
        playerAPits[BOARD_SIZE-1] = new MancalaPit(0, true);
        playerBPits[BOARD_SIZE-1] = new MancalaPit(0, true);
        
        prevBoard = new HistoryBoard(playerAPits, playerBPits);
        
        undoCount = 3;
        isUndoable = false;

        turn = PlayerTypes.PlayerA;
    }

    /***
     * gets the current players turn
     * @return the current players turn
     */
    public PlayerTypes getTurn() {
        return turn;
    }
    
    /**
     * Get the player A's pit list.
     * @return player A pits
     */
    public MancalaPit[] getPlayerAPits() {
    	return playerAPits;
    }
    
    /**
     * Get the player B's pit list.
     * @return player B pits
     */
    public MancalaPit[] getPlayerBPits() {
    	return playerBPits;
    }
    
    /**
     * Switch the turn.
     */
    public void nextTurn() {
    	turn = turn == PlayerTypes.PlayerA ? PlayerTypes.PlayerB : PlayerTypes.PlayerA;
    	undoCount = 3;
    	isUndoable = false;
    }
    
    /**
     * The player who has turn pick up stones from the certain pit and placing them.
     * @param targetPit - the index of pit that the player chose
     * @return True if the player gains free turn, false if not.
     */
    public boolean pickUpStones(int targetPit) {
    	System.out.println(targetPit);
    	prevBoard.save(playerAPits, playerBPits);
    	isUndoable = true;
    	
    	boolean freeTurn = false;
    	MancalaPit[] playerPit = turn == PlayerTypes.PlayerA ? playerAPits : playerBPits;
    	MancalaPit[] opponentPit = turn == PlayerTypes.PlayerA ? playerBPits : playerAPits;
    	MancalaPit[] currentPit = playerPit;
    	
    	//Pull the stones
    	MancalaStoneCluster cluster = playerPit[targetPit].pullStones();
    	
    	//Place the stones
    	boolean isPlayerPit = true;
    	boolean passed = true;
    	
    	while(passed) {
    		targetPit++;
    		//Skip the opponent mancala
    		if(!isPlayerPit && targetPit == BOARD_SIZE-1) {
    			targetPit++;
    		}
    		
    		//Switch the side
    		if(targetPit >= BOARD_SIZE) {
    			targetPit = 0;
    			isPlayerPit = !isPlayerPit;
    			currentPit = isPlayerPit ? playerPit : opponentPit;
    		}
    		
    		//Place a stones
    		currentPit[targetPit].passStones(cluster);
    		passed = cluster.getStoneCount() <= 0 ? false : true;
    		
    		//Check if the last stone was placing on the empty pit or the mancala.    		
    		if(cluster.getStoneCount() == 0) {
    			if(currentPit[targetPit].isMancala()) {
    				freeTurn = true;
    			} else if(currentPit[targetPit].getContainedCluster().getStoneCount() == 1) {
        			playerPit[BOARD_SIZE-1].addStones(opponentPit[BOARD_SIZE-2-targetPit].pullStones());
    				playerPit[BOARD_SIZE-1].addStones(currentPit[targetPit].pullStones());
        		}
    		}
    	}
    	
    	return freeTurn;
    }
    
    /**
     * Check the game is over or not. Game is over when the stones in pits are gone.
     * @return True if game is over, false if not.
     */
    public boolean isGameOver() {
    	boolean isAEmpty = true;
    	boolean isBEmpty = true;
    	for(MancalaPit p : playerAPits) {
    		if(!p.isMancala() && p.getContainedCluster().getStoneCount() != 0) {
    			isAEmpty = false;
    		}
    	}
    	for(MancalaPit p : playerBPits) {
    		if(!p.isMancala() && p.getContainedCluster().getStoneCount() != 0) {
    			isBEmpty = false;
    		}
    	}
    	return isAEmpty && isBEmpty;
    }
    
    /**
     * Go back to the previous state of board.
     * @return true if undo the board successfully, false if not.
     */
    public boolean undo() {
    	if(undoCount == 0 || !isUndoable) {
    		return false;
    	}
    	MancalaPit[] aPits = prevBoard.getPitsOfPlayerA();
    	MancalaPit[] bPits = prevBoard.getPitsOfPlayerB();
    	for(int i = 0; i < aPits.length; i++) {
			playerAPits[i] = new MancalaPit(aPits[i].getContainedCluster().getStoneCount(), aPits[i].isMancala());
		}
		for(int i = 0; i < bPits.length; i++) {
			playerBPits[i] = new MancalaPit(bPits[i].getContainedCluster().getStoneCount(), bPits[i].isMancala());
		}
    	undoCount--;
    	isUndoable = false;
    	return true;
    }
    
    /**
     * Get the undo count.
     * @return undoCount
     */
    public int getUndoCount() {
    	return undoCount;
    }

    /***
     * Enum that represents the two types of players
     */
    public enum PlayerTypes {
        PlayerA,
        PlayerB
    }

    /***
     * Enum that represents the possible starting sizes
     */
    public enum PitSize {
        Three,
        Four
    }
}
