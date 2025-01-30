package bearbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that must be completed by a specific date.
 * A Deadline task stores a description, due date, and completion status.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Constructs a new Deadline task with the specified description, due date, and completion status.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date of the task.
     * @param isDone {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Deadline(String description, LocalDate dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Converts this Deadline task into a formatted string suitable for saving to a file.
     * The format follows: {@code "D | 1 | description | YYYY-MM-DD"} for completed tasks
     * and {@code "D | 0 | description | YYYY-MM-DD"} for incomplete tasks.
     * The due date is stored in the {@code YYYY-MM-DD} format for easier parsing upon loading.
     *
     * @return A string representation of the Deadline task in storage format.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
        // since dueDate is a LocalDate, Java automatically converts it to "YYYY-MM-DD"
        // store as YYYY-MM-DD --> easier to parse when loading
    }

    /**
     * Returns a string representation of this Deadline task, including its type, status, and due date.
     * The format follows: {@code "[D][X] description (by: MMM d yyyy)"} for completed deadlines
     * and {@code "[D][ ] description (by: MMM d yyyy)"} for incomplete deadlines.
     *
     * The due date is formatted using {@code "MMM d yyyy"} (e.g., "Feb 1 2025").
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + dueDate.format(formatter) + ")";
        // format dueDate using formatter
    }
}
