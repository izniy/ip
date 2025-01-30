package bearbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that occurs over a specified date range.
 * An Event task stores a description, start date, end date, and its completion status.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs a new Event task with the specified description, start date, end date, and completion status.
     *
     * @param description The description of the event.
     * @param start       The starting date of the event.
     * @param end         The ending date of the event.
     * @param isDone      {@code true} if the event is marked as done, {@code false} otherwise.
     */
    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts this Event task into a formatted string suitable for saving to a file.
     * The format follows: {@code "E | 1 | description | YYYY-MM-DD | YYYY-MM-DD"} for completed tasks
     * and {@code "E | 0 | description | YYYY-MM-DD | YYYY-MM-DD"} for incomplete tasks.
     *
     * @return A string representation of the Event task in storage format.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of this Event task, including its type, status, and date range.
     * The format follows: {@code "[E][X] description (from: MMM d yyyy to: MMM d yyyy)"} for completed events
     * and {@code "[E][ ] description (from: MMM d yyyy to: MMM d yyyy)"} for incomplete events.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}
