package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scoreboard.exception.*;

import java.util.ArrayList;
import java.util.List;

class GetMatchTest {

    @Test
    void givenScoreboardWithNoMatches_whenGetListOfMatches_thenReceiveEmptyList() {
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = new ArrayList<>();

        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }

    @Test
    void givenScoreboardWithTwoMatches_whenGetListOfMatches_thenReceiveTwoMatchesSortedByTotalScore() throws NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException, NegativeScoreException, MatchDoesNotExistException {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        Match expectedOngoingMatch1 = new Match(teamC, teamB);
        expectedOngoingMatch1.setHomeTeamScore(1);
        List<Match> expectedOngoingMatches = List.of(expectedOngoingMatch1, new Match(teamA, teamD));

        scoreboard.newMatch(teamA, teamD);
        scoreboard.newMatch(teamC, teamB);
        scoreboard.updateScore(teamC, 1, teamB, 0);
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }

    @Test
    void givenScoreboardWithTwoMatchesWithSameScore_whenGetListOfMatches_thenReceiveTwoMatchesSortedByCreationDate() throws InterruptedException, NullTeamNameException, MatchAlreadyExistException, TeamAlreadyHasOngoingMatchException {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamC, teamB), new Match(teamA, teamD));

        scoreboard.newMatch(teamA, teamD);
        //Thread sleep is required because otherwise Date is same for both matches (Relates to JDK-8196003 I guess)
        Thread.sleep(200);
        scoreboard.newMatch(teamC, teamB);
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }
}