package bearbot.commands;

import bearbot.exceptions.*;
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
    public void execute() throws BearBotException {
        if (this.tasks.size() < 100) {
            String[] words = input.split(" ", 2);
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new EmptyDescriptionException(words[0]);
            }
            switch (words[0]) {
                case "todo":
                    tasks.add(new Todo(words[1]));
                    break;
                case "deadline":
                    if (!words[1].contains(" /by ")) {
                        throw new BearBotException("Deadline task must contain '/by' date.");
                    }
                    String[] dueDate = words[1].split(" /by ");
                    tasks.add(new Deadline(dueDate[0], dueDate[1]));
                    break;
                case "event":
                    if (!words[1].contains(" /from ") || !words[1].contains(" /to ")) {
                        throw new BearBotException("Event task must contain '/from' and '/to' fields.");
                    }
                    String[] eventDetails = words[1].split(" /from | /to ");
                    tasks.add(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                    break;
                default:
                    throw new BearBotException();
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new TaskLimitException();
        }
    }
}