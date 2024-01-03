import java.util.List;

public class Match {
    private List<Team> teamsPlaying;
    private Team winner, loser;
    static int matchNumber;

    public Match(List<Team> teamsPlaying) {
        this.teamsPlaying = teamsPlaying;
        matchNumber++;
    }

    private static void updateScore() {
        
    }
}
