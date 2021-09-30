/**
 * Base class for all game. Defined several general method.
 */
public abstract class BoardGame implements Rule {
    protected Board board;
    protected Team[] teams;
    protected ScoreBoard scoreBoard;

    /**
     * show the teams and players
     */
    public void showTeam() {
        System.out.println("=========TEAM=========");
        for (Team team : teams) {
            System.out.printf("Team %s:\n", team.getSymbol());
            team.showPlayers();
        }
    }

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
        board.fill(marks);
    }

    /**
     * init the board with same mark
     *
     * @param mark
     */
    public void initBoard(String mark) {
        board.fill(mark);
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
     * @return false if move invalid, true if the move success and change the board.
     */
    public abstract boolean move(int row, int column, String mark);


}
