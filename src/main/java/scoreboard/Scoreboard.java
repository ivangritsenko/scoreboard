package scoreboard;

import java.util.*;
import java.util.stream.Collectors;

import static scoreboard.ExceptionMessages.*;

class Scoreboard implements ScoreboardInterface {

    private List<Match> ongoingMatches = new ArrayList<>();

    @Override
    public void newMatch(String homeTeamName, String awayTeamName) {
        checkForNullArguments(homeTeamName, awayTeamName);
        checkMatchAlreadyExist(homeTeamName, awayTeamName);
        checkTeamsAreNotTakingPartInOngoingMatch(homeTeamName, awayTeamName);

        ongoingMatches.add(new Match(homeTeamName, awayTeamName));
    }

    private void checkMatchAlreadyExist(String homeTeamName, String awayTeamName) {
        boolean matchAlreadyExist = ongoingMatches.stream()
                .anyMatch(match -> match.getHomeTeamName().equalsIgnoreCase(homeTeamName) || match.getAwayTeamName().equalsIgnoreCase(awayTeamName));

        if (matchAlreadyExist) {
            throw new IllegalArgumentException(String.format(MATCH_ALREADY_EXIST, homeTeamName, awayTeamName));
        }
    }

    private void checkForNullArguments(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null) {
            throw new IllegalArgumentException(TEAM_NAME_IS_NULL);
        }
    }

    private void checkTeamsAreNotTakingPartInOngoingMatch(String homeTeamName, String awayTeamName) {
        //'thereIsOngoingMatchFor' can be called only once. But there is not much ongoing matches at the same time, so for the code simplicity I leave it as it is
        if (thereIsOngoingMatchFor(homeTeamName)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_HAS_ONGOING_MATCH, homeTeamName));
        }

        if (thereIsOngoingMatchFor(awayTeamName)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_HAS_ONGOING_MATCH, awayTeamName));
        }
    }

    private boolean thereIsOngoingMatchFor(String teamName) {
        return ongoingMatches.stream()
                .anyMatch(match -> match.getHomeTeamName().equalsIgnoreCase(teamName) || match.getAwayTeamName().equalsIgnoreCase(teamName));
    }

    @Override
    public void updateScore(String homeTeamName, Integer homeTeamScore, String awayTeamName, Integer awayTeamScore) {

    }

    @Override
    public void finishMatch(String homeTeamName, String awayTeamName) {

    }

    @Override
    public List<MatchInterface> getOngoingMatches() {
        return ongoingMatches.stream()
                .sorted(Comparator.comparingInt((Match o) -> o.getAwayTeamScore() + o.getHomeTeamScore())
                        .thenComparing(Match::getCreatedOn))
                .collect(Collectors.toList());
    }
}
