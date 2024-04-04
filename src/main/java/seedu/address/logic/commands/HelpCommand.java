package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Here are the available commands and their usage:\n"
            + "1. " + AddCommand.COMMAND_DESCRIPTION
            + "2. " + EditCommand.COMMAND_DESCRIPTION
            + "3. " + FindCommand.COMMAND_DESCRIPTION
            + "4. " + ListCommand.COMMAND_DESCRIPTION
            + "5. " + DeleteCommand.COMMAND_DESCRIPTION
            + "6. " + ClearCommand.COMMAND_DESCRIPTION
            + "7. " + ExitCommand.COMMAND_DESCRIPTION
            + "8. " + UndoCommand.COMMAND_DESCRIPTION
            + "9. " + CopyCommand.COMMAND_DESCRIPTION
            + "For more detailed information on each command, please press F1 to refer to the project website.";



    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
