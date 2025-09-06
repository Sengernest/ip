package sengernest.tasks;

/**
 * Represents a generic task.
 */
public class Task {
    /**
     * The description of the task.
     */
    private final String tasking;

    /**
     * Whether the task is finished.
     */
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
     */
    public boolean isEmpty() {
        return this.tasking.isEmpty();
    }

    /**
     * Checks if the task has been marked as finished.
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
     */
    public String getTasking() {
        String status = this.finished ? "[X]" : "[ ]";
        return status + " " + this.tasking;
    }

    /**
     * Returns the raw task description without any status prefix.
     */
    public String getTaskDescription() {
        return this.tasking;
    }

    /**
     * Returns a string representing the task for saving to a file.
     */
    public String toFileFormat() {
        return "| " + (this.finished ? "1" : "0") + " | " + this.tasking;
    }
}
