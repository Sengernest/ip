package sengernest.exceptions;

/**
 * Exception thrown when the format of a date entered is invalid.
 */
public class InvalidDateFormatException extends Exception {

    /**
     * Constructs a new InvalidDateFormatException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
