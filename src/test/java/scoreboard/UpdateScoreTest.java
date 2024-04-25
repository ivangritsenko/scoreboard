package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static scoreboard.ExceptionMessages.*;

class UpdateScoreTest {

    @Test
    void givenScoreboard_whenUpdateScoreWithNullNameArg_thenExceptionIsThrown() {
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = TEAM_NAME_IS_NULL;

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore(null, 1,  null, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore(null, 1, "", 1));
        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateScore("", 1, null, 1)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void givenScoreboardWithAMatch_whenUpdateScoreForNonExistingMatch_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = String.format(MATCH_DOES_NOT_EXIST, teamA, teamC);

        scoreboard.newMatch(teamA, teamB);

       String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateScore(teamA, 1, teamC, 1)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void givenScoreboardWithAMatch_whenUpdateScoreForTheMatchWithNegativeValueArg_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = SCORE_CANNOT_BE_NEGATIVE;

        scoreboard.newMatch(teamA, teamB);

        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateScore(teamA, -1, teamB, 1)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void givenScoreboardWithAMatch_whenUpdateScoreForTheMatch_thenScoreIsUpdated() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        Scoreboard scoreboard = new Scoreboard();
        int expectedHomeTeamScore = 1;
        int expectedAwayTeamScore = 3;

        scoreboard.newMatch(teamA, teamB);
        scoreboard.updateScore(teamA, expectedHomeTeamScore, teamB, expectedAwayTeamScore);
        MatchInterface match = scoreboard.getOngoingMatches().get(0);

        Assertions.assertEquals(expectedHomeTeamScore, match.getHomeTeamScore());
        Assertions.assertEquals(expectedAwayTeamScore, match.getAwayTeamScore());
    }

    @Test
    void givenScoreboardWithAMatch_whenDecreaseScoreForTheMatch_thenScoreIsUpdated() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        Scoreboard scoreboard = new Scoreboard();
        int expectedHomeTeamScore = 1;
        int expectedAwayTeamScore = 3;

        //Init match and update score
        scoreboard.newMatch(teamA, teamB);
        scoreboard.updateScore(teamA, expectedHomeTeamScore + 2, teamB, expectedAwayTeamScore + 2);
        MatchInterface matchWithUpdatedScore = scoreboard.getOngoingMatches().get(0);

        Assertions.assertEquals(expectedHomeTeamScore + 2, matchWithUpdatedScore.getHomeTeamScore());
        Assertions.assertEquals(expectedAwayTeamScore + 2, matchWithUpdatedScore.getAwayTeamScore());

        //Decrease score
        scoreboard.updateScore(teamA, expectedHomeTeamScore, teamB, expectedAwayTeamScore);
        MatchInterface matchWithDecreasedScore = scoreboard.getOngoingMatches().get(0);

        Assertions.assertEquals(expectedHomeTeamScore, matchWithDecreasedScore.getHomeTeamScore());
        Assertions.assertEquals(expectedAwayTeamScore, matchWithDecreasedScore.getAwayTeamScore());
    }
}