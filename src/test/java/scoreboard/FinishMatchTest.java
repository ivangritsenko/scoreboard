package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static scoreboard.ExceptionMessages.MATCH_DOES_NOT_EXIST;
import static scoreboard.ExceptionMessages.TEAM_NAME_IS_NULL;

class FinishMatchTest {

    @Test
    void givenScoreboardWithAMatch_whenTryToFinishNotExistingMatch_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = String.format(MATCH_DOES_NOT_EXIST, teamC, teamD);
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));

        scoreboard.newMatch(teamA, teamB);

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(teamA, teamD)).getMessage();
        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(teamC, teamD)).getMessage();
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }

    @Test
    void givenScoreboardWithTwoMatches_whenFinishOneMatch_thenOnlyOneOngoingMatchIsLeft() {
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

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(null, ""));
        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class,
                () -> scoreboard.finishMatch("", null)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}