package bearbot.commands;

import bearbot.exceptions.*;
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
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new BearBotException("Task index is out of range!");
        }
        taskList.removeTask(index);
        System.out.println("Out of the honey jar!");
        System.out.println(taskList.getTasks().get(index));
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }
}
