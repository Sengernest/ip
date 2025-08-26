package sengernest.tasks;

/**
 * Represents a generic task.
 */
public class Task {
    /** The description of the task. */
    private final String tasking;

    /** Whether the task is finished. */
    private boolean finished;

    /**
     * Constructs a Task with the given description.
     *
     * @param tasking The description of the task.
     */
    public Task(String tasking) {
        this.tasking = tasking;
        this.finished = false;
    }

    /**
     * Checks if the task description is empty.
     *
     * @return true if the task description is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasking.isEmpty();
    }

    /**
     * Checks if the task has been marked as finished.
     *
     * @return true if the task is finished, false otherwise.
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Marks the task as finished.
     */
    public void finish() {
        this.finished = true;
    }

    /**
     * Marks the task as not finished.
     */
    public void unfinish() {
        this.finished = false;
    }

    /**
     * Returns the task description with a finished status prefix.
     *
     * @return A string with "[X]" if finished or "[ ]" if not finished, followed by the task description.
     */
    public String getTasking() {
        if (this.finished) {
            return "[X] " + this.tasking;
        } else {
            return "[ ] " + this.tasking;
        }
    }

    /**
     * Returns the raw task description without any status prefix.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return this.tasking;
    }

    /**
     * Returns a string representing the task for saving to a file.
     *
     * @return The task string in file format.
     */
    public String toFileFormat() {
        if (this.isFinished()) {
            return "| 1 | " + this.getTaskDescription();
        } else {
            return "| 0 | " + this.getTaskDescription();
        }
    }
}
