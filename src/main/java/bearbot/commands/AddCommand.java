package bearbot.commands;

public class AddCommand extends Command {
    private String[] tasks;
    private int taskCount;

    public AddCommand(String[] tasks, int taskCount, String description) {
        super(description);
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    @Override
    public void execute() {
        if (taskCount < tasks.length) {
            tasks[taskCount] = this.getDescription();
            System.out.println("added: " + this.getDescription());
        } else {
            System.out.println("Sorry, the task list is full");
        }
    }
}