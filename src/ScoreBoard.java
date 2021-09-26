import java.util.ArrayList;
import java.util.Arrays;

public class ScoreBoard {
    private ArrayList<String> team;
    private int scores[];

    public ScoreBoard(int teamNum) {
        team = new ArrayList<>(teamNum);
        scores = new int[teamNum];
    }

    public void addTeam(int teamNo, String symbol) {
        team.add(symbol);
    }

    public void addTeams(String[] symbols) {
        team.addAll(Arrays.asList(symbols));
    }

    public void score(String symbol, int score) {
        int index = team.indexOf(symbol);
        scores[index] += score;
    }

    public void reset() {
        Arrays.fill(scores, 0);
    }

    public void showScore() {
        System.out.println("==============Score==============");
        for (int i = 0; i < team.size(); i++) {
            System.out.printf("%6s %2s", "Team" + i, team.get(i));
            if (i != team.size() - 1)
                System.out.print(" : ");
        }
        System.out.println();

        for (int score : scores) {
            System.out.printf("%7d  ", score);
        }
        System.out.println();
    }
}
