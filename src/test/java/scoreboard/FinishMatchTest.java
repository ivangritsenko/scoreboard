package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scoreboard.exception.MatchAlreadyExistException;
import scoreboard.exception.MatchDoesNotExistException;
import scoreboard.exception.NullTeamNameException;
import scoreboard.exception.TeamAlreadyHasOngoingMatchException;

import java.util.List;

import static scoreboard.ExceptionMessages.MATCH_DOES_NOT_EXIST;
import static scoreboard.ExceptionMessages.TEAM_NAME_IS_NULL;

class FinishMatchTest {

    @Test
    void givenScoreboardWithAMatch_whenTryToFinishNotExistingMatch_thenExceptionIsThrown() throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = String.format(MATCH_DOES_NOT_EXIST, teamC, teamD);
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));

        scoreboard.newMatch(teamA, teamB);

        Assertions.assertThrows(MatchDoesNotExistException.class, () -> scoreboard.finishMatch(teamA, teamD)).getMessage();
        String actualExceptionMessage = Assertions.assertThrows(MatchDoesNotExistException.class, () -> scoreboard.finishMatch(teamC, teamD)).getMessage();
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }

    @Test
    void givenScoreboardWithTwoMatches_whenFinishOneMatch_thenOnlyOneOngoingMatchIsLeft() throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException, MatchDoesNotExistException {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamC, teamD));

        scoreboard.newMatch(teamC, teamD);
        scoreboard.newMatch(teamA, teamB);
        scoreboard.finishMatch(teamA, teamB);
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }

    @Test
    void givenScoreboard_whenCallFinishMatchMethodWithNullArg_thenExceptionIsThrown() {
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = TEAM_NAME_IS_NULL;

        Assertions.assertThrows(NullTeamNameException.class, () -> scoreboard.finishMatch(null, null));
        Assertions.assertThrows(NullTeamNameException.class, () -> scoreboard.finishMatch(null, ""));
        String actualExceptionMessage = Assertions.assertThrows(NullTeamNameException.class,
                () -> scoreboard.finishMatch("", null)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}