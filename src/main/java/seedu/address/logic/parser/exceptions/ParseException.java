package seedu.address.logic.parser.exceptions;

import java.util.Optional;

import seedu.address.commons.exceptions.CommandException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.CommandPart;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException implements CommandException {
    /**
     * The part of the command that causes the error.
     */
    private final Optional<CommandPart> erroneousPart;

    /**
     * Constructs a {@code ParseException} with the specified detail message and command part.
     * @param message The detail message.
     * @param erroneousPart The part of the command that causes the error, if any.
     */
    public ParseException(String message, Optional<CommandPart> erroneousPart) {
        super(message);
        assert erroneousPart != null;
        this.erroneousPart = erroneousPart;
    }

    /**
     * Constructs a {@code ParseException} with the specified detail message, command part, and cause.
     * @param message The detail message.
     * @param cause The cause of the error.
     * @param erroneousPart The part of the command that causes the error, if any.
     */
    public ParseException(String message, Throwable cause, Optional<CommandPart> erroneousPart) {
        super(message, cause);
        assert erroneousPart != null;
        this.erroneousPart = erroneousPart;
    }

    /**
     * Constructs a {@code ParseException} with the specified detail message and command part.
     * @param message The detail message.
     * @param erroneousPart The part of the command that causes the error.
     */
    public ParseException(String message, CommandPart erroneousPart) {
        this(message, Optional.of(erroneousPart));
    }

    /**
     * Constructs a {@code ParseException} with the specified detail message, command part, and cause.
     * @param message The detail message.
     * @param cause The cause of the error.
     * @param erroneousPart The part of the command that causes the error.
     */
    public ParseException(String message, Throwable cause, CommandPart erroneousPart) {
        this(message, cause, Optional.of(erroneousPart));
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

    @Override
    public Optional<CommandPart> getErroneousPart() {
        return erroneousPart;
    }
}
