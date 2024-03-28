package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Finds the index of the first occurrence of the given substring in this {@link CommandPart}.
     * @param substring The substring to search for.
     * @param fromIndex The index to start the search from.
     * @return The index of the first occurrence of the given substring in this {@link CommandPart}, or -1 if not found.
     */
    public int indexOf(String substring, int fromIndex) {
        return toString().indexOf(substring, fromIndex);
    }

    /**
     * Trims the whitespace from the start and end of this {@link CommandPart}.
     * If the {@link CommandPart} is empty after trimming, the returned part will be at the start of the original part.
     * @return The trimmed {@link CommandPart}.
     */
    public CommandPart trim() {
        int start = startIndex;
        int end = endIndex;

        while (end > start && Character.isWhitespace(commandString.charAt(end - 1))) {
            end--;
        }

        while (start < end && Character.isWhitespace(commandString.charAt(start))) {
            start++;
        }

        return new CommandPart(commandString, start, end);
    }

    /**
     * Splits this {@link CommandPart} around matches of the given regular expression.
     * @param regex The regular expression to split around.
     * @return An array of {@link CommandPart}s computed by splitting this {@link CommandPart} around matches of the
     *         given regular expression.
     * @see String#split(String)
     */
    public CommandPart[] split(Pattern regex) {
        Matcher matcher = regex.matcher(toString());
        int lastEnd = 0;
        ArrayList<CommandPart> parts = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.end() > 0) {
                parts.add(getSubstring(lastEnd, matcher.start()));
            }
            lastEnd = matcher.end();
        }
        if (lastEnd < getLength() || lastEnd == 0) {
            parts.add(getSubstring(lastEnd));
        }
        return parts.toArray(new CommandPart[0]);
    }

    /**
     * Splits this {@link CommandPart} around matches of the given regular expression.
     * @see #split(Pattern)
     */
    public CommandPart[] split(String regex) {
        return split(Pattern.compile(regex));
    }
}
