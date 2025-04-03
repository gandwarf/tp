package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProductPreferenceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProductPreference(null, null));
    }

    @Test
    public void equals() {
        ProductPreference pp1 = new ProductPreference("ProductA", new Frequency(1));
        ProductPreference pp2 = new ProductPreference("ProductA", new Frequency(1));
        ProductPreference pp3 = new ProductPreference("ProductB", new Frequency(2));
        ProductPreference pp4 = new ProductPreference("ProductA", new Frequency(2));

        // same values -> returns true
        assertTrue(pp1.equals(pp2));

        // same object -> returns true
        assertTrue(pp1.equals(pp1));

        // null -> returns false
        assertFalse(pp1.equals(null));

        // different type -> returns false
        assertFalse(pp1.equals(5));

        // different product preference -> returns false
        assertFalse(pp1.equals(pp3));

        // different frequency -> returns false
        assertFalse(pp1.equals(pp4));
    }

    @Test
    public void hashCode_sameValues_sameHashCode() {
        ProductPreference pp1 = new ProductPreference("ProductA", new Frequency(1));
        ProductPreference pp2 = new ProductPreference("ProductA", new Frequency(1));
        assertTrue(pp1.hashCode() == pp2.hashCode());
    }


}
