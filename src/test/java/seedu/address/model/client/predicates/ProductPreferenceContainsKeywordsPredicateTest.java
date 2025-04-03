package seedu.address.model.client.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class ProductPreferenceContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ProductPreferenceContainsKeywordsPredicate firstPredicate =
                new ProductPreferenceContainsKeywordsPredicate(firstPredicateKeywordList);
        ProductPreferenceContainsKeywordsPredicate secondPredicate =
                new ProductPreferenceContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProductPreferenceContainsKeywordsPredicate firstPredicateCopy =
                new ProductPreferenceContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_productPreferenceContainsKeywords_returnsTrue() {
        // One keyword
        ProductPreferenceContainsKeywordsPredicate predicate =
                new ProductPreferenceContainsKeywordsPredicate(Collections.singletonList("Book"));
        assertTrue(predicate.test(new ClientBuilder().withProductPreference("Fiction Book").build()));

        // Multiple keywords
        predicate = new ProductPreferenceContainsKeywordsPredicate(Arrays.asList("Book", "Coffee"));
        assertTrue(predicate.test(new ClientBuilder().withProductPreference("Coffee Book").build()));

        // Only one matching keyword
        predicate = new ProductPreferenceContainsKeywordsPredicate(Arrays.asList("Book", "Coffee"));
        assertTrue(predicate.test(new ClientBuilder().withProductPreference("Recipe Book").build()));

        // Mixed-case keywords
        predicate = new ProductPreferenceContainsKeywordsPredicate(Arrays.asList("BOoK", "CoFFeE"));
        assertTrue(predicate.test(new ClientBuilder().withProductPreference("Coffee Book").build()));
    }

    @Test
    public void test_productPreferenceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ProductPreferenceContainsKeywordsPredicate predicate =
                new ProductPreferenceContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withProductPreference("Coffee").build()));

        // Non-matching keyword
        predicate = new ProductPreferenceContainsKeywordsPredicate(Collections.singletonList("Book"));
        assertFalse(predicate.test(new ClientBuilder().withProductPreference("Coffee Coaster").build()));

        // Keywords match name, phone, email and address, but does not match product preference
        predicate = new ProductPreferenceContainsKeywordsPredicate(
                Arrays.asList("Alice", "91345678", "alice@email.com", "Main", "Street")
        );
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withPhone("91345678")
                .withEmail("alice@email.com").withAddress("Main Street").withProductPreference("Shampoo").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ProductPreferenceContainsKeywordsPredicate predicate = new ProductPreferenceContainsKeywordsPredicate(keywords);

        String expected = ProductPreferenceContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }

}
