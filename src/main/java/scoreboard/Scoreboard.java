package scoreboard;

import scoreboard.exception.*;

import java.util.*;
import java.util.stream.Collectors;

class Scoreboard implements ScoreboardInterface {

    private List<Match> ongoingMatches = new ArrayList<>();

    @Override
    public void newMatch(String homeTeamName, String awayTeamName) throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException {
        checkForNullArguments(homeTeamName, awayTeamName);
        checkMatchAlreadyExist(homeTeamName, awayTeamName);
        checkTeamsAreNotTakingPartInOngoingMatch(homeTeamName, awayTeamName);

        ongoingMatches.add(new Match(homeTeamName, awayTeamName));
    }

    private void checkMatchAlreadyExist(String homeTeamName, String awayTeamName) throws MatchAlreadyExistException {
        boolean matchAlreadyExist = ongoingMatches.stream()
                .anyMatch(match -> match.getHomeTeamName().equalsIgnoreCase(homeTeamName) || match.getAwayTeamName().equalsIgnoreCase(awayTeamName));

        if (matchAlreadyExist) {
            throw new MatchAlreadyExistException(homeTeamName, awayTeamName);
        }
    }

    private void checkForNullArguments(String homeTeamName, String awayTeamName) throws NullTeamNameException {
        if (homeTeamName == null || awayTeamName == null) {
            throw new NullTeamNameException();
        }
    }

    private void checkForNegativeScore(int homeTeamScore, int awayTeamScore) throws NegativeScoreException {
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new NegativeScoreException();
        }
    }

    private void checkTeamsAreNotTakingPartInOngoingMatch(String homeTeamName, String awayTeamName) throws TeamAlreadyHasOngoingMatchException {
        //'thereIsOngoingMatchFor' can be called only once. But there is not much ongoing matches at the same time, so for the code simplicity I leave it as it is
        if (thereIsOngoingMatchFor(homeTeamName)) {
            throw new TeamAlreadyHasOngoingMatchException(homeTeamName);
        }

        if (thereIsOngoingMatchFor(awayTeamName)) {
            throw new TeamAlreadyHasOngoingMatchException(awayTeamName);
        }
    }

    private boolean thereIsOngoingMatchFor(String teamName) {
        return ongoingMatches.stream()
                .anyMatch(match -> match.getHomeTeamName().equalsIgnoreCase(teamName) || match.getAwayTeamName().equalsIgnoreCase(teamName));
    }

    private Match findMatchBy(String homeTeamName, String awayTeamName) throws MatchDoesNotExistException {
        return ongoingMatches.stream()
                .filter(match -> match.getHomeTeamName().equalsIgnoreCase(homeTeamName) && match.getAwayTeamName().equalsIgnoreCase(awayTeamName))
                .findFirst()
                .orElseThrow(() -> new MatchDoesNotExistException(homeTeamName, awayTeamName));
    }

    @Override
    public void updateScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) throws NullTeamNameException, MatchDoesNotExistException, NegativeScoreException {
        checkForNullArguments(homeTeamName, awayTeamName);
        checkForNegativeScore(homeTeamScore, awayTeamScore);

        Match requestedForScoreUpdateMatch = findMatchBy(homeTeamName, awayTeamName);
        requestedForScoreUpdateMatch.setHomeTeamScore(homeTeamScore);
        requestedForScoreUpdateMatch.setAwayTeamScore(awayTeamScore);
    }

    @Override
    public void finishMatch(String homeTeamName, String awayTeamName) throws NullTeamNameException, MatchDoesNotExistException {
        checkForNullArguments(homeTeamName, awayTeamName);

        Match requestedToFinishMatch = findMatchBy(homeTeamName, awayTeamName);

        ongoingMatches.remove(requestedToFinishMatch);
    }

    @Override
    public List<MatchInterface> getOngoingMatches() {
        return ongoingMatches.stream()
                .sorted(Comparator.comparingInt((Match o) -> o.getAwayTeamScore() + o.getHomeTeamScore())
                        .thenComparing(Match::getCreatedOn)
                        .reversed())
                .collect(Collectors.toList());
    }
}
