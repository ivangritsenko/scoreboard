package scoreboard;

import scoreboard.exception.*;

import java.util.List;

public interface ScoreboardInterface {

    /**
     * Creates new match with given teams names.
     *
     * @throws NullTeamNameException if one of the team names is null
     * @throws MatchAlreadyExistException when trying to create match that is already exist
     * @throws TeamAlreadyHasOngoingMatchException if one of the teams already take park in the ongoing match
     *
     */
    void newMatch(String homeTeamName, String awayTeamName) throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException;

    /**
     * Update score of existing match
     *
     * @throws NullTeamNameException if one of the team names is null
     * @throws MatchDoesNotExistException if there is no ongoing match for specified teams
     * @throws NegativeScoreException if any of scores is less than 0
     */
    void updateScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) throws NullTeamNameException, MatchDoesNotExistException, NegativeScoreException;

    /**
     * Finish ongoing match
     *
     * @throws NullTeamNameException if one of the team names is null
     * @throws MatchDoesNotExistException if there is no ongoing match for specified teams
     */
    void finishMatch(String homeTeamName, String awayTeamName) throws NullTeamNameException, MatchDoesNotExistException;

    /**
     * Get list of ongoing matches ordered by score or creation date
     */
    List<MatchInterface> getOngoingMatches();

}
