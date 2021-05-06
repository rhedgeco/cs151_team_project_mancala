package MancalaBackend;

public class HistoryBoard {
	private static final int BOARD_SIZE = 7;
	private MancalaPit[] playerAPits = new MancalaPit[BOARD_SIZE];
	private MancalaPit[] playerBPits = new MancalaPit[BOARD_SIZE];

	public HistoryBoard(MancalaPit[] aPits, MancalaPit[] bPits) {
		save(aPits, bPits);
	}

	public void save(MancalaPit[] aPits, MancalaPit[] bPits) {
		for(int i = 0; i < aPits.length; i++) {
			playerAPits[i] = new MancalaPit(aPits[i].getContainedCluster().getStoneCount(), aPits[i].isMancala());
		}
		for(int i = 0; i < bPits.length; i++) {
			playerBPits[i] = new MancalaPit(bPits[i].getContainedCluster().getStoneCount(), bPits[i].isMancala());
		}
	}

	public MancalaPit[] getPitsOfPlayerA() {
		return playerAPits;
	}

	public MancalaPit[] getPitsOfPlayerB() {
		return playerBPits;
	}
}
