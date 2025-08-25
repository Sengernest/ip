package sengernest.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter DISPLAY = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

    public Deadline(String tasking, LocalDateTime by) {
        super(tasking);
        this.by = by;
    }

    @Override
    public String getTasking() {
        return "[D]" + super.getTasking() + " (by: " + by.format(DISPLAY) + ")";
    }

    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " (by: " + by.format(DISPLAY) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isFinished() ? "1" : "0") + " | " + super.getTaskDescription() + " | " + by.format(FILE_FORMAT);
    }

}
