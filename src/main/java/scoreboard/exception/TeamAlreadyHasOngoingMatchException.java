package scoreboard.exception;

import static scoreboard.ExceptionMessages.TEAM_ALREADY_HAS_ONGOING_MATCH;

public class TeamAlreadyHasOngoingMatchException extends Exception {

    public TeamAlreadyHasOngoingMatchException(String teamName) {
        super(String.format(TEAM_ALREADY_HAS_ONGOING_MATCH, teamName));
    }

}
