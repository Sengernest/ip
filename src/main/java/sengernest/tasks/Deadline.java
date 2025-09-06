package sengernest.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a specific due date and time.
 */
public class Deadline extends Task {
    /**
     * Formatter used for displaying the deadline to the user.
     */
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

    /**
     * Formatter used for saving the deadline to a file.
     */
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * The date and time by which the task should be completed.
     */
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param tasking The description of the task.
     * @param by      The date and time by which the task is due.
     */
    public Deadline(String tasking, LocalDateTime by) {
        super(tasking);
        this.by = by;
    }

    /**
     * Returns the task description with the Deadline type prefix and due date.
     *
     * @return A string representing the deadline for display purposes.
     */
    @Override
    public String getTasking() {
        return "[D] " + super.getTasking() + formatDueDate();
    }

    /**
     * Returns the raw task description with the due date.
     *
     * @return A string describing the deadline, including its due date.
     */
    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + formatDueDate();
    }

    /**
     * Returns a string representation of the deadline suitable for saving to a file.
     *
     * @return A string representing the deadline in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s",
                isFinished() ? 1 : 0,
                super.getTaskDescription(),
                by.format(FILE_FORMAT));
    }

    /**
     * Returns the formatted due date for display.
     *
     * @return A string in the format " (by: DATE)".
     */
    private String formatDueDate() {
        return " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }
}
