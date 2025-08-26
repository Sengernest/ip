package sengernest.exceptions;

/**
 * Exception thrown when a task that requires a date is missing one.
 */
public class MissingDateException extends Exception {

    /**
     * Constructs a new MissingDateException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public MissingDateException(String message) {
        super(message);
    }
}
