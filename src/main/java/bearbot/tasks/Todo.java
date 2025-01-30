package bearbot.tasks;

/**
 * Represents a Todo task, which is a simple task without a specific deadline or event duration.
 * A Todo task stores a description and whether it is marked as done.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts this Todo task into a formatted string suitable for saving to a file.
     * The format follows: {@code "T | 1 | description"} for completed tasks and
     * {@code "T | 0 | description"} for incomplete tasks.
     *
     * @return A string representation of the Todo task in storage format.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of this Todo task, including its type and status.
     * The format follows: {@code "[T][X] description"} for completed tasks and
     * {@code "[T][ ] description"} for incomplete tasks.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
