package bearbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}
