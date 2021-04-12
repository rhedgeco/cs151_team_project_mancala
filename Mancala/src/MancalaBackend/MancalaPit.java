package MancalaBackend;

/***
 * MancalaPit represents a pit on the mancala board.
 * It may be a normal pit, or a mancala pit.
 */
public class MancalaPit {

    private boolean isMancala;
    private MancalaStoneCluster stones;

    /***
     * Creates a new mancala pit
     * @param stoneCount number of new stones to put in the pit
     * @param isMancala is this a mancala pit?
     */
    public MancalaPit(int stoneCount, boolean isMancala) {
        this.isMancala = isMancala;
        this.stones = new MancalaStoneCluster(stoneCount);
    }

    /***
     * Removes all stones in the mancala pit
     * @return Mancala cluster with all stones in the pit
     */
    public MancalaStoneCluster pullStones() {
        MancalaStoneCluster cluster = stones;
        stones = new MancalaStoneCluster(0);
        return cluster;
    }

    /***
     * passes a mancala cluster over the pit and drops one stone
     * @param cluster cluster that is passing over the pit
     * @return boolean that represents a failed or successful drop
     * will return false if there are no stones in the cluster
     */
    public boolean passStones(MancalaStoneCluster cluster) {
        MancalaStone stone = cluster.releaseStone();
        if (stone == null) return false;

        stones.addStone(stone);
        return true;
    }

    /***
     * Gets the number of stones in the pit
     * @return number stones in the pit
     */
    public int getStoneCount() {
        return stones.getStoneCount();
    }
}
