package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

/**
 * Contains integration tests for ExpandCommand using the actual Model.
 */
public class ExpandCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Executes ExpandCommand with a valid index and verifies that the command succeeds.
     */
    @Test
    public void execute_validIndex_success() throws Exception {
        Index validIndex = INDEX_FIRST_PERSON;
        Client targetClient = model.getSortedFilteredClientList().get(validIndex.getZeroBased());

        ExpandCommand command = new ExpandCommand(validIndex);
        CommandResult result = command.execute(model);

        assertEquals(String.format(ExpandCommand.MESSAGE_EXPAND_CLIENT_SUCCESS, targetClient.getName()),
                result.getFeedbackToUser());
        assertEquals(targetClient, result.getExpandedClient());
        assertEquals(true, result.isShowExpandedView());
    }

    /**
     * Executes ExpandCommand with an index out of bounds and expects a CommandException.
     */
    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredClientList().size() + 1);
        ExpandCommand command = new ExpandCommand(outOfBoundIndex);

        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, e.getMessage());
        }
    }

    /**
     * Tests the equals method of ExpandCommand.
     */
    @Test
    public void equals() {
        ExpandCommand expandFirst = new ExpandCommand(INDEX_FIRST_PERSON);
        ExpandCommand expandSecond = new ExpandCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(expandFirst, expandFirst);

        // different types -> returns false
        assertFalse(expandFirst.equals(1));

        // null -> returns false
        assertFalse(expandFirst.equals(null));

        // different index -> returns false
        assertFalse(expandFirst.equals(expandSecond));
    }
}
