package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's frequency of product purchasing.
 */
public class Frequency {
    public static final String MESSAGE_CONSTRAINTS = "Frequency should be a non-negative integer and should not exceed 2000000000";

    public final int frequency;

    /**
     * Constructs a {@code Frequency}.
     *
     * @param frequency A valid frequency.
     */
    public Frequency(int frequency) {
        requireNonNull(frequency);
        checkArgument(isValidFrequency(frequency), MESSAGE_CONSTRAINTS);
        this.frequency = frequency;
    }

    public static boolean isValidFrequency(int frequency) {
        return frequency >= 0;
    }

    @Override
    public String toString() {
        return Integer.toString(frequency);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Frequency)) {
            return false;
        }

        Frequency otherFrequency = (Frequency) other;
        return frequency == otherFrequency.frequency;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(frequency);
    }
}
