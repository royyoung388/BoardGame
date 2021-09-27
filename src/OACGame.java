import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Order and Chaos Game
 * The board size if 6, win condition is 5, only 2 teams, 2 players
 */
public class OACGame extends Game {
    private Team winner;

    public OACGame() {
        this(new Player("Order"), new Player("Chaos"));
    }

    public OACGame(Player player1, Player player2) {
        teams = new Team[]{new Team("1"), new Team("2")};
        teams[0].addPlayer(player1);
        teams[1].addPlayer(player2);

        scoreBoard = new ScoreBoard(teams);

        createBoard(6);
        initBoard("");
//        initBoard(new String[][]{{"X", "X", "O", "X", "X", "X"}
//                , {"X", "O", "O", "", "", ""}
//                , {"O", "X", "O", "O", "", ""}
//                , {"O", "O", "X", "X", "", ""}
//                , {"O", "X", "X", "O", "", ""},
//                {"X", "O", "X", "X", "", ""}});
    }

    @Override
    public void start() {
        int turn = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Oder and Chaos GAME START!");

        while (!isEnd()) {
            board.show();
            System.out.printf("Player %s Turn:\n", teams[turn].nextPlayer().getName());

            while (true) {
                try {
                    System.out.println("Please input the pieces you want to move: (X/O)");
                    String piece = input.next().toUpperCase();
                    if (!piece.equals("X") && !piece.equals("O")) {
                        System.out.println("Error: please input X or O");
                        continue;
                    }

                    System.out.println("Please input the row and column to move: ");
                    int row = input.nextInt();
                    int column = input.nextInt();
                    if (move(row, column, piece)) {
                        turn = turn == 0 ? 1 : 0;
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
            System.out.println("The winner is: Player " + (winner() == teams[0] ? "Order" : "Chaos"));
            scoreBoard.score(winner().getSymbol(), 1);
        } else {
            System.out.println("Draw! no winner");
        }

        scoreBoard.showScore();
    }

    @Override
    public void newGame() {
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

    @Override
    public boolean isEnd() {
        if (Utils.checkRow(board, 5) != null
                || Utils.checkColumn(board, 5) != null
                || Utils.checkDiagonal(board, 5) != null) {
            winner = teams[0];
            return true;
        } else if (Utils.checkFull(board)) {
            winner = teams[1];
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
}
