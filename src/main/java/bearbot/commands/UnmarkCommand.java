package bearbot.commands;

import bearbot.tasks.Task;
import java.util.List;

public class UnmarkCommand extends Command {
    private List<Task> tasks;
    private int index;

    public UnmarkCommand(List<Task> tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        this.tasks.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(index));
    }

}
