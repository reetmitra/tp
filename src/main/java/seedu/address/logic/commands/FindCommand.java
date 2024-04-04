package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CourseContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name, course, or role contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": Finds all persons whose name, course, or role contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n";

    public static final String MESSAGE_USAGE = COMMAND_DESCRIPTION
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob cs2103t STUDENT";

    private final NameContainsKeywordsPredicate namePredicate;
    private final CourseContainsKeywordsPredicate coursePredicate;
    private final RoleContainsKeywordsPredicate rolePredicate;

    /**
     * Constructs a FindCommand with the specified predicates.
     *
     * @param namePredicate Predicate for filtering people based on keywords contained in their name.
     * @param coursePredicate Predicate for filtering people based on keywords contained in their course.
     * @param rolePredicate Predicate for filtering people based on keywords contained in their role.
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate, CourseContainsKeywordsPredicate coursePredicate,
                       RoleContainsKeywordsPredicate rolePredicate) {
        this.namePredicate = namePredicate;
        this.coursePredicate = coursePredicate;
        this.rolePredicate = rolePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.or(coursePredicate).or(rolePredicate));
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
        return namePredicate.equals(otherFindCommand.namePredicate)
                && coursePredicate.equals(otherFindCommand.coursePredicate)
                && rolePredicate.equals(otherFindCommand.rolePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .add("coursePredicate", coursePredicate)
                .add("rolePredicate", rolePredicate)
                .toString();
    }
}
