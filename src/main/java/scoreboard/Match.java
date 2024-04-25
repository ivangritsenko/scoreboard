package scoreboard;

import java.util.Date;
import java.util.Objects;

class Match implements MatchInterface {

    private final Date createdOn = new Date();

    private String homeTeamName;
    private String awayTeamName;
    private Integer homeTeamScore = 0;
    private Integer awayTeamScore = 0;

    public Match(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    @Override
    public Date getCreatedOn() {
        return Date.from(createdOn.toInstant());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeamName, match.homeTeamName) && Objects.equals(awayTeamName, match.awayTeamName) && Objects.equals(homeTeamScore, match.homeTeamScore) && Objects.equals(awayTeamScore, match.awayTeamScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);
    }
}
