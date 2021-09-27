import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = null;
        Scanner input = new Scanner(System.in);
        int flag = 1;

        while (flag != 0) {
            flag = 1;
            System.out.println("Input number to Select your game");
            System.out.println("1. Tic Tac Toe Game");
            System.out.println("2. Order And Chaos Game");
            System.out.println("3. Custom Tic Tac Toe Game");
            System.out.println("0. Exit");

            String select = input.next();
            switch (select) {
                case "1":
                    game = new TTTGame();
                    game.start();
                    break;
                case "2":
                    game = new OACGame();
                    game.start();
                    break;
                case "3":
                    game = new CustomTTTGame();
                    game.start();
                    break;
                case "0":
                    flag = 0;
                    break;
                default:
                    System.out.println("Error: the input is invalid, please input again");
                    flag = 2;
                    break;
            }

            while (flag == 1) {
                System.out.println("Do you want to play the game again? (Y/N)");
                select = input.next().toLowerCase();

                if (select.equals("y")) {
                    game.newGame();
                    game.start();
                } else if (select.equals("n"))
                    flag = 2;
                else
                    System.out.println("Error: the input is invalid, please input again");
            }
        }
    }
}
