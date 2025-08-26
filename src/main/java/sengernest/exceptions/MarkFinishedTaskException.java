package sengernest.exceptions;

/**
 * Exception thrown when attempting to mark a task that is already finished.
 */
public class MarkFinishedTaskException extends Exception {

    /**
     * Constructs a new MarkFinishedTaskException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public MarkFinishedTaskException(String message) {
        super(message);
    }
}
