package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void valueOf_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Role.valueOf(null));
    }

    @Test
    public void valueOf_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> Role.valueOf(invalidRole));
    }

    @Test
    public void valueOf_validRole_success() {
        String validRole = "STUDENT";
        assertEquals(Role.valueOf(validRole), Role.STUDENT);
    }

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid roles
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("professor")); // non-uppercase
        assertFalse(Role.isValidRole("STUDENT ")); // spaces at the end
        assertFalse(Role.isValidRole(" STUDENT")); // spaces at the front
        assertFalse(Role.isValidRole("STUDENT123")); // numbers within characters
        assertFalse(Role.isValidRole("STUDENT-")); // special characters

        // valid roles
        assertTrue(Role.isValidRole("STUDENT"));
        assertTrue(Role.isValidRole("PROFESSOR"));
        assertTrue(Role.isValidRole("TA"));
    }

    @Test
    public void equals() {
        Role role = Role.STUDENT;

        // same values -> returns true
        assertTrue(role.equals(Role.STUDENT));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(Role.PROFESSOR));
    }

    @Test
    public void hashcode() {
        Role role = Role.STUDENT;

        // same values -> returns same hash code
        assertEquals(role.hashCode(), Role.STUDENT.hashCode());

        // different values -> returns different hash code
        assertNotEquals(role.hashCode(), Role.PROFESSOR.hashCode());
    }
}
