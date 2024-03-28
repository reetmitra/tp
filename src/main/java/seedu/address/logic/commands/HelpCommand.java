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
            + "1. " + AddCommand.MESSAGE_USAGE + "\n"
            + "2. " + EditCommand.MESSAGE_USAGE + "\n"
            + "3. " + FindCommand.MESSAGE_USAGE + "\n"
            + "4. " + ListCommand.MESSAGE_USAGE + "\n"
            + "5. " + DeleteCommand.MESSAGE_USAGE + "\n"
            + "6. " + ClearCommand.MESSAGE_USAGE + "\n"
            + "7. " + ExitCommand.MESSAGE_USAGE + "\n"
            + "For more detailed information on each command, please press F1 to refer to the project website.";



    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
