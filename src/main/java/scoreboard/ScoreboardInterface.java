package scoreboard;

import java.util.LinkedList;

public interface ScoreboardInterface {

    void newMatch(String homeTeamName, String awayTeamName);
    void updateScore(String homeTeamName, Integer homeTeamScore, String awayTeamName, Integer awayTeamScore);
    void finishMatch(String homeTeamName, String awayTeamName);
    LinkedList<MatchInterface> getMatchedInProgress();

}
