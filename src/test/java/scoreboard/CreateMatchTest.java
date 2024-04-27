package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scoreboard.exception.MatchAlreadyExistException;
import scoreboard.exception.NullTeamNameException;
import scoreboard.exception.TeamAlreadyHasOngoingMatchException;

import java.util.List;

import static scoreboard.ExceptionMessages.*;

class CreateMatchTest {

    @Test
    void givenScoreboard_whenCreateMatchWithNullArg_thenExceptionIsThrown() {
        Scoreboard scoreboard = new Scoreboard();
        String expectedExceptionMessage = TEAM_NAME_IS_NULL;

        Assertions.assertThrows(NullTeamNameException.class, () -> scoreboard.newMatch(null, null));
        Assertions.assertThrows(NullTeamNameException.class, () -> scoreboard.newMatch(null, ""));
        String actualExceptionMessage = Assertions.assertThrows(NullTeamNameException.class,
                () -> scoreboard.newMatch("", null)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void givenScoreboard_whenCreateMatch_thenMatchIsAddedToOngoingMatches() throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException {
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
    void givenScoreboard_whenCreateMatchThatIsAlreadyCreated_thenExceptionIsThrown() throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String expectedExceptionMessage = String.format(MATCH_ALREADY_EXIST, teamA, teamB);
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));

        scoreboard.newMatch(teamA, teamB);

        String actualExceptionMessage = Assertions.assertThrows(MatchAlreadyExistException.class, () -> scoreboard.newMatch(teamA, teamB)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getOngoingMatches());
    }

    @Test
    void givenScoreboard_whenCreateMatchWithTeamThatHasOngoingMatch_thenExceptionIsThrown() throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String expectedExceptionMessage = String.format(TEAM_ALREADY_HAS_ONGOING_MATCH, teamB);
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamA, teamB));

        scoreboard.newMatch(teamA, teamB);

        String actualExceptionMessage = Assertions.assertThrows(TeamAlreadyHasOngoingMatchException.class, () -> scoreboard.newMatch(teamB, teamC)).getMessage();
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage);
        Assertions.assertEquals(expectedOngoingMatches, scoreboard.getOngoingMatches());
    }
}