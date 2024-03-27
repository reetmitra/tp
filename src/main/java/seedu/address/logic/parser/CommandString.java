package seedu.address.logic.parser;

/**
 * Represents the user's full input command string.
 * E.g. 'delete 1'.
 */
public class CommandString {
    private final String commandText;

    /**
     * Constructs a {@code CommandString}.
     * @param commandText A valid command string.
     */
    public CommandString(String commandText) {
        assert commandText != null;
        this.commandText = commandText;
    }

    @Override
    public String toString() {
        return commandText;
    }

    /**
     * Returns the length of the command string.
     */
    public int getLength() {
        return commandText.length();
    }

    @Override
    public int hashCode() {
        return commandText.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandString)) {
            return false;
        }

        CommandString otherCommandString = (CommandString) other;
        return commandText.equals(otherCommandString.commandText);
    }

    /**
     * Returns the character at the specified index.
     * @param index The index of the character to return.
     * @return The character at the specified index.
     */
    public char charAt(int index) {
        return commandText.charAt(index);
    }
}
