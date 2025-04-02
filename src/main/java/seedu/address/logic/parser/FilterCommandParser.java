package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.predicates.PriorityPredicate;
import seedu.address.model.client.predicates.ProductPreferenceContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    @Override
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PREFERENCE, PREFIX_PRIORITY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PREFERENCE, PREFIX_PRIORITY);

        if (!isExactlyOneFilter(argMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_ONLY_ONE_FILTER));
        }

        boolean isPriorityFilterPresent = argMultimap.getValue(PREFIX_PRIORITY).isPresent();

        Predicate<Client> predicate;
        if (isPriorityFilterPresent) {
             predicate =
                    new PriorityPredicate(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY)));

        } else {
            predicate = new ProductPreferenceContainsKeywordsPredicate(
                    Arrays.asList(argMultimap.getValue(PREFIX_PREFERENCE).get().split("\\s+")));
        }

        return new FilterCommand(predicate);

    }

    /**
     * Returns true if exactly one filter is present in the argument multimap.
     */
    private boolean isExactlyOneFilter(ArgumentMultimap argMultimap) {
        return argMultimap.getSize() == 2;
    }

}
