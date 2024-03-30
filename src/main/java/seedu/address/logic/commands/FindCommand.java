package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CourseContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name or course contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names or course contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob cs2103t";

    private final NameContainsKeywordsPredicate nPredicate;
    private final CourseContainsKeywordsPredicate cPredicate;

    public FindCommand(NameContainsKeywordsPredicate nPredicate, CourseContainsKeywordsPredicate cPredicate) {
        this.nPredicate = nPredicate;
        this.cPredicate = cPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(nPredicate.or(cPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return nPredicate.equals(otherFindCommand.nPredicate) && cPredicate.equals(otherFindCommand.cPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", nPredicate)
                .add("predicate", cPredicate)
                .toString();
    }
}
