package sengernest.exceptions;

/**
 * Exception thrown when a task number is missing in a user command
 * that requires one (e.g., mark, unmark, or delete commands).
 */
public class MissingTaskNumberException extends Exception {

    /**
     * Constructs a new MissingTaskNumberException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public MissingTaskNumberException(String message) {
        super(message);
    }
}
