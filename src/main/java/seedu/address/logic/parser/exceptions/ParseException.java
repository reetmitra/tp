package seedu.address.logic.parser.exceptions;

import java.util.Optional;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.CommandPart;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {
    /**
     * The part of the command that causes the error.
     */
    private final Optional<CommandPart> commandPart;

    private ParseException(String message, Optional<CommandPart> commandPart) {
        super(message);
        assert commandPart != null;
        this.commandPart = commandPart;
    }

    private ParseException(String message, Throwable cause, Optional<CommandPart> commandPart) {
        super(message, cause);
        assert commandPart != null;
        this.commandPart = commandPart;
    }

    /**
     * Constructs a ParseException with the specified detail message and command part.
     * @param message The detail message.
     * @param commandPart The part of the command that causes the error.
     */
    public ParseException(String message, CommandPart commandPart) {
        this(message, Optional.of(commandPart));
    }

    /**
     * Constructs a ParseException with the specified detail message, command part, and cause.
     * @param message The detail message.
     * @param cause The cause of the error.
     * @param commandPart The part of the command that causes the error.
     */
    public ParseException(String message, Throwable cause, CommandPart commandPart) {
        this(message, cause, Optional.of(commandPart));
    }

    /**
     * Constructs a {@link ParseException} with the given message, without specifying the part of the command that
     * causes the error.
     */
    public ParseException(String message) {
        this(message, Optional.empty());
    }

    /**
     * Constructs a {@link ParseException} with the given message and cause, without specifying the part of the command
     * that causes the error.
     */
    public ParseException(String message, Throwable cause) {
        this(message, cause, Optional.empty());
    }
}
