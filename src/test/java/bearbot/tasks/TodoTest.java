package bearbot.tasks;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void constructor_createsTodoWithCorrectDescription() {
        Todo todo = new Todo("Read book", false);
        assertEquals("Read book", todo.getDescription(), "Description should match the given string");
    }

    @Test
    void constructor_initializesTaskAsNotDone() {
        Todo todo = new Todo("Finish homework", false);
        assertFalse(todo.isDone, "Newly created task should not be marked as done");
    }

    @Test
    void markAsDone_changesStatusCorrectly() {
        Todo todo = new Todo("Exercise", false);
        todo.markAsDone();
        assertTrue(todo.isDone, "Task should be marked as done");
    }

    @Test
    void markAsNotDone_changesStatusCorrectly() {
        Todo todo = new Todo("Exercise", true); // Start as done
        todo.markAsNotDone();
        assertFalse(todo.isDone, "Task should be marked as not done");
    }

    @Test
    void toString_formatsCorrectly() {
        Todo todo = new Todo("Write report", false);
        assertEquals("[T][ ] Write report", todo.toString(), "String format should match expected output");

        todo.markAsDone();
        assertEquals("[T][X] Write report", todo.toString(), "Task should show as completed");
    }
}

