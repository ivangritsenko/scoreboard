package scoreboard.exception;

import static scoreboard.ExceptionMessages.SCORE_CANNOT_BE_NEGATIVE;

public class NegativeScoreException extends Exception {

    public NegativeScoreException() {
        super(SCORE_CANNOT_BE_NEGATIVE);
    }

}
