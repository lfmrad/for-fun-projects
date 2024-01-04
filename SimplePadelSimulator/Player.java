import java.util.HashMap;
import java.util.Map;

public class Player {
    private int scoredPoints;
    private String alias;
    private Team team;
    private static Map<Integer, Player> playersWithId = new HashMap<>();
    private static int idCounter = 0;
    
    public Player(String alias, Team team) {
        this.alias = alias;
        this.scoredPoints = 0;
        playersWithId.put(idCounter++, this);
    }

    public String getAlias() {
        return alias;
    }

    public static Player getPlayerById(int id) {
        return playersWithId.get(id);
    }

    public void scoredPoint() {
        scoredPoints++;
    }

    public Team getTeam() {
        return team;
    }
}

