package bearbot.commands;

public class ListCommand extends Command {
    private String[] tasks;
    private int taskCount;

    public ListCommand(String[] tasks, int taskCount) {
        super("list");
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    @Override
    public void execute() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
