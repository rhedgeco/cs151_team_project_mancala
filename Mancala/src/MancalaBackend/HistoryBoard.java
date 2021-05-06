package MancalaBackend;

/**
 * HistoryBoard contains the information of board in past. *
 */
public class HistoryBoard {
	private static final int BOARD_SIZE = 7;
	private MancalaPit[] playerAPits = new MancalaPit[BOARD_SIZE];
	private MancalaPit[] playerBPits = new MancalaPit[BOARD_SIZE];

	/**
	 * Construct HistoryBoard object with the data of board.
	 * @param aPits - playerAPits
	 * @param bPits - playerBPits
	 */
	public HistoryBoard(MancalaPit[] aPits, MancalaPit[] bPits) {
		save(aPits, bPits);
	}

	/**
	 * Save the board data by copying information.
	 * @param aPits - playerAPits
	 * @param bPits - playerBPits
	 */
	public void save(MancalaPit[] aPits, MancalaPit[] bPits) {
		for(int i = 0; i < aPits.length; i++) {
			playerAPits[i] = new MancalaPit(aPits[i].getContainedCluster().getStoneCount(), aPits[i].isMancala());
		}
		for(int i = 0; i < bPits.length; i++) {
			playerBPits[i] = new MancalaPit(bPits[i].getContainedCluster().getStoneCount(), bPits[i].isMancala());
		}
	}

	/**
	 * Get player A's pits information
	 * @return playerAPits
	 */
	public MancalaPit[] getPitsOfPlayerA() {
		return playerAPits;
	}

	/**
	 * Get player B's pits information
	 * @return playerBPits
	 */
	public MancalaPit[] getPitsOfPlayerB() {
		return playerBPits;
	}
}
