package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CreateMatchTest {

    @Test
    void givenScoreboard_whenCreateMatchWithNullArg_thenExceptionIsThrown() {
        Scoreboard scoreboard = new Scoreboard();
        String exceptionMessage = "Team name must no be null";

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch(null, null), exceptionMessage);
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch(null, ""), exceptionMessage);
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch("", null), exceptionMessage);
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

        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getMatchedInProgress());
    }

    @Test
    void givenScoreboard_whenCreateMatchThatIsAlreadyCreated_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String exceptionMessage = "Match is already created";
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));
        Runnable createMatchRunnable = () -> scoreboard.newMatch(teamA, teamB);

        createMatchRunnable.run();

        Assertions.assertThrows(IllegalArgumentException.class, createMatchRunnable::run, exceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getMatchedInProgress());
    }

    @Test
    void givenScoreboard_whenCreateMatchWithTeamThatHasOngoingMatch_thenExceptionIsThrown() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String exceptionMessage = String.format("Cannot create new match for the team %s because it already has ongoing match", teamB);
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));

        scoreboard.newMatch(teamA, teamB);

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.newMatch(teamB, teamC), exceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getMatchedInProgress());
    }
}