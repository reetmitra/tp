package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CommandPartTest {

    @Test
    public void substring_validInput_returnsSubstring() {
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
    public void equals_sameString_returnsTrue() {
        CommandString commandString = new CommandString("delete 0,0");
        CommandPart wholeCommand = new CommandPart(commandString);
        assertEquals(wholeCommand, wholeCommand);
        assertEquals(wholeCommand.getSubstring(0, 10), wholeCommand);

        CommandPart firstArgument = wholeCommand.getSubstring(7, 8); // the first "0"
        assertEquals(firstArgument.getSubstring(0, 1), firstArgument);
        assertEquals(firstArgument.getSubstring(0), firstArgument);

        CommandPart secondArgument = wholeCommand.getSubstring(9, 10); // the second "0"
        assertEquals(firstArgument, secondArgument);
        assertEquals(firstArgument.getStartIndex(), 7);
        assertEquals(firstArgument.getEndIndex(), 8);
        assertEquals(secondArgument.getStartIndex(), 9);
    }

    @Test
    public void equals_null_returnsFalse() {
        CommandString commandString = new CommandString("delete 0,0");
        CommandPart wholeCommand = new CommandPart(commandString);
        assertNotEquals(wholeCommand, null);
    }

    @Test
    public void hashcode() {
        assertEquals(new CommandPart(new CommandString("delete 0,0")).hashCode(),
                new CommandPart(new CommandString("delete 0,0")).hashCode());
    }

    @Test
    public void substring_invalidInput_throwsError() {
        CommandString commandString = new CommandString("delete 0,1");
        CommandPart wholeCommand = new CommandPart(commandString);
        assertThrows(Error.class, () -> wholeCommand.getSubstring(11, 20));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(11));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(1, 0));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(-1, 0));
        assertThrows(Error.class, () -> wholeCommand.getSubstring(-1, -1));
    }

    @Test
    public void split_validInput_returnsResult() {
        // iterate over test cases ["a:b:c", ":", ["a", "b", "c"]], etc.
        // for each test case, create a CommandPart object and call split
        // compare the result with String.split result
        for (String[] testCase : new String[][] {
            {"a:b:c", ":"},
            {":a:b:d", ":"},
            {":a:b:e:", ":"}, // as with String.split, trailing empty parts are not included
            {"a:::b", "::"}, // overlapping match
            {":a:::c", ":"},
            {"abf", ""}, // empty regex, split into individual characters
            {"", ""},
        }) {
            CommandString commandString = new CommandString(testCase[0]);
            CommandPart part = new CommandPart(commandString);
            CommandPart[] result = part.split(testCase[1]);
            String[] expected = testCase[0].split(testCase[1]);
            List<String> resultToString = Arrays.stream(result).map(CommandPart::toString).collect(Collectors.toList());
            List<String> expectedList = Arrays.asList(expected);
            assertEquals(expectedList, resultToString,
                    "Splitting '" + testCase[0] + "' by '" + testCase[1] + "' gives " + resultToString
                    + " (length " + resultToString.size() + ") instead of " + expectedList
                    + " (length " + expectedList.size() + ")");
            for (CommandPart subpart : result) {
                assertTrue(subpart.getCommandString() == commandString); // test identity equality
            }
        }
    }
}
