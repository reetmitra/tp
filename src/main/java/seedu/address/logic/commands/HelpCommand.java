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
            + "1. Add:\n"
            + "   Syntax: add n/NAME e/EMAIL r/ROLE a/ADDRESS c/COURSE [t/TAG]… [p/PHONE]\n"
            + "   Example: add n/James Ho p/22224444 e/jamesho@example.com r/STUDENT a/PGPR c/CS2103T t/friend\n"
            + "2. Edit:\n"
            + "   Syntax: edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [r/ROLE] [a/ADDRESS] [c/COURSE] [t/TAG]…\n"
            + "   Example: edit 2 n/James Lee e/jameslee@example.com\n"
            + "3. Find:\n"
            + "   Syntax: find KEYWORD [MORE_KEYWORDS]\n"
            + "   Example: find James Jake\n"
            + "4. List:\n"
            + "   Syntax: list\n"
            + "   Example: list\n"
            + "5. Delete:\n"
            + "   Syntax: delete INDEX\n"
            + "   Example: delete 1\n"
            + "6. Clear:\n"
            + "   Syntax: clear\n"
            + "   Example: clear\n"
            + "For more detailed information on each command, please refer to the project website.";



    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
