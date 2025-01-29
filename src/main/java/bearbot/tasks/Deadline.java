package bearbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
        // since dueDate is a LocalDate, Java automatically converts it to "YYYY-MM-DD"
        // store as YYYY-MM-DD --> easier to parse when loading
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + dueDate.format(formatter) + ")";
        // format dueDate using formatter
    }
}
