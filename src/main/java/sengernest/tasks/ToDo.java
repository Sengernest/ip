package sengernest.tasks;

/**
 * Represents a To-Do task, which is a basic task without a specific date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param tasking The description of the task.
     */
    public ToDo(String tasking) {
        super(tasking);
    }

    /**
     * Returns the task description with a To-Do type prefix.
     *
     * @return The formatted task description, prefixed with "[T] ".
     */
    @Override
    public String getTasking() {
        return "[T] " + super.getTasking();
    }

    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return A string in file format, prefixed with "T ".
     */
    @Override
    public String toFileFormat() {
        return "T " + super.toFileFormat();
    }
}
