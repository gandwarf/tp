package seedu.address.model.client;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;


public class PriorityTest {

    @Test
    public void fromIntTest_validInt_successful() {
        assertEquals(Priority.fromInt(1), Priority.STANDARD);
        assertEquals(Priority.fromInt(2), Priority.PREMIUM);
        assertEquals(Priority.fromInt(3), Priority.VIP);
    }

    @Test
    public void fromIntTest_invalidInt_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Priority.fromInt(0));
        assertThrows(IllegalArgumentException.class, () -> Priority.fromInt(4));
    }

    @Test
    public void toStringTest() {
        assertEquals(Priority.STANDARD.toString(), "STANDARD");
        assertEquals(Priority.PREMIUM.toString(), "PREMIUM");
        assertEquals(Priority.VIP.toString(), "VIP");
    }

    @Test
    public void fromStringTest_validString_successful() throws IllegalValueException {
        assertEquals(Priority.fromString("STANDARD"), Priority.STANDARD);
        assertEquals(Priority.fromString("PREMIUM"), Priority.PREMIUM);
        assertEquals(Priority.fromString("VIP"), Priority.VIP);
    }

    @Test
    void isValidIntegerPriorityValidValue() {
        assertTrue(Priority.isValidIntegerPriority(1));
        assertTrue(Priority.isValidIntegerPriority(2));
        assertTrue(Priority.isValidIntegerPriority(3));
    }

    @Test
    void isValidIntegerPriorityInvalidValue() {
        assertFalse(Priority.isValidIntegerPriority(0));
        assertFalse(Priority.isValidIntegerPriority(4));
        assertFalse(Priority.isValidIntegerPriority(-1));
    }

    @Test
    void fromStringInvalidValueThrowsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> Priority.fromString("INVALID"));
        assertThrows(IllegalValueException.class, () -> Priority.fromString("")); //empty string
        assertThrows(IllegalValueException.class, () -> Priority.fromString("   ")); //only spaces
        assertThrows(IllegalValueException.class, () -> Priority.fromString("vip")); //lowercase
        assertThrows(IllegalValueException.class, () -> Priority.fromString("PREMIUMVIP")); //concatenated
        assertThrows(IllegalValueException.class, () -> Priority.fromString("Vip")); //mixed case
    }


}
