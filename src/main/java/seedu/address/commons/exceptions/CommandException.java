package seedu.address.commons.exceptions;

import java.util.Optional;

import seedu.address.logic.parser.CommandPart;

/**
 * Represents an error during command parse or execution.
 * @see seedu.address.logic.parser.exceptions.ParseException
 * @see seedu.address.logic.commands.exceptions.CommandExecutionException
 */
public interface CommandException {
    /**
     * Gets the part of the command that causes the error.
     * @return The part of the command that causes the error.
     */
    public Optional<CommandPart> getErroneousPart();
}
