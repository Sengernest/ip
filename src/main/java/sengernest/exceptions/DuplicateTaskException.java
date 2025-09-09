package sengernest.exceptions;

/**
 * Exception thrown when a task description is empty.
 */
public class DuplicateTaskException extends Exception {
    /**
     * Constructs a new DuplicateTaskException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public DuplicateTaskException(String message) {
        super(message);
    }
}
