package sengernest.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    /**
     * Formatter used for displaying date and time to the user.
     */
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

    /**
     * Formatter used for saving date/time to a file.
     */
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * The start time of the event.
     */
    private final LocalDateTime start;

    /**
     * The end time of the event.
     */
    private final LocalDateTime end;

    /**
     * Constructs an Event task with the given description, start, and end times.
     *
     * @param tasking The description of the event.
     * @param start   The start time of the event.
     * @param end     The end time of the event.
     */
    public Event(String tasking, LocalDateTime start, LocalDateTime end) {
        super(tasking);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task description with the Event type prefix and the time range.
     *
     * @return A string representing the event for display purposes.
     */
    @Override
    public String getTasking() {
        return "[E] " + super.getTasking() + formatTimeRange();
    }

    /**
     * Returns the raw task description with the time range.
     *
     * @return A string describing the event, including its time range.
     */
    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + formatTimeRange();
    }

    /**
     * Returns a string representation of the event suitable for saving to a file.
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s",
                isFinished() ? 1 : 0,
                super.getTaskDescription(),
                start.format(FILE_FORMAT),
                end.format(FILE_FORMAT));
    }

    /**
     * Returns the formatted time range for display.
     *
     * @return A string in the format " (from: start to: end)".
     */
    private String formatTimeRange() {
        return " (from: " + start.format(DISPLAY_FORMAT) + " to: " + end.format(DISPLAY_FORMAT) + ")";
    }
}
