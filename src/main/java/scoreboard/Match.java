package scoreboard;

class Match implements MatchInterface {

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

}
