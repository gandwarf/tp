package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Arrays;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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

        boolean isPreferenceFilterPresent = argMultimap.getValue(PREFIX_PREFERENCE).isPresent();
        boolean isPriorityFilterPresent = argMultimap.getValue(PREFIX_PRIORITY).isPresent();
        boolean isBothFiltersPresent = isPreferenceFilterPresent && isPriorityFilterPresent;
        boolean isNoFilterPresent = !isPreferenceFilterPresent && !isPriorityFilterPresent;
        if (isBothFiltersPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_ONLY_ONE_FILTER));
        }

        if (isNoFilterPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_ONLY_ONE_FILTER));
        }

        if (isPriorityFilterPresent) {
            PriorityPredicate priorityPredicate =
                    new PriorityPredicate(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY)));
            return new FilterCommand(priorityPredicate);
        } else {
            return new FilterCommand(new ProductPreferenceContainsKeywordsPredicate(
                    Arrays.asList(argMultimap.getValue(PREFIX_PREFERENCE).get().split("\\s+"))));
        }

    }

}
