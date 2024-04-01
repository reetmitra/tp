package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link seedu.address.logic.commands.Command}.
 */
public class CommandExecutionException extends Exception {
    public CommandExecutionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandExecutionException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
