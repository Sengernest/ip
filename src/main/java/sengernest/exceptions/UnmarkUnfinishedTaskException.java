package sengernest.exceptions;

/**
 * Exception thrown when the user is attempting
 * to unmark an already unmarked task.
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
