package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains helper methods for testing {@link CommandPart}.
 */
public class CommandPartTest {

    @Test
    public void commandPartSubstring_validInput_returnsSubstring() {
        CommandString commandString = new CommandString("delete 0,1");
        CommandPart wholeCommand = new CommandPart(commandString);
        assertEquals(wholeCommand.toString(), "delete 0,1");
        CommandPart arguments = wholeCommand.getSubstring(7);
        assertEquals(arguments.toString(), "0,1");
        CommandPart firstArgument = arguments.getSubstring(0, 1);
        assertEquals(firstArgument.toString(), "0");
        assertEquals(firstArgument.getStartIndex(), 7);
    }

    @Test
    public void commandPartEquals_sameString_returnsTrue() {
        CommandString commandString = new CommandString("delete 0,0");
        CommandPart wholeCommand = new CommandPart(commandString);
        assertEquals(wholeCommand.getSubstring(0, 10), wholeCommand);
        CommandPart firstArgument = wholeCommand.getSubstring(7, 8);
        assertEquals(firstArgument.getSubstring(0, 1), firstArgument);
        assertEquals(firstArgument.getSubstring(0), firstArgument);
        CommandPart secondArgument = wholeCommand.getSubstring(9, 10);
        assertEquals(firstArgument, secondArgument);
        assertEquals(firstArgument.getStartIndex(), 7);
        assertEquals(secondArgument.getStartIndex(), 9);
    }

    @Test
    public void commandPartSubstring_invalidInput_throwsError() {
        CommandString commandString = new CommandString("delete 0,1");
        CommandPart wholeCommand = new CommandPart(commandString);
        assertThrows(Error.class, () -> wholeCommand.getSubstring(11, 20));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(11));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(1, 0));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(-1, 0));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(-1, -1));
    }
}
