package bearbot.tasks;

import java.time.LocalDate;

/**
 * Represents an abstract task with a description and completion status.
 * It serves as a base class for different types of tasks such as {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts the task into a formatted string suitable for data storage.
     * This method must be implemented by all subclasses.
     *
     * @return A formatted string representing the task data.
     */
    public abstract String toDataString();

    /**
     * Creates a {@code Task} object from a formatted data string.
     * Determines the correct subclass of {@code Task} (Todo, Deadline, Event) based on the stored type.
     *
     * @param data A string containing task data formatted as "Type | isDone | Description | (Optional Dates)".
     * @return The corresponding {@code Task} object.
     * @throws IllegalArgumentException if the task type in the storage file is invalid.
     */
    public static Task fromDataString(String data) {
        String[] parts = data.split(" \\| ");  // | is a regex OR operator
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            return new Deadline(description, LocalDate.parse(parts[3]), isDone);
        case "E":
            return new Event(description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]), isDone);
        default:
            throw new IllegalArgumentException("Invalid task type in storage file");
        }
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return A string representing the completion status: {@code "[X]"} if done, {@code "[ ]"} otherwise.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task, including status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}
