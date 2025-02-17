package bearbot.tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void constructor_validInput_success() {
        LocalDate dueDate = LocalDate.of(2025, 2, 20);
        Deadline deadline = new Deadline("Submit report", dueDate, false);

        assertEquals("Submit report", deadline.getDescription());
        assertTrue(deadline.toString().contains("[ ]"));
        assertEquals(dueDate, deadline.dueDate);
    }

    @Test
    public void toString_uncompletedDeadline_correctFormat() {
        LocalDate dueDate = LocalDate.of(2025, 2, 20);
        Deadline deadline = new Deadline("Submit report", dueDate, false);

        assertEquals("[D][ ] Submit report (by: Feb 20 2025)", deadline.toString());
    }

    @Test
    public void toString_completedDeadline_correctFormat() {
        LocalDate dueDate = LocalDate.of(2025, 2, 20);
        Deadline deadline = new Deadline("Submit report", dueDate, true);

        assertEquals("[D][X] Submit report (by: Feb 20 2025)", deadline.toString());
    }

    @Test
    public void toDataString_uncompletedDeadline_correctFormat() {
        LocalDate dueDate = LocalDate.of(2025, 2, 20);
        Deadline deadline = new Deadline("Submit report", dueDate, false);

        assertEquals("D | 0 | Submit report | 2025-02-20", deadline.toDataString());
    }

    @Test
    public void toDataString_completedDeadline_correctFormat() {
        LocalDate dueDate = LocalDate.of(2025, 2, 20);
        Deadline deadline = new Deadline("Submit report", dueDate, true);

        assertEquals("D | 1 | Submit report | 2025-02-20", deadline.toDataString());
    }
}
