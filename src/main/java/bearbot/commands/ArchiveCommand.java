package bearbot.commands;

import bearbot.tasks.Task;
import bearbot.tasks.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a command to archive all tasks in the task list.
 */
public class ArchiveCommand extends Command {
    private static final String ARCHIVE_FILE = "archive.txt";
    private final TaskList taskList;

    /**
     * Constructs an ArchiveCommand with the specified task list.
     *
     * @param taskList The task list to archive.
     */
    public ArchiveCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        List<Task> tasks = taskList.getTasks();

        if (tasks.isEmpty()) {
            return "No tasks to archive!";
        }

        try (FileWriter writer = new FileWriter(ARCHIVE_FILE)) {
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
            taskList.clearTasks();
            return "All tasks have been archived and removed from the list.";
        } catch (IOException e) {
            return "Error: Unable to archive tasks.";
        }
    }
}