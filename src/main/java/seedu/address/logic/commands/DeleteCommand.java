package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandExecutionException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes 1 or more people identified using it's displayed index from the address book.
 * If more than 1 person are to be deleted, the index of each person should be separated by a comma.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": Deletes the contact(s) identified by the index number(s) used in the displayed address book.\n";

    public static final String MESSAGE_USAGE = COMMAND_DESCRIPTION
            + "Parameters (for one contact): INDEX\n"
            + "Parameters (for multiple contacts): INDEX1, INDEX2, ...\n"
            + "Example: " + COMMAND_WORD + " 1, 2, 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted contact(s): \n%1$s";

    private final List<Index> targetIndexList;

    /**
     * Creates a DeleteCommand to delete a person at the specified {@code Index}.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndexList = new ArrayList<>();
        this.targetIndexList.add(targetIndex);
    }

    /**
     * Creates a DeleteCommand to delete people from multiple specified {@code IndexList}.
     */
    public DeleteCommand(List<Index> targetIndexList) {
        this.targetIndexList = targetIndexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandExecutionException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person[] peopleToDelete = new Person[targetIndexList.size()];

        // Get a list of people to be deleted.
        for (int i = 0; i < targetIndexList.size(); i++) {
            Index index = targetIndexList.get(i);
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandExecutionException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(index.getZeroBased());
            peopleToDelete[i] = personToDelete;
        }

        model.saveAddressBook(); // Save previous copy of address book if command execution successful.
        String resultMessage = "";

        // Delete the people from model.
        for (Person personToDelete: peopleToDelete) {
            model.deletePerson(personToDelete);
            resultMessage += Messages.format(personToDelete) + "\n";
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, resultMessage.trim()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndexList.equals(otherDeleteCommand.targetIndexList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndexList)
                .toString();
    }
}
