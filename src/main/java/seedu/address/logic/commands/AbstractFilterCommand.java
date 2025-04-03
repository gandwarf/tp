package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Base class for commands that filter or find the list of clients based on some criteria.
 */
public abstract class AbstractFilterCommand extends Command {
    protected final Predicate<Client> predicate;

    public AbstractFilterCommand(Predicate<Client> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }
}
