import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private static final DateTimeFormatter DISPLAY = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    public Event(String tasking, LocalDateTime start, LocalDateTime end) {
        super(tasking);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTasking() {
        return "[E]" + super.getTasking() + " (from: " + start.format(DISPLAY) + " to: " + end.format(DISPLAY) + ")";
    }

    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " (from: " + start.format(DISPLAY) + " to: " + end.format(DISPLAY) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isFinished() ? "1" : "0") + " | " + super.getTaskDescription() + " | "
                + start.format(FILE_FORMAT) + " | " + end.format(FILE_FORMAT);
    }

}
