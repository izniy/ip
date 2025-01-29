package bearbot.commands;

import bearbot.Ui;
import bearbot.exceptions.*;
import bearbot.tasks.*;

import java.time.LocalDate;

public class AddCommand extends Command {
    private final TaskList taskList;
    private final String description;
    private final LocalDate date;
    private final LocalDate startDate;
    private final LocalDate endDate;

    // Constructor for Todo
    public AddCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
        this.date = null;
        this.startDate = null;
        this.endDate = null;
    }

    // Constructor for Deadline
    public AddCommand(TaskList taskList, String description, LocalDate date) {
        this.taskList = taskList;
        this.description = description;
        this.date = date;
        this.startDate = null;
        this.endDate = null;
    }

    // Constructor for Event
    public AddCommand(TaskList taskList, String description, LocalDate startDate, LocalDate endDate) {
        this.taskList = taskList;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.date = null;
    }

    @Override
    public void execute() throws BearBotException {
        if (this.taskList.getSize() >= 100) {
            throw new TaskLimitException();
        }

        Task newTask;
        if (date != null) {
            newTask = new Deadline(description, date, false);
        } else if (startDate != null && endDate != null) {
            newTask = new Event(description, startDate, endDate, false);
        } else {
            newTask = new Todo(description, false);
        }

        taskList.addTask(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }
}

/*
public class AddCommand extends Command {
    private final TaskList taskList;
    private final String input;
    // assigned once and cannot be modified

    public AddCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    @Override
    public void execute() throws BearBotException {
        if (this.taskList.getSize() >= 100) {
            throw new TaskLimitException();
        }

        String[] words = input.split(" ", 2);
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new EmptyDescriptionException(words[0]);
        }

        String taskType = words[0];
        String taskDetails = words[1];

        switch (taskType) {
            case "todo":
                taskList.addTask(new Todo(taskDetails, false));
                break;
            case "deadline":
                if (!taskDetails.contains(" /by ")) {
                    throw new BearBotException("Deadline task must contain '/by' date.");
                }
                String[] deadlineTaskParts = taskDetails.split(" /by ", 2);
                try {
                    LocalDate dueDate = LocalDate.parse(deadlineTaskParts[1].trim());
                    taskList.addTask(new Deadline(deadlineTaskParts[0], dueDate, false));
                } catch (DateTimeParseException e) {
                    throw new BearBotException("Invalid date format for deadline! Use YYYY-MM-DD.");
                }
                break;
            case "event":
                if (!taskDetails.contains(" /from ") || !taskDetails.contains(" /to ")) {
                    throw new BearBotException("Event task must contain '/from' and '/to' fields.");
                }
                String[] eventParts = words[1].split(" /from | /to ", 3);
                try {
                    LocalDate startDate = LocalDate.parse(eventParts[1].trim());
                    LocalDate endDate = LocalDate.parse(eventParts[2].trim());
                    taskList.addTask(new Event(eventParts[0], startDate, endDate, false));  // ✅ Now Event enforces validity
                } catch (DateTimeParseException e) {
                    throw new BearBotException("Invalid date format for event! Use yyyy-mm-dd.");
                }
                break;
            default:
                throw new BearBotException("Invalid command: " + taskType);
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getSize().get(taskList.getSize() - 1));
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
*/
