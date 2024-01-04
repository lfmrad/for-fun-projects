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
        Team teamAlpha = new Team("Equipo A");
        Team teamBeta = new Team("Equipo B");

        Player player1 = new Player("Alfonso", teamAlpha);
        Player player2 = new Player("Ramiro", teamAlpha);
        Player player3 = new Player("Jesús", teamBeta);
        Player player4 = new Player("Antonio", teamBeta);

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