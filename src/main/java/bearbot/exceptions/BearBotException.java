package bearbot.exceptions;

public class BearBotException extends Exception {
    private static final String DEFAULT_MESSAGE = "RAWR! This is not Bear Behaviour! ";

    public BearBotException() {
        super(DEFAULT_MESSAGE);
    }

    public BearBotException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
}
