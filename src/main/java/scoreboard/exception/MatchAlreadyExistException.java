package scoreboard.exception;

import static scoreboard.ExceptionMessages.MATCH_ALREADY_EXIST;

public class MatchAlreadyExistException extends Exception {

    public MatchAlreadyExistException(String homeTeamName, String awayTeamName) {
        super(String.format(MATCH_ALREADY_EXIST, homeTeamName, awayTeamName));
    }

}
