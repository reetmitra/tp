package seedu.address.model.person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CourseContainsKeywordsPredicate firstPredicate = new CourseContainsKeywordsPredicate(firstPredicateKeywordList);
        CourseContainsKeywordsPredicate secondPredicate =
                new CourseContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CourseContainsKeywordsPredicate firstPredicateCopy =
                new CourseContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_courseContainsKeywords_returnsTrue() {
        // One keyword
        CourseContainsKeywordsPredicate predicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new PersonBuilder().withCourse("CS2103T").build()));

        // Only one matching keyword
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS2109"));
        assertTrue(predicate.test(new PersonBuilder().withCourse("CS2103T").build()));

        // Mixed-case keywords
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("cS2103t"));
        assertTrue(predicate.test(new PersonBuilder().withCourse("CS2103T").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCourse("CS2103T").build()));

        // Non-matching keyword
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("CS2109"));
        assertFalse(predicate.test(new PersonBuilder().withCourse("CS2103T").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withCourse("CS2103T").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(keywords);

        String expected = CourseContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
