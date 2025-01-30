package bearbot.commands;

import bearbot.exceptions.*;
import bearbot.tasks.Task;
import bearbot.tasks.TaskList;


/**
 * Represents a command to delete a task from the task list.
 * This command removes the task at the specified index and displays a confirmation message.
 */
public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task list and index.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param index    The zero-based index of the task to be deleted.
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list.
     *
     * If the index is invalid (out of range), a {@link BearBotException} is thrown.
     * Otherwise, the task is removed, and a confirmation message is displayed.
     *
     * @throws BearBotException If the specified index is out of range.
     */
    @Override
    public void execute() throws BearBotException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new BearBotException("Task index is out of range!");
        }

        Task toRemove = taskList.getOneTask(index);
        taskList.removeTask(index);

        System.out.println("Out of the honey jar!");
        System.out.println(toRemove);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
