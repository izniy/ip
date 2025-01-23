package bearbot.commands;

import bearbot.tasks.Task;
import java.util.List;

public class MarkCommand extends Command {
    private List<Task> tasks;
    private int index;

    public MarkCommand(List<Task> tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(index));
    }
}
