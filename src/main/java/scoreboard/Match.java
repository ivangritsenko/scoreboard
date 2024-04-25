package scoreboard;

import java.util.Date;
import java.util.Objects;

class Match implements MatchInterface {

    private final Date createdOn = new Date();

    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;

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

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
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

    @Override
    public String toString() {
        return "Match{" +
                "createdOn=" + createdOn +
                ", homeTeamName='" + homeTeamName + '\'' +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                '}';
    }
}
