import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Tic-tac-toe Game.
 * The board size if 3X3, win condition is 3, only 2 teams, 2 players.
 */
public class TTTGame extends Game {
    private Team winner;

    public TTTGame() {
        this(new Player("X"), new Player("O"));
    }

    public TTTGame(Player player1, Player player2) {
        teams = new Team[]{new Team("X"), new Team("O")};
        teams[0].addPlayer(player1);
        teams[1].addPlayer(player2);

        scoreBoard = new ScoreBoard(teams);

        createBoard(3);
        initBoard("");
    }

    @Override
    public void start() {
        int turn = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("TTT GAME START!");

        while (!isEnd()) {
            board.show();
            if (turn == 0) {
                System.out.println("Player X Turn");
            } else {
                System.out.println("Player O Turn");
            }

            while (true) {
                try {
                    System.out.println("Please input the row and column to move: ");

                    int row = input.nextInt();
                    int column = input.nextInt();

                    if (move(row, column, teams[turn].getSymbol())) {
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
            System.out.println("The winner is: " + (winner() == teams[0] ? "Player X" : "Player O"));
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

    private Team findWinner(String mark) {
        if (teams[0].getSymbol().equals(mark))
            return teams[0];
        else if (teams[1].getSymbol().equals(mark))
            return teams[1];
        else
            return null;
    }

    @Override
    public boolean isEnd() {
        // check the row
        for (int i = 0; i < 3; i++) {
            if (!board.getMark(i, 0).equals("")
                    && board.getMark(i, 0).equals(board.getMark(i, 1))
                    && board.getMark(i, 0).equals(board.getMark(i, 2))) {
                winner = findWinner(board.getMark(i, 0));
                return true;
            }
        }

        //check the column
        for (int i = 0; i < 3; i++) {
            if (!board.getMark(0, i).equals("")
                    && board.getMark(0, i).equals(board.getMark(1, i))
                    && board.getMark(0, i).equals(board.getMark(2, i))) {
                winner = findWinner(board.getMark(0, i));
                return true;
            }
        }

        //check the diagonal
        if (!board.getMark(1, 1).equals("")
                && (board.getMark(0, 0).equals(board.getMark(1, 1))
                && board.getMark(0, 0).equals(board.getMark(2, 2))
                || board.getMark(0, 2).equals(board.getMark(1, 1))
                && board.getMark(2, 0).equals(board.getMark(1, 1)))) {
            winner = findWinner(board.getMark(1, 1));
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
