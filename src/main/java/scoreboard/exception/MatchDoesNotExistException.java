package scoreboard.exception;

import static scoreboard.ExceptionMessages.MATCH_DOES_NOT_EXIST;

public class MatchDoesNotExistException extends Exception {

    public MatchDoesNotExistException(String homeTeamName, String awayTeamName) {
        super(String.format(MATCH_DOES_NOT_EXIST, homeTeamName, awayTeamName));
    }

}
