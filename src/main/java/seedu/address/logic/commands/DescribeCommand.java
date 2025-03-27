package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Describes the client by editing the description attribute.
 * If the description is empty, it clears that attribute for the client.
 */
public class DescribeCommand extends EditCommand {

    public static final String COMMAND_WORD = "desc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Describes the client identified "
            + "by the index number used in the displayed client list. "
            + "Description will be overwritten by the input value.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "DESCRIPTION TEXT \n"
            + "Enter an empty DESCRIPTION to clear the client description.\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "This client loves Chinese plates.";

    /**
     * @param index of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public DescribeCommand(Index index, EditClientDescriptor editClientDescriptor) {
        super(index, editClientDescriptor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DescribeCommand)) {
            return false;
        }

        DescribeCommand otherDescribeCommand = (DescribeCommand) other;
        return index.equals(otherDescribeCommand.index)
                && editClientDescriptor.equals(otherDescribeCommand.editClientDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editClientDescriptor", editClientDescriptor)
                .toString();
    }
}
