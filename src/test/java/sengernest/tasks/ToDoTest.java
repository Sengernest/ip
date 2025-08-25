package sengernest.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    public void testInitialState() {
        ToDo todo = new ToDo("Read a book");
        assertFalse(todo.isFinished(), "New ToDo should not be finished");
        assertEquals("[T][ ] Read a book", todo.getTasking(), "Initial display should have [T] and unchecked box");
        assertEquals("T | 0 | Read a book", todo.toFileFormat(), "File format should start with T and 0 for unfinished");
    }

    @Test
    public void testFinishAndUnfinish() {
        ToDo todo = new ToDo("Do homework");

        todo.finish();
        assertTrue(todo.isFinished(), "ToDo should be marked finished");
        assertEquals("[T][X] Do homework", todo.getTasking(), "Display should have [T] and X when finished");
        assertEquals("T | 1 | Do homework", todo.toFileFormat(), "File format should have 1 when finished");

        todo.unfinish();
        assertFalse(todo.isFinished(), "ToDo should be marked unfinished again");
        assertEquals("[T][ ] Do homework", todo.getTasking(), "Display should return to unchecked");
    }
}
