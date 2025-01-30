package bearbot.commands;

/**
 * Represents an abstract command that can be executed.
 * All specific commands must extend this class and implement the {@code execute} method.
 */
public abstract class Command {

    /**
     * Executes the command's logic.
     * Subclasses must provide their own implementation for this method.
     *
     * @throws Exception If an error occurs during command execution.
     */
    public abstract void execute() throws Exception;
}
