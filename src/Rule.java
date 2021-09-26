/***
 * Interface of game rule
 */
public interface Rule {
    /**
     * Is the game end?
     * @return
     */
    boolean isEnd();

    /**
     * Who is the winner?
     * @return
     */
    Player winner();

    /**
     * Is the move valid?
     * @return
     * @param row
     * @param column
     * @param mark
     */
    boolean canMove(int row, int column, String mark);
}
