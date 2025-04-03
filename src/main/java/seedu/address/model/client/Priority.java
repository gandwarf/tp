package seedu.address.model.client;

import seedu.address.commons.exceptions.IllegalValueException;


/**
 * Represents the priority of a client in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIntegerPriority(int)} Priority(int)}
 */
public enum Priority {

    VIP, PREMIUM, STANDARD;

    public static final String MESSAGE_CONSTRAINTS_INT = "Priority should only be either 1, 2 or 3, "
            + "where 3 is the highest priority and 1 is the lowest priority";

    public static final String MESSAGE_CONSTRAINTS_STRING = "Priority should only be either VIP, PREMIUM or STANDARD, "
            + "all in uppercase";
    /**
     * Converts an integer value to the corresponding Priority.
     */
    public static Priority fromInt(int value) {
        switch (value) {
        case 3:
            return VIP;
        case 2:
            return PREMIUM;
        case 1:
            return STANDARD;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_INT);
        }
    }

    /**
     * Converts a string value to the corresponding Priority.
     */
    public static Priority fromString(String value) throws IllegalValueException {
        switch (value) {
        case "VIP":
            return VIP;
        case "PREMIUM":
            return PREMIUM;
        case "STANDARD":
            return STANDARD;
        default:
            throw new IllegalValueException(MESSAGE_CONSTRAINTS_STRING);
        }
    }

    public static boolean isValidIntegerPriority(int value) {
        return value >= 1 && value <= 3;
    }
}
