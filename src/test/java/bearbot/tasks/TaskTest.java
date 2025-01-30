package bearbot.tasks;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void markTaskAsDone_correctlyUpdatesStatus() {
        Task task = new Todo("Read book", false);
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done");
    }

    @Test
    void markTaskAsNotDone_correctlyUpdatesStatus() {
        Task task = new Todo("Read book", true);
        task.markAsNotDone();
        assertFalse(task.isDone, "Task should be marked as not done");
    }

    @Test
    void getStatus_correctlyReturnsStatus() {
        Task task1 = new Todo("Read book", false);
        Task task2 = new Todo("Write report", true);

        assertEquals("[ ]", task1.getStatus(), "Unmarked task should have [ ] status");
        assertEquals("[X]", task2.getStatus(), "Marked task should have [X] status");
    }
}
