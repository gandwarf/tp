package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

public class ExpandCommandParserTest {

    private final ExpandCommandParser parser = new ExpandCommandParser();

    /**
     * Parses an invalid input (non-numeric) and throws ParseException.
     */
    @Test
    public void parse_nonNumericArgs_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    /**
     * Parses an empty string and throws ParseException.
     */
    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    /**
     * Parses a negative number and throws ParseException.
     */
    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertParseFailure(parser, "-5", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
