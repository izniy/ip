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
    public String execute() throws BearBotException {
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
        return "Got it. I've added this task:\n"
                + newTask.toString() + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
