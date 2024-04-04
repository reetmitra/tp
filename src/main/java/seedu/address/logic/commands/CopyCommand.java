package seedu.address.logic.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandExecutionException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Copies the email of the person identified by the index number used in the displayed person list.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
        + ": Copies the email of the person identified by the index number used in the displayed person list.\n";

    public static final String MESSAGE_USAGE = COMMAND_DESCRIPTION
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";
    private final Index targetIndex;

    public CopyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandExecutionException {
        try {
            // Get the person from the model
            Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());

            // Get the email of the person
            String email = person.getEmail().value;

            // Copy to clipboard
            StringSelection stringSelection = new StringSelection(email);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            return new CommandResult("Email copied to clipboard.");
        } catch (Exception e) {
            throw new CommandExecutionException("Error while copying email to clipboard.", e);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CopyCommand)) {
            return false;
        }

        CopyCommand otherCopyCommand = (CopyCommand) other;
        return targetIndex.equals(otherCopyCommand.targetIndex);
    }

    private void copyEmail(String email) {
        StringSelection stringSelection = new StringSelection(email);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
