package bearbot.commands;

import bearbot.exceptions.BearBotException;
import bearbot.tasks.TaskList;

public class MarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() throws BearBotException {
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new BearBotException("Task index is out of range!");
        }
        taskList.markTask(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTasks().get(index));
    }
}
