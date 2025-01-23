package bearbot.commands;

import bearbot.tasks.Task;

import java.util.List;

public class AddCommand extends Command {
    private List<Task> tasks;
    private Task task;

    public AddCommand(List<Task> tasks, Task task) {
        this.tasks = tasks;
        this.task = task;
    }

    @Override
    public void execute() {
        if (this.tasks.size() < 100) {
            tasks.add(this.task);
            System.out.println("added: " + this.task.getDescription());
        } else {
            System.out.println("Sorry, the task list is full");
        }
    }
}