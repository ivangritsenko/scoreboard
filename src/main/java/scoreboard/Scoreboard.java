package scoreboard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Scoreboard implements ScoreboardInterface{

    private List<Match> ongoingMatches = new ArrayList<>();

    @Override
    public void newMatch(String homeTeamName, String awayTeamName) {

    }

    @Override
    public void updateScore(String homeTeamName, Integer homeTeamScore, String awayTeamName, Integer awayTeamScore) {

    }

    @Override
    public void finishMatch(String homeTeamName, String awayTeamName) {

    }

    @Override
    public LinkedList<MatchInterface> getMatchedInProgress() {
        return null;
    }
}
