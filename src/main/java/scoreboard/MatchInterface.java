/**
 * All rights reserved.
 * (c) 1991-2023 Amacker&Partner Informatik AG
 * Albisriederstr. 252A, CH-8047 Zürich, Switzerland
 */
package scoreboard;

import java.util.Date;

public interface MatchInterface {

    String getHomeTeamName();
    String getAwayTeamName();
    Integer getHomeTeamScore();
    Integer getAwayTeamScore();
    Date getCreatedOn();

}
