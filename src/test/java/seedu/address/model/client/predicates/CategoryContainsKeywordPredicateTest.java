package seedu.address.model.client.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class CategoryContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CategoryContainsKeywordsPredicate firstPredicate =
                new CategoryContainsKeywordsPredicate(firstPredicateKeywordList);
        CategoryContainsKeywordsPredicate secondPredicate =
                new CategoryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CategoryContainsKeywordsPredicate firstPredicateCopy =
                new CategoryContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_categoryContainsKeywords_returnsTrue() {
        // One keyword
        CategoryContainsKeywordsPredicate predicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("VIP"));
        assertTrue(predicate.test(new ClientBuilder().withTags("VIP", "friend").build()));

        // Multiple keywords
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("VIP", "friend"));
        assertTrue(predicate.test(new ClientBuilder().withTags("VIP", "friend").build()));

        // Only one matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("business", "friend"));
        assertTrue(predicate.test(new ClientBuilder().withTags("VIP", "friend").build()));

        // Mixed-case keywords
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("bUsInesS", "FriEnD"));
        assertTrue(predicate.test(new ClientBuilder().withTags("business", "friend").build()));
    }

    @Test
    public void test_categoryDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CategoryContainsKeywordsPredicate predicate =
                new CategoryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withTags("business", "friend").build()));

        // Non-matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("VIP"));
        assertFalse(predicate.test(new ClientBuilder().withTags("business", "friend").build()));

        // Keywords match name, phone, email and address, but does not match category
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Alice", "92345678", "alice@email.com",
                "Main", "Street"));
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withPhone("92345678")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("business", "VIP").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CategoryContainsKeywordsPredicate predicate = new CategoryContainsKeywordsPredicate(keywords);

        String expected = CategoryContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
