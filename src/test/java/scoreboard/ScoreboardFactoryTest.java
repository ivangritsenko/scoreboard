package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreboardFactoryTest {

    @Test
    void givenScoreboardFactory_whenReceiveSecondScoreboard_thenExistedScoreboardIsReturned() {
        ScoreboardInterface scoreboard1 = ScoreboardFactory.getScoreboard();
        ScoreboardInterface scoreboard2 = ScoreboardFactory.getScoreboard();

        Assertions.assertSame(scoreboard1, scoreboard2);
    }

}