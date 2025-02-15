package bearbot.commands;

import bearbot.exceptions.BearBotException;
import bearbot.tasks.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 * Updates the status of a task at a specified index and confirms the action by displaying the updated task.
 */
public class MarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified task list and task index.
     *
     * @param taskList The task list that contains the task to be marked.
     * @param index    The zero-based index of the task to be marked as done.
     */
    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as done.
     * <p>
     * If the index is invalid (out of range), a {@link BearBotException} is thrown.
     * Otherwise, the task is updated, and a confirmation message is displayed.
     *
     * @throws BearBotException If the specified index is out of range.
     */
    @Override
    public String execute() throws BearBotException {
        assert index >= 0 && index < taskList.getSize() : "Task index is out of range";

        taskList.markTask(index);
        return "Nice! I've marked this task as done:\n" + taskList.getOneTask(index);
    }
}
