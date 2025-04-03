package seedu.address.model.sortcomparators;

import java.util.Comparator;

import seedu.address.model.client.Client;

/**
 * The comparator that sorts by the alphabetical order of the clients' name.
 */
public class NameComparator implements Comparator<Client> {

    public static final String COMPARATOR_WORD = "name";

    /**
     * Compares two clients based on their name in lexicographical order.
     *
     * @param clientA the first client to be compared.
     * @param clientB the second client to be compared.
     * @return -1 if clientA should go first, 0 if it cannot be determined, and 1 if clientB should go first.
     */
    @Override
    public int compare(Client clientA, Client clientB) {
        String clientAName = clientA.getFullName();
        String clientBName = clientB.getFullName();

        return clientAName.compareTo(clientBName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameComparator)) {
            return false;
        }

        return true;
    }

}
