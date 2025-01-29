package bearbot.commands;

import bearbot.exceptions.*;
import bearbot.tasks.Task;
import bearbot.tasks.TaskList;

import java.util.List;

public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final int index;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

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
