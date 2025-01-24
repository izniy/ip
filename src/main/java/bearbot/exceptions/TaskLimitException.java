package bearbot.exceptions;

public class TaskLimitException extends BearBotException {
    public TaskLimitException() {
        super("Oh dear! Your honey jar is full!");
    }
}
