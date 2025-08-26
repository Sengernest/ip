package sengernest.exceptions;

/**
 * Exception thrown when attempting to unmark a task that is already unfinished.
 */
public class UnmarkUnfinishedTaskException extends Exception {

    /**
     * Constructs a new UnmarkUnfinishedTaskException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public UnmarkUnfinishedTaskException(String message) {
        super(message);
    }
}
