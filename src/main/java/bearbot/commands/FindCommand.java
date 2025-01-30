package bearbot.commands;

import bearbot.tasks.Task;
import bearbot.tasks.TaskList;
import java.util.List;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final TaskList taskList;
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified task list and keyword.
     *
     * @param taskList The task list to search within.
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        List<Task> matchingTasks = taskList.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            System.out.println("Honey jar is empty! No matching tasks found!");
        } else {
            System.out.println("Here are the matching tasks:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
