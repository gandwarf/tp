package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Priority;
import seedu.address.model.client.predicates.PriorityPredicate;
import seedu.address.model.client.predicates.ProductPreferenceContainsKeywordsPredicate;

public class FilterCommandTest {
    @Test
    void executeFilterByPrioritySuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(Priority.VIP));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredClientList().size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void executeFilterByProductPreferenceSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ProductPreferenceContainsKeywordsPredicate predicate =
                new ProductPreferenceContainsKeywordsPredicate(Arrays.asList("shampoo"));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredClientList().size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void executeFilterNoMatch() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ProductPreferenceContainsKeywordsPredicate predicate =
                new ProductPreferenceContainsKeywordsPredicate(Arrays.asList("nonexistent"));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void equalsDifferentFilterCommandReturnsFalse() {
        FilterCommand filterCommand1 = new FilterCommand(new PriorityPredicate(Optional.of(Priority.VIP)));
        FilterCommand filterCommand2 =
                new FilterCommand(new ProductPreferenceContainsKeywordsPredicate(Arrays.asList("shampoo")));
        assertFalse(filterCommand1.equals(filterCommand2));
    }

    @Test
    void equalsSameObjectReturnsTrue() {
        FilterCommand filterCommand = new FilterCommand(new PriorityPredicate(Optional.of(Priority.VIP)));
        assertTrue(filterCommand.equals(filterCommand));
    }

    @Test
    void equalsNullReturnsFalse() {
        FilterCommand filterCommand = new FilterCommand(new PriorityPredicate(Optional.of(Priority.VIP)));
        assertFalse(filterCommand.equals(null));
    }

    @Test
    void equalsDifferentTypeReturnsFalse() {
        FilterCommand filterCommand = new FilterCommand(new PriorityPredicate(Optional.of(Priority.VIP)));
        assertFalse(filterCommand.equals(5));
    }

    @Test
    void equalsDifferentPredicateReturnsFalse() {
        FilterCommand filterCommand1 = new FilterCommand(new PriorityPredicate(Optional.of(Priority.VIP)));
        FilterCommand filterCommand2 = new FilterCommand(new PriorityPredicate(Optional.of(Priority.PREMIUM)));
        assertFalse(filterCommand1.equals(filterCommand2));
    }

    @Test
    void equalsSamePredicateReturnsTrue() {
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(Priority.VIP));
        FilterCommand filterCommand1 = new FilterCommand(predicate);
        FilterCommand filterCommand2 = new FilterCommand(predicate);
        assertTrue(filterCommand1.equals(filterCommand2));
    }

    @Test
    void toStringMethod() {
        PriorityPredicate predicate = new PriorityPredicate(Optional.of(Priority.VIP));
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=Priority: Optional[VIP]}";
        assertEquals(expected, filterCommand.toString());
    }
}
