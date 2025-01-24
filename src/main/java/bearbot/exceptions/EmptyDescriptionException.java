package bearbot.exceptions;

public class EmptyDescriptionException extends BearBotException {
    public EmptyDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty!");
    }
}
