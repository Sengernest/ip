package sengernest.exceptions;

/**
 * Exception thrown when the user enters a command that is not recognized
 * by the Sengernest application.
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
