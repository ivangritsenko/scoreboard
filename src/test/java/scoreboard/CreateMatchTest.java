package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static scoreboard.ExceptionMessages.*;

class CreateMatchTest {

    @Test
    void givenScoreboard_whenCreateMatchWithNullArg_thenExceptionIsThrown() {
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = TEAM_NAME_IS_NULL;

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch(null, ""));
        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class,
                () -> scoreboard.newMatch("", null)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void givenScoreboard_whenCreateMatch_thenMatchIsAddedToOngoingMatches() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB), new Match(teamC, teamD));

        scoreboard.newMatch(teamA, teamB);
        scoreboard.newMatch(teamC, teamD);

        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getOngoingMatches());
    }

    @Test
    void givenScoreboard_whenCreateMatchThatIsAlreadyCreated_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String expectedExceptionMessage = String.format(MATCH_ALREADY_EXIST, teamA, teamB);
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));
        Runnable createMatchRunnable = () -> scoreboard.newMatch(teamA, teamB);

        createMatchRunnable.run();

        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class, createMatchRunnable::run).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getOngoingMatches());
    }

    @Test
    void givenScoreboard_whenCreateMatchWithTeamThatHasOngoingMatch_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String expectedExceptionMessage = String.format(TEAM_ALREADY_HAS_ONGOING_MATCH, teamB);
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));

        scoreboard.newMatch(teamA, teamB);

        String actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch(teamB, teamC)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getOngoingMatches());
    }
}