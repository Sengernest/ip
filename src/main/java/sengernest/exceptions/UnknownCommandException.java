package sengernest.exceptions;

/**
 * Exception thrown when an unknown command is entered.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs a new UnknownCommandException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
