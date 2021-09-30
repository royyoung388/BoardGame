import java.util.ArrayList;
import java.util.List;

/**
 * Team, every team could have multiply players.
 */
public class Team {
    private String symbol;
    private List<Player> players;
    private int point = 0;

    public Team(String symbol) {
        this.symbol = symbol;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addPlayers(Player[] players) {
        this.players.addAll(List.of(players));
    }

    // the next player to play game.
    public Player nextPlayer() {
        if (point >= players.size())
            point = 0;
        return players.get(point++);
    }

    // show all players of this team
    public void showPlayers() {
        for (Player p : players)
            System.out.printf("  Player %s  ", p.getName());
        System.out.println();
    }

    public String getSymbol() {
        return symbol;
    }
}
