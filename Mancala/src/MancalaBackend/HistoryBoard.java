package MancalaBackend;

public class HistoryBoard {
	private static final int BOARD_SIZE = 7;
	private MancalaPit[] playerAPits = new MancalaPit[BOARD_SIZE];
	private MancalaPit[] playerBPits = new MancalaPit[BOARD_SIZE];

	public HistoryBoard(MancalaPit[] aPits, MancalaPit[] bPits) {
		playerAPits = aPits;
		playerBPits = bPits;
	}

	public void save(MancalaPit[] aPits, MancalaPit[] bPits) {
		playerAPits = aPits;
		playerBPits = bPits;
	}

	public MancalaPit[] getPitsOfPlayerA() {
		return playerAPits;
	}

	public MancalaPit[] getPitsOfPlayerB() {
		return playerBPits;
	}
}
