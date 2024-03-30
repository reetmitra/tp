package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CourseContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        CourseContainsKeywordsPredicate firstCoursePredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("first"));
        CourseContainsKeywordsPredicate secondCoursePredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstNamePredicate, firstCoursePredicate);
        FindCommand findSecondCommand = new FindCommand(secondNamePredicate, secondCoursePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstNamePredicate, firstCoursePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate nPredicate = prepareNamePredicate(" ");
        CourseContainsKeywordsPredicate cPredicate = prepareCoursePredicate(" ");
        FindCommand command = new FindCommand(nPredicate, cPredicate);
        expectedModel.updateFilteredPersonList(nPredicate.or(cPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate nPredicate = prepareNamePredicate("Kurz Elle Kunz");
        CourseContainsKeywordsPredicate cPredicate = prepareCoursePredicate(" ");
        FindCommand command = new FindCommand(nPredicate, cPredicate);
        expectedModel.updateFilteredPersonList(nPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleCourseKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate nPredicate = prepareNamePredicate(" ");
        CourseContainsKeywordsPredicate cPredicate = prepareCoursePredicate("IS2218 CS2030S");
        FindCommand command = new FindCommand(nPredicate, cPredicate);
        expectedModel.updateFilteredPersonList(cPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate nPredicate = prepareNamePredicate("Elle");
        CourseContainsKeywordsPredicate cPredicate = prepareCoursePredicate("IS2218");
        FindCommand command = new FindCommand(nPredicate, cPredicate);
        expectedModel.updateFilteredPersonList(nPredicate.or(cPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate nPredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        CourseContainsKeywordsPredicate cPredicate = new CourseContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(nPredicate, cPredicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + nPredicate
                                                               + ", predicate=" + cPredicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code CourseContainsKeywordsPredicate}.
     */
    private CourseContainsKeywordsPredicate prepareCoursePredicate(String userInput) {
        return new CourseContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
