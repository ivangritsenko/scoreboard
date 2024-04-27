package scoreboard.exception;

import static scoreboard.ExceptionMessages.TEAM_NAME_IS_NULL;

public class NullTeamNameException extends Exception {

    public NullTeamNameException() {
        super(TEAM_NAME_IS_NULL);
    }

}
