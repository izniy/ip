package bearbot.commands;

import bearbot.exceptions.*;
import bearbot.tasks.*;

import java.time.LocalDate;

/**
 * Represents a command to add a new task to the task list.
 * Supports adding three types of tasks:
 * {@link Todo} - A simple task without a deadline.
 * {@link Deadline} - A task with a specific due date.
 * {@link Event} - A task that occurs within a date range.
 */
public class AddCommand extends Command {
    private final TaskList taskList;
    private final String description;
    private final LocalDate date;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an {@code AddCommand} to add a {@link Todo} task.
     *
     * @param taskList    The task list to which the task will be added.
     * @param description The description of the todo task.
     */
    public AddCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
        this.date = null;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Constructs an {@code AddCommand} to add a {@link Deadline} task.
     *
     * @param taskList    The task list to which the task will be added.
     * @param description The description of the deadline task.
     * @param date        The due date of the deadline task.
     */
    public AddCommand(TaskList taskList, String description, LocalDate date) {
        this.taskList = taskList;
        this.description = description;
        this.date = date;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Constructs an {@code AddCommand} to add an {@link Event} task.
     *
     * @param taskList    The task list to which the task will be added.
     * @param description The description of the event task.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     */
    public AddCommand(TaskList taskList, String description, LocalDate startDate, LocalDate endDate) {
        this.taskList = taskList;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.date = null;
    }

    /**
     * Executes the command by adding the appropriate task to the task list.
     * The task is added to the task list, and a confirmation message is displayed.
     *
     * @throws BearBotException If the task list reaches its limit.
     */
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
