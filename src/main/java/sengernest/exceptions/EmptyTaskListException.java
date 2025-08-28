package sengernest.exceptions;

/**
 * Exception thrown when a task list is empty.
 */
public class EmptyTaskListException extends Exception {

    /**
     * Constructs a new EmptyTaskListException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public EmptyTaskListException(String message) {
        super(message);
    }
}
