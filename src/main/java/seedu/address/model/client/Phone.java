package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.stream.Stream;

/**
 * Represents a Client's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {
    public static final String MESSAGE_CONSTRAINTS =
            "Please provide an 8-digit Singaporean number without spaces.\n"
            + "The first digit can only be 3, 6, 8, or 9.\n"
            + "The second digit cannot be 9 if the first digit is 9.";
    public static final String VALIDATION_REGEX = "^\\d{8}$";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS + " " + phone);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        boolean isEightDigits = isEightDigits(test);
        if (!isEightDigits) {
            return false;
        }

        boolean isFirstDigitValid = isFirstDigitValid(test);
        boolean isSecondDigitValid = isSecondDigitValid(test);

        return isFirstDigitValid && isSecondDigitValid;
    }

    /**
     * Returns true if a given string is 8 digits.
     */
    public static boolean isEightDigits(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the first digit of the given String is a valid Singaporean 8-digit phone number starting digit.
     * Assumes that the input {@code Phone} is an 8-digit number.
     */
    public static boolean isFirstDigitValid(String test) {
        requireNonNull(test);
        assert isEightDigits(test);

        char firstDigit = test.charAt(0);
        return Stream.of('3', '6', '8', '9').anyMatch(digit -> firstDigit == digit);
    }

    /**
     * Returns true if the second digit of the given String is valid
     * as a Singaporean number that starts with 9 cannot be continued with another 9.
     * Assumes that the input {@code Phone} is an 8-digit number.
     */
    public static boolean isSecondDigitValid(String test) {
        requireNonNull(test);
        assert isEightDigits(test);

        char firstDigit = test.charAt(0);
        char secondDigit = test.charAt(1);
        return !(firstDigit == '9' && secondDigit == '9');
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
