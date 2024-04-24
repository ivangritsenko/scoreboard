package scoreboard;

public class ScoreboardFactory {

    private static Scoreboard scoreboard;

    public static ScoreboardInterface getScoreboard() {
        if (scoreboard == null) {
            scoreboard = new Scoreboard();
        }

        return scoreboard;
    }

}
