package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void givenScoreboardWithTwoMatches_whenGetListOfMatches_thenReceiveTwoMatchesSortedByTotalScore() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamC, teamB), new Match(teamA, teamD));

        scoreboard.newMatch(teamA, teamD);
        scoreboard.newMatch(teamC, teamB);
        scoreboard.updateScore(teamC, 1, teamB, 0);
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }

    @Test
    void givenScoreboardWithTwoMatchesWithSameScore_whenGetListOfMatches_thenReceiveTwoMatchesSortedByCreationDate() {
        String teamA = "TeamA";
        String teamB = "TeamB";
        String teamC = "TeamC";
        String teamD = "TeamD";
        Scoreboard scoreboard = new Scoreboard();
        List<Match> expectedOngoingMatches = List.of(new Match(teamC, teamB), new Match(teamA, teamD));

        scoreboard.newMatch(teamC, teamB);
        scoreboard.newMatch(teamA, teamD);
        List<MatchInterface> actualOngoingMatches = scoreboard.getOngoingMatches();

        Assertions.assertEquals(expectedOngoingMatches, actualOngoingMatches);
    }
}