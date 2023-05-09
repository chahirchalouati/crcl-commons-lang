package org.crcl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PredicateUtilsTest {

    @Test
    public void testAllMatch() {
        Predicate<String> startsWithJ = name -> name.startsWith("J");
        Predicate<String> hasLengthFour = name -> name.length() == 4;

        Predicate<String> combinedPredicate = PredicateUtils.allMatch(startsWithJ, hasLengthFour);

        String[] names = {"John", "Jane", "Alice", "bob", "Mike"};

        assertTrue(combinedPredicate.test(names[0])); // John
        assertTrue(combinedPredicate.test(names[1])); // Jane
        assertFalse(combinedPredicate.test(names[2])); // Alice
        assertFalse(combinedPredicate.test(names[3])); // bob
        assertFalse(combinedPredicate.test(names[4])); // Mike
    }

    @Test
    public void testAnyMatch() {
        Predicate<String> startsWithJ = name -> name.startsWith("J");
        Predicate<String> hasLengthFour = name -> name.length() == 4;

        Predicate<String> combinedPredicate = PredicateUtils.anyMatch(startsWithJ, hasLengthFour);

        String[] names = {"John", "Jane", "Alice", "bob", "Mike"};

        assertTrue(combinedPredicate.test(names[0]));  // John
        assertTrue(combinedPredicate.test(names[1]));  // Jane
        assertFalse(combinedPredicate.test(names[2]));  // Alice
        assertFalse(combinedPredicate.test(names[3])); // bob
        assertTrue(combinedPredicate.test(names[4])); // Mike
    }

    @Test
    public void testAnyNonMatch() {
        Predicate<String> startsWithJ = name -> name.startsWith("J");
        Predicate<String> hasLengthFour = name -> name.length() == 4;

        Predicate<String> combinedPredicate = PredicateUtils.anyNonMatch(startsWithJ, hasLengthFour);

        String[] names = {"John", "Jane", "Alice", "bob", "Mike"};

        List<String> listOfNames = Arrays.stream(names)
                .filter(combinedPredicate)
                .toList();


        assertFalse(combinedPredicate.test(names[0])); // John
        assertFalse(combinedPredicate.test(names[1])); // Jane
        assertTrue(combinedPredicate.test(names[2])); // Alice
        assertTrue(combinedPredicate.test(names[3]));  // bob
        assertFalse(combinedPredicate.test(names[4]));  // Mike


        assertEquals(2, listOfNames.size());
        assertEquals("Alice", listOfNames.get(0));
        assertEquals("bob", listOfNames.get(1));
    }

    @Test
    public void testAllNonMatch() {
        Predicate<String> startsWithJ = name -> name.startsWith("J");
        Predicate<String> hasLengthFour = name -> name.length() == 4;

        Predicate<String> combinedPredicate = PredicateUtils.allNonMatch(startsWithJ, hasLengthFour);

        String[] names = {"John", "Jane", "Alice", "bob", "Mike"};

        List<String> listOfNames = Arrays.stream(names)
                .filter(combinedPredicate)
                .toList();

        assertFalse(combinedPredicate.test(names[0])); // John
        assertFalse(combinedPredicate.test(names[1])); // Jane
        assertTrue(combinedPredicate.test(names[2])); // Alice
        assertTrue(combinedPredicate.test(names[3]));  // bob
        assertTrue(combinedPredicate.test(names[4]));  // Mike

        assertEquals(3, listOfNames.size());
        assertEquals("Alice", listOfNames.get(0));
        assertEquals("bob", listOfNames.get(1));
        assertEquals("Mike", listOfNames.get(2));
    }
}

