package bearbot.commands;

import bearbot.exceptions.BearBotException;
import bearbot.tasks.TaskList;

public class UnmarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() throws BearBotException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new BearBotException("Task index is out of range!");
        }
        taskList.unmarkTask(index);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getOneTask(index));
    }

}
