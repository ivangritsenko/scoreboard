package scoreboard;

import java.util.Date;

public interface MatchInterface {

    String getHomeTeamName();
    String getAwayTeamName();
    int getHomeTeamScore();
    int getAwayTeamScore();
    Date getCreatedOn();

}
