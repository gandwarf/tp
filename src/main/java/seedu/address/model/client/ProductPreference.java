package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

import java.util.Objects;


/**
 * Represents a Client's product preference in the address book.
 */
public class ProductPreference {
    public final String productPreference;
    public final Frequency frequency;

    /**
     * Constructs a {@code ProductPreference}.
     *
     * @param productPreference A valid product preference.
     * @param frequency A valid frequency.
     */
    public ProductPreference(String productPreference, Frequency frequency) {
        requireNonNull(productPreference);
        requireNonNull(frequency);
        this.productPreference = productPreference;
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return productPreference;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProductPreference)) {
            return false;
        }

        ProductPreference otherProductPreference = (ProductPreference) other;
        return productPreference.trim().equalsIgnoreCase(otherProductPreference.productPreference.trim())
                && frequency.equals(otherProductPreference.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productPreference, frequency);
    }

}
