package sengernest.exceptions;

/**
 * Exception thrown when an operation is attempted on an empty task list.
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
