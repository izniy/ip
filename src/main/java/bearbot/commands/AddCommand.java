package bearbot.commands;

import bearbot.tasks.*;

import java.util.List;

public class AddCommand extends Command {
    private List<Task> tasks;
    private String input;

    public AddCommand(List<Task> tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    @Override
    public void execute() {
        if (this.tasks.size() < 100) {
            String[] words = input.split(" ", 2);
            if (words[0].equals("todo")) {
                tasks.add(new Todo(words[1]));
            } else if (words[0].equals("deadline")) {
                String[] parts = words[1].split(" /by ");
                tasks.add(new Deadline(parts[0], parts[1]));
            } else if (words[0].equals("event")) {
                String[] parts = words[1].split(" /from | /to ");
                tasks.add(new Event(parts[0], parts[1], parts[2]));
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Sorry, you have reached the task limit of 100. No more tasks can be added.");
        }
    }
}