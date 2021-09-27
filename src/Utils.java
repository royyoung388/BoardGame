/**
 * General methods to check the board with win condition.
 */
public class Utils {
    /**
     * check the row
     *
     * @param board
     * @param winCond
     * @return
     */
    public static String checkRow(Board board, int winCond) {
        for (int i = 0; i < board.getRow(); i++) {
            String s = "";
            int count = 0;
            for (int j = 0; j < board.getColumn(); j++) {
                // find the longest mark
                String mark = board.getMark(i, j);
                if (!mark.equals("")) {
                    if (mark.equals(s))
                        count++;
                    else {
                        s = mark;
                        count = 1;
                    }
                } else {
                    s = "";
                    count = 0;
                }

                // judge winner by the length
                if (count >= winCond)
                    return mark;
            }
        }
        return null;
    }

    /**
     * check the column
     *
     * @param board
     * @param winCond
     * @return
     */
    public static String checkColumn(Board board, int winCond) {
        for (int i = 0; i < board.getColumn(); i++) {
            String s = "";
            int count = 0;
            for (int j = 0; j < board.getRow(); j++) {
                // find the longest mark
                String mark = board.getMark(j, i);
                if (!mark.equals("")) {
                    if (mark.equals(s))
                        count++;
                    else {
                        s = mark;
                        count = 1;
                    }
                } else {
                    s = "";
                    count = 0;
                }

                // judge winner by the length
                if (count >= winCond)
                    return mark;
            }
        }
        return null;
    }

    /**
     * check the diagonal
     *
     * @param board
     * @param winCond
     * @return
     */
    public static String checkDiagonal(Board board, int winCond) {
        for (int i = 0; i <= board.getRow() - winCond; i++)
            for (int j = 0; j <= board.getColumn() - winCond; j++) {
                String s = "", bs = "";
                int count = 0, bcount = 0;

                // stop early
                if (i != 0 && j > 0)
                    break;

                // start point
                int x = i, y = j;
                while (x < board.getRow() && y < board.getColumn()) {
                    // find the longest mark
                    String mark = board.getMark(x, y);
                    String bmark = board.getMark(x, board.getRow() - y - 1);

                    if (!mark.equals("")) {
                        if (mark.equals(s))
                            count++;
                        else {
                            s = mark;
                            count = 1;
                        }
                    } else {
                        s = "";
                        count = 0;
                    }

                    // judge winner by the length
                    if (count >= winCond) {
                        return mark;
                    }

                    // back diagonal
                    if (!bmark.equals("")) {
                        if (bmark.equals(bs))
                            bcount++;
                        else {
                            bs = bmark;
                            bcount = 1;
                        }
                    } else {
                        bs = "";
                        bcount = 0;
                    }

                    // judge winner by the length
                    if (bcount >= winCond) {
                        return bmark;
                    }

                    x++;
                    y++;
                }

            }

        return null;
    }

    /**
     * check the board is full or has empty
     *
     * @param board
     * @return
     */
    public static boolean checkFull(Board board) {
        for (int i = 0; i < board.getRow(); i++)
            for (int j = 0; j < board.getColumn(); j++)
                if (board.getMark(i, j).equals(""))
                    return false;
        return true;
    }
}
