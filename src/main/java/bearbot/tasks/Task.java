package bearbot.tasks;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String toDataString();

    // fromDataString() needs to return a specific subclass so it should be a static method
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

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}
