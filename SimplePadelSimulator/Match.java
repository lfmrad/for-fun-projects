import java.util.ArrayList;
import java.util.List;

public class Match {
    private List<Team> teamsPlaying;
    private Team winner, loser;
    private List<PointScored> pointsHistory = new ArrayList<>();
    private boolean complete = false;
    static int matchNumber;
    private final int PADEL_DELTA = 15;

    public Match(List<Team> teamsPlaying) {
        this.teamsPlaying = teamsPlaying;
        matchNumber++;
    }

    public void updateScore(int playerId) {
        Player scoringPlayer = Player.getPlayerById(playerId);
        scoringPlayer.scoredPoint();
        pointsHistory.add(new PointScored(scoringPlayer));
    }

    public void computeScore() {
        int[] points = new int[2];
        int[] games = new int[2];
        int[] sets = new int[2];

        for (PointScored point : pointsHistory) {
            String playerAlias = point.getPlayer().getAlias();
            String teamAlias = point.getTeam().getAlias();
            System.out.println("Player " + playerAlias + "scored " + PADEL_DELTA + " for Team: " + teamAlias);
        }
    }

    public void getMatchStatus() {

    }
}
