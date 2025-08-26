package sengernest.exceptions;

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
