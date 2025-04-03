package seedu.address.model.client.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.model.client.Priority;
import seedu.address.testutil.ClientBuilder;

public class PriorityPredicateTest {

    @Test
    void matchesPriorityVip() {
        Priority priority = Priority.VIP;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        Client client = new ClientBuilder().withPriority(3).build();
        assertTrue(predicate.test(client));
    }

    @Test
    void matchesPriorityPremium() {
        Priority priority = Priority.PREMIUM;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        Client client = new ClientBuilder().withPriority(2).build();
        assertTrue(predicate.test(client));
    }

    @Test
    void matchesPriorityStandard() {
        Priority priority = Priority.STANDARD;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        Client client = new ClientBuilder().withPriority(1).build();
        assertTrue(predicate.test(client));
    }

    @Test
    void doesNotMatchPriorityVip() {
        Priority priority = Priority.VIP;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        Client client = new ClientBuilder().withPriority(2).build();
        assertFalse(predicate.test(client));
    }

    @Test
    void doesNotMatchPriorityPremium() {
        Priority priority = Priority.PREMIUM;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        Client client = new ClientBuilder().withPriority(1).build();
        assertFalse(predicate.test(client));
    }

    @Test
    void doesNotMatchPriorityStandard() {
        Priority priority = Priority.STANDARD;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        Client client = new ClientBuilder().withPriority(3).build();
        assertFalse(predicate.test(client));
    }

    @Test
    void matchesEmptyPriority() {
        PriorityPredicate predicate = new PriorityPredicate(Optional.empty());
        Client client = new ClientBuilder().withPriority(3).build();
        assertFalse(predicate.test(client));
    }

    @Test
    void equalsSameObject() {
        Priority priority = Priority.VIP;
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(priority));
        assertTrue(predicate.equals(predicate));
    }

    @Test
    void equalsDifferentObjectSamePriority() {
        Priority priority = Priority.VIP;
        PriorityPredicate predicate1 = new PriorityPredicate(Optional.of(priority));
        PriorityPredicate predicate2 = new PriorityPredicate(Optional.of(priority));
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    void notEqualsDifferentPriority() {
        PriorityPredicate predicate1 = new PriorityPredicate(Optional.of(Priority.VIP));
        PriorityPredicate predicate2 = new PriorityPredicate(Optional.of(Priority.PREMIUM));
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void notEqualsNull() {
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(Priority.VIP));
        assertFalse(predicate.equals(null));
    }

    @Test
    void notEqualsDifferentType() {
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(Priority.VIP));
        assertFalse(predicate.equals("String"));
    }
}
