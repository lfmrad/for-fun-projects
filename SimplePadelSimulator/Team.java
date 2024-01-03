import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private int score;
    private String alias;
    private List<Player> playersInTeam;
    Map<Player, Integer> playersWithID = new HashMap<>();;


    public Team(String alias, List<Player> addedPlayers) {
        this.alias = alias;
        this.score = 0;
        this.playersInTeam = addedPlayers;
    }

    public static Player getPlayerById(int id) {

    }
}
