package scoreboard;

import java.util.List;

public interface ScoreboardInterface {

    void newMatch(String homeTeamName, String awayTeamName);
    void updateScore(String homeTeamName, Integer homeTeamScore, String awayTeamName, Integer awayTeamScore);
    void finishMatch(String homeTeamName, String awayTeamName);
    List<MatchInterface> getOngoingMatches();

}
