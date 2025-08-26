package sengernest.exceptions;

/**
 * Exception thrown when a task number provided by the user is invalid
 * (e.g., out of range or not present in the task list).
 */
public class InvalidTaskNumberException extends Exception {

    /**
     * Constructs a new InvalidTaskNumberException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
