package bearbot.commands;

import bearbot.exceptions.BearBotException;
import bearbot.tasks.TaskList;

/**
 * Represents a command to unmark a task as done in the task list.
 * Updates the status of a task at a specified index,
 * marking it as not done and confirming the action by displaying the updated task.
 */
public class UnmarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task list and task index.
     *
     * @param taskList The task list that contains the task to be unmarked.
     * @param index    The zero-based index of the task to be marked as not done.
     */
    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as not done.
     * <p>
     * If the index is invalid (out of range), a {@link BearBotException} is thrown.
     * Otherwise, the task is updated, and a confirmation message is displayed.
     *
     * @throws BearBotException If the specified index is out of range.
     */
    @Override
    public String execute() throws BearBotException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new BearBotException("Task index is out of range!");
        }
        taskList.unmarkTask(index);
        return "OK, I've marked this task as not done yet:\n" + taskList.getOneTask(index);
    }

}
