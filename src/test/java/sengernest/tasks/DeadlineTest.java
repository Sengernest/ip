package sengernest.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testInitialState() {
        LocalDateTime deadlineTime = LocalDateTime.of(2025, 8, 24, 13, 0);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);

        assertFalse(deadline.isFinished(), "New Deadline should not be finished");
        assertEquals(
                "[D][ ] Submit assignment (by: Aug 24 2025 1:00pm)",
                deadline.getTasking(),
                "Display should match expected format with AM/PM time"
        );
        assertEquals(
                "D | 0 | Submit assignment | 2025-08-24 1300",
                deadline.toFileFormat(),
                "File format should have correct date/time"
        );
    }

    @Test
    public void testFinishAndUnfinish() {
        LocalDateTime deadlineTime = LocalDateTime.of(2025, 12, 1, 9, 30);
        Deadline deadline = new Deadline("Pay bills", deadlineTime);

        deadline.finish();
        assertTrue(deadline.isFinished(), "Deadline should be finished");
        assertEquals(
                "[D][X] Pay bills (by: Dec 1 2025 9:30am)",
                deadline.getTasking(),
                "Display should have [D][X] when finished"
        );
        assertEquals(
                "D | 1 | Pay bills | 2025-12-01 0930",
                deadline.toFileFormat(),
                "File format should have 1 when finished"
        );

        deadline.unfinish();
        assertFalse(deadline.isFinished(), "Deadline should be unfinished again");
        assertEquals(
                "[D][ ] Pay bills (by: Dec 1 2025 9:30am)",
                deadline.getTasking(),
                "Display should go back to unfinished state"
        );
    }

    @Test
    public void testTaskDescription() {
        LocalDateTime deadlineTime = LocalDateTime.of(2025, 10, 15, 20, 45);
        Deadline deadline = new Deadline("Meeting", deadlineTime);

        assertEquals(
                "Meeting (by: Oct 15 2025 8:45pm)",
                deadline.getTaskDescription(),
                "Task description should match expected date/time format"
        );
    }
}
