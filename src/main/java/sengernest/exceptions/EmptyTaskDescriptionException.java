package sengernest.exceptions;

/**
 * Exception thrown when a task description is empty.
 */
public class EmptyTaskDescriptionException extends Exception {

    /**
     * Constructs a new EmptyTaskDescriptionException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}
