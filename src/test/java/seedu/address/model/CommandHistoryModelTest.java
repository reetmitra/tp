package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandHistoryModelTest {
    private final CommandHistoryModel commandHistoryModel = new CommandHistoryModel();

    @Test
    public void emptyCommandHistory_returnTestString() {
        String testString1 = "Test1";
        String testString2 = "Test2";
        String testString3 = "Test3";

        assertEquals(testString1, commandHistoryModel.getNextCommandHistory(testString1));
        assertEquals(testString2, commandHistoryModel.getNextCommandHistory(testString2));
        assertEquals(testString3, commandHistoryModel.getPreviousCommandHistory(testString3));
        assertEquals(testString3, commandHistoryModel.getPreviousCommandHistory(testString3));
        assertEquals(testString1, commandHistoryModel.getPreviousCommandHistory(testString1));
    }

    @Test
    public void consecutiveGetNext_returnByNewest() {
        String newestCommand = "newest command";
        String newerCommand = "newer command";
        String newCommand = "new command";

        commandHistoryModel.addCommand(newCommand);
        commandHistoryModel.addCommand(newerCommand);
        commandHistoryModel.addCommand(newestCommand);

        assertEquals(newestCommand, commandHistoryModel.getPreviousCommandHistory(""));
        assertEquals(newerCommand, commandHistoryModel.getPreviousCommandHistory(""));
        assertEquals(newCommand, commandHistoryModel.getPreviousCommandHistory(""));
    }

    @Test
    public void consecutiveGetPrevious_returnByOldest() {
        String oldestCommand = "oldest command";
        String olderCommand = "older command";
        String oldCommand = "old command";

        commandHistoryModel.addCommand(""); // Start point of the assert below.
        commandHistoryModel.addCommand(oldestCommand);
        commandHistoryModel.addCommand(olderCommand);
        commandHistoryModel.addCommand(oldCommand);

        // Go to start point.
        commandHistoryModel.getPreviousCommandHistory("");
        commandHistoryModel.getPreviousCommandHistory(oldCommand);
        commandHistoryModel.getPreviousCommandHistory(olderCommand);
        commandHistoryModel.getPreviousCommandHistory(oldestCommand);

        assertEquals(oldestCommand, commandHistoryModel.getNextCommandHistory(""));
        assertEquals(olderCommand, commandHistoryModel.getNextCommandHistory(""));
        assertEquals(oldCommand, commandHistoryModel.getNextCommandHistory(""));
    }

    @Test
    public void currentCommandSaved() {
        String currentString = "current";
        String savedString = "saved";

        commandHistoryModel.addCommand("unsaved");
        commandHistoryModel.getPreviousCommandHistory(currentString);

        assertEquals(currentString, commandHistoryModel.getNextCommandHistory(savedString));
        assertEquals(savedString, commandHistoryModel.getPreviousCommandHistory(savedString));
    }

    @Test
    public void resetTempOnAddCommand() {
        commandHistoryModel.addCommand("hello");
        commandHistoryModel.addCommand("world");
        commandHistoryModel.getPreviousCommandHistory("bye"); // Change current command to "bye".
        commandHistoryModel.getPreviousCommandHistory("earth"); // Change "world" to "earth".

        commandHistoryModel.addCommand("reset");
        assertEquals("reset", commandHistoryModel.getPreviousCommandHistory(""));
        assertEquals("world", commandHistoryModel.getPreviousCommandHistory(""));
        assertEquals("hello", commandHistoryModel.getPreviousCommandHistory(""));
    }
}
