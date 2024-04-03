package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandExecutionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

public class CopyCommandTest {

    @Test
    public void execute_copySuccessful() throws CommandExecutionException {
        // Create a new model manager
        Model model = new ModelManager();

        // Add a person with an email to the model
        Person person = new Person(
            new Name("Alice"),
            Optional.of(new Phone("12345678")),
            new Email("alice@example.com"),
            Role.STUDENT,
            Optional.of(new Address("123 Street")),
            new Course("CS2103T"),
            new HashSet<Tag>()
        );
        model.addPerson(person);

        // Create a new CopyCommand and execute it
        CopyCommand copyCommand = new CopyCommand(Index.fromOneBased(1));
        CommandResult result = copyCommand.execute(model);

        // Check if the command was successful
        assertEquals("Email copied to clipboard.", result.getFeedbackToUser());
    }

    @Test
    public void equals() {
        CopyCommand copyFirstCommand = new CopyCommand(Index.fromOneBased(1));
        CopyCommand copySecondCommand = new CopyCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertEquals(copyFirstCommand, copyFirstCommand);

        // same values -> returns true
        CopyCommand copyFirstCommandCopy = new CopyCommand(Index.fromOneBased(1));
        assertEquals(copyFirstCommand, copyFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(copyFirstCommand, 1);

        // null -> returns false
        assertNotEquals(copyFirstCommand, null);

        // different person -> returns false
        assertNotEquals(copyFirstCommand, copySecondCommand);
    }
}