package sengernest.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    /** The start time of the event. */
    private final LocalDateTime start;

    /** The end time of the event. */
    private final LocalDateTime end;

    /** Formatter used for displaying date and time to the user. */
    private static final DateTimeFormatter DISPLAY = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

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
        return "[E]" + super.getTasking() + " (from: " + start.format(DISPLAY) + " to: " + end.format(DISPLAY) + ")";
    }

    /**
     * Returns the raw task description with the time range.
     *
     * @return A string describing the event, including its time range.
     */
    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " (from: " + start.format(DISPLAY) + " to: " + end.format(DISPLAY) + ")";
    }

    /**
     * Returns a string representation of the event suitable for saving to a file.
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isFinished() ? "1" : "0") + " | " + super.getTaskDescription() + " | "
                + start.format(FILE_FORMAT) + " | " + end.format(FILE_FORMAT);
    }
}
