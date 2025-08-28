package sengernest.exceptions;

/**
 * Exception thrown when the user is attempting
 * to mark an already marked task.
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
