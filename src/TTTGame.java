import java.util.Scanner;

public class TTTGame extends Game {
    private Player winner, player1, player2;
    private ScoreBoard scoreBoard;

    public TTTGame() {
        this(new Player("X"), new Player("O"));
    }

    public TTTGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        scoreBoard = new ScoreBoard(2);
        scoreBoard.addTeams(new String[]{player1.getSymbol(), player2.getSymbol()});

        createBoard(3);
        initBoard("");
    }

    @Override
    public void start() {
        Player turn = player1;
        Scanner input = new Scanner(System.in);
        System.out.println("TTT GAME START!");

        while (!isEnd()) {
            board.show();
            if (turn == player1) {
                System.out.println("Player 1 Turn");
            } else {
                System.out.println("Player 2 Turn");
            }

            while (true) {
                try {
                    System.out.println("Please input the row and column to move: ");

                    int row = input.nextInt();
                    int column = input.nextInt();

                    if (move(row, column, turn.getSymbol())) {
                        turn = turn == player1 ? player2 : player1;
                        break;
                    } else {
                        System.out.println("Error: the input is invalid, please input again");
                    }
                } catch (Exception ignored) {
                    System.out.println("Error: the input is invalid, please input again");
                }
            }
        }

        if (winner() != null) {
            System.out.println("The winner is: " + (winner() == player1 ? "Player 1" : "Player 2"));
            scoreBoard.score(winner().getSymbol(), 1);
        } else {
            System.out.println("Draw! no winner");
        }

        scoreBoard.showScore();
    }

    @Override
    public void reset() {
        board.reset();
        scoreBoard.reset();
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

    private Player getWinner(String mark) {
        if (player1.getSymbol().equals(mark))
            return player1;
        else if (player2.getSymbol().equals(mark))
            return player2;
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
                winner = getWinner(board.getMark(i, 0));
                return true;
            }
        }

        //check the column
        for (int i = 0; i < 3; i++) {
            if (!board.getMark(0, i).equals("")
                    && board.getMark(0, i).equals(board.getMark(1, i))
                    && board.getMark(0, i).equals(board.getMark(2, i))) {
                winner = getWinner(board.getMark(0, i));
                return true;
            }
        }

        //check the diagonal
        if (!board.getMark(1, 1).equals("")
                && (board.getMark(0, 0).equals(board.getMark(1, 1))
                && board.getMark(0, 0).equals(board.getMark(2, 2))
                || board.getMark(0, 2).equals(board.getMark(1, 1))
                && board.getMark(2, 0).equals(board.getMark(1, 1)))) {
            winner = getWinner(board.getMark(1, 1));
            return true;
        }

        return false;
    }

    @Override
    public Player winner() {
        return winner;
    }

    @Override
    public boolean canMove(int row, int column, String mark) {
        return row > 0 && row <= board.getRow() && column > 0 && column <= board.getColumn()
                && board.getMark(row - 1, column - 1).equals("");
    }
}
