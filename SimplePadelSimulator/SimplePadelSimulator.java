import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimplePadelSimulator {
    private static List<Match> matchHistory = new ArrayList<>();

    public static void main(String[] args) {
        // set up field
        // simulate point
        // print field
    }

    static private void setUpMatch() {
        Player player1 = new Player("Alfonso");
        Player player2 = new Player("Ramiro");
        Player player3 = new Player("Jesús");
        Player player4 = new Player("Antonio");
        List<Player> teamAlphaPlayers = new ArrayList<>();
        List<Player> teamBetaPlayers = new ArrayList<>();
        teamAlphaPlayers.add(player1);
        teamAlphaPlayers.add(player2);
        teamBetaPlayers.add(player3);
        teamBetaPlayers.add(player4);

        Team teamAlpha = new Team("Equipo A", teamAlphaPlayers);
        Team teamBeta = new Team("Equipo B", teamBetaPlayers);
        List<Team> teamsPlaying = new ArrayList<>();
        teamsPlaying.add(teamAlpha);
        teamsPlaying.add(teamBeta);

        Match currentMatch = new Match(teamsPlaying);
        matchHistory.add(currentMatch);
    }

    static private void simulateGame() {

        int numberOfPlayers = 4;
        int whoScored = ThreadLocalRandom.current().nextInt(0, numberOfPlayers + 1);

        // Match.updateScore(scoring player random id)
    
    }
}