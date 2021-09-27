import java.util.Arrays;

/**
 * Scoreboard, record the score of team.
 */
public class ScoreBoard {
    private Team[] teams;
    private int scores[];

    public ScoreBoard(Team[] teams) {
        this.teams = teams;
        scores = new int[teams.length];
    }

    // record a score
    public void score(String symbol, int score) {
        for (int i = 0; i < teams.length; i++)
            if (symbol.equals(teams[i].getSymbol())) {
                scores[i] += score;
                break;
            }
    }

    // reset the scoreboard with 0
    public void reset() {
        Arrays.fill(scores, 0);
    }

    public void showScore() {
        System.out.println("==============Score==============");
        for (int i = 0; i < teams.length; i++) {
            System.out.printf("%6s %2s", "Team" + i, teams[i].getSymbol());
            if (i != teams.length - 1)
                System.out.print(" : ");
        }
        System.out.println();

        for (int score : scores) {
            System.out.printf("%7d  ", score);
        }
        System.out.println();
    }
}
