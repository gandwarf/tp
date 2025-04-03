package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isEightDigits() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isEightDigits(null));

        // invalid phone numbers
        assertFalse(Phone.isEightDigits("")); // empty string
        assertFalse(Phone.isEightDigits(" ")); // spaces only
        assertFalse(Phone.isEightDigits("91")); // not 8 numbers
        assertFalse(Phone.isEightDigits("phone")); // non-numeric
        assertFalse(Phone.isEightDigits("9011p041")); // alphabets within digits
        assertFalse(Phone.isEightDigits("9312 1534")); // spaces within digits
        assertFalse(Phone.isEightDigits("911")); // less than 8 numbers
        assertFalse(Phone.isEightDigits("9312153434342324")); //more than 8 numbers

        // valid phone numbers
        assertTrue(Phone.isEightDigits("93121534")); //exactly 8 numbers
    }

    @Test
    public void isFirstDigitValid() {
        // null phone number.
        assertThrows(NullPointerException.class, () -> Phone.isFirstDigitValid(null));

        // Invalid phone number.
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("")); // empty string
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("  ")); // spaces only
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("123")); // not 8 numbers
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("phone")); // not numeric
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("9011p041")); // alphabets within digits
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("9312 1534")); // spaces within digits
        assertThrows(AssertionError.class, () -> Phone.isFirstDigitValid("9312153434342324")); //more than 8 numbers
        assertFalse(Phone.isFirstDigitValid("52345618"));
        assertFalse(Phone.isFirstDigitValid("00045678"));

        // valid phone numbers
        assertTrue(Phone.isFirstDigitValid("91234567"));
        assertTrue(Phone.isFirstDigitValid("31524569"));
        assertTrue(Phone.isFirstDigitValid("61524119"));
        assertTrue(Phone.isFirstDigitValid("83264892"));
    }

    @Test
    public void isSecondDigitValid() {
        // null phone number.
        assertThrows(NullPointerException.class, () -> Phone.isSecondDigitValid(null));

        // Invalid phone number.
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("")); // empty string
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("  ")); // spaces only
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("123")); // not 8 numbers
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("phone")); // not numeric
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("9011p041")); // alphabets within digits
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("9312 1534")); // spaces within digits
        assertThrows(AssertionError.class, () -> Phone.isSecondDigitValid("9312153434342324")); //more than 8 numbers
        assertFalse(Phone.isSecondDigitValid("99345618"));

        // valid phone numbers
        assertTrue(Phone.isSecondDigitValid("91234567"));
        assertTrue(Phone.isSecondDigitValid("39524569"));
    }

    @Test
    public void equals() {
        Phone phone = new Phone("91345678");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("91345678")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("87654321")));
    }
}
