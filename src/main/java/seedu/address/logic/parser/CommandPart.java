package seedu.address.logic.parser;

/**
 * Represents a part of the user's full input command string.
 * This class maintains a reference to the full command string and the start/end position of the part in the full
 * command string.
 * This is useful for error reporting, so that the caller can know which part of the command string caused the error.
 */
public class CommandPart {
    private final CommandString commandString;
    private final int startIndex;
    private final int endIndex;

    /**
     * Constructs a {@code CommandPart} object that contains the whole command string.
     */
    public CommandPart(CommandString commandString) {
        assert commandString != null;
        this.commandString = commandString;
        this.startIndex = 0;
        this.endIndex = commandString.getLength();
    }

    private CommandPart(CommandString commandString, int startIndex, int endIndex) {
        assert commandString != null;
        this.commandString = commandString;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        assert startIndex >= 0;
        assert startIndex <= endIndex;
        assert endIndex <= commandString.getLength();
    }

    /**
     * Returns the full command string object.
     */
    public CommandString getCommandString() {
        return commandString;
    }

    /**
     * Returns the start position of the part in the full command string.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Returns the end position of the part in the full command string.
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Returns this part of the command string, as a {@code String}.
     */
    @Override
    public String toString() {
        return commandString.toString().substring(startIndex, endIndex);
    }

    /**
     * Returns the length of this part of the command string.
     */
    public int getLength() {
        return endIndex - startIndex;
    }

    /**
     * Checks if the part of the command string is empty.
     */
    public boolean isEmpty() {
        return getLength() == 0;
    }

    /**
     * Returns a substring of this {@link CommandPart}.
     * @param start The start index of the substring (inclusive).
     * @param end The end index of the substring (exclusive).
     * @return The substring of this {@link CommandPart}.
     */
    public CommandPart getSubstring(int start, int end) {
        assert start >= 0 && end <= getLength() && start <= end;
        return new CommandPart(commandString, startIndex + start, startIndex + end);
    }

    /**
     * Returns a substring of this {@link CommandPart}.
     * @param start The start index of the substring (inclusive).
     * @return The substring of this {@link CommandPart}, starting at the given index and ending at the end of the part.
     */
    public CommandPart getSubstring(int start) {
        return getSubstring(start, getLength());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandPart)) {
            return false;
        }

        CommandPart otherCommandPart = (CommandPart) other;
        return toString().equals(otherCommandPart.toString());
    }
}
