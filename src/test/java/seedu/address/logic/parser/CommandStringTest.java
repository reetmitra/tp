package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandStringTest {

    @Test
    public void equals_null_returnsFalse() {
        CommandString commandString = new CommandString("delete 0,0");
        assertNotEquals(commandString, null);
    }

    @Test
    public void equals_sameCommandString_returnsTrue() {
        CommandString commandString = new CommandString("delete 0,0");
        assertEquals(commandString, commandString);
        assertEquals(commandString, new CommandString("delete 0,0"));
    }

    @Test
    public void hashcode() {
        CommandString commandString = new CommandString("delete 0,0");
        assertEquals(commandString.hashCode(), new CommandString("delete 0,0").hashCode());
        assertNotEquals(commandString.hashCode(), new CommandString("delete 0,1").hashCode());
    }
}
