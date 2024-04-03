package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand;

public class CopyCommandParserTest {

    private CopyCommandParser parser = new CopyCommandParser();

    @Test
    public void parse_validArgs_returnsCopyCommand() {
        assertParseSuccess(parser, "1", new CopyCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format("Invalid command format! \n%s", CopyCommand.MESSAGE_USAGE));
    }
}
