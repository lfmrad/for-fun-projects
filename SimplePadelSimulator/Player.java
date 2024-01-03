import java.util.HashMap;
import java.util.Map;

public class Player {
    private int pointsWon;
    private String alias;

    private static Map<Player, Integer> playersWithId = new HashMap<>();
    private static int idCounter = 0;
    
    public Player(String alias) {
        this.alias = alias;
        this.pointsWon = 0;

        playersWithId.put(this, idCounter++);

    }
}

