package bearbot.commands;

import bearbot.tasks.Task;

import java.util.List;

public class ListCommand extends Command {
    private List<Task> tasks;

    public ListCommand(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
