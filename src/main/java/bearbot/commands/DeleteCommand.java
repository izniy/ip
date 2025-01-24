package bearbot.commands;

import bearbot.exceptions.*;
import bearbot.tasks.*;

import java.util.List;

public class DeleteCommand extends Command {
    private int index;
    private List<Task> tasks;

    public DeleteCommand(List<Task> tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() throws BearBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BearBotException("Task index is out of range!");
        }
        Task removedTask = tasks.remove(index);
        System.out.println("Out of the honey jar!");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
