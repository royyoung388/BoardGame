import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomTTTGame extends Game {
    private Team winner;
    private Team[] teams;
    private int winCond;
    private ScoreBoard scoreBoard;

    public CustomTTTGame() {
        getPara();
    }

    @Override
    public void start() {
        int turn = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Custom TTT GAME START!");

        while (!isEnd()) {
            board.show();
            System.out.printf("Player %s Turn\n", teams[turn].getSymbol());

            while (true) {
                try {
                    System.out.println("Please input the row and column to move: ");

                    int row = input.nextInt();
                    int column = input.nextInt();

                    if (move(row, column, teams[turn].getSymbol())) {
                        turn = (turn + 1) % teams.length;
                        break;
                    } else {
                        System.out.println("Error: the input is invalid, please input again");
                    }
                } catch (InputMismatchException e) {
                    input.next();
                    System.out.println("Error: the input is invalid, please input again");
                }
            }
        }

        if (winner() != null) {
            System.out.println("The winner is: Player " + winner().getSymbol());
            scoreBoard.score(winner().getSymbol(), 1);
        } else {
            System.out.println("Draw! no winner");
        }

        scoreBoard.showScore();
    }

    @Override
    public void newGame() {
        while (true) {
            System.out.println("Do you want to use the same parameter? (Y/N)");
            String select = new Scanner(System.in).next().toLowerCase();
            if (select.equals("y"))
                break;
            else if (select.equals("n")) {
                getPara();
                break;
            } else
                System.out.println("Error: the input is invalid, please input again");

        }

        board.reset();
    }

    @Override
    public boolean move(int row, int column, String mark) {
        if (canMove(row, column, mark)) {
            board.move(row - 1, column - 1, mark);
            return true;
        } else {
            return false;
        }
    }

    private Team findWinner(String mark) {
        for (Team t : teams)
            if (t.getSymbol().equals(mark))
                return t;

        return null;
    }

    @Override
    public boolean isEnd() {
        // check the row
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
                if (count >= winCond) {
                    winner = findWinner(mark);
                    return true;
                }
            }
        }

        //check the column
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
                if (count >= winCond) {
                    winner = findWinner(mark);
                    return true;
                }
            }
        }

        //check the diagonal
        for (int i = 0; i <= board.getRow() - winCond; i++) {
            String s = "", bs = "";
            int count = 0, bcount = 0;

            for (int j = 0; j <= board.getColumn() - winCond; j++) {
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
                        winner = findWinner(mark);
                        return true;
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
                        winner = findWinner(bmark);
                        return true;
                    }

                    x++;
                    y++;
                }

            }
        }

        return false;
    }

    @Override
    public Team winner() {
        return winner;
    }

    @Override
    public boolean canMove(int row, int column, String mark) {
        return row > 0 && row <= board.getRow() && column > 0 && column <= board.getColumn()
                && board.getMark(row - 1, column - 1).equals("");
    }

    public void getPara() {
        Scanner input = new Scanner(System.in);
        int teamNum, playerNum, row, column, winCond;
        String[] symbols;

        System.out.println("Custom TTT GAME Settings:");


        while (true) {
            try {
                // input team number
                System.out.println("Please input the total number of teams:");
                teamNum = input.nextInt();
                symbols = new String[teamNum];

                // input team symbol
                for (int i = 0; i < teamNum; i++) {
                    System.out.println("Please input the symbol of TEAM " + (i + 1));
                    symbols[i] = input.next();
                    if (symbols[i].length() > 1) {
                        System.out.println("Error: The length of symbol must equal 1, Please input again.");
                        i--;
                    }
                }

                // input player number
                while (true) {
                    System.out.println("Please input the total number of players:");
                    playerNum = input.nextInt();
                    if (playerNum < teamNum)
                        System.out.println("Error: the number of player must not less than number of team.");
                    else
                        break;
                }

                // input the row and column
                while (true) {
                    System.out.println("Please input the number of rows and columns:");
                    row = input.nextInt();
                    column = input.nextInt();
                    if (row <= 0 || column <= 0)
                        System.out.println("Error: the number of rows and columns must larger than 0.");
                    else
                        break;
                }

                // input the win condition
                while (true) {
                    System.out.println("Please input the win condition:");
                    winCond = input.nextInt();
                    if (winCond < 1 || winCond > Math.max(row, column))
                        System.out.println("Error: there is no winner under the win condition.");
                    else
                        break;
                }

                break;
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Error: the input is invalid, please input again.");
            }
        }

        this.winCond = winCond;

        // create teams and players
        teams = new Team[teamNum];
        for (int i = 0; i < teamNum; i++) {
            teams[i] = new Team(symbols[i]);
        }
        for (int i = 0; i < playerNum; i++) {
            teams[i % teamNum].addPlayer(new Player(symbols[i % teamNum]));
        }
        scoreBoard = new ScoreBoard(teams);

        createBoard(row, column);
        initBoard("");
    }
}
