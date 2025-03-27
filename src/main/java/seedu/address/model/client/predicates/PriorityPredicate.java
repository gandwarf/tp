package seedu.address.model.client.predicates;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.client.Client;
import seedu.address.model.client.Priority;

/**
 * Tests that a {@code Client}'s {@code Priority} matches the priority given.
 */

public class PriorityPredicate implements Predicate<Client> {
    private final Optional<Priority> priority;

    public PriorityPredicate(Optional<Priority> priority) {
        this.priority = priority;
    }

    @Override
    public boolean test(Client client) {
        return client.getPriority().equals(priority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PriorityPredicate)) {
            return false;
        }

        PriorityPredicate otherPriorityPredicate = (PriorityPredicate) other;
        return priority.equals(otherPriorityPredicate.priority);
    }

    @Override
    public String toString() {
        return "Priority: " + priority;
    }
}
