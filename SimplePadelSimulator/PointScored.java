public class PointScored {
    private Team scoringTeam;
    private Player scoringPlayer;

    public PointScored(Player scoringPlayer) {
        this.scoringPlayer = scoringPlayer;
        this.scoringTeam = scoringPlayer.getTeam();
    }

    public Player getPlayer() {
        return scoringPlayer;
    }

    public Team getTeam() {
        return scoringTeam;
    }
}
