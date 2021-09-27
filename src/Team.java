import java.util.ArrayList;
import java.util.List;

public class Team {
    private String symbol;
    private ArrayList<Player> players;
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

    public Player next() {
        if (point >= players.size())
            point = 0;
        return players.get(point++);
    }

    public String getSymbol() {
        return symbol;
    }
}
