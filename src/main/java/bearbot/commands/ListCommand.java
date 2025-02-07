package bearbot.commands;

import bearbot.tasks.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * The {@code ListCommand} retrieves and displays all tasks currently stored in the {@link TaskList}.
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs a {@code ListCommand} with the specified task list.
     *
     * @param taskList The task list whose tasks will be displayed.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command by printing all tasks in the task list.
     * If the task list is empty, it still displays a message without tasks.
     */
    @Override
    public String execute() {
        if (taskList.getSize() == 0) {
            return "Your task list is empty!";
        }

        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            response.append(i + 1).append(". ").append(taskList.getOneTask(i)).append("\n");
        }
        return response.toString();
    }
}
