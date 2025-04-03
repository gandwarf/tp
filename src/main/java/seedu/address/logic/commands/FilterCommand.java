package seedu.address.logic.commands;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.client.Client;


/**
 * Filters all persons in the address book to display only those that match the given filter condition.
 */
public class FilterCommand extends AbstractFilterCommand {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons based on either priority level or "
            + "product preferencecontaining any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "shampoo";

    public static final String MESSAGE_ONLY_ONE_FILTER = "Filter command takes exactly one filter condition of either "
            + "product preference or priority and the arguments must not be empty!";

    public FilterCommand(Predicate<Client> predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
