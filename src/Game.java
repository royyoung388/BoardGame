/**
 * Base class for all game. Define several general method.
 */
public abstract class Game implements Rule {
    protected Board board;

    /**
     * create a square board
     *
     * @param length
     */
    public void createBoard(int length) {
        createBoard(length, length);
    }

    /**
     * create a board with row and column
     *
     * @param row
     * @param column
     */
    public void createBoard(int row, int column) {
        board = new Board(row, column);
    }

    /**
     * init the board with specific marks
     *
     * @param marks
     */
    public void initBoard(String[][] marks) {
        board.reset(marks);
    }

    /**
     * init the board with same mark
     *
     * @param mark
     */
    public void initBoard(String mark) {
        board.reset(mark);
    }

    /**
     * start the game
     */
    public abstract void start();

    /**
     * start a new game
     */
    public abstract void newGame();

    /**
     * move the chess, change the mark on the board
     *
     * @param row
     * @param column
     * @param mark
     * @return false if can not move, true if move valid
     */
    public abstract boolean move(int row, int column, String mark);
}
