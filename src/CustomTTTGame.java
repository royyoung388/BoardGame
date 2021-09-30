import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Custom tic-tac-toe game.
 * Get parameter by getPara(), every input will be checked.
 */
public class CustomTTTGame extends BoardGame {
    private Team winner;
    private int winCond;
    private Scanner input;

    public CustomTTTGame() {
        getPara();
    }

    @Override
    public void start() {
        int turn = 0;
        input = new Scanner(System.in);
        System.out.println("Custom TTT GAME START!");

        while (!isEnd()) {
            board.show();
            System.out.printf("Team %s,Player %s Turn\n", teams[turn].getSymbol(), teams[turn].nextPlayer().getName());

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

        board.show();
        if (winner() != null) {
            System.out.println("The winner is: Team " + winner().getSymbol());
            scoreBoard.score(winner().getSymbol(), 1);
        } else {
            System.out.println("Draw! no winner");
        }

        scoreBoard.showScore();
    }

    @Override
    public void newGame() {
        while (true) {
            // use the same parameter
            System.out.println("Do you want to use the same parameter? (Y/N)");
            String select = input.next().toLowerCase();
            if (select.equals("y"))
                break;
            else if (select.equals("n")) {
                getPara();
                break;
            } else
                System.out.println("Error: the input is invalid, please input again");

        }

        board.fill();
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
        String mark;
        if ((mark = Utils.checkRow(board, winCond)) != null
                || (mark = Utils.checkColumn(board, winCond)) != null
                || (mark = Utils.checkDiagonal(board, winCond)) != null) {
            winner = findWinner(mark);
            return true;
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
                        System.out.println("Error: The length of symbol must equal 1, please input again.");
                        i--;
                    } else
                        for (int j = 0; j < i; j++)
                            if (symbols[i].equals(symbols[j])) {
                                System.out.println("Error: The symbol has been used, please input a new one:");
                                i--;
                                break;
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
            teams[i % teamNum].addPlayer(new Player(String.valueOf(i + 1)));
        }
        showTeam();

        scoreBoard = new ScoreBoard(teams);

        createBoard(row, column);
        initBoard("");
    }
}
