package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FrequencyTest {

    @Test
    public void constructor_validFrequency_success() {
        Frequency frequency = new Frequency(5);
        assertEquals(5, frequency.frequency);
    }

    @Test
    public void constructor_invalidFrequency_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Frequency(-1));
    }

    @Test
    public void isValidFrequency() {
        // valid frequencies
        assertTrue(Frequency.isValidFrequency(0));
        assertTrue(Frequency.isValidFrequency(1));
        assertTrue(Frequency.isValidFrequency(100));

        // invalid frequencies
        assertFalse(Frequency.isValidFrequency(-1));
        assertFalse(Frequency.isValidFrequency(-100));
    }

    @Test
    public void equals() {
        Frequency frequency = new Frequency(5);
        Frequency frequencyCopy = new Frequency(5);
        Frequency frequencyDifferent = new Frequency(10);

        // same object -> returns true
        assertTrue(frequency.equals(frequency));

        // same values -> returns true
        assertTrue(frequency.equals(frequencyCopy));

        // different types -> returns false
        assertFalse(frequency.equals(5));

        // null -> returns false
        assertFalse(frequency.equals(null));

        // different frequency -> returns false
        assertFalse(frequency.equals(frequencyDifferent));
    }

    @Test
    public void hashCode_sameFrequencyObjects_sameHashCode() {
        Frequency frequency1 = new Frequency(5);
        Frequency frequency2 = new Frequency(5);
        assertEquals(frequency1.hashCode(), frequency2.hashCode());
    }

    @Test
    void toStringReturnsCorrectString() {
        Frequency frequency = new Frequency(5);
        assertEquals("5", frequency.toString());
    }

    @Test
    void toStringZeroFrequency() {
        Frequency frequency = new Frequency(0);
        assertEquals("0", frequency.toString());
    }

    @Test
    void toStringLargeFrequency() {
        Frequency frequency = new Frequency(1000);
        assertEquals("1000", frequency.toString());
    }





}
