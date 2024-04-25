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


}